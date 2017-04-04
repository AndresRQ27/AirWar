package com.Enemigos;

import DataStructures.MyLinkedList.Node;
import com.Game.Game;
import com.ProjectileFactory.Projectile;
import com.Unidad.Unidad;

import java.awt.*;

/**
 * Created by Cristian44 on 23/3/2017.
 */
public class Kamikaze extends Unidad {

    public Kamikaze(Game game, int x, int y){
        this.game = game;
        BufferImage("/kamikaze.png");
        loadDimension();
        this.x = x;
        this.y = y;
        this.xa = 1;
        this.ya = 1;
    }

    @Override
    public void move(){
        /**
         * Los 2 primeros if son para que el kamikaze siga la posicion del jugador
         * el tercer if lo que hace es que si se pasa del margen de la pantalla la destruye
         */
        if (collision() == true){
            this.vis = false;
        }
        if (this.x + xa < game.J1.x){
            xa = 3;
        }
        if (this.x + xa > game.J1.x){
            xa = -3;
        }

        if (this.y + this.ya > game.getHeight()){
            this.vis = false;
        }

        /**
         * Aca es donde va asignando el movimiento
         */
        this.x += this.xa;
        this.y += this.ya;
    }

    private boolean collision() {
        boolean aux = false;
        if (game.J1.getBounds().intersects(getBounds())){
            aux = true;
            game.J1.muerte();
        }
        if (game.J1.getMuniciones() != null) {
            Node<Projectile> current = game.J1.getMuniciones().getHead();
            while (current != null) {
                if (current.getObject().getBounds().intersects(getBounds())) {
                    aux = true;
                    current.getObject().destruir();
                    game.J1.puntaje ++;
                    break;
                }
                current = current.getNext();
            }
        }
        return aux;
    }
}
