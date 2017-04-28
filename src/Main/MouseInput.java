package Main;

import Sound.Sounds;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Dell on 15/04/2017.
 */
public class MouseInput implements MouseListener{
    private Game game;

    public  MouseInput(Game game){
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if (game.State == GameStates.GAMEOVER){
            if (my >= 460 && my <= 510){
                if(mx >= 640/2 - 50 && mx <= 640/2 + 50){
                    //Return from Game Over button pressed
                    game.State = GameStates.MENU;
                    game.restartGame();
                }
            }
        }

        if (game.State == GameStates.HELP){
            if (my >= 460 && my <= 510){
                if(mx >= 640/2 - 50 && mx <= 640/2 + 50){
                    //Return from helpScreen button pressed
                    game.State = GameStates.MENU;
                }
            }
        }
        if (game.State == GameStates.MENU) {
            game.Write = false;
            if (my >= 150 && my <= 200) {
                if (mx >= 640/2 - 50 && mx <= 640/2 + 50) {
                    //Play button pressed
                    game.State = GameStates.CHANGINGLEVEL;
                    Sounds.BACKGROUND.loop();
                }
            }
            if (my >= 260 && my <= 300) {
                if (mx >= 640/2 - 70 && mx <= 640/2 + 70) {
                    //Name button pressed
                    game.Write = true;
                }
            }


            if (my >= 350 && my <= 400) {
                if (mx >= 640/2 - 50 && mx <= 640/2 + 50) {
                    //Help button pressed
                    game.State = GameStates.HELP;
                }
            }
        }
        if (game.State == GameStates.GAMECOMPLETE){
            if (my >= 460 && my <= 510) {
                if (mx >= 640 / 3 + 50 && mx <= 640 / 3 + 150) {
                    //Play button pressed
                    game.State = GameStates.MENU;
                    game.restartGame();

                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
