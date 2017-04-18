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
    private int movilidadX;
    /**
     *
     * @param game parámetro del juego en el que está
     * @param player jugador al que tiene que perseguir
     * @param x posicion en X
     * @param y posicion en Y
     * Es necesario asignar las movilidades en X y Y
     * y alive como true
     */
    Kamikaze(Game game,Player player,int x, int y, int power){
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
        super.move();
        if (this.isEvil){
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
