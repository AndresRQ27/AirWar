package com.Unidad;

import com.Game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase abstracta Unidad
 * La clase padre del jugador y enemigos
 * @author  by pedro on 27/3/2017.
 */
public abstract class Unidad {
    public int x, y, xa, ya, width, height;//x,y=ubicacion del objeto; xa,ya=velocidad de movimiento en x y y,
    public int resistencia,vida;
    public Game game;
    public BufferedImage sprite;
    public boolean vis = true;

    /**
     * Método abstracto move()
     */
    abstract public void move();

    /**
     * BufferImage()
     * Se encarga de se asignarle al sprite la imagen del objeto
     * @param imagen tipo String, es el nombre de la imagen que se quiere
     */
    public void BufferImage(String imagen){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream(imagen));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loadDimension()
     * Método para cargar el width(ancho) heigh(largo) de la imagen
     */
    public void loadDimension(){
        width = sprite.getWidth(null);
        height = sprite.getHeight(null);
    }

    /**
     * getBounds
     * crea un rectangulo con los parametros del objeto
     * @return rectángulo, tipo Rectangle, es el rectángulo creado
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y,width,height);
    }

    /**
     * isVis
     * @return boolean, vis puede ser true o false
     */
    public boolean isVis() {
        return vis;
    }
}
