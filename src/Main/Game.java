package Main;

import Aircraft.Enemy;
import Aircraft.EnemySpawner;
import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Jugador.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

/**
 * Created by Cristian44 on 24/3/2017.
 */
public class Game extends JPanel{
    public final int WIDTH = 640;
    public final int HEIGHT = 640;
    Player player = new Player(this);
    public SimpleLinkedList screenQueue = new SimpleLinkedList();
    public SimpleLinkedList levelQueue = new SimpleLinkedList();
    private Nivel nivel1 = new Nivel(0,-3000);



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
        generateEnemiesQueue(50);
    }

    private void update(){
        nivel1.move();
        player.update();
        updateEnemies();
    }

    private void updateEnemies(){
        if (screenQueue != null){
            int index = 0;
            Node <Enemy> current = screenQueue.getHead();
            while (current != null){
                current.getObject().update();

                if (current.getObject().alive == false){
                    if (current.getObject().projectiles !=null){
                        if (current.getObject().projectiles.getHead() == null) {
                            screenQueue.removeInPosition(index);
                        }else {
                            index++;
                        }
                    }else{
                        screenQueue.removeInPosition(index);
                    }

                }else{
                    index++;
                }
                current = current.getNext();
            }
        }
    }

    private void updateEnemiesInScreen(){
        if (screenQueue.getlength()<5){
            Node <Enemy> current = levelQueue.getHead();
            while (current != null && screenQueue.getlength()<5){
                screenQueue.addLast(current.getObject());
                current = current.getNext();
                levelQueue.removeFirst();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        nivel1.paint(g2d);
        player.paint(g2d);
        player.paintProjectiles(g2d);
        if (screenQueue != null){
            Node <Enemy> current = screenQueue.getHead();
            while (current != null) {
                current.getObject().paint(g2d);
                current.getObject().paintProjectiles(g2d);
                current = current.getNext();
            }
        }

    }

    public void generateEnemiesQueue(int cantidad){
        while (cantidad > 0){
            try {
                levelQueue.addLast(EnemySpawner.getEnemy(this.RandomType(),this,player,RandomX(),0));
            } catch (Exception e) {
                e.printStackTrace();
            }
            cantidad--;
        }
    }

    public int RandomType (){
        Random number = new Random();
        return number.nextInt(5);
    }

    public int RandomX(){
        Random number = new Random();
        return number.nextInt(WIDTH-64);
    }


    public static void main (String [] args){
        JFrame frame = new JFrame("Air Wars");
        Game game = new Game();
        frame.add(game);
        frame.setSize(game.WIDTH, game.HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        while (true) {
            game.update();
            game.updateEnemiesInScreen();

            game.repaint();


            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
