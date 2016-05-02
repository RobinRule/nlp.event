package nlp.event.extractor;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import nlp.event.Feature.FeatureBuilder;
import nlp.model.Model;

public class AnchorIdentifier {
	private Model bmodel;
	private Model cmodel;
	private File m_s;
	private final Logger log = Logger.getLogger(AnchorIdentifier.class.getName());
	public AnchorIdentifier(Model m,Model m1) {
		bmodel = m;
		cmodel = m1;
		m_s = new File("./data/model/");
		m_s.mkdirs();
	}
	
	public void setTrainingCorpus(){
		
	}
	public void setTestCorpus(){
		
	}
	public void setCModel(Model m){
		this.cmodel = m;
	}
	/** Set training model
	 * @param m
	 */
	public void setBModel(Model m){
		this.bmodel = m;
	}
	/** binary classifier training
	 * @param train
	 */
	public void trainB(String train){
		log.info("Starting Training.");
		try {
			bmodel.train(train,m_s.toString()+"/bmodel");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error occured in training:"+e.getStackTrace().toString());
		}
	}
	/** binary classifier prediction
	 * @param f
	 */
	public void predictB(String f){
		log.info("Starting Prediction.");
		try {
			bmodel.predict(m_s.toString()+"/bmodel",f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error occured in Prediction:"+e.getStackTrace().toString());
		}
	}
}
