package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.pdf.PdfReader;


public class SplitterModel {
	private PdfReader inputPDF;
	
	/**
	 * Reading pdf file and convert this pdf into a splitted page list
	 * @param selectedFile
	 * @throws IOException
	 */
	public SplitterModel(File selectedFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(selectedFile.getAbsolutePath());
		inputPDF = new PdfReader(inputStream);
		inputPDF.unethicalreading = true;	
	}
	
	public PdfReader getPDFInput(){
		return inputPDF;
	}

	
}
