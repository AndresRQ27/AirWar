package Main;

import Aircraft.Enemy;
import Aircraft.EnemySpawner;
import Aircraft.EnemiesList;
import Aircraft.EnemyTypes;
import DataStructures.MyLinkedList.MyQueue;
import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Player.Player;
import Server.Server;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.*;



/**
 * Created by Cristian44 on 24/3/2017.
 */
public class Game extends JPanel {

    private static final Random random = new Random();
    private int numplanes;

    public final int WIDTH = 640;
    public final int HEIGHT = 640;
    public Player player = new Player(this);

    public MyQueue screenQueue = new MyQueue();
    private MyQueue planesQueue = new MyQueue();
    private MyQueue turretQueue = new MyQueue();
    private MyQueue bossQueue = new MyQueue();

    private int numEnemies = 3;
    public int stage = 1;
    public final int finalStage = 9;
    private Level level = new Level(stage);

    private static int Lives;
    private static int score;

    public GameStates State = GameStates.MENU;
    public Menu menuScreen;
    public GameOver gameOverScreen;
    public Help helpScreen;
    public ChangeLevel changeLevel;
    public GameComplete gameComplete;


    public static String nombre = "";
    public boolean Write = false;



    private Game() {

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e){

                if(State ==  GameStates.GAME ) {
                    player.keyReleased(e);
                }else if(State == GameStates.MENU && Write == true) {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && nombre.length() > 0) {
                        nombre = nombre.substring(0, nombre.length() - 1);
                    }else if(nombre.length() < 9){
                        if (e.getKeyText(e.getKeyCode()).length() ==  1) {
                            nombre = nombre + e.getKeyChar();
                        }else{

                        }
                    }
                }

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(State ==  GameStates.GAME ) {
                    player.keyPressed(e);
                }
            }});
        addMouseListener(new MouseInput(this));
        setFocusable(true);

        generateEnemiesQueue(25 * this.stage);
        menuScreen = new Menu(this);
        gameOverScreen = new GameOver(this);
        helpScreen = new Help(this);
        changeLevel = new ChangeLevel(this);
        gameComplete = new GameComplete(this);
    }

    public void changeLevel(){
        this.stage++;
        changeLevel = new ChangeLevel(this);
        this.player.posX =WIDTH/2 - player.LADOSPRITE/2;
        this.player.posY = HEIGHT - 120;
        this.State = GameStates.CHANGINGLEVEL;
        this.level = new Level(this.stage);
        this.screenQueue = new MyQueue();
        this.planesQueue = new MyQueue();
        this.turretQueue = new MyQueue();
        this.bossQueue = new MyQueue();
        this.player.projectiles = new SimpleLinkedList();
        if (stage < 3){
            this.numEnemies = 3;
        }else if (stage < 6){
            this.numEnemies = 5;
        }else if (stage <= 10){
            this.numEnemies = 7;
        }
        generateEnemiesQueue(25 * stage);
    }

    public void restartGame(){
        this.nombre = "";
        this.player = new Player(this);
        this.stage = 1;
        this.level = new Level(stage);
        changeLevel = new ChangeLevel(this);
        this.screenQueue = new MyQueue();
        this.planesQueue = new MyQueue();
        this.turretQueue = new MyQueue();
        this.bossQueue = new MyQueue();
        generateEnemiesQueue(25 * stage);
    }

    public void gameComplete(){
        changeLevel = new ChangeLevel(this);
        this.State = GameStates.GAMECOMPLETE;

    }

    private void update(){
        Lives = player.Lifes;
        score = player.score;
        level.move();
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
        if (screenQueue.getlength()<numEnemies){

            Node<Aircraft.Enemy> planes = planesQueue.getHead();
            Node<Aircraft.Enemy> turret = turretQueue.getHead();

            if (numplanes >= numEnemies && turret != null){
                screenQueue.enqueue(turret.getObject());
                turretQueue.dequeue();
                numplanes = 0;

            } else {
                while (planes != null && screenQueue.getlength() < numEnemies-1) {
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
                        Player.fightBoss = true;
                    }
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if (State == GameStates.MENU){
            menuScreen.render(g2d);
        }else if (State == GameStates.GAME) {
            level.paint(g2d);
            player.paint(g2d);
            player.paintProjectiles(g2d);
            if (screenQueue != null) {
                Node<Enemy> current = screenQueue.getHead();
                while (current != null) {
                    current.getObject().paint(g2d);
                    current.getObject().paintProjectiles(g2d);
                    current = current.getNext();
                }
            }
        }else if (State == GameStates.GAMEOVER){
            gameOverScreen.render(g2d);
        }else if(State == GameStates.HELP) {
            helpScreen.render(g2d);
        }else if (State == GameStates.CHANGINGLEVEL){
            changeLevel.render(g2d);
        }else if (State == GameStates.GAMECOMPLETE){
            gameComplete.render(g2d);
        }
    }

    private void generateEnemiesQueue(int number){

        double planes = 3*number/4;
        double turrets = number/4 + 1;
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
        int timer = 0;
        Server server = new Server("Hilo server", game.player);
        server.start();


        while (true) {
            if (game.State == GameStates.GAME) {
                frame.setTitle("Air Wars || Nivel:" + game.stage + " || Player: " + nombre+ " || Vidas: " + Lives + " || Score: " + score + " || Escudos: " + Player.numShields + "||");
                game.update();
                game.updateEnemiesInScreen();
            }else if (game.State == GameStates.GAMEOVER) {
                frame.setTitle("Air Wars");
            } else if (game.State == GameStates.GAMECOMPLETE) {
                frame.setTitle("Air Wars");
            }else if (game.State == GameStates.CHANGINGLEVEL){
                timer++;
                if (timer>300){
                    game.State = GameStates.GAME;
                    timer = 0;
                }
            }
            game.repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
