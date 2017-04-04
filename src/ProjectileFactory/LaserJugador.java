package ProjectileFactory;

import Main.Game;

/**
 * Created by pedro on 4/4/2017.
 */
public class LaserJugador extends Projectile {

    public LaserJugador (Game game, int x, int y){
        this.game = game;
        BufferImage("/laser.png");
        loadDimension();
        this.x = x+width;
        this.y = y-height;
        this.ataque = 1;//cambiar
    }
    @Override
    public void move(){
        this.y -= speed;
        if (collision()){
            destruir();
        }
        if (this.y - speed < 0){
            destruir();
        }
    }
}
