package Jugador;

import Aircraft.Projectile;
import Aircraft.ProjectileFactory;
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
    int movilidadX;
    int vidas;
    public SimpleLinkedList projectiles;

    public Player (Game game){
        this.game = game;
        this.posX = game.WIDTH/2 - LADOSPRITE/2;
        this.posY = game.HEIGHT - 120;
        this.alive = true;
        this.vidas = 3;
        this.ammunition = 1;
        this.projectiles = new SimpleLinkedList();
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

    public void paint(Graphics2D g) {
        if (alive == true){
            g.drawImage(sprite,posX,posY,null);
        }
    }

    @Override
    public boolean collision() {
        return false;
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
}
