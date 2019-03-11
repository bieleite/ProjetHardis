/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;
import javax.xml.bind.DatatypeConverter;
/**
 *
 * @author gabrielleite
 */
public class Helpers {
     public static String sha1(String input) throws UnsupportedEncodingException {
    String sha1 = null;
    try {
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
        sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
    }    catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    return sha1;
}
    public static void main(String [] args) throws UnsupportedEncodingException{
        /*Pour Madame Talens, le mdp est password*/
        System.out.println(sha1("password").equalsIgnoreCase("5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8")); /*Test password crypted*/
    }
    
}
