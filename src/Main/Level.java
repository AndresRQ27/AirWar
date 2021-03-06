package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Cristian44 on 14/4/2017.
 */
class Level {
    private BufferedImage fondo;
    private int posX;
    private int posY;
    private final int movilidad;
    private int level;

    public Level(int level){
        this.posX = 0;
        this.posY = -3840;
        this.movilidad = 1;
        this.level = level;
        try {
            this.fondo = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/level_" + this.level + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void paint(Graphics2D g){
        g.drawImage(fondo,posX,posY,null);
    }

    public void move(){
        posY += movilidad;
        if (posY == 0){
            posY = -3840;
        }
    }
}
