package nlp.event.extractor;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.corpus.ACECorpus;
import nlp.corpus.Corpus;
import nlp.corpus.document.format.TimblFormat;
import nlp.event.Feature.FeatureBuilder;
import nlp.model.Timbl;
import nlp.util.*;

public class Extractor {
	AnchorIdentifier ai;
	public Extractor() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws ClassNotFoundException{
		//annotation
		PropertyConfigurator.configure("log4j.properties");
		AnnotateAutomator aAutomator = null;
		try {
			aAutomator = new AnnotateAutomator(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] fs= {"postag"};
		FeatureBuilder fb = new FeatureBuilder(fs,"isAnchorIdentifier",new TimblFormat());
		Corpus corp = new ACECorpus("./data/ACE/bc");
		aAutomator.setCorpus(corp);
		System.out.println("Begin for train file annotation");
		aAutomator.annotate();
		System.out.println("Begin for feature building");
		fb.output("./data/annotatedCorpus_train", true, aAutomator.getaCorpus());
		System.out.println("Done for training");
		
		System.out.println("Begin for test file");
		aAutomator.setCorpus(new ACECorpus("./data/ACE/bc/1"));
		System.out.println("Begin for test file annotation");
		aAutomator.annotate();
		System.out.println("Begin for feature building");
		fb.output("./data/annotatedCorpus_test", true, aAutomator.getaCorpus());
		System.out.println("Done for Test");
		
		AnchorIdentifier ai = null;
		try {
			ai = new AnchorIdentifier(new Timbl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ai.train("data/annotatedCorpus_train");
		ai.predict("data/annotatedCorpus_test");
		
	}
}
