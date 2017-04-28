package PowerUps;

import Main.Game;

/**
 * Created by Cristian44 on 15/4/2017.
 */
public class PowerUpFactory {
    public static PowerUp getPower(int tipo, Game game) throws  Exception{
        if (tipo >=80 && tipo < 90){
            return new Shield(game);
        }if(tipo >= 90 && tipo < 95){
            return new Missile(game);
        }if (tipo >=95 && tipo < 100) {
            return new Laser(game);
        }else{
            throw new Exception("Unknow PowerUp");
        }
    }
}
