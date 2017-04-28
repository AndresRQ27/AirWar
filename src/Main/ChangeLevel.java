package Main;

import java.awt.*;

/**
 * Created by Cristian44 on 18/04/2017.
 */
public class ChangeLevel {
    private Game game;
    private String levelScreen;

    public ChangeLevel(Game game) {
        this.game = game;
        this.levelScreen = "Level: " + game.stage;
    }

    public void render(Graphics2D g) {
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setColor(Color.black);
        g.fillRect(0,0,640,640);
        g.setFont(fnt1);
        g.setColor(Color.white);
        g.drawString(levelScreen,game.WIDTH/2-30, game.HEIGHT/2);

    }
}
