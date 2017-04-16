package Player;

import Aircraft.Enemy;
import PowerUps.PowerUp;
import Projectiles.Projectile;
import Projectiles.ProjectileFactory;
import Aircraft.Unidad;
import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public class Player extends Unidad{
    boolean W = false,A = false,S = false,D = false;
    public int movilidadX;
    public int Lifes;
    public boolean invincibility;
    public boolean dying;
    public int timer;
    public SimpleLinkedList projectiles;
    public SimpleLinkedList powerUps;
    public int score;

    public Player (Game game){
        this.game = game;
        this.posX = game.WIDTH/2 - LADOSPRITE/2;
        this.posY = game.HEIGHT - 120;
        this.alive = true;
        this.Lifes = 3;
        this.resistance = 2;
        this.ammunition = 1;
        this.invincibility = false;
        this.dying = false;
        this.timer = 0;
        this.score = 0;
        this.projectiles = new SimpleLinkedList();
        this.powerUps = new SimpleLinkedList();
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(){
        move();
        moveProjectile();
    }

    @Override
    public  void move(){
        if (this.dying == true){
            if (timer == 15){
                reSpawn();
            }else{
                timer++;
            }
        }
        if (collision() && invincibility == false){
            if(resistance <= 0){
                blowup();
            }
        }
        if (W == true){
            movilidadY = -3;
        }else if (S == true) {
            movilidadY = 3;
        }else {
            movilidadY = 0;
        }
        if (A == true){
            movilidadX = -3;
        }else if (D == true){
            movilidadX = 3;
        }else{
            movilidadX = 0;
        }
        if (posX + movilidadX > 0 && posX + movilidadX < game.getWidth()-LADOSPRITE)
            posX += movilidadX;
        if (posY + movilidadY > 0 && posY + movilidadY <game.getHeight()-LADOSPRITE){
            posY +=movilidadY;
        }
    }

    @Override
    public void moveProjectile(){
        int index = 0;
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().moverProyectilJugador();
                if (current.getObject().alive == true){
                    index++;
                }else{
                    projectiles.removeInPosition(index);
                }
                current = current.getNext();
            }
        }
    }

    @Override
    public boolean collision() {
        boolean aux = false;

        Node <Enemy> current = game.screenQueue.getHead();
        while (current!=null) {
            if (current.getObject().projectiles != null) {
                Node<Projectile> currentP = current.getObject().projectiles.getHead();
                while (currentP != null) {
                    if (currentP.getObject().getBounds().intersects(getBounds())) {
                        this.resistance -= currentP.getObject().attack;
                        currentP.getObject().destroy();
                        aux = true;
                        break;
                    }
                    currentP = currentP.getNext();
                }
                if (current.getObject().getBounds().intersects(getBounds())) {
                    if (current.getObject().dying == false){
                        if (current.getObject().isPowerUp == true && current.getObject().isEvil == false){
                            current.getObject().powerUp.Use();
                            current.getObject().destroy();
                        }else{
                            current.getObject().blowup();
                            blowup();
                        }
                        aux = true;
                    }
                }
            }
            current = current.getNext();
        }
        return aux;
    }

    @Override
    public void paintProjectiles(Graphics2D g){
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().paint(g);
                current = current.getNext();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            W = false;
        }if (e.getKeyCode() == KeyEvent.VK_S) {
            S = false;
        }if (e.getKeyCode() == KeyEvent.VK_A){
            A = false;
        }if( e.getKeyCode() ==KeyEvent.VK_D) {
            D = false;
        }if (e.getKeyCode() == KeyEvent.VK_J){
            shoot();
        }if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (powerUps.getHead() != null){
                Node <PowerUp> current = powerUps.getHead();
                current.getObject().Use();
                powerUps.removeFirst();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            W = true;
        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            S = true;
        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            A = true;
        }else if (e.getKeyCode() ==KeyEvent.VK_D){
            D = true;
        }
    }

    @Override
    public void shoot() {
        try {
            projectiles.addFirst(ProjectileFactory.getProjectile(ammunition,game,this,posX+22,posY-25,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void blowup(){
        dying = true;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/explosion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Lifes > 0){
            this.Lifes--;
            this.invincibility = true;
        }else{
            destroy();
        }
    }

    public void reSpawn(){
        this.resistance = 2;
        dying = false;
        invincibility = false;
        timer = 0;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PushPowerUp(PowerUp powerUp){
        this.powerUps.addFirst(powerUp);
    }
}
