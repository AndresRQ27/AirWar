package com.Unidad;

import com.Game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by pedro on 27/3/2017.
 */
public abstract class Unidad {
    public int x, y, xa, ya, width, height;
    public int resistencia;
    public Game game;
    public BufferedImage sprite;
    public boolean vis = true;


    abstract public void move();

    //utilizado en los constructures de los hijos
    //Método para cargar la imagen
    public void BufferImage(String imagen){
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream(imagen));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Método para cargar el width(ancho) heigh(largo) de la imagen
    public void loadDimension(){
        width = sprite.getWidth(null);
        height = sprite.getHeight(null);
    }
    //.

    public Rectangle getBounds(){
        return new Rectangle(x, y, sprite.getWidth(null),sprite.getHeight(null));
    }



    public boolean isVis() {
        return vis;
    }
}
