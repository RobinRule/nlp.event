package nlp.event.Features;

import java.util.Iterator;

import edu.stanford.nlp.maxent.Features;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;

public class RightContextPosTag extends Features{

	public RightContextPosTag() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return "RightContextPosTag";
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
		
		while (!current.equals(t)){
			current = (AnnotatedToken) it.next();	
		}
		
		if(it.hasNext()){
			first = (AnnotatedToken) it.next();
			firstPosTag = first.getPos();
			if(it.hasNext()){
				second = (AnnotatedToken) it.next();
				secPosTag = second.getPos();
				if(it.hasNext()){
					third = (AnnotatedToken) it.next();
					thirdPosTag = third.getPos();
				}
			}
		}
		
		return firstPosTag + secPosTag + thirdPosTag;
	}

}
