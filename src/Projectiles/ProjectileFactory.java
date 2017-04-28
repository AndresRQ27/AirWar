package Projectiles;

import Player.Player;
import Main.Game;

/**
 * Created by Cristian44 on 28/3/2017.
 */
public class ProjectileFactory{
    public static Projectile getProjectile(ProjectileTypes type, Game game, Player player, int x, int y, int source) throws Exception{
        switch (type){
            case BULLET:
                return new Bullet(game,player,x,y,source);
            case MISSILE:
                return new Missile(game,player,x,y,source);
            case LASER:
                return new Laser(game,player,x,y);
            default:
                throw new Exception("Unknow Projectile Type");
        }
    }
}
