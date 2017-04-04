package com.PowerUps;

/**
 * Created by pedro on 17/3/2017.
 * El Factory para la creación de poderes
 */
public class PowerUpsFactory {
    public static PowerUps getPower(String tipo) throws Exception{
        if (tipo == "Escudo"){
            return new Escudo();
        }if(tipo == "Misiles"){
            return new Misiles();
        }if(tipo == "Laser"){
            return new Laser();
        }else{
            throw new Exception("Poder no existe");
        }
    }
}
