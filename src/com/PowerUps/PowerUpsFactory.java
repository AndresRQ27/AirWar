package com.PowerUps;

import com.Game.Game;

/**
 * Created by pedro on 17/3/2017.
 * El Factory para la creación de poderes
 */
public class PowerUpsFactory {
    public static PowerUps getPower(String tipo, Game game) throws Exception{
        if (tipo == "Escudo"){
            return new Escudo(game);
        }if(tipo == "Misiles"){
            return new Misiles();
        }if(tipo == "Laser"){
            return new Laser();
        }else{
            throw new Exception("Poder no existe");
        }
    }
}
