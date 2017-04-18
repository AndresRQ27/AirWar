package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Cristian44 on 14/4/2017.
 */
class Nivel {
    private BufferedImage fondo;
    private int posX;
    private int posY;
    private final int movilidad;
    private int nivel;

    public Nivel (){
        this.posX = 0;
        this.posY = -3840;
        this.movilidad = 1;
        this.nivel = 1;
        try {
            this.fondo = ImageIO.read(getClass().getResourceAsStream("/background" + nivel + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNivel(int nivel){
        this.posX = 0;
        this.posY = -3840;
        this.nivel = nivel;
        try {
            this.fondo = ImageIO.read(getClass().getResourceAsStream("/background" + nivel + ".png"));
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
