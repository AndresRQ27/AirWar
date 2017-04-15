package Aircraft;

import Jugador.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public class Laser extends Projectile {
    public Player player;

    public Laser (Game game, Player player, int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.attack = 3;
        this.alive = true;
        this.movilidadY = 5;
        this.width = 32;
        this.height = 32;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
