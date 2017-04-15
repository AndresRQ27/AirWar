package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Cristian44 on 14/4/2017.
 */
public class Nivel {
    public BufferedImage fondo;
    public int posX;
    public int posY;
    public int movilidad;

    public Nivel (int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.movilidad = 1;
        try {
            this.fondo = ImageIO.read(getClass().getResourceAsStream("/background1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(fondo,posX,posY,null);
    }

    public void move(){
        posY += movilidad;
    }
}
