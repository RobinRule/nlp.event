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
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		//annotation
		//
		String[] fs= {"postag"};
		AnnotateAutomator aAutomator = new AnnotateAutomator(false);
		
		FeatureBuilder fb = new FeatureBuilder(fs,"isAnchorIdentifier",new TimblFormat());
		
		aAutomator.setCorpus(new ACECorpus("./data/ACE/"));
		aAutomator.annotate();
		fb.output("./data/annotatedCorpus_train", true, aAutomator.getaCorpus());
		/*
		aAutomator.setCorpus(new ACECorpus("./data/test"));
		aAutomator.annotate();
		fb.output("./data/annotatedCorpus_test", true, aAutomator.getaCorpus());
		
		AnchorIdentifier ai = new AnchorIdentifier(new Megam());
		ai.train("./data/annotatedCorpus_train");
		ai.predict("./data/annotatedCorpus_test");*/
	}
}
