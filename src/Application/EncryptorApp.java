/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import enigma.Enigma;
import static enigma.Enigma.screen;
import java.util.Scanner;

/**
 *
 * @author dhaffner
 */
public class EncryptorApp {
    
    
    
    public static void main(String[] args) {
        
        String choice;
        
        
        while (true) {
            // úvodní výběr z metod
            System.out.println("Welcome in Encryptor ...");
            System.out.println("Choose the encrypting method:");
            System.out.println("   1 - Enigma");
            System.out.println("   (the rest of methods are under construction yet)");

            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("Insert your choice: ");
            choice = sc.nextLine(); 

            if ("1".equals(choice)) {
                // spustí kódování Enigma
                screen = new Enigma();
                screen.show();

                break;
            } 

            System.out.println("Invalid choice, try again, please ...");
            System.out.println();
        }    
    }
    
}
