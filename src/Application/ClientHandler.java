/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import enigma.Enigma;
import java.net.Socket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhaffner
 */
class ClientHandler extends ParseMessage implements Runnable {

    Socket sock;
    int id;

    ClientHandler(Socket iSocket, int iID) throws IOException {
        sock = iSocket;
        id = iID;
        out = sock.getOutputStream();
        in = sock.getInputStream();

    }

    public void start() {
        new Thread(this).start();
    }

    void log(String str) {
        System.out.println(str);
    }

    @Override
    public void run() {

        try {
            String choice;
            boolean quit = false;

            while (!quit) {
                // úvodní výběr z metod
                write("Welcome in Encryptor ...\n");
                write("Choose the encrypting method / E(encrypting) or D(decrypting) / the text message\n");
                write("For example: 1/E/skakal pes pres oves\n");
                write("\n");        
                
                write("1-Enigma\n");
                write("(the rest of methods are under construction yet)\n");

                write("\n");
                write("Insert your choice: ");
                choice = read("", "\n");

                String [] parsed = choice.split("/");
                
                if ("1".equals(parsed[0]) && ("E".equals(parsed[1]) || "D".equals(parsed[1])) ) {
                    // spustí kódování či dekódování Enigma
                    Enigma enigma = new Enigma(parsed[2]);
                        if ("E".equals(parsed[1])) {
                            String code = "Encrypted code is: " + enigma.encryptEnigma() +"\n";
                            write(code);
                        }    
                        if ("D".equals(parsed[1])) { 
                            String code = "Decrypted code is: " + enigma.decryptEnigma() +"\n";
                            write(code);
                        }    
                } else {
                    write("Invalid choice ...\n");
                }
                write ("Once again? (E for end): \n");
                choice = read("", "\n");
                
                if ('E' == choice.charAt(0)) {
                    quit = true;
                }
            }
            sock.shutdownOutput();
            sock.close();
        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, "Error in ClientHandler running.", ex);
        }
    }
}
