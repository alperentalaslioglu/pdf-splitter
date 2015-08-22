package controller;
import java.io.IOException;
import java.util.Iterator;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.PdfSplitter;
import javafx.scene.control.TextField;

public class SplitButtonLister implements EventHandler<ActionEvent> {
	private TextField fromPageNumber; 
	private TextField toPageNumber;

	public SplitButtonLister(TextField fromPageNumber, TextField toPageNumber) {
		this.fromPageNumber = fromPageNumber;
		this.toPageNumber = toPageNumber;	
	}

	@Override
	public void handle(ActionEvent arg0) {
		if( fromPageNumber.getText().isEmpty() || toPageNumber.getText().isEmpty() ){
			showAlert("Error","Empty Number Fields","Please enter start and end number fields.");
		}else if(
				Integer.parseInt(fromPageNumber.getText().trim()) 
				- 
				Integer.parseInt(toPageNumber.getText().trim())
				> 0
				){
			
			showAlert("Error","Invalid Numbers","Please enter valid inputs.");
			
		}else{
			
			int start = Integer.parseInt(fromPageNumber.getText().trim());
			int end = Integer.parseInt(toPageNumber.getText().trim());
			
			String path =  PdfSplitter.model.getFilePath();
			
			Iterator<PDDocument> iterator = PdfSplitter.model.getDocument().listIterator();
			
			// I am using variable i to denote page numbers. 
	        int i = 1;
	        PDDocument pd = null;
	        while(iterator.hasNext()){
	            pd = iterator.next();
	            
	          
	               
	                   
	        } 
	        
	        try {
				pd.save("path" + i++ + ".pdf");
			} catch (COSVisitorException | IOException e) {
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



