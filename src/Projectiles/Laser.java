package Projectiles;

import Player.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 12/4/2017.
 */
class Laser extends Projectile {
    private final Player player;

    public Laser (Game game, Player player, int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.attack = 3;
        this.alive = true;
        this.movilidadY = 4;
        this.width = 10;
        this.height = 16;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
