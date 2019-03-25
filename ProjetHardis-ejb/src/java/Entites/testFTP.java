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
    
    
  public void upload(String lien){
      FTPClient client = new FTPClient();
 
  FileInputStream fis = null;
  
 
  try {
 
 
client.connect("cpanel.freehosting.com");
 
 
client.login("lucialei", "rj3fTOw378");
  
String s = System.getProperty("user.name"); 

 String lienComplet =  "C:\\Users\\"+s+"\\Documents\\"+lien;
 
 
 
String filename = lienComplet;
 
 
fis = new FileInputStream(filename);
  
 
 
// Store file on server and logout
 

boolean b = client.storeFile("/public_html/"+lien, fis);

if (b)
{
    File file = new File(lienComplet);
  //  file.delete();
}
 
 
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
       String user = "lucialei";
       String pass = "rj3fTOw378";
        String remoteFile = "/public_ftp/conditions.docx";
  String lien ="ftp://"+user+":"+pass+"@"+server+remoteFile;  
       
        FTPClient ftpClient = new FTPClient();
        try {
 
                  
            
            ftpClient.connect(server, 21);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "/public_html/conditions.docx";
            File downloadFile1 = new File("C:\\Users\\pedago\\Desktop\\conditions.docx");
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
