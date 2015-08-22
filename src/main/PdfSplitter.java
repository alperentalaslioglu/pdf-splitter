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
import view.SplitterView;

public class PdfSplitter {
	public static SplitterModel model;

	public static void main(String[] args) throws IOException {
		
		
		/*
		
		 try {
			 PdfSplitter.splitPDF(new FileInputStream("t1.pdf"), 
		                    new FileOutputStream("output1.pdf"), 1, 12);
			
		         
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		
		*/
		
		

		 SplitterView.launch(SplitterView.class); // Launch the JavaFX application

		
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
