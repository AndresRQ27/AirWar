package com.Jugador;

import DataStructures.MyLinkedList.Node;
import DataStructures.MyLinkedList.SimpleLinkedList;
import com.Enemigos.Kamikaze;
import com.Game.Game;
import com.PowerUps.PowerUps;
import com.ProjectileFactory.ProjectileFactory;
import com.Unidad.Unidad;

import java.awt.event.KeyEvent;

/**
 * Clase Jugador, hereda de la clase Unidad
 *@see com.Unidad.Unidad
 * Tiene todos las funciones del jugador
 * @author created by pedro on 27/3/2017.
 */
public class Jugador extends Unidad {
    private String nombre;//nombre del jugador
    public int Ix, Iy, puntaje, maxNivel, tempJugado;//Ix,Iy = posiciones iniciales del jugador; maximo nivel; tiempo jugado
    private SimpleLinkedList municiones;// Lista enlazada que contiene las municiones del jugador
    private SimpleLinkedList powerUps;//Lista enlazada que contiene los power Ups del juego
    public int municion = 1;//tipo de munición 1(Bala) 2(misil) 3(laser)

    /**
     *Constructor Jugador
     * @param nombre, es nombre del jugador
     * @param game, es la clase sobre la cual va a funcionar el jugador
     */
    public Jugador(String nombre, Game game) {
        this.game = game;
        this.BufferImage("/player.png");
        this.loadDimension();
        this.nombre = nombre;
        this.x = 250; this.Ix = x;
        this.y = 200; this.Iy = y;
        this.puntaje= 0;
        this.resistencia = 0;
        this.vida = 3;
        this.powerUps = new SimpleLinkedList();
        this.municiones = new SimpleLinkedList();

    }

    /**
     * Método move()
     * Se fija si el jugador está dentro de los bordes de la pantalla y suma xa y ya en "x" y en "y" respectivamente
     */
    @Override
    public void move() {
        if(x + xa > 0 && x + xa < game.getWidth()-width){
            x += xa;
        }
        if( y + ya > 0 && y + ya < game.getHeight()-height){
            y += ya;
        }
    }

    /**
     * método fire()
     * Se encarga de agregar las municiones de cualquier tipo a la lista municiones
     */
    private void fire(){
        try {
            municiones.addFirst(ProjectileFactory.getProjectile(municion,this.x,this.y,this.game));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muerte()
     * Disminuye la resistencia cada que es golpeado y lo manda donde empezó
     * si la resistencia es menor que 0 se le rebaja una vida
     * @see com.Enemigos.Kamikaze
     */
    public void muerte(){
        this.resistencia --;
        this.x = Ix;
        this.y = Iy;
        if (resistencia<0) {
            this.vida--;
            resistencia = 0;
        }
    }

    /**
     * Método guardarPower()
     * Agrega los poderes obtenidos en el juego a la lista powerUps
     * @param power tipo PowerUps, es el poder que se va agregar
     * @see com.Enemigos.Kamikaze
     */
    public void guardarPower(PowerUps power){
        this.powerUps.addFirst(power);
    }

    /**
     *Método keyReleased()
     * se encarga de manejar los acciones que ocurren cuando se suelta una tecla
     * @param e tipo KeyEvent, es el evento del teclado, identifica de que tecla proviene
     */
    public void  keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            xa = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            xa = 0;
        }

        if (key == KeyEvent.VK_UP) {
            ya = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            ya = 0;
        }
    }

    /**
     * Método keyPressed()
     * se encarga de manejar los acciones que ocurren cuando se apreta una tecla
     * cuando la tecla es v, se utiliza el primer poder que hay en la pila de poderes
     * @param e tipo KeyEvent, es el evento del teclado, identifica de que tecla proviene
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            xa = -3;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            xa =  3;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            ya = -3;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            ya = 3;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            this.fire();
        }
        if(e.getKeyCode() == KeyEvent.VK_V){
            if (powerUps.getHead() != null){
                Node <PowerUps> current = powerUps.getHead();
                current.getObject().UsarPoder();
                powerUps.removeFirst();
                System.out.print(powerUps.getHead());
            }else{
                System.out.print("notiene poderes");
            }
        }if(e.getKeyCode() == KeyEvent.VK_M){
            game.enemigos.addLast(new Kamikaze(game,20,20));
        }

    }

    /**
     * getMuniciones()
     * @return municiones, tipo SimpleLinkedList; la lista enlazada municiones
     */
    public SimpleLinkedList getMuniciones() {
        return municiones;
    }

    /**
     * getX
     * @return
     */
}
