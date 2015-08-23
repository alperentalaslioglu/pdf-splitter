package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommonMethods {
	
	public static void showAlert(String title, String header, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.show();
	}

}
