
package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        String adresseIP = "localhost";
        System.out.println("Client en cours de connexion...");
        Socket socket = new Socket(adresseIP, 12344);
        System.out.println("Client connecte");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
                System.out.println("\n--- CALCULATRICE ---");
                System.out.println("1. Addition : x + 5");
                System.out.println("2. Soustraction : x - 5");
                System.out.println("3. Multiplication : x * 5");
                System.out.println("4. Division : x / 5");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
                int x = 0;
                if (choix >= 1 && choix <= 4) {
                    System.out.print("Saisir x : ");
                    x = scanner.nextInt();
                }
                out.writeInt(choix);
                out.writeInt(x);
                out.flush();
                if (choix >= 1 && choix <= 4) {
                    double resultat = in.readDouble();
                    System.out.println("Resultat = " + resultat);
                }
                else if (choix < 0 || choix > 4) {
                    System.out.println("Choix invalide");
                }

        } while (choix != 0);
        in.close();
        out.close();
        socket.close();
        System.out.println("Client deconnecte");
    }
}

