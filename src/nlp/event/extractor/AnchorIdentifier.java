package nlp.event.extractor;

import java.io.File;
import java.io.IOException;

import nlp.corpus.ACECorpus;
import nlp.corpus.Corpus;
import nlp.event.Feature.FeatureBuilder;
import nlp.model.Megam;
import nlp.model.Model;

public class AnchorIdentifier {
	private Model model;
	public AnchorIdentifier(Model m) {
		model = m;
	}
	
	public void setTrainingCorpus(){
		
	}
	public void setTestCorpus(){
		
	}
	public void train(String train){
		try {
			model.train(train,"./data/model");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void predict(String f){
		try {
			model.predict("./data/model",f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
