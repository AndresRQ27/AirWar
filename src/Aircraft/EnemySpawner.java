package Aircraft;

import Main.Game;
import Jugador.Player;

/**
 * Created by Cristian44 on 12/4/2017.
 */
public class EnemySpawner  {
    public static Enemy getEnemy (int type, Game game,Player player, int x, int y) throws Exception{
        if (type == 0){
            return new Turret(game,player,x,y);
        }else if (type == 1){
            return new MissileTurret(game,player,x,y);
        }else if (type == 2){
            return new Kamikaze(game,player,x,y);
        }else if (type == 3){
            return new Bomber(game,player,x,y);
        }else if (type == 4){
            return new Jet(game,player,x,y);
        }else{
            throw new Exception("Unknow Enemy Type");
        }
    }
}
