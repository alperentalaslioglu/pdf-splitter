package model;

/**
 * 
 * This is an integer textfield implementation
 * The textfield only accept integer numbers
 * 
 */

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import main.CommonMethods;

public class IntegerTextField extends TextField {

	public IntegerTextField() {
		this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent t) {
				char enteredChar = t.getCharacter().charAt(0);
				if (!(enteredChar >= '0' && enteredChar <= '9')) {
					CommonMethods.showAlert("Illegal Argument", "Wrong input type", "Please enter number!");
					clear();
				}
			}
		});
	}
}
