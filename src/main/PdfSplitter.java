package main;

import java.io.IOException;
import model.SplitterModel;
import view.SplitterView;

public class PdfSplitter {
	public static SplitterModel model;

	public static void main(String[] args) throws IOException {

		SplitterView.launch(SplitterView.class); // Launch the JavaFX
													// application

	}

}
