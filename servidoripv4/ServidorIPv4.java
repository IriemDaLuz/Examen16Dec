package servidoripv4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorIPv4 {

    public static void main(String[] args) {
        int port = 54321;

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor para cálculo de direcciones IPv4 iniciado en el puerto " + port + ".");

            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);

                String message;
                while (true) {
                    String ip1 = input.readLine();
                    System.out.println("Se ha recibido como primera dirección IPv4 la: " + ip1);

                    if (ip1.equals("0.0.0.0/0")) {
                        System.out.println("Fin de la conexión.");
                        break;
                    }

                    String ip2 = input.readLine();
                    System.out.println("Se ha recibido como segunda dirección IPv4 la: " + ip2);

                    boolean mismaRed = verificarMismaRed(ip1, ip2);

                    if (mismaRed == true) {
                        System.out.println("Las dos direcciones pertenecen a la misma red.");
                    } else {
                        System.out.println("Las dos direcciones NO pertenecen a la misma red.");
                    }
                }

                client.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean verificarMismaRed(String ip1, String ip2) {
    boolean mismaRed = false;
    String[] partesIp1 = ip1.split("/");
    String[] partesIp2 = ip2.split("/");

    String direccion1 = partesIp1[0];
    int mascara1 = Integer.parseInt(partesIp1[1]);

    String direccion2 = partesIp2[0];
    int mascara2 = Integer.parseInt(partesIp2[1]);

    if (direccion1.equals(direccion2)) {
        mismaRed = true;
    } else {
        mismaRed = false;
    }

    return mismaRed;
    }
}
