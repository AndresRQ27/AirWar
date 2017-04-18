package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Cristian44 on 18/4/2017.
 */
public class GameComplete {
    private Game game;
    private BufferedImage button;
    private BufferedImage background;

    public GameComplete(Game game) {
        this.game = game;
        try {
            this.button = ImageIO.read(getClass().getResourceAsStream("/button.png"));
            this.background = ImageIO.read(getClass().getResourceAsStream("/MissionComplete.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g) {
        g.drawImage(background, 0, 0, 640, 640, null);
        Font fnt1 = new Font("arial", Font.BOLD, 30);

        g.setFont(fnt1);
        g.setColor(Color.white);

        g.drawString("Score:" + game.player.score,game.WIDTH / 3 + 60, 400);

        g.drawImage(button, game.WIDTH / 3 + 50, 460, 100, 50, null);
        g.drawString("Back", game.WIDTH / 3 + 60, 495);
    }
}
