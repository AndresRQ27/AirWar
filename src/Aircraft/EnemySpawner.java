package Aircraft;

import Main.Game;
import Player.Player;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public class EnemySpawner {
    public static Enemy createEnemy(EnemyTypes enemy, Game game, Player player, int x, int y, int power) throws Exception{
        switch (enemy){
            case JET:
                return new Jet(game,player,x,y,85);

            case MISSILETURRET:
                return new MissileTurret(game,player,x,y,power);

            case TURRET:
                return new Turret(game, player, x, y,power);

            case BOMBER:
                return new Bomber(game,player,x,y,power);

            case KAMIKAZE:
                return new Kamikaze(game,player,x,y,power);

            case BOSS:
                return new Boss(game,player);

            default:
                throw new Exception("Unknown Enemy Type");
        }
    }
}
