package nlp.event.extractor;

import java.io.File;
import java.io.IOException;

import nlp.corpus.ACECorpus;
import nlp.corpus.Corpus;
import nlp.model.Megam;
import nlp.model.Model;

public class AnchorIdentifier {
	private Model model;
	//private Corpus corpus;
	public AnchorIdentifier(Model m) {
		model = m;
		//corpus = traindata; 
		// TODO Auto-generated constructor stub
		
	}
	public void train(){
		model.train();
	}
	public void predict(File f){
		model.predict();
	}
}
