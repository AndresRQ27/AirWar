package com.PowerUps;

import com.Game.Game;

/**
 * Created by pedro on 17/3/2017.
 * Poder
 */
public class Misiles extends PowerUps{
    public Misiles(Game game) {
        this.game=game;
    }

    public void UsarPoder(){
        System.out.println("Soy un misíl");
        this.game.J1.municion = 2;
    }
}
