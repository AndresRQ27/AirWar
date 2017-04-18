package Main;

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
                if(mx >= 640/3 + 100 && mx <= 640/3 + 200){
                    //Return from Game Over button pressed
                    game.State = GameStates.MENU;
                }
            }
        }

        if (game.State == GameStates.HELP){
            if (my >= 460 && my <= 510){
                if(mx >= 640/3 + 100 && mx <= 640/3 + 200){
                    //Return from helpScreen button pressed
                    game.State = GameStates.MENU;
                }
            }
        }
        if (game.State == GameStates.MENU) {
            if (my >= 150 && my <= 200) {
                if (mx >= 640 / 3 + 100 && mx <= 640 / 3 + 200) {
                    //Play button pressed
                    game.State = GameStates.CHANGINGLEVEL;
                }
            }
            if (my >= 350 && my <= 400) {
                if (mx >= 640 / 3 + 100 && mx <= 640 / 3 + 200) {
                    //Help button pressed
                    game.State = GameStates.HELP;
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
