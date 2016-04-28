package nlp.event.extractor;

import java.io.IOException;

import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.corpus.ACECorpus;
import nlp.corpus.Corpus;
import nlp.corpus.document.format.TimblFormat;
import nlp.event.Feature.FeatureBuilder;
import nlp.util.*;

public class Extractor {
	AnchorIdentifier ai;
	public Extractor() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws ClassNotFoundException{
		//annotation
		AnnotateAutomator aAutomator = null;
		try {
			aAutomator = new AnnotateAutomator(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aAutomator.setCorpus(new ACECorpus("./data/ACE/bc/1"));
		aAutomator.annotate();
		AnnotatedDoc aDoc = aAutomator.getAnnotatedDoc();
		System.out.println(aDoc.toString());
		/*
		AnnotateAutomator aAutomator = null;
		try {
			aAutomator = new AnnotateAutomator(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] fs= {"postag"};
		FeatureBuilder fb = new FeatureBuilder(fs,"isAnchorIdentifier",new TimblFormat());
		Corpus corp = new ACECorpus("./data/ACE/bc/1");
		//System.out.println("hahdshf:"+corp.size());
		aAutomator.setCorpus(corp);
		aAutomator.annotate();
		fb.output("./data/annotatedCorpus_train", true, aAutomator.getaCorpus());
		System.out.println("Done");
		/*
		aAutomator.setCorpus(new ACECorpus("./data/test"));
		aAutomator.annotate();
		fb.output("./data/annotatedCorpus_test", true, aAutomator.getaCorpus());
		
		AnchorIdentifier ai = new AnchorIdentifier(new Megam());
		ai.train("./data/annotatedCorpus_train");
		ai.predict("./data/annotatedCorpus_test");*/
	}
}
