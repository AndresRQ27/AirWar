package PowerUps;

import Main.Game;
import Projectiles.ProjectileTypes;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 15/4/2017.
 */
public class Laser extends PowerUp {
    Laser(Game game){
        this.game = game;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/PowerUpLaser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Use(){
        this.game.player.ammunition = ProjectileTypes.LASER;
    }
}
