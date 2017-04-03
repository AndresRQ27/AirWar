package com.Jugador;

import com.Game.Game;
import com.Municiones.Bala;
import com.Unidad.Unidad;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by pedro on 27/3/2017.
 */
public class Jugador extends Unidad {
    private String nombre;
    public int Ix, Iy, puntaje, maxNivel, tempJugado;
    private ArrayList municiones;
    //Pila de Power Ups

    //constructor
    public Jugador(String nombre, Game game) {
        this.game = game;
        this.BufferImage("/player.png");
        this.nombre = nombre;
        this.x = 250; this.Ix = x;
        this.y = 200; this.Iy = y;
        this.puntaje= 0;
        this.resistencia = 3;
        this.municiones = new ArrayList();

    }

    @Override
    public void move() {
        if(x + xa > 0 && x + xa < game.getWidth()-width){
            x += xa;
        }else{
            xa = 0;
        }if( y + ya > 0 && y + ya < game.getHeight()-height){
            y += ya;
        }else{
            ya = 0;
        }if(collisionEnemigo()){
            this.resistencia --;
            x= Ix;
            y= Iy;
        }
    }
    //.

    public void fire(){
        municiones.add(new Bala(this.x, this.y, this.game));
    }
    public boolean collisionEnemigo(){
        return false;
    }

    //Eventos del teclado
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
    }
    //.

    //Getters
    public ArrayList getMuniciones() {
        return municiones;
    }
    public int getX() {
        return this.x;
    }
    //.
}