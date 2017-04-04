package Enemigos;

import DataStructures.MyLinkedList.Node;
import Jugador.Player;
import Main.Game;
import ProjectileFactory.Projectile;
import Unidad.Unidad;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Cristian44 on 28/3/2017.
 */
public class Turrent extends Unidad {
    Player player;

    public Turrent (Game game, Player player, int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.movilidadY = 1;
        this.alive = true;
        this.municion = 1;

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/turrent.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics2D g) {
        if (alive == true){
            g.drawImage(sprite, posX, posY, null);
        }else{
        }
    }

    @Override
    public void mover(){
        if (alive == true){
            if (collision()){
                destruir();
            }
            super.mover();
        }
        else {
            super.mover();
        }

    }

    private boolean collision() {
        boolean aux = false;

        if (player.projectiles != null) {
            Node<Projectile> current = player.projectiles.getHead();
            while (current != null) {
                if (current.getObject().getBounds().intersects(getBounds())) {
                    aux = true;
                }
                current = current.getNext();
            }
            if (player.getBounds().intersects(getBounds())){
                aux = true;
            }
        }
        return aux;
    }


}
