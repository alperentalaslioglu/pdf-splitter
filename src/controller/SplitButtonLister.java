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
import main.PdfSplitter;
import model.SplitterModel;

public class SplitButtonLister implements EventHandler<ActionEvent> {
	private TextField fromPageNumber;
	private TextField toPageNumber;

	public SplitButtonLister(TextField fromPageNumber, TextField toPageNumber) {
		this.fromPageNumber = fromPageNumber;
		this.toPageNumber = toPageNumber;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if (fromPageNumber.getText().isEmpty() || toPageNumber.getText().isEmpty()) {
			showAlert("Error", "Empty Number Fields", "Please enter start and end number fields.");
		} else if (Integer.parseInt(fromPageNumber.getText().trim())
				- Integer.parseInt(toPageNumber.getText().trim()) > 0) {

			showAlert("Error", "Invalid Numbers", "Please enter valid inputs.");

		} else {

			int start = Integer.parseInt(fromPageNumber.getText().trim());
			int end = Integer.parseInt(toPageNumber.getText().trim());

			// Create a writer for the outputstream
			Document document = new Document();

			String outputPath = PdfSplitter.model.getFilePath() + "\\splitted.pdf";
			FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(outputPath);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			PdfWriter writer = null;
			try {
				writer = PdfWriter.getInstance(document, outputStream);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			try {
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void showAlert(String title, String header, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.show();
	}

}
