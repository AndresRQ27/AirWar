package Aircraft;

import DataStructures.MyLinkedList.SimpleLinkedList;
import Player.Player;
import Main.Game;
import PowerUps.PowerUpFactory;
import Projectiles.ProjectileTypes;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 12/4/2017.
 */
class MissileTurret extends Enemy {

    MissileTurret (Game game, Player player, int x, int y, int power){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.resistance = 6;
        this.movilidadY = 1;
        this.alive = true;
        this.ammunition = ProjectileTypes.MISSILE;
        this.timer = 0;
        this.dying = false;
        this.isEvil = true;
        this.scoreValue=600;
        this.projectiles = new SimpleLinkedList();

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/missileTurret.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (power >= 80){
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
}
