package Aircraft;

import Main.Game;
import Jugador.Player;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public class EnemySpawner  {
    public static Enemy createEnemy(EnemyTypes enemy, Game game, Player player, int x, int y) throws Exception{
        switch (enemy){
            case JET:
                return new Jet(game,player,x,y);

            case BOMBER:
                return new Bomber(game,player,x,y);

            case TURRET:
                return new Turret(game,player,x,y);

            case KAMIKAZE:
                return new Kamikaze(game,player,x,y);

            case MISSILETURRET:
                return new MissileTurret(game,player,x,y);

            default:
                throw new Exception("Unknown Enemy Type");
        }
    }
}
