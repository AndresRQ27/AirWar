package Aircraft;

import Jugador.Player;
import Main.Game;

/**
 * Created by Cristian44 on 28/3/2017.
 */
public class ProjectileFactory{
    public static Projectile getProjectile(int type, Game game, Player player, int x, int y, int source) throws Exception{
        if (type == 1){
            return new Bullet(game,player,x,y,source);
        }else if (type == 2){
            return new Missile(game,player,x,y,source);
        }else if (type == 3){
            return new Laser(game,player,x,y);
        }else{
            throw new Exception("Unknow Projectile Type");
        }
    }
}
