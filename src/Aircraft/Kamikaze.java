package Aircraft;

import Jugador.Player;
import Main.Game;


import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 23/3/2017.
 */
public class Kamikaze extends Enemy {
    /**
     * movilidadX es la aceleracion en el eje X
     */
    int movilidadX;
    /**
     *
     * @param game
     * @param player
     * @param x posicion en X
     * @param y posicion en Y
     * Es necesario asignar las movilidades en X y Y
     * y alive como true
     */
    public Kamikaze(Game game,Player player,int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.movilidadX = 3;
        this.movilidadY = 2;
        this.alive = true;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/kamikaze.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mover(){
        /**
         * Los 2 primeros if son para que el kamikaze siga la posicion del jugador
         * el tercer if lo que hace es que si se pasa del margen de la pantalla la destruye
         */
        if (collision() == true){
            destroy();
        }
        if (posX + movilidadX < player.posX){
            movilidadX = 3;
        }
        if (posX + movilidadX > player.posX){
            movilidadX = -3;
        }
        super.mover();

        /**
         * Aca es donde va asignando el movimiento
         */
        posX += movilidadX;
        posY += movilidadY;
    }
}
