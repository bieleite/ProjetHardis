/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 6170361
 */
public class testPDF {
    public void test(String dest, List<List<String>> lignes) throws FileNotFoundException
    {
  

       com.itextpdf.text.Document document = new com.itextpdf.text.Document();
       try{
    //Read file using PdfReader
  

    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\anastasia.salari\\Documents\\conditio.pdf"));
         document.open();
         Paragraph p = new Paragraph();
         
        
           Paragraph p1 = new Paragraph();

      
         
        
       p1.setSpacingAfter(10);
       p1.setSpacingBefore(50);
       p1.setAlignment(10);
       p1.add("Hardis group");
               document.add(p1);
       
        p.setSpacingAfter(10);
       p.setSpacingBefore(50);
       p.setAlignment(2);
       p.setIndentationLeft(3);
       p.add(dest);
       document.add(p);
        
        
    
        PdfPTable table = new PdfPTable(5); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table
 
        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f};
        table.setWidths(columnWidths);
        
        for (List<String> list : lignes){
            table = new PdfPTable(5);
            for (String s : list){
 
        PdfPCell cell1 = new PdfPCell(new Paragraph(s));
        cell1.setBorderColor(BaseColor.BLUE);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        table.addCell(cell1);

            }
        
        }
 
        document.add(table);
 
        document.close();

         writer.close();
    
 
  } catch (IOException e) {
    e.printStackTrace();
  }
       catch (DocumentException e) {
    e.printStackTrace();
  }
    }
}
