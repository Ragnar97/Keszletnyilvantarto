/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FajlKezeles;

/**
 *
 * @author ndavid97
 */
import java.math.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Titkositas {
    
    public String titkositas(String jelszo){
        try {
            MessageDigest algoritmus=MessageDigest.getInstance("SHA-256");
            byte[] feldolgozas=algoritmus.digest(jelszo.getBytes());
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i < feldolgozas.length; i++) {
                sb.append(Integer.toHexString(0xff & feldolgozas[i]));
            }
            
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        
}
}
