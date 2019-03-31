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
import com.itextpdf.text.Phrase;
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
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author 6170361
 */
public class testPDF {

    public void test(Facture f) throws FileNotFoundException {

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            //Read file using PdfReader

            String s = System.getProperty("user.name");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\" + s + "\\Documents\\FACT" + f.getId() + ".pdf"));

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
            p.add(f.getDevis().getClient().getEntreprise().getAdresseFact().getCodePostal().toString());
            document.add(p);

            PdfPTable table = new PdfPTable(5); // 3 columns.

            PdfPCell cell1 = new PdfPCell(new Phrase("Consultant"));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell1);

            PdfPCell cel = new PdfPCell(new Phrase("Quantité"));
            cel.setBorderColor(BaseColor.BLUE);
            cel.setPaddingLeft(10);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cel);

            PdfPCell cell = new PdfPCell(new Phrase("Unité"));
            cell.setBorderColor(BaseColor.BLUE);
            cell.setPaddingLeft(10);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell);

            PdfPCell cellll = new PdfPCell(new Phrase("PrixHT/jour"));
            cellll.setBorderColor(BaseColor.BLUE);
            cellll.setPaddingLeft(10);
            cellll.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellll.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cellll);

            document.add(table);

            document.close();

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
