package nlp.event.extractor;

import java.io.IOException;
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
			model.train(train,"data/model");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void predict(String f){
		try {
			model.predict("data/model",f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
