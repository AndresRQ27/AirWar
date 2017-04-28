package Server;


import Player.Player;


import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.KeyEvent;



/**
 * Created by pedro on 25/4/2017.
 */
public class Server extends Thread{

    private final static int PORT = 1334;
    Player jugador;
    Robot robot;

    public Server(String msg,Player jugador) {
        super(msg);
        this.jugador = jugador;
        try {
            robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            //Socket de servidor para esperar peticiones de la red
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor> Servidor iniciado"+ Inet4Address.getLocalHost().getHostAddress());
            System.out.println("Servidor> En espera de cliente...");
            //Socket de cliente
            Socket clientSocket;
            while(true){
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                //se lee peticion del cliente
                String request = input.readLine();
                System.out.println("Cliente> petición [" + request +  "]");
                //se procesa la peticion y se espera resultado
                String strOutput = process(request);
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(strOutput);
                //cierra conexion
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * procesa peticion del cliente y retorna resultado
     * @param request peticion del cliente
     * @return String
     */
    public String process(String request){
        String result;
        if(request!=null)switch (request) {
            case "Hola":
                result = "Conectado";
                break;
            case "Up":
                robot.keyPress(KeyEvent.VK_W);
                sleep(100);
                robot.keyRelease(KeyEvent.VK_W);
                break;
            case "Down":
                robot.keyPress(KeyEvent.VK_S);
                sleep(100);
                robot.keyRelease(KeyEvent.VK_S);
                break;
            case "Left":
                robot.keyPress(KeyEvent.VK_A);
                sleep(100);
                robot.keyRelease(KeyEvent.VK_A);
                break;
            case "Right":
                robot.keyPress(KeyEvent.VK_D);
                sleep(100);
                robot.keyRelease(KeyEvent.VK_D);
                break;
            case "Shoot":
                robot.keyRelease(KeyEvent.VK_J);
                break;
            case "Power":
                robot.keyRelease(KeyEvent.VK_SPACE);
                break;
        }
        return String.valueOf(this.jugador.getScore());
    }

    private void sleep(int s){
        try {
            Thread.sleep(s);
        }catch (InterruptedException e){

        }
    }

}
