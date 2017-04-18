package Aircraft;

import Main.Game;
import Projectiles.ProjectileTypes;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by andres on 22/03/17.
 */
public abstract class Unidad {
    /**
     * Movilidad es la velocidad con la que se mueve en el eje Y
     * Resistencia = vida
     * El attack se le resta a la resistance
     * Cuando la vida/resistance sea <= 0, se destruye el enemigo
     * sprite es la imagen que va a tener cada unidad
     * LADOSPRITE es el tamaño de cada sprite, este no va a cambiar para las unidades
     * posX va a ser la posicion en X en la que grafica la imagen
     * posY va a ser la posicion en Y en la que grafica la imagen
     * alive define si la unidad esta vida o muerta
     * game sirve para que las unidades sepan los margenes de la pantalla
     */
    public int movilidadY;
    protected int resistance;
    public BufferedImage sprite;
    public static final int LADOSPRITE = 64;
    public int posX;
    public int posY;
    public boolean alive;
    public Game game;
    public ProjectileTypes ammunition;

    public abstract void shoot();

    public abstract void move();

    public abstract void moveProjectile();

    /**
     * Este metodo no va a cambiar para ninguna unidad, se utiliza ya sea cuando es destruida por el jugador
     * o cuando se sale del mapa (posY + ya > game.getHeight)
     */
    public void destroy(){
        alive = false;
    }

    /**
     * Este metodo update sirve para llamar a los metodos de moverProyectilJugador las balas del jugador y de los enemigos
     * y tambien llamar al movimiento de la unidad
     */
    public abstract void update();

    /**
     * Cuando el alive = false, se eliminara el espacio de memoria donde se pintaba la unidad
     * @param g objeto Graphics2D
     */
    public void paint(Graphics2D g){
        if (alive){
            g.drawImage(sprite,posX,posY,null);
        }
    }

    public abstract void paintProjectiles(Graphics2D g);

    /**
     * este metodo se devolver un rectangulo de 64 * 64 con el que se revisan las colisiones
     * @return rectangle 64*64
     */
    public Rectangle getBounds() {
        if (alive) {
            return new Rectangle(posX, posY+10, LADOSPRITE, LADOSPRITE-10);
        }else{
            return new Rectangle(posX,posY,0,0);
        }
    }

    /**
     * @return true si el objeto esta en colision, false de otra manera
     */
    public abstract boolean collision ();

}
