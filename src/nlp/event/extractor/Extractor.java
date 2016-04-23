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
import nlp.model.Megam;

public class Extractor {
	AnchorIdentifier ai;
	public Extractor() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		//annotation
		//
		AnnotateAutomator aAutomator = new AnnotateAutomator(new ACECorpus("./data/ACE"),true);
		String[] fs= {"postag","isanchoridentifier"};
		FeatureBuilder fb = new FeatureBuilder(fs,"isAnchorIdentifier",new TimblFormat());
		while(aAutomator.annotate()){
			AnnotatedDoc aDoc = aAutomator.getAnnotatedDoc();
			for(AnnotatedSentence aSen: aDoc){
				for (AnnotatedToken aToken : aSen) {
					
				}
			}
		}
		
		AnchorIdentifier ai = new AnchorIdentifier(new Megam());
	}
}
