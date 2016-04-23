package nlp.event.Features;

import org.jsoup.nodes.Document;

import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.event.Feature.Feature;

public class PosTag extends Feature{

	public PosTag() {
		// TODO Auto-generated constructor stub
	}
	public String getname(){
		return "Postag";
	}
	public String getValue(AnnotatedToken t) {
		// TODO Auto-generated method stub
		return t.getPos();
	}
}
