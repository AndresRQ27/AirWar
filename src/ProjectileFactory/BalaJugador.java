package ProjectileFactory;

import Player.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public class BalaJugador extends Projectile {

    public BalaJugador (Game game,int x, int y){
        this.game = game;
        BufferImage("/bala.png");
        loadDimension();
        this.x = x+width;
        this.y = y-height;
        this.ataque = 1;
    }
    @Override
    public void move(){
        this.y -= speed;
        if (collision()){
            destruir();
        }
        if (this.y - speed < 0){
            destruir();
        }
    }
}
