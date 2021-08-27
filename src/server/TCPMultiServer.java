package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TCPMultiServer {
	boolean listening = true;
	List<TCPServerThread> hilosClientes;

    public void ejecutar() throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 4444.");
            System.exit(1);
        }
        System.out.println("Escuchando puerto 4444.");

        while (listening) {
        	
        	TCPServerThread hilo = new TCPServerThread(serverSocket.accept(), this);
            hilosClientes.add(hilo);
            hilo.start();
        }

        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
    	
    	TCPMultiServer tms = new TCPMultiServer();
    	tms.hilosClientes = new ArrayList<>();
    	tms.ejecutar();
    }
}