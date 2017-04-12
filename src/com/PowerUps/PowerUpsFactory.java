package com.PowerUps;

import com.Game.Game;

/**
 * PowerUpsFactory
 * Es el factory para los poderes
 * @return un Poder
 * @author  by pedro on 17/3/2017.
 */
public class PowerUpsFactory {
    public static PowerUps getPower(String tipo, Game game) throws Exception{
        if (tipo == "Escudo"){
            return new Escudo(game);
        }if(tipo == "Misile"){
            return new Misiles(game);
        }if(tipo == "Laser"){
            return new Laser(game);
        }else{
            throw new Exception("Poder no existe");
        }
    }
}
