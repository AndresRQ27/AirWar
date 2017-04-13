package Aircraft;

import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Jugador.Player;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public abstract class Enemy extends Unidad {
    public Player player;
    public SimpleLinkedList projectile;

    /**
     * Este metodo solo hay que sobreescribirlo en la clase del kamikaze
     */
    @Override
    public  void update(){

    }

    @Override
    public void mover(){
        if (posY + movilidadY > game.getHeight()){
            destroy();
        }
        posY += movilidadY;
    }

    @Override
    public boolean collision() {
        boolean aux = false;

        if (player.projectiles != null) {
            Node<Projectile> current = player.projectiles.getHead();
            while (current != null) {
                if (current.getObject().getBounds().intersects(getBounds())) {
                    current.getObject().destruir();
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

    @Override
    public void shoot() {
        try {
            projectile.addFirst(ProjectileFactory.getProjectile(ammunition,game,player,posX+22,posY+25));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
