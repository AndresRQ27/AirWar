package Main;

import Enemigos.Kamikaze;
import Enemigos.Turrent;
import Unidad.Unidad;
import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Jugador.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Cristian44 on 24/3/2017.
 */
public class Game extends JPanel{
    Player player = new Player(this);
    public SimpleLinkedList enemigosPantalla = new SimpleLinkedList();

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
            }
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }});
        setFocusable(true);
        enemigosPantalla.addLast(new Kamikaze(this,player,300,0));
        enemigosPantalla.addLast(new Kamikaze(this,player,300,100));
        enemigosPantalla.addLast(new Turrent(this,player,300,0));
    }
    private void update(){
        player.actualizar();
        updateEnemigos();
    }
    private void updateEnemigos(){
        if (enemigosPantalla!= null){
            Node <Unidad> current = enemigosPantalla.getHead();
            while (current != null){
                current.getObject().mover();
                current = current.getNext();
            }
        }
    }

    public void actualizarEnemigosPantalla(){
        int index = 0;
        if (enemigosPantalla!= null){
            Node <Unidad> current = enemigosPantalla.getHead();
            while (current != null){
                if (current.getObject().alive == true){
                    index++;
                }else{
                    enemigosPantalla.removeInPosition(index);
                }
                current = current.getNext();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        player.paint(g2d);
        player.paintBalas(g2d);
        if (enemigosPantalla!= null){
            Node <Unidad> current = enemigosPantalla.getHead();
            while (current != null) {
                current.getObject().paint(g2d);
                current = current.getNext();
            }
        }

    }

    public static void main (String [] args){
        JFrame frame = new JFrame("Air Wars");
        Game game = new Game();
        frame.add(game);
        frame.setSize(960, 640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.update();
            game.repaint();
            game.actualizarEnemigosPantalla();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
