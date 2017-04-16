package Aircraft;

import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Player.Player;
import PowerUps.PowerUp;
import Projectiles.Projectile;
import Projectiles.ProjectileFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public abstract class Enemy extends Unidad {
    public Player player;
    public SimpleLinkedList projectiles;
    public PowerUp powerUp;
    public int timer;
    public int scoreValue;
    public boolean dying;
    public boolean isPowerUp;
    public boolean isEvil;

    /**
     * Este metodo solo hay que sobreescribirlo en la clase del kamikaze
     */
    @Override
    public void update(){
        if (alive == true && isEvil == true){
            if(posY % 150 == 0){
                shoot();
            }
        }
        moveProjectile();
        move();
    }

    @Override
    public void move(){
        if (timer>10){
            destroy();
        }
        if (dying == true && timer <= 10){
            timer++;
        }
        if (collision() && dying == false){
            if(resistance <= 0){
                if (isPowerUp == true){
                    isEvil = false;
                    generatePowerUp();
                }else{
                    blowup();
                }
            }
        }

        if (posY + movilidadY > game.getHeight()){
            destroy();
        }
        posY += movilidadY;
    }

    @Override
    public void moveProjectile(){
        int index = 0;
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().moveEnemyProjectile();
                if (current.getObject().alive == true){
                    index++;
                }else{
                    projectiles.removeInPosition(index);
                }
                current = current.getNext();
            }
        }
    }

    public void paintProjectiles(Graphics2D g){
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().paint(g);
                current = current.getNext();
            }
        }
    }

    @Override
    public boolean collision() {
        boolean aux = false;
        if (player.projectiles != null) {
            Node<Projectile> current = player.projectiles.getHead();
            while (current != null) {
                if (current.getObject().getBounds().intersects(getBounds()) && isEvil == true) {
                    this.resistance -= current.getObject().attack;
                    current.getObject().destroy();
                    aux = true;
                    break;
                }
                current = current.getNext();
            }
        }
        return aux;
    }

    @Override
    public void shoot() {
        try {
            projectiles.addFirst(ProjectileFactory.getProjectile(ammunition,game,player,posX+22,posY+25,1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void blowup(){
        dying = true;
        player.score+=scoreValue;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/explosion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generatePowerUp(){
        player.score+=scoreValue;
        if(isPowerUp == true){
            this.sprite = this.powerUp.sprite;
        }
    }
}