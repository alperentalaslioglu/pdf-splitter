package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import main.CommonMethods;
import main.PdfSplitter;
import model.SplitterModel;

public class SplitButtonListener implements EventHandler<ActionEvent> {
	private TextField fromPageNumber;
	private TextField toPageNumber;

	public SplitButtonListener(TextField fromPageNumber, TextField toPageNumber) {
		this.fromPageNumber = fromPageNumber;
		this.toPageNumber = toPageNumber;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if (fromPageNumber.getText().isEmpty() || toPageNumber.getText().isEmpty()) {
			CommonMethods.showAlert("Error", "Empty Number Fields", "Please enter start and end number fields.");
		} else if (
				Integer.parseInt(fromPageNumber.getText().trim())
				- 
				Integer.parseInt(toPageNumber.getText().trim()) 
				> 0
				) {// if( start < end )

			CommonMethods.showAlert("Error", "Invalid Numbers", "Please enter valid inputs.");

		} else {

			int start = Integer.parseInt(fromPageNumber.getText().trim());
			int end = Integer.parseInt(toPageNumber.getText().trim());

			Document document = new Document();
			FileOutputStream outputStream = createOutputStream();
			outputSplittedPdf(start, end, document, outputStream);
			CommonMethods.showAlert("Success!", "Splitted!", "PDF successfully splitted!. Look your absolute path!");
		}

	}

	public FileOutputStream createOutputStream() {
		String outputPath = PdfSplitter.model.getFilePath() + "\\splitted.pdf";
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(outputPath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			CommonMethods.showAlert("Error!", "Not Splitted!", "PDF was not splitted!");
		}
		return outputStream;
	}

	public void outputSplittedPdf(int start, int end, Document document, FileOutputStream outputStream) {
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, outputStream);
		} catch (DocumentException e) {
			e.printStackTrace();
			CommonMethods.showAlert("Error!", "Not Splitted!", "PDF was not splitted!");
		}

		document.open();
		PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
		PdfImportedPage page;
		PdfReader inputPDF = PdfSplitter.model.getPDFInput();


		while (start <= end) {
			document.newPage();
			page = writer.getImportedPage(inputPDF, start);
			cb.addTemplate(page, 0, 0);
			start++;
		}
		
		closeFileOutput(document, outputStream);
	}

	public void closeFileOutput(Document document, FileOutputStream outputStream) {
		try {
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
