/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;




/**
 *
 * @author 6170361
 */
public class testFTP {
    
    
  public void upload(){
      FTPClient client = new FTPClient();
 
  FileInputStream fis = null;
  
 
  try {
 
 
client.connect("cpanel.freehosting.com");
 
 
client.login("lucialei", "rj3fTOw378");
  
 
 
String filename = "C:\\Users\\6170361\\Desktop\\test.txt";
 
 
fis = new FileInputStream(filename);
  
 
 
// Store file on server and logout
 
 
client.storeFile(filename, fis);
 
 
client.logout();
 
 
 
 
  } catch (IOException e) {
 
 
e.printStackTrace();
 
  } finally {
 
 
try {
 
 
    if (fis != null) {
 
 
 
  fis.close();
 
 
    }
 
 
    client.disconnect();
 
 
} catch (IOException e) {
 
 
    e.printStackTrace();
 
 
}
 
  }}
    
    public void downloadFTP()
    {

        
                
    String server = "cpanel.freehosting.com";
     int port = 21;
       String user = "lucialei";
       String pass = "rj3fTOw378";

       
        FTPClient ftpClient = new FTPClient();
        try {
 
                  
            
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "test.txt";
            File downloadFile1 = new File("C:\\Users\\6170361\\Documents\\test.txt");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }
            
             } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
