package Projectiles;

import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public abstract class Projectile {
    int posX;
    int posY;
    public int attack;
    BufferedImage sprite;
    int movilidadY;
    public boolean alive;
    Game game;
    int width;
    int height;

    public void moverProyectilJugador() {
        if (posY - movilidadY < 0) {
            destroy();
        }
        posY -= movilidadY;
    }

    public void moveEnemyProjectile() {
        if (posY + movilidadY > game.HEIGHT) {
            destroy();
        }
        posY += movilidadY;
    }

    public void moveBossProjectile(boolean movimientoReverso, Aircraft.Boss boss){

        if (posY + movilidadY > game.HEIGHT) {
            destroy();
        }
        posY += movilidadY;

        if (boss.alive) {
            int movilidadX = 1;
            if (!movimientoReverso) {
                posX += movilidadX;
            }
            if (movimientoReverso) {
                posX -= movilidadX;
            }
        }
    }

    public void destroy() {
        alive = false;
    }

    public void paint(Graphics2D g) {
        if (alive) {
            g.drawImage(sprite, posX, posY, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, width, height);
    }
}
