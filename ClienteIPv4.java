package clienteipv4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteIPv4{
    public static void main(String[] args) {
        String host = "localhost";
        int port = 54321; 

        try {
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor" + host + "en el puerto" + port);

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            
            
            while(true) {
                System.out.println("Mandame la primera dirección IPv4:");
                String ip1 = userInput.readLine();
                output.println(ip1);

                if (ip1.equals("0.0.0.0/0")) {
                    System.out.println("Fin de la conexión.");
                    break;
                }

                System.out.println("Mandame la segunda dirección IPv4:");
                String ip2 = userInput.readLine();
                output.println(ip2);


                String respuesta = input.readLine();
                System.out.println(respuesta);

            }
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}