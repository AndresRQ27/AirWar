package Aircraft;

import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by andres on 22/03/17.
 */
public abstract class Unidad {
    /**
     * Movilidad es la velocidad con la que se mueve en el eje Y
     * Resistencia = vida
     * El ataque se le resta a la resistencia
     * Cuando la vida/resistencia sea <= 0, se destruye el enemigo
     * sprite es la imagen que va a tener cada unidad
     * LADOSPRITE es el tamaño de cada sprite, este no va a cambiar para las unidades
     * posX va a ser la posicion en X en la que grafica la imagen
     * posY va a ser la posicion en Y en la que grafica la imagen
     * alive define si la unidad esta vida o muerta
     * game sirve para que las unidades sepan los margenes de la pantalla
     */
    public int movilidadY;
    public int ataque;
    public int resistencia;
    public BufferedImage sprite;
    public int LADOSPRITE = 64;
    public int posX;
    public int posY;
    public boolean alive;
    public Game game;
    public int municion;

    public void shoot(){
    }

    /**
     * Este metodo solo hay que sobreescribirlo en la clase del kamikaze
      */
    public void mover(){
        if (posY + movilidadY > game.getHeight()){
            destruir();
        }
        posY += movilidadY;
    }

    /**
     * Este metodo no va a cambiar para ninguna unidad, se utiliza ya sea cuando es destruida por el jugador
     * o cuando se sale del mapa (posY + ya > game.getHeight)
     */
    public void destruir(){
        alive = false;
    }

    public void actualizar(){}
    public void paint(Graphics2D g){}

    public Rectangle getBounds() {
        if (alive == true) {
            return new Rectangle(posX, posY, LADOSPRITE, LADOSPRITE);
        }else{
            return new Rectangle(posX,posY,0,0);
        }
    }
}
