
package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur en attente d'un client...");
            Socket socket = serverSocket.accept();
            System.out.println("Un client est connecte");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {

                // Recevoir le choix et la valeur
                int choix = in.readInt();
                int x = in.readInt();

                if (choix == 0) {
                    break;
                }
                double resultat;
                switch (choix) {
                    case 1:
                        resultat = x + 5;
                        break;

                    case 2:
                        resultat = x - 5;
                        break;

                    case 3:
                        resultat = x * 5;
                        break;

                    case 4:
                        resultat = (double) x / 5;
                        break;

                    default:
                        out.writeUTF("Choix invalide");
                        out.flush();
                        continue;
                }
                out.writeDouble(resultat);
                out.flush();
                System.out.println("Resultat = " + resultat);
            }
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
            System.out.println("Connexion terminee");
    }
}

