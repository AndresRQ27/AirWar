package ProjectileFactory;

import Jugador.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public class BalaJugador extends Projectile {
    public Player player;

    public BalaJugador (Game game, Player player, int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.ataque = 1;
        this.alive = true;
        this.movilidadY = 3;
        this.width = 32;
        this.height = 32;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/bala.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void mover(){
        if (collision()){
            destruir();
        }
        if (posY - movilidadY < 0){
            destruir();
        }
        posY-=movilidadY;
    }
}
