package Main;

import Aircraft.EnemySpawner;
import Aircraft.Enemies;
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
    public Player player = new Player(this);
    public MyQueue screenQueue = new MyQueue();
    private MyQueue planesQueue = new MyQueue();
    private MyQueue turretQueue = new MyQueue();

    private Nivel nivel = new Nivel();
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
        new Enemies();

        //Aviones
        while ((int)planes > 0){
            try {
                planesQueue.enqueue(EnemySpawner.createEnemy(Enemies.planesList.whatsIn(random.nextInt(Enemies.planesList.getlength())),this,player,64*(random.nextInt(8)+1),0, random.nextInt(100)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            planes--;
        }

        //Torre
        while ((int)turrets > 0){
            try {
                turretQueue.enqueue(EnemySpawner.createEnemy(Enemies.turretList.whatsIn(random.nextInt(Enemies.turretList.getlength())),this,player,64*(random.nextInt(8)+1),0, random.nextInt(100)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            turrets--;
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
