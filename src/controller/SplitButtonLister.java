package controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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



