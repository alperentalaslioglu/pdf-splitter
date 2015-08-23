package view;

import controller.SplitButtonListener;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import model.IntegerTextField;
import controller.*;

public class SplitterView extends Application {
	private Text chosenPDFName;
	private String title = "PDF Splitter";
	public static Stage primaryStage;
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle(title);
		
		// Window label
		Label label = new Label("Select PDF File");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
		HBox labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);
		
		// Input File Name
		chosenPDFName = new Text();
		chosenPDFName.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
		chosenPDFName.setFill(Color.FIREBRICK);
						
		// From Page
		TextField fromPageNumber = new IntegerTextField();
		fromPageNumber.setPromptText("Start");
		fromPageNumber.setMaxWidth(50);
		
		// End Page
		TextField toPageNumber = new IntegerTextField();
		toPageNumber.setPromptText("End");
		toPageNumber.setMaxWidth(50);
		
		// Split Button
		Button splitButton = new Button("Split!");
		
		// Horizontal Box
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(25, 25, 25, 0));
		hbox.setMargin(fromPageNumber, new Insets(0, 5, 0, 0));
		hbox.setMargin(toPageNumber, new Insets(0, 5, 0, 0));
		hbox.getChildren().addAll(fromPageNumber,toPageNumber,splitButton);
		hbox.setVisible(false);
		splitButton.setOnAction(new SplitButtonListener(fromPageNumber,toPageNumber));

		// Pdf Chooser Button
		Button pdfChooseButton = new Button("Choose a pdf");
		pdfChooseButton.setOnAction(new FileChooserButtonListener(chosenPDFName,hbox));	
		
		// Vertical box
		VBox vbox = new VBox(30);
		vbox.setPadding(new Insets(25, 25, 25, 25));
		vbox.getChildren().addAll(labelHb,pdfChooseButton,chosenPDFName,hbox);
		
		// Scene
		Scene scene = new Scene(vbox, 500, 400); // width x height
		primaryStage.setScene(scene);
		primaryStage.show();
			
	}
	
}
