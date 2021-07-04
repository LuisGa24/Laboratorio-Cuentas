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
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ClienteUDP{
    private DatagramSocket socket;
    private static int n = 7000;

    private ClienteUDP() {

        try { // crear objeto DatagramSocket para enviar y recibir paquetes
            
            socket = new DatagramSocket(n++); // Al no especificar un puerto. el S.O.le
            // asigna uno...

        } catch (SocketException excepcionSocket) {
//            System.exit(1);
        }

    }

    private void ejecutarCliente(String o) {

        try {

            String mensaje = o;
           

            
            byte[] datos = mensaje.getBytes();

//            SavingsAccount mensaje = new SavingsAccount('c', null, 0, "cfr", 0);
//
//            
//            byte[] datos = mensaje.toByteArray();
//            byte[] datos = mensaje.getBytes();
            // crear y enviar paquete

            DatagramPacket enviarPaquete = new DatagramPacket(datos, datos.length,
                    InetAddress.getByName("127.0.0.1"), 6000);

            socket.send(enviarPaquete);
            
            byte[] datos2 = new byte[10000];

                DatagramPacket recibirPaquete = new DatagramPacket(datos2, datos2.length);

                System.out.println("Esperando Conexión...");

                socket.receive(recibirPaquete); //esperar el paquete


//                SavingsAccount mensaje2 = SavingsAccount.fromByteArray(recibirPaquete.getData());
                String mensaje2 = new String(recibirPaquete.getData(), 0, recibirPaquete.getLength());
                
                System.out.println(mensaje2);
            
            
        } catch (IOException excepcionES) {
        }




    }

    /**
     * @param args the command line arguments
     */
    public static Object Cliente(String s) {
        Object ret=null;
        ClienteUDP aplicacion=null;
       
            aplicacion = new ClienteUDP();
        
        aplicacion.ejecutarCliente(s); 
        return ret;
    }

    
}
