package com.ProjectileFactory;


import com.Game.Game;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public class BalaJugador extends Projectile {

    public BalaJugador (Game game, int x, int y){
        this.game = game;
        BufferImage("/bala.png");
        loadDimension();
        this.x = x+width;
        this.y = y-height;
        this.ataque = 1;
    }

}
