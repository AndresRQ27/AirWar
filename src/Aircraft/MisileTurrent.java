package Aircraft;

import Jugador.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public class MisileTurrent extends Enemy {

    public MisileTurrent (Game game, Player player, int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.movilidadY = 1;
        this.alive = true;
        this.ammunition = 2;

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/misileLauncher.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mover(){
        if (alive == true){
            if (collision()){
                destroy();
            }
            super.mover();
        }
        else {
            super.mover();
        }

    }

}
