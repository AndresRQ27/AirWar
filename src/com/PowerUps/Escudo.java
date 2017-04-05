package com.PowerUps;

import com.Game.Game;

/**
 * Created by pedro on 17/3/2017.
 * Poder
 */
public class Escudo extends PowerUps{

    public Escudo(Game game) {
        this.game=game;
    }

    public void UsarPoder(){
        System.out.println("Soy un escudo");
        game.J1.resistencia += 5;
    }
}
