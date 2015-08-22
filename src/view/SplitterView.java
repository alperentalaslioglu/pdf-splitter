package view;

import controller.SplitButtonLister;
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
	private Text originalPDF;
	private Text outputPDF;
	private String title = "PDF Splitter";
	
	public SplitterView() {

	}
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(title);
		
		// Window label
		Label label = new Label("Select PDF File");
		label.setTextFill(Color.DARKBLUE);
		label.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
		HBox labelHb = new HBox();
		labelHb.setAlignment(Pos.CENTER);
		labelHb.getChildren().add(label);
		
		// Input File Name
		originalPDF = new Text();
		originalPDF.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
		originalPDF.setFill(Color.FIREBRICK);
		
		//Output File 
		outputPDF = new Text();
		outputPDF.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
		outputPDF.setFill(Color.FIREBRICK);		
				
		////Split borders
		//From Page
		TextField fromPageNumber = new IntegerTextField();
		fromPageNumber.setPromptText("Start");
		fromPageNumber.setMaxWidth(50);
		
		//End Page
		TextField toPageNumber = new IntegerTextField();
		toPageNumber.setPromptText("End");
		toPageNumber.setMaxWidth(50);

		
		Button btn2 = new Button("Split!");
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(25, 25, 25, 0));
		hbox.setMargin(fromPageNumber, new Insets(0, 5, 0, 0));
		hbox.setMargin(toPageNumber, new Insets(0, 5, 0, 0));
		hbox.getChildren().addAll(fromPageNumber,toPageNumber,btn2);
		hbox.setVisible(false);
		btn2.setOnAction(new SplitButtonLister(fromPageNumber,toPageNumber));

				
		// Pdf Chooser Button
		Button btn1 = new Button("Choose a file");
		btn1.setOnAction(new FileChooserButtonListener(originalPDF,outputPDF,hbox));	
		
		// Vbox
		VBox vbox = new VBox(30);
		vbox.setPadding(new Insets(25, 25, 25, 25));
		vbox.getChildren().addAll(labelHb,btn1,originalPDF,hbox);
		
		
		// Scene
		Scene scene = new Scene(vbox, 500, 400); // w x h
		primaryStage.setScene(scene);
		primaryStage.show();
			
	}
	
}
