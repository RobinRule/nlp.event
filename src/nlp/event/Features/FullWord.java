<<<<<<< HEAD
<<<<<<< HEAD
package nlp.event.Features;
import java.io.IOException;

import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.corpus.ACECorpus;
=======

package nlp.event.Features;
import nlp.annotator.util.AnnotatedToken;
>>>>>>> master
=======

package nlp.event.Features;
import nlp.annotator.util.AnnotatedToken;
>>>>>>> master
import nlp.event.Feature.Feature;

public class FullWord extends Feature{
	public FullWord(){
		
	}

	@Override
	public String getname() {
		
		return "token";
	}

	@Override
	public String getValue(AnnotatedToken t) {
		
		return t.getToken();
	}

<<<<<<< HEAD
<<<<<<< HEAD
	public void main(String[] args) throws IOException, ClassNotFoundException{
		AnnotateAutomator aAutomator = new AnnotateAutomator(true);
		aAutomator.setCorpus(new ACECorpus("./data/ACE/bc/CNN_IP_20030405.1600.00-2.sgm"));
		aAutomator.annotate();
		AnnotatedDoc aDoc = aAutomator.getAnnotatedDoc();
		AnnotatedSentence aSen = new AnnotatedSentence(aDoc);
		System.out.println(aDoc.toString());
=======
	public void main(String[] args){
>>>>>>> master
=======
	public void main(String[] args){
>>>>>>> master
		
	}

}