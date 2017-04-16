package Main;

import Aircraft.EnemySpawner;
import Aircraft.EnemyTypes;
import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

/**
 * Created by Cristian44 on 24/3/2017.
 */
public class Game extends JPanel{

    private static final Random random = new Random();
    private static final EnemyTypes[] enemyList = EnemyTypes.values();
    private static Random randomPower = new Random();

    public final int WIDTH = 640;
    public final int HEIGHT = 640;
    public Player player = new Player(this);
    public SimpleLinkedList screenQueue = new SimpleLinkedList();
    private SimpleLinkedList levelQueue = new SimpleLinkedList();

    private Nivel nivel1 = new Nivel(0,-3000);
    public static int Lifes;
    public static int score;


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
        //Cambiar el generador de enemigos por 25*X donde X es el nivel
        generateEnemiesQueue(50);
    }

    private void update(){
        Lifes = player.Lifes;
        score = player.score;
        nivel1.move();
        player.update();
        updateEnemies();
    }

    private void updateEnemies(){
        if (screenQueue != null){
            int index = 0;
            Node<Aircraft.Enemy> current = screenQueue.getHead();
            while (current != null){
                current.getObject().update();

                if (!current.getObject().alive){
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
        if (screenQueue.getlength()<2){
            Node<Aircraft.Enemy> current = levelQueue.getHead();
            while (current != null && screenQueue.getlength()<2){
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
            Node<Aircraft.Enemy> current = screenQueue.getHead();
            while (current != null) {
                current.getObject().paint(g2d);
                current.getObject().paintProjectiles(g2d);
                current = current.getNext();
            }
        }
    }

    private void generateEnemiesQueue(int cantidad){
        while (cantidad > 0){
            try {
                //RandomX = random.nextInt(WIDTH-64);
                //RandomType = EnemySpawner.createEnemy(enemyList[random.nextInt(enemyList.length)]; **usando enum
                levelQueue.addLast(EnemySpawner.createEnemy(enemyList[random.nextInt(enemyList.length)],this,player,64*random.nextInt(10),0,randomPower.nextInt(100)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            cantidad--;
        }
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

            frame.setTitle("Air Wars || Vidas: " + Lifes + " || Score: " + score);
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
