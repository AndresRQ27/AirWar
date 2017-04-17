package PowerUps;

import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 15/4/2017.
 */
public class Shield extends PowerUp {
    public Shield(Game game){
        this.game = game;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/PowerUpShield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Use() {
        this.game.player.invincibility = true;
        this.game.player.shield = true;
        try {
            this.game.player.sprite = ImageIO.read(getClass().getResourceAsStream("/shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
