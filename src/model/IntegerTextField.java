package model;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class IntegerTextField extends TextField {

	public IntegerTextField() {
		this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent t) {
				char enteredChar = t.getCharacter().charAt(0);
				if (!(enteredChar >= '0' && enteredChar <= '9')) {
					showAlert("Illegal Argument", "Wrong input type", "Please enter number!");
				}
			}
		});
	}

	private void showAlert(String title, String header, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.show();
	}

}
