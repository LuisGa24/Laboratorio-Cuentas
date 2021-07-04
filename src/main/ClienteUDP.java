/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Jose
 */

import Domain.SavingsAccount;
import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class ClienteUDP {
    private DatagramSocket socket;

    public ClienteUDP() {

        try { // crear objeto DatagramSocket para enviar y recibir paquetes

            socket = new DatagramSocket(7000); // Al no especificar un puerto. el S.O.le
            // asigna uno...

        } catch (SocketException excepcionSocket) {
            System.exit(1);
        }

    }

    public void ejecutarCliente() {

        try {


            SavingsAccount mensaje = new SavingsAccount('c', null, 0, "cfr", 0);

            
            byte[] datos = mensaje.toByteArray();
//            byte[] datos = mensaje.getBytes();
            // crear y enviar paquete

            DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length,
                    InetAddress.getByName("127.0.0.1"), 6000);

            socket.send(enviarPaquete);
        } catch (IOException excepcionES) {
        }




    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteUDP aplicacion = new ClienteUDP();
        aplicacion.ejecutarCliente();         
    }
}
