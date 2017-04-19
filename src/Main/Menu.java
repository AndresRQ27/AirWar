package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;


/**
 * Created by David on 15/04/2017.
 */
public class Menu extends JFrame {
    private Game game;
    private BufferedImage button;
    private BufferedImage BackGround;


    public Menu(Game game) {
        this.game = game;
        try {
            this.button = ImageIO.read(getClass().getResourceAsStream("/Screens/button.png"));
            this.BackGround = ImageIO.read(getClass().getResourceAsStream("/Screens/backgroundMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void render(Graphics2D g) {

        g.drawImage(BackGround, 0, 0, 640, 640, null);
        Font fnt0 = new Font("arial", Font.BOLD, 59);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("AIR WAR", game.WIDTH / 2 - 130, 100);


        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawImage(button, game.WIDTH / 2 - 50, 150, 100, 50, null);
        g.drawString("Play", game.WIDTH / 2 - 33, 185);
        g.drawImage(button, game.WIDTH / 2 - 50, 250, 100, 50, null);

        g.drawImage(button, game.WIDTH / 2 - 50, 350, 100, 50, null);
        g.drawString("Help", game.WIDTH / 2 - 33, 385);

        Font fnt2 = new Font("arial", Font.BOLD, 25);
        g.setFont(fnt2);
        g.drawString("Scores", game.WIDTH / 2 - 40, 285);
    }
}