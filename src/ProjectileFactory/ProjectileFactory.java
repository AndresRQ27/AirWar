package ProjectileFactory;

import Main.Game;

/**
 * Created by Cristian44 on 28/3/2017.
 */
public class ProjectileFactory{
    public static Projectile getProjectilev(int type, Game game, int x, int y) throws Exception{
        if (type == 1){
            return new BalaJugador(game,x,y);
        }else if (type == 2){
            return new BalaJugador(game,x,y);
        }else if (type == 3){
            return new BalaJugador(game,x,y);
        }else{
            throw new Exception("Unknow Projectile Type");
        }
    }
}
