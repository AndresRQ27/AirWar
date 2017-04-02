package com.Jugador.Vidas;

import com.Game.Game;
import com.Unidad.Unidad;

/**
 * Created by pedro on 28/3/2017.
 */
public class Vida extends Unidad{
    public Vida(Game game, int x) {
        this.game = game;
        loadImage("C:\\Users\\pedro\\Desktop\\vida.png");
        loadDimension();
        this.x = x;
        this.y = 0;
    }

    @Override
    public void move() {
    }

}
