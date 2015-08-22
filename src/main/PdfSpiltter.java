package main;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.apache.pdfbox.util.Splitter;

public class PdfSpiltter {

	public static void main(String[] args) throws IOException {

		PDDocument document = PDDocument.load(new File("test.pdf"));

        Splitter splitter = new Splitter();

		
     // We need this as split method returns a list
        List<PDDocument> listOfSplitPages;
        
        // We are receiving the split pages as a list of PDFs
        listOfSplitPages = splitter.split(document);
        
        // We need an iterator to iterate through them
        Iterator<PDDocument> iterator = listOfSplitPages.listIterator();
        
        // I am using variable i to denote page numbers. 
        int i = 1;
        while(iterator.hasNext()){
            PDDocument pd = iterator.next();
           try{
                // Saving each page with its assumed page no.
                pd.save("C:\\Users\\Alperen\\Desktop\\Yeniklasör(2)\\" + i++ + ".pdf");
            } catch (COSVisitorException anException){
                // Something went wrong with a PDF object
                System.out.println("Something went wrong with page " + (i-1) + "\n Here is the error message" + anException);                
            }            
        }        
		

	}

}
