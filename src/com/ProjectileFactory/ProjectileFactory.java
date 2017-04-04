package com.ProjectileFactory;


import com.Game.Game;

/**
 * Created by Cristian44 on 28/3/2017.
 */
public class ProjectileFactory{
    public static Projectile getProjectilev(int type, int x, int y, Game game) throws Exception{
        if (type == 1){
            return new BalaJugador(game,x,y);
        }else if (type == 2){
            return new MisileJugador(game,x,y);
        }else if (type == 3){
            return new LaserJugador(game,x,y);
        }else{
            throw new Exception("Unknow Projectile Type");
        }
    }
}
