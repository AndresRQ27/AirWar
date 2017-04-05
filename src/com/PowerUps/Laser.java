package com.PowerUps;

import com.Game.Game;

/**
 * Created by pedro on 17/3/2017.
 * Poder
 */
public class Laser extends PowerUps {
    public Laser(Game game) {
        this.game=game;
    }

    public void UsarPoder(){
        System.out.println("Soy un láser");
        this.game.J1.municion = 3;
    }
}
