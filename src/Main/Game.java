package Main;

import Aircraft.EnemySpawner;
import Aircraft.Unidad;
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
        generateEnemiesQueue(100);

    }

    private void update(){
        player.update();
        updateEnemies();
    }

    private void updateEnemies(){
        if (screenQueue != null){
            int index = 0;
            Node <Unidad> current = screenQueue.getHead();
            while (current != null){
                current.getObject().mover();

                if (current.getObject().alive == true){
                    index++;
                }else{
                    screenQueue.removeInPosition(index);
                }
                current = current.getNext();
            }
        }
    }

    private void updateEnemiesInScreen(){
        if (screenQueue.getlength()<5){
            Node <Unidad> current = levelQueue.getHead();
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
        player.paint(g2d);
        player.paintBalas(g2d);
        if (screenQueue != null){
            Node <Unidad> current = screenQueue.getHead();
            while (current != null) {
                current.getObject().paint(g2d);
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
        return number.nextInt(4);
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
