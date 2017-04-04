package ProjectileFactory;

import DataStructures.MyLinkedList.Node;
import Main.Game;
import Unidad.Unidad;

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

    public void mover() {
        if (posY + movilidadY > game.getHeight()) {
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

    public boolean collision() {
        boolean aux = false;
        if (game.enemigosPantalla != null) {
            Node<Unidad> current = game.enemigosPantalla.getHead();
            while (current != null) {
                if (current.getObject().getBounds().intersects(this.getBounds())) {
                    aux = true;
                }
                current = current.getNext();
            }
        }
        return aux;
    }

}
