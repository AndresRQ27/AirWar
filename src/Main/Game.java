package Main;

import Aircraft.EnemySpawner;
import Aircraft.EnemiesList;
import Aircraft.EnemyTypes;
import DataStructures.MyLinkedList.MyQueue;
import DataStructures.MyLinkedList.Node;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.*;

/**
 * Created by Cristian44 on 24/3/2017.
 */
public class Game extends JPanel{

    private static final Random random = new Random();
    private int numplanes;

    public final int WIDTH = 640;
    public final int HEIGHT = 640;
    public final Player player = new Player(this);

    public final MyQueue screenQueue = new MyQueue();
    private final MyQueue planesQueue = new MyQueue();
    private final MyQueue turretQueue = new MyQueue();
    private final MyQueue bossQueue = new MyQueue();

    private final Nivel nivel = new Nivel();
    private static int Lifes;
    private static int score;


    private Game() {
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
        nivel.move();
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
        if (screenQueue.getlength()<5){

            Node<Aircraft.Enemy> planes = planesQueue.getHead();
            Node<Aircraft.Enemy> turret = turretQueue.getHead();

            if (numplanes >= 5 && turret != null){
                screenQueue.enqueue(turret.getObject());
                turretQueue.dequeue();
                numplanes = 0;

            } else {
                while (planes != null && screenQueue.getlength() < 4) {
                    screenQueue.enqueue(planes.getObject());
                    planes = planes.getNext();
                    planesQueue.dequeue();
                    numplanes++;
                }

                if (planes == null && screenQueue.getlength() == 0){
                    Node<Aircraft.Enemy> boss = bossQueue.getHead();
                    if (boss != null) {
                        screenQueue.enqueue(boss.getObject());
                        bossQueue.dequeue();
                    }
                }
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        nivel.paint(g2d);
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

        double planes = 3*cantidad/4;
        double turrets = cantidad/4 + 1;
        new EnemiesList();

        //Aviones
        while ((int)planes > 0){
            try {
                planesQueue.enqueue(EnemySpawner.createEnemy(EnemiesList.planesList.whatsIn(random.nextInt(EnemiesList.planesList.getlength())),this,player,64*(random.nextInt(8)+1),0, random.nextInt(100)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            planes--;
        }

        //Torre
        while ((int)turrets > 0){
            try {
                turretQueue.enqueue(EnemySpawner.createEnemy(EnemiesList.turretList.whatsIn(random.nextInt(EnemiesList.turretList.getlength())),this,player,64*(random.nextInt(8)+1),0, random.nextInt(100)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            turrets--;
        }

        //BOSS
        try {
            bossQueue.enqueue(EnemySpawner.createEnemy(EnemyTypes.BOSS, this, player, 0, 0, 0));
        } catch (Exception e){
            e.printStackTrace();
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
