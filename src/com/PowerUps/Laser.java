package com.PowerUps;

import DataStructures.MyLinkedList.SimpleLinkedList;
import com.Game.Game;
import com.ProjectileFactory.ProjectileFactory;

/**
 * Clase Laser
 * es el poder Laser
 * @see com.PowerUps.PowerUps
 * Created by pedro on 17/3/2017.
 */
public class Laser extends PowerUps {
    public Laser(Game game) {
        this.game=game;
    }

    public void UsarPoder() {
        System.out.println("Soy un láser");
        game.J1.municion = 3;
    }
}
