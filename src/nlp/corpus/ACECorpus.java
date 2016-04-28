package nlp.corpus;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

import nlp.corpus.ACECorpus;
import nlp.corpus.document.ACEDocument;
import nlp.corpus.document.Document;

public class ACECorpus implements Corpus {

	private File rootdir;
	private LinkedList<nlp.corpus.document.ACEDocument> doclist; 
	
	public ACECorpus(String src) throws IOException {
		rootdir = new File(src);
		FilenameFilter sgmFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".sgm")) {
					System.out.println("aaa");
					return true;
				} else {
					return false;
				}
			}
		};
		doclist = new LinkedList<nlp.corpus.document.ACEDocument>();
		for(File d:rootdir.listFiles()){
			if(d.isDirectory())
				for(File f: d.listFiles(sgmFilter))
					doclist.add(new ACEDocument(f));
		}
	}
	
	@Override
	public Document nextDocument() {
		// TODO Auto-generated method stub
		return doclist.remove();
	}
	public static void main(String[] args) throws IOException{
		ACECorpus t = new ACECorpus("./data/ACE");
	}

	@Override
	public Boolean empty() {
		return doclist.isEmpty();
	}
}
