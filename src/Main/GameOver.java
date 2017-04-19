package Main;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Created by David on 16/04/2017.
 */
public class GameOver {
    private Game game;
    private BufferedImage button;
    private BufferedImage GameOver;

    public GameOver(Game game) {
        this.game = game;
        try {
            this.button = ImageIO.read(getClass().getResourceAsStream("/Screens/button.png"));
            this.GameOver = ImageIO.read(getClass().getResourceAsStream("/Screens/GameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics2D g) {

        g.drawImage(GameOver, 0, 0, 640, 640, null);
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.setColor(Color.white);
        g.drawImage(button, game.WIDTH / 3 + 100, 460, 100, 50, null);
        g.drawString("Back", game.WIDTH / 3 + 115, 495);

    }
}
