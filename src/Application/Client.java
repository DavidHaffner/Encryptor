/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.net.Socket;
import java.net.InetSocketAddress;

public class Client extends ParseMessage {

    Socket sock;

    Client(String host, int port) {
        try {
            // nastavení socketu                                        // 1. ř.31: out.flush() -> účel?
            sock = new Socket();                                        // 2. proč to správně nekomunikuje se Serverem? když na serveru více \n, bude fungovat? jinak ošetřit?
            sock.connect(new InetSocketAddress(host, port));            // 3. kdyby socket byl na jiný server, tak do host -> IP adresa?
            out = sock.getOutputStream();                               // 4. zadání na ten další úkol (řazení/streamy)?
            in = sock.getInputStream();

            // vlastní průběh komunikace
            // podle šablony z ClientHandleru zadá 1/E/skakal pes pres oves + ukončí spojení
            System.out.println(read("", "\n"));
            write("1/E/skakal pes pres oves\n");

            System.out.println(read("", "\n"));
            write("E\n");

            out.flush();
            sock.shutdownOutput();
            sock.close();
            System.out.println("Communication END");
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client c = new Client(
                args.length > 0 ? args[0] : "localhost",
                args.length > 1 ? Integer.parseInt(args[1]) : 8081
        );
    }
}
