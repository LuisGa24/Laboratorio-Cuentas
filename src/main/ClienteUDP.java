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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private void ejecutarCliente(String o) throws ParseException {

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
                
                String[] paqR = mensaje2.split("/");
                
                int nC= Integer.parseInt(paqR[0]);
                
                for (int i = 1; i <= nC; i++) {
                    System.out.println(paqR[i]);
                    String cuenta[] = paqR[i].split("*");
                    if(cuenta[0].equals("AHORROS")){
                        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = format.parse(cuenta[3]);
//                        SavingsAccount sa=new SavingsAccount(cuenta[2].charAt(0), date, Float.parseFloat(cuenta[6]), mensaje, i);
                    }
                }
            
            
        } catch (IOException excepcionES) {
        }




    }

    /**
     * @param args the command line arguments
     */
    public static Object Cliente(String s) throws ParseException {
        Object ret=null;
        ClienteUDP aplicacion=null;
       
            aplicacion = new ClienteUDP();
        
        aplicacion.ejecutarCliente(s); 
        return ret;
    }

    
}
