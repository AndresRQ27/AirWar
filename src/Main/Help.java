package Main;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by David on 16/04/2017.
 */
public class Help {
    private Game game;
    private BufferedImage shield;
    private BufferedImage Missile;
    private BufferedImage Laser;
    private BufferedImage BackGround;
    private BufferedImage button;

    public Help(Game game) {
        this.game = game;
        try {
            this.shield = ImageIO.read(getClass().getResourceAsStream("/PowerUpShield.png"));
            this.Missile = ImageIO.read(getClass().getResourceAsStream("/PowerUpMissile.png"));
            this.Laser = ImageIO.read(getClass().getResourceAsStream("/PowerUpLaser.png"));
            this.BackGround = ImageIO.read(getClass().getResourceAsStream("/backgroundMenu.png"));
            this.button = ImageIO.read(getClass().getResourceAsStream("/button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics2D g) {

        g.drawImage(BackGround, 0, 0, 640, 640, null);
        Font fnt0 = new Font("arial", Font.BOLD, 59);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Instructions", game.WIDTH/ 3, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("UP = W", game.WIDTH/ 2 - 300, 200);
        g.drawString("DOWN = S", game.WIDTH/ 2 - 300, 250);
        g.drawString("LEFT = A", game.WIDTH/ 2 - 300, 300);
        g.drawString("RIGHT = D", game.WIDTH/ 2 - 300, 350);
        g.drawString("FIRE = J", game.WIDTH/ 2 - 300, 400);
        g.drawString("SKILL = SPACE", game.WIDTH/ 2 - 300, 450);
        g.drawImage(shield, game.WIDTH / 2 -50, 150, 100, 50, null);
        g.drawString("= Skill SHIELD", game.WIDTH/ 2 + 50, 190);
        g.drawImage(Missile, game.WIDTH / 2 -50, 250, 100, 50, null);
        g.drawString("= Skill MISSILE", game.WIDTH/ 2 + 50, 290);
        g.drawImage(Laser, game.WIDTH / 2 -50, 350, 100, 50, null);
        g.drawString("= Skill LASER", game.WIDTH/ 2 + 50, 390);
        g.drawImage(button, game.WIDTH / 3 + 100, 460, 100, 50, null);
        g.drawString("Back", game.WIDTH / 3 + 115, 495);


    }
}