package com.ProjectileFactory;



import DataStructures.MyLinkedList.Node;
import com.Game.Game;
import com.Unidad.Unidad;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public abstract class Projectile {
    public int x, y, speed = 3;
    public int ataque;
    public BufferedImage sprite;
    public boolean alive = true;
    public Game game;
    public int width;
    public int height;

    public void move(){
        if (this.y - speed < 0){
            destruir();
        }
        this.y -= speed;
    }

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

    public void destruir() {
        alive = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, width, height);
    }

    public boolean isAlive() {
        return alive;
    }
}
