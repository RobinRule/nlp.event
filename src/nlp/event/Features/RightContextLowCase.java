package nlp.event.Features;

import java.io.IOException;
import java.util.Iterator;

import edu.stanford.nlp.maxent.Features;
import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.corpus.ACECorpus;

public class RightContextLowCase {

	public RightContextLowCase() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return "RightContextLowerCase";
	}
	
	public String getValue(AnnotatedToken t){
		AnnotatedSentence s = t.getParent();		
		Iterator it = s.iterator();
		
		AnnotatedToken current = (AnnotatedToken) it.next();
		AnnotatedToken first = null;
		String firstLC = "*";
		AnnotatedToken second = null;
		String secLC = "*";
		AnnotatedToken third = null;
		String thirdLC = "*";
		
		while (!current.equals(t)){
			current = (AnnotatedToken) it.next();	
		}
		
		if(it.hasNext()){
			first = (AnnotatedToken) it.next();
			firstLC = first.getToken().toLowerCase();
			if(it.hasNext()){
				second = (AnnotatedToken) it.next();
				secLC = second.getToken().toLowerCase();
				if(it.hasNext()){
					third = (AnnotatedToken) it.next();
					thirdLC = third.getToken().toLowerCase();
				}
			}
		}
		return firstLC + secLC + thirdLC;
	}
	
	public static void main (String [] args) throws ClassNotFoundException, IOException{
		AnnotateAutomator aAutomator = new AnnotateAutomator(null, true);
		aAutomator.setCorpus(new ACECorpus("./data/ACE/"));
		aAutomator.annotate();
		AnnotatedDoc aDoc = aAutomator.getAnnotatedDoc();
		AnnotatedSentence aSen = new AnnotatedSentence(aDoc);
		AnnotatedToken aToken = new AnnotatedToken("test", aSen);
		
	}
}
