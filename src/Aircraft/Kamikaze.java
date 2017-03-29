package Aircraft;

import Aircraft.Unidad;
import DataStructures.MyLinkedList.Node;
import Jugador.Player;
import Main.Game;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Cristian44 on 23/3/2017.
 */
public class Kamikaze extends Unidad {
    /**
     * movilidadX es la aceleracion en el eje X
     * player es necesario para que el kamikaze siga al jugador
     */
    int movilidadX;
    private Player player;

    /**
     *
     * @param game
     * @param player
     * @param x posicion en X
     * @param y posicion en Y
     * Es necesario asignar las movilidades en X y Y
     * y alive como true
     */
    public Kamikaze(Game game,Player player,int x, int y){
        this.game = game;
        this.player = player;
        this.posX = x;
        this.posY = y;
        this.movilidadX = 3;
        this.movilidadY = 3;
        this.alive = true;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/kamikaze.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mover(){
        /**
         * Los 2 primeros if son para que el kamikaze siga la posicion del jugador
         * el tercer if lo que hace es que si se pasa del margen de la pantalla la destruye
         */
        if (collision() == true){
            destruir();
        }
        if (posX + movilidadX < player.posX){
            movilidadX = 3;
        }
        if (posX + movilidadX > player.posX){
            movilidadX = -3;
        }

        if (posY + movilidadY > game.getHeight()){
            destruir();
        }

        /**
         * Aca es donde va asignando el movimiento
         */
        posX += movilidadX;
        posY += movilidadY;
    }

    /**
     * Cuando el alive = false, se eliminara el espacio de memoria donde se pintaba la unidad
     * @param g
     */
    public void paint(Graphics2D g) {
        if (alive == true){
            g.drawImage(sprite, posX, posY, null);
        }else{
        }
    }

    private boolean collision() {
        boolean aux = false;

        if (player.projectiles != null) {
            Node<Projectile> current = player.projectiles.getHead();
            while (current != null) {
                if (current.getObject().getBounds().intersects(getBounds())) {
                    aux = true;
                    break;
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
