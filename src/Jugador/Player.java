package Jugador;

import ProjectileFactory.Projectile;
import ProjectileFactory.ProjectileFactory;
import Unidad.Unidad;
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
    int movilidadX;
    int vidas;
    public SimpleLinkedList projectiles;

    public Player (Game game){
        this.game = game;
        this.posX = 600;
        this.posY = 500;
        this.alive = true;
        this.vidas = 3;
        this.municion = 1;
        this.projectiles = new SimpleLinkedList();
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(){
        mover();
        moverBalas();
    }

    @Override
    public  void mover(){
        if (posX + movilidadX > 0 && posX + movilidadX < game.getWidth()-LADOSPRITE)
            posX += movilidadX;
        if (posY + movilidadY > 0 && posY + movilidadY <game.getHeight()-LADOSPRITE){
            posY +=movilidadY;
        }
    }

    public void moverBalas(){
        int index = 0;
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().mover();
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
        }else {
            //descontar vidas
        }
    }

    public void paintBalas (Graphics2D g){
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().paint(g);
                current = current.getNext();
            }
        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            movilidadY = 0;
        }if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() ==KeyEvent.VK_D) {
            movilidadX = 0;
        }if (e.getKeyCode() == KeyEvent.VK_J){
            shoot();
        }
    }

    public synchronized void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            movilidadY = -2;
        }if (e.getKeyCode() == KeyEvent.VK_S) {
            movilidadY = 2;
        }if (e.getKeyCode() == KeyEvent.VK_A) {
            movilidadX = -2;
        }if (e.getKeyCode() ==KeyEvent.VK_D){
            movilidadX = 2;
        }
    }

    @Override
    public void shoot() {
        try {
            projectiles.addFirst(ProjectileFactory.getProjectilev(municion,game,this,posX+16,posY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
