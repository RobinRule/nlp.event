package nlp.event.Features;

import java.util.Iterator;

import org.jsoup.nodes.Document;

import edu.stanford.nlp.maxent.Features;
import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.event.Feature.Feature;

public class LeftContextPosTag extends Features{

	public LeftContextPosTag() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return "LeftContextPosTag";
	}
	
	public String getValue(AnnotatedToken t){
		AnnotatedSentence s = t.getParent();
		Iterator it = s.iterator();
		
		AnnotatedToken current = (AnnotatedToken) it.next();
		AnnotatedToken first = null;
		String firstPosTag = "-NULL-";
		AnnotatedToken second = null;
		String secPosTag = "-NULL-";
		AnnotatedToken third = null;
		String thirdPosTag = "-NULL-";
		
		while(!current.equals(t)){
			third = second;
			if (!third.equals(null))
				thirdPosTag = third.getPos();
			second = first;
			if (!second.equals(null))
				secPosTag = second.getPos();
			first = current;
			if (!first.equals(null))
				firstPosTag = first.getPos();
			current = (AnnotatedToken) it.next();
		}							
		return thirdPosTag + secPosTag + firstPosTag;
	}
	
	

}
