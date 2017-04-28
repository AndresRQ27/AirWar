package PowerUps;

import Main.Game;

import java.awt.image.BufferedImage;

/**
 * Created by Cristian44 on 15/4/2017.
 */
public abstract class PowerUp {
    Game game;
    public BufferedImage sprite;

    public  abstract  void Use();
}
