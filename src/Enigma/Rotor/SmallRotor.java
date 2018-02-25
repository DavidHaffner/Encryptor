/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enigma.Rotor;


public class SmallRotor extends Rotor {
    
    @Override
    public void setAlphabet()
    {
    int i = 0; 

    rotor[i] = ' ';
    i++;

    rotor[i] = 'E';
    i++;

    rotor[i] = 'J';
    i++;

    rotor[i] = 'O';
    i++;

    rotor[i] = 'T';
    i++;

    rotor[i] = 'Y';
    i++;

    rotor[i] = 'C';
    i++;

    rotor[i] = 'H';
    i++;

    rotor[i] = 'M';
    i++;

    rotor[i] = 'R';
    i++;

    rotor[i] = 'W';
    i++;

    rotor[i] = 'A';
    i++;

    rotor[i] = 'F';
    i++;

    rotor[i] = 'K';
    i++;

    rotor[i] = 'P';
    i++;

    rotor[i] = 'U';
    i++;

    rotor[i] = 'Z';
    i++;

    rotor[i] = 'D';
    i++;

    rotor[i] = 'I';
    i++;

    rotor[i] = 'N';
    i++;

    rotor[i] = 'S';
    i++;

    rotor[i] = 'X';
    i++;

    rotor[i] = 'B';
    i++;

    rotor[i] = 'G';
    i++;

    rotor[i] = 'L';
    i++;

    rotor[i] = 'Q';
    i++;

    rotor[i] = 'V';

    System.out.println("Finished Initialising Med Rotor  i="+i);
    }

}