package com.Unidad;

import com.Game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pedro on 27/3/2017.
 */
public abstract class Unidad {
    public int x, y, xa, ya, width, height;
    public int resistencia;
    public Game game;
    public Image imagen;
    public ImageIcon ii;
    public boolean vis = true;


    abstract public void move();

    //utilizado en los constructures de los hijos
    //Método para cargar la imagen
    public void loadImage(String ruta){
        this.ii = new ImageIcon(ruta);
        this.imagen = ii.getImage();
    }
    //Método para cargar el width(ancho) heigh(largo) de la imagen
    public void loadDimension(){
        width = imagen.getWidth(null);
        height = imagen.getHeight(null);
    }
    //.

    public Rectangle getBounds(){
        return new Rectangle(x, y, imagen.getWidth(null),imagen.getHeight(null));
    }



    public boolean isVis() {
        return vis;
    }
}
