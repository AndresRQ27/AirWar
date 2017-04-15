package Aircraft;

import Jugador.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public class Bullet extends Projectile {
    public Player player;

    public Bullet(Game game, Player player, int x, int y, int source){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.alive = true;
        this.movilidadY = 5;
        this.width = 32;
        this.height = 32;
        if (source == 0){
            this.attack = 1;
            try {
                sprite = ImageIO.read(getClass().getResourceAsStream("/bullet.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            this.attack = 2;
            try {
                sprite = ImageIO.read(getClass().getResourceAsStream("/bullet2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
