package Aircraft;

import DataStructures.MyLinkedList.SimpleLinkedList;
import Jugador.Player;
import Main.Game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Cristian44 on 28/3/2017.
 */
public class Turret extends Enemy {

    public Turret (Game game, Player player, int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.resistance = 5;
        this.movilidadY = 1;
        this.alive = true;
        this.ammunition = 1;
        this.timer = 0;
        this.dying = false;
        this.projectiles = new SimpleLinkedList();

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/turret.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
