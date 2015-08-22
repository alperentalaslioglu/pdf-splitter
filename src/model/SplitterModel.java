package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.Splitter;

public class SplitterModel {
	private List<PDDocument> listOfSplitPages;

	public SplitterModel(File pdfFile) throws IOException {
		PDDocument document = PDDocument.load(pdfFile);
		Splitter splitter = new Splitter();
		listOfSplitPages = splitter.split(document);
	}

	public List<PDDocument> getDocument() {
		return listOfSplitPages;
	}
}
