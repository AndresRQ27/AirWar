package Jugador;

import Aircraft.Unidad;
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

    public Player (Game game){
        this.game = game;
        this.posX = 600;
        this.posY = 500;
        this.alive = true;
        this.vidas = 3;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void move (){
        if (posX + movilidadX > 0 && posX + movilidadX < game.getWidth()-LADOSPRITE)
            posX += movilidadX;
        if (posY + movilidadY > 0 && posY + movilidadY <game.getHeight()-LADOSPRITE){
            posY +=movilidadY;
        }
    }
    public void paint(Graphics2D g) {
        if (alive == true){
            g.drawImage(sprite,posX,posY,null);
        }else {
            //descontar vidas
        }

    }
    public synchronized void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            movilidadY = 0;
        }if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() ==KeyEvent.VK_D) {
            movilidadX = 0;
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
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, LADOSPRITE, LADOSPRITE);
    }




}
