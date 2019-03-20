/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.awt.Desktop;
import java.io.File;
/**
 *
 * @author 6170361
 */
public class testPDF {
    public void test()
    {
        try {

		File pdfFile = new File("C:\\Users\\6170361\\Documents\\GitHub\\ProjetHardis\\ProjetHardis-war\\Documents\\Conditions\\cond.pdf");
		if (pdfFile.exists()) {

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}

		System.out.println("Done");

	  } catch (Exception ex) {
		ex.printStackTrace();
	  }

    }
}
