package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.Splitter;

public class SplitterModel {
	private List<PDDocument> listOfSplittedPages;
	private File pdfFile;
	
	/**
	 * Reading pdf file and convert this pdf into a splitted page list
	 * @param pdfFile
	 * @throws IOException
	 */
	public SplitterModel(File pdfFile) throws IOException {
		this.pdfFile = pdfFile;
		PDDocument document = PDDocument.load(pdfFile);
		Splitter splitter = new Splitter();
		listOfSplittedPages = splitter.split(document);
	}

	public List<PDDocument> getDocument() {
		return listOfSplittedPages;
	}
	
	public String getFilePath(){
		return pdfFile.getAbsolutePath();
	}
}
