package com.Municiones;

import com.Game.Game;
import com.Unidad.Unidad;

/**
 * Created by pedro on 28/3/2017.
 */
public class Bala extends Unidad {
    private final int speed = 2;
    private final int topGame = 0;

    //Constructor
    public Bala(int x, int y, Game game) {
        this.game = game;
        loadImage("C:\\Users\\pedro\\Desktop\\bala.jpg");
        loadDimension();
        this.x = x+width;
        this.y = y-height;

    }

    @Override
    public void move() {
        this.y -= speed;
        if(y == topGame){
            this.vis = false;
        }
        if(collsionEnemigo()){
            game.J1.puntaje ++;
            this.vis = false;
        }

    }
    //.


    public boolean collsionEnemigo(){
        return false;
    }
}
