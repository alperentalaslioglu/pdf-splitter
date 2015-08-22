package model;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.Splitter;

public class SplitterModel {
	private List<PDDocument> listOfSplitPages;

	public SplitterModel(PDDocument document) throws IOException {
		Splitter splitter = new Splitter();
		listOfSplitPages = splitter.split(document);
	}

	public List<PDDocument> getDocument() {
		return listOfSplitPages;
	}
}
