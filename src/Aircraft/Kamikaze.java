package Aircraft;

import DataStructures.MyLinkedList.SimpleLinkedList;
import Player.Player;
import Main.Game;
import PowerUps.PowerUpFactory;


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
    public Kamikaze(Game game,Player player,int x, int y, int power){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.resistance = 2;
        this.movilidadX = 3;
        this.movilidadY = 2;
        this.alive = true;
        this.timer = 0;
        this.dying = false;
        this.isEvil = true;
        this.scoreValue = 200;
        this.projectiles = new SimpleLinkedList();
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/kamikaze.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (power >=  80){
            isPowerUp = true;
            try {
                powerUp = PowerUpFactory.getPower(power,game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            isPowerUp = false;
            powerUp = null;
        }
    }

    @Override
    public void move(){
        /**
         * Los 2 primeros if son para que el kamikaze siga la posicion del jugador
         **/
        super.move();
        if (this.isEvil == true){
            if (posX + movilidadX < player.posX){
                movilidadX = 3;
            }
            if (posX + movilidadX > player.posX){
                movilidadX = -3;
            }
            posX += movilidadX;
        }
        posY += movilidadY;
    }

    @Override
    public void shoot(){

    }
}
