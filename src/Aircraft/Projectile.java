package Aircraft;

import DataStructures.MyLinkedList.Node;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public abstract class Projectile {
    public int posX;
    public int posY;
    public int ataque;
    public BufferedImage sprite;
    public int movilidadY;
    public boolean alive;
    public Game game;
    public int width;
    public int height;

    public void moverProyectilJugador() {
        if (posY - movilidadY < 0) {
            destruir();
        }
        posY -= movilidadY;
    }

    public void moverProyectilEnemigo() {
        if (posY + movilidadY > game.HEIGHT) {
            destruir();
        }
        posY += movilidadY;
    }

    public void destruir() {
        alive = false;
    }

    public void paint(Graphics2D g) {
        if (alive == true) {
            g.drawImage(sprite, posX, posY, null);
        } else {
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, width, height);
    }
}
