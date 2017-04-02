package com.Game;

import com.Jugador.Jugador;
import com.Jugador.Vidas.Vida;
import com.Municiones.Bala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by pedro on 27/3/2017.
 */
public class Game  extends JPanel {
    public Jugador J1;
    Vida v1 = new Vida(this, 5), v2 = new Vida(this,v1.x+v1.width+5),v3 = new Vida(this, v2.x+ v2.width+5);

    public Game(String nombre) {
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setBackground(Color.BLACK);
        setFocusable(true);
        J1 = new Jugador(nombre, this);

    }

    @Override//Proviene de Jpanel, grafica los objetos
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serif",Font.BOLD,20));

        g2d.drawImage(J1.imagen,J1.x, J1.y, this);//Grafica Jugador
        if(J1.resistencia>=3) {
            g2d.drawImage(v1.imagen, v1.x, v1.y, this);
            g2d.drawImage(v2.imagen, v2.x, v2.y, this);//Grafica 3 vida
            g2d.drawImage(v3.imagen, v3.x, v3.y, this);
        }else if(J1.resistencia == 2){
            g2d.drawImage(v1.imagen, v1.x, v1.y, this);//Grafica 2 vidas
            g2d.drawImage(v2.imagen, v2.x, v2.y, this);
        }else if(J1.resistencia == 1){
            g2d.drawImage(v1.imagen, v1.x, v1.y, this);//Grafica 1 vida
        }
        g2d.drawString("Score: "+Integer.toString(J1.puntaje),495,20);//Grafica el puntaje

        /**
         * Si hay balas en el arreglo de municiones del jugador
         * las grafica cuando las dispara
         */
        ArrayList municiones = J1.getMuniciones();
        for (Object mun : municiones){
            Bala m = (Bala) mun;
            g2d.drawImage(m.imagen,m.x,m.y, this);
        }
        //.
    }

    //Se encarga de mover todos los sprites en la pantalla
    public void move(){
        J1.move();

        ArrayList municiones = J1.getMuniciones();
        for (int i = 0; i < municiones.size(); i++) {
            Bala m = (Bala) municiones.get(i);
            if (m.isVis()) {
                m.move();
            } else {
                municiones.remove(i);
            }
        }

    }

    //clase MyKeyListener para escuchar los eventos del teclado
    public class MyKeyListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e)  {
            J1.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            J1.keyReleased(e);
        }
    }

    //Main
    public static void main(String[] args) throws InterruptedException{
        JFrame frame = new JFrame("AirWar");
        Game game = new Game("Pedro");
        frame.add(game);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true){
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
    //.
}