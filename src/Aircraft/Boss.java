package Aircraft;

import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Main.Game;
import Main.GameStates;
import Player.Player;
import Projectiles.Projectile;
import Projectiles.ProjectileFactory;
import Projectiles.ProjectileTypes;
import Sound.Sounds;

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
    private int ancho;
    private int alto;
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
        this.ammunition = ProjectileTypes.MISSILE;
        this.dying = false;
        this.isEvil = true;
        this.scoreValue = 2000;
        this.projectiles = new SimpleLinkedList();
        this.movimientoReverso = false;
        this.alto = 205;
        this.ancho = 308;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/boss.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getBounds() {
        if (alive) {
            return new Rectangle(posX, posY, this.ancho, (this.alto+10)/3);
        }else{
            return new Rectangle(posX,posY,0,0);
        }
    }

    @Override
    public void update(){
        if (alive && isEvil){
            if(posX % 10 == 0){
                shoot();
            }
        }
        moveProjectile();
        move();
    }

    @Override
    public void shoot() {
        try {
            projectiles.addFirst(ProjectileFactory.getProjectile(ammunition,game,player,posX+(this.ancho)/4,(this.alto-60),1));
            projectiles2.addFirst(ProjectileFactory.getProjectile(ammunition,game,player,posX+3*(this.ancho)/4,(this.alto-60),1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveProjectile(){
        int index = 0;
        int index2 = 0;
        if (projectiles != null){
            Node<Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().moveEnemyProjectile();
                if (current.getObject().alive){
                    index++;
                }else{
                    projectiles.removeInPosition(index);
                }
                current = current.getNext();
            }
        }

        if (projectiles2 != null){
            Node<Projectile> current2 = projectiles2.getHead();
            while (current2 != null){
                current2.getObject().moveEnemyProjectile();
                if (current2.getObject().alive){
                    index2++;
                } else {
                    projectiles2.removeInPosition(index2);
                }
                current2 = current2.getNext();
            }
        }
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

        if (projectiles2 != null){
            Node <Projectile> current2 = projectiles2.getHead();
            while (current2 != null){
                current2.getObject().paint(g);
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

        if (posX < (game.WIDTH - this.ancho) && !movimientoReverso)
            posX += movilidadX;
        if (posX > 0 && movimientoReverso)
            posX -= movilidadX;
        if (posX == (game.WIDTH - this.ancho))
            movimientoReverso = true;
        if (posX == 0)
            movimientoReverso = false;
    }
    @Override
    public void blowup(){
        dying = true;
        player.score+=scoreValue;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/boss_blowup.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void destroy(){
        super.destroy();
        Player.fightBoss = false;
        if (game.stage < game.finalStage){
            game.changeLevel();
        }else{
            game.gameComplete();
            Sounds.BACKGROUND.stop();
            Sounds.VICTORY.play();
        }

    }
}
