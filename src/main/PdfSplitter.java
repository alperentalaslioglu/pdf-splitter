package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import model.SplitterModel;

public class PdfSplitter {
	public static SplitterModel model;

	public static void main(String[] args) throws IOException {
		
		
		
		
		 try {
			 PdfSplitter.splitPDF(new FileInputStream("t1.pdf"), 
		                    new FileOutputStream("output1.pdf"), 1, 12);
			
		         
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
		
		
		
		
		

		// SplitterView.launch(SplitterView.class); // Launch the JavaFX
		// application

		/*
		 * 
		 * 
		 * PDDocument document = PDDocument.load(new File("test.pdf"));
		 * 
		 * Splitter splitter = new Splitter();
		 * 
		 * 
		 * // We need this as split method returns a list List<PDDocument>
		 * listOfSplitPages;
		 * 
		 * // We are receiving the split pages as a list of PDFs
		 * listOfSplitPages = splitter.split(document);
		 * 
		 * // We need an iterator to iterate through them Iterator<PDDocument>
		 * iterator = listOfSplitPages.listIterator();
		 * 
		 * // I am using variable i to denote page numbers. int i = 1;
		 * while(iterator.hasNext()){ PDDocument pd = iterator.next(); try{ //
		 * Saving each page with its assumed page no. pd.save(
		 * "C:\\Users\\Alperen\\Desktop\\Yeniklasör(2)\\" + i++ + ".pdf"); }
		 * catch (COSVisitorException anException){ // Something went wrong with
		 * a PDF object System.out.println("Something went wrong with page " +
		 * (i-1) + "\n Here is the error message" + anException); } }
		 * 
		 * 
		 */
	}

	public static void splitPDF(InputStream inputStream, OutputStream outputStream, int fromPage, int toPage) {
		Document document = new Document();
		try {
			PdfReader inputPDF = new PdfReader(inputStream);
			PdfReader.unethicalreading = true;

			int totalPages = inputPDF.getNumberOfPages();

			// make fromPage equals to toPage if it is greater
			if (fromPage > toPage) {
				fromPage = toPage;
			}
			if (toPage > totalPages) {
				toPage = totalPages;
			}

			// Create a writer for the outputstream
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);

			document.open();
			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
			PdfImportedPage page;

			while (fromPage <= toPage) {
				document.newPage();
				page = writer.getImportedPage(inputPDF, fromPage);
				cb.addTemplate(page, 0, 0);
				fromPage++;
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document.isOpen())
				document.close();
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
