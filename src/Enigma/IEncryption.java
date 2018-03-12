/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enigma;

/**
 *
 * @author dhaffner
 */
public interface IEncryption {
    
    public String encrypt();  // metodu jsem oproti zadání raději nazval anglicky
    public String decrypt();  // metodu jsem oproti zadání raději nazval anglicky
    
    // kvůli předělání na komunikaci Client-Server vypadly metody na ukládání a načítání z/do souboru
}
