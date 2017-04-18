package Aircraft;

import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Main.Game;
import Player.Player;
import Projectiles.Projectile;
import Projectiles.ProjectileFactory;
import Projectiles.ProjectileTypes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by andres on 15/04/17.
 * AirWar
 * Aircraft
 */

public class Boss extends Enemy {

    /**
     * movilidadX es la aceleracion en el eje X
     */
    private final int movilidadX;
    private boolean movimientoReverso;
    public SimpleLinkedList projectiles2 = new SimpleLinkedList();

    //EL TAMAÑO DEL BOSS DE DE ALTO: 205 Y ANCHO: 308

    /**
     * Constructor
     * @param game
     * @param player
     * Valores fijos para X y Y pues inician en el centro
     * Es necesario asignar las movilidades en X y Y
     * y alive como true
     */
    public Boss(Game game, Player player){
        this.game = game;
        this.player = player;
        this.posX = game.WIDTH/2;
        this.posY = 0;
        this.resistance = 50;
        this.movilidadX = 1;
        this.movilidadY = 0;
        this.alive = true;
        this.timer = 0;
        this.ammunition = ProjectileTypes.BULLET;
        this.dying = false;
        this.isEvil = true;
        this.scoreValue = 2000;
        this.projectiles = new SimpleLinkedList();
        this.movimientoReverso = false;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/boss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getBounds() {
        if (alive) {
            return new Rectangle(posX, posY, 308, 215/3);
        }else{
            return new Rectangle(posX,posY,0,0);
        }
    }

    @Override
    public void shoot() {
        try {
            projectiles.addFirst(ProjectileFactory.getProjectile(ammunition,game,player,posX+308/4,145,1));
            projectiles2.addFirst(ProjectileFactory.getProjectile(ammunition,game,player,posX+3*308/4,145,1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveProjectile(){
        int index = 0;
        if (projectiles != null && projectiles2 != null){
            Node<Projectile> current = projectiles.getHead();
            Node<Projectile> current2 = projectiles2.getHead();
            while (current != null && current2 != null){
                current.getObject().moveBossProjectile(movimientoReverso, this);
                current2.getObject().moveBossProjectile(movimientoReverso, this);
                if (current.getObject().alive && current2.getObject().alive){
                    index++;
                }else{
                    projectiles.removeInPosition(index);
                    projectiles2.removeInPosition(index);
                }
                current = current.getNext();
                current2 = current2.getNext();
            }
        }
    }

    @Override
    public void paintProjectiles(Graphics2D g){
        if (projectiles != null && projectiles2 != null){
            Node <Projectile> current = projectiles.getHead();
            Node <Projectile> current2 = projectiles2.getHead();
            while (current != null && current2 != null){
                current.getObject().paint(g);
                current2.getObject().paint(g);
                current = current.getNext();
                current2 = current2.getNext();
            }
        }
    }

    @Override
    public void move() {
        if (timer > 20) {
            destroy();
        }
        if (dying && timer <= 20) {
            timer++;
        }
        if (collision()) {
            if (resistance <= 0) {
                blowup();
            }
        }

        if (posX < (game.WIDTH - 308) && !movimientoReverso)
            posX += movilidadX;
        if (posX > 0 && movimientoReverso)
            posX -= movilidadX;
        if (posX == (game.WIDTH - 308))
            movimientoReverso = true;
        if (posX == 0)
            movimientoReverso = false;
    }
}
