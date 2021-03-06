package Player;

import Aircraft.Boss;
import Aircraft.Enemy;
import DataStructures.MyLinkedList.MyStack;
import Main.GameStates;
import PowerUps.PowerUp;
import PowerUps.Shield;
import Projectiles.Projectile;
import Projectiles.ProjectileFactory;
import Aircraft.Unidad;
import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import Main.Game;
import Projectiles.ProjectileTypes;
import Sound.Sounds;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Cristian44 on 27/3/2017.
 */
public class Player extends Unidad{
    private boolean W = false,A = false,S = false,D = false;
    public int Lifes;
    private int movilidadX;
    public boolean invincibility;
    private boolean dying;
    private int timer;
    private int shieldTimer;
    public SimpleLinkedList projectiles;
    private MyStack powerUps;
    public static int numShields = 0;
    public int score;
    private int scoreAux;
    public boolean shield;
    public static boolean fightBoss = false;

    public Player (Game game){
        this.game = game;
        this.posX = game.WIDTH/2 - LADOSPRITE/2;
        this.posY = game.HEIGHT - 120;
        this.alive = true;
        this.Lifes = 3;
        this.ammunition = ProjectileTypes.BULLET;
        this.invincibility = false;
        this.shield = false;
        this.dying = false;
        this.timer = 0;
        this.shieldTimer = 0;
        this.score = 0;
        this.scoreAux = 10000;
        this.projectiles = new SimpleLinkedList();
        this.powerUps = new MyStack();
       setSprite(0);
    }

    @Override
    public void update(){
        if(shieldTimer >= 400){
            invincibility = false;
            shield = false;
            shieldTimer = 0;
            setSprite(0);
        }
        if (shield){
            shieldTimer++;
        }if (this.score - this.scoreAux>0){
            this.Lifes++;
            this.scoreAux += 10000;
        }
        move();
        moveProjectile();
    }

    @Override
    public  void move(){
        if (this.dying){
            if (timer == 30){
                reSpawn();
            }else{
                timer++;
            }
        }

        if (fightBoss){
            if (collisionBoss()){
                blowup();
            }
        } else {
            if (collision() && !invincibility) {
                blowup();
            }
        }
        if (W){
            movilidadY = -3;
        }else if (S) {
            movilidadY = 3;
        }else {
            movilidadY = 0;
        }

        if (A){
            movilidadX = -3;
        }else if (D){
            movilidadX = 3;
        }else{
            movilidadX = 0;
        }
        if (posX + movilidadX > 0 && posX + movilidadX < game.getWidth()-LADOSPRITE)
            posX += movilidadX;
        if (posY + movilidadY > 0 && posY + movilidadY <game.getHeight()-LADOSPRITE){
            posY +=movilidadY;
        }
    }

    private boolean collisionBoss() {
        boolean aux = false;
        Node <Aircraft.Boss> current = game.screenQueue.getHead();
        if (!this.getBounds().intersects(current.getObject().getBounds())) {
                while (current != null) {
                    if (current.getObject().projectiles != null && current.getObject().projectiles2 != null) {
                        Node<Projectile> projectileNode1 = current.getObject().projectiles.getHead();
                        Node<Projectile> projectileNode2 = current.getObject().projectiles2.getHead();
                        while (projectileNode1 != null && projectileNode2 != null) {
                            if (projectileNode1.getObject().getBounds().intersects(getBounds())) {
                                projectileNode1.getObject().destroy();
                                if (!this.invincibility) {
                                    aux = true;
                                }
                                break;
                            }

                            if (projectileNode2.getObject().getBounds().intersects(getBounds())) {
                                projectileNode2.getObject().destroy();
                                if (!this.invincibility) {
                                    aux = true;
                                }
                                break;
                            }
                            projectileNode1 = projectileNode1.getNext();
                            projectileNode2 = projectileNode2.getNext();
                        }
                    }
                    current = current.getNext();
                }
        } else {
            for (int i = 0; i <= Lifes; i++) {
                this.blowup();
            }
            System.out.println("Nice try smartass");
        }
        return aux;
    }

    @Override
    public void moveProjectile(){
        int index = 0;
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().moverProyectilJugador();
                if (current.getObject().alive){
                    index++;
                }else{
                    projectiles.removeInPosition(index);
                }
                current = current.getNext();
            }
        }
    }

    @Override
    public boolean collision() {
        boolean aux = false;
        Node <Enemy> current = game.screenQueue.getHead();
        while (current!=null) {
            if (current.getObject().projectiles != null) {
                Node<Projectile> currentP = current.getObject().projectiles.getHead();
                while (currentP != null) {
                    if (currentP.getObject().getBounds().intersects(getBounds())) {
                        currentP.getObject().destroy();
                        aux = true;
                        break;
                    }
                    currentP = currentP.getNext();
                }
                if (current.getObject().getBounds().intersects(getBounds())) {
                    if (!current.getObject().dying){
                        if (current.getObject().isPowerUp && !current.getObject().isEvil){
                            if (current.getObject().powerUp.getClass() == Shield.class){
                                if (numShields < 5) {
                                    powerUps.push(current.getObject().powerUp);
                                    numShields++;
                                }
                                current.getObject().destroy();
                            }else{
                                current.getObject().powerUp.Use();
                                current.getObject().destroy();
                            }
                        }else{
                            current.getObject().blowup();
                            aux = true;
                        }
                    }
                }
            }
            current = current.getNext();
        }
        return aux;
    }

    @Override
    public void paintProjectiles(Graphics2D g){
        if (projectiles != null){
            Node <Projectile> current = projectiles.getHead();
            while (current != null){
                current.getObject().paint(g);
                current = current.getNext();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (alive) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                W = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                S = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                A = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                D = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                shoot();
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (powerUps.getHead() != null) {
                    Node<PowerUp> current = powerUps.getHead();
                    current.getObject().Use();
                    shieldTimer = 0;
                    Sounds.SHIELD.play();
                    powerUps.removeFirst();
                    numShields--;
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (alive) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                W = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                S = true;
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                A = true;
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                D = true;
            }
        }
    }

    @Override
    public void shoot() {
        try {
            projectiles.addFirst(ProjectileFactory.getProjectile(ammunition,game,this,posX+22,posY-25,0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ammunition == ProjectileTypes.BULLET){
            Sounds.BULLET.play();
        } else if (ammunition == ProjectileTypes.MISSILE){
            Sounds.MISSILE.play();
        } else if (ammunition == ProjectileTypes.LASER){
            Sounds.LASER.play();
        }
    }

    private void blowup(){
        dying = true;
        setSprite(1);
        Sounds.EXPLOSION.play();
        if (Lifes > 0){
            this.Lifes--;
            this.invincibility = true;
        }else{
            destroy();
            game.State = GameStates.GAMEOVER;
            Sounds.BACKGROUND.stop();
            Sounds.GAMEOVER.play();
        }
        numShields = 0;
    }

    private void reSpawn(){
        this.resistance = 2;
        this.dying = false;
        this.invincibility = false;
        this.timer = 0;
        this.powerUps = new MyStack();
        this.projectiles = new SimpleLinkedList();
        this.ammunition = ProjectileTypes.BULLET;
        setSprite(0);
    }

    private void setSprite(int type){
        if (type == 0){
            try {
                this.sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/player.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                this.sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/explosion.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getScore() {
        return score;
    }
}
