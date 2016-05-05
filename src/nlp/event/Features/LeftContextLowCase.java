package nlp.event.Features;

import java.util.Iterator;

import edu.stanford.nlp.maxent.Features;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;

public class LeftContextLowCase extends Features{

	public LeftContextLowCase() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName(){
		return "LeftContextLowCase";
	}
	
	public String getValue(AnnotatedToken t){
		
		AnnotatedSentence s = t.getParent();	
		Iterator it = s.iterator();
		
		AnnotatedToken current = (AnnotatedToken) it.next();
		
		AnnotatedToken first = null;
		String firstLC = "-NULL-";
		AnnotatedToken second = null;
		String secLC = "-NULL-";
		AnnotatedToken third = null;
		String thirdLC = "-NULL-";
		
		while(!current.equals(t)){
			third = second;
			if (!third.equals(null)){
				thirdLC = third.getToken().toLowerCase();
			}
			second = first;
			if (!second.equals(null)){
				secLC = second.getToken().toLowerCase();
			}
			first = current;
			if (!first.equals(null)){
				firstLC = first.getToken().toLowerCase();
			}
				
			current = (AnnotatedToken) it.next();
		}							
		return thirdLC + secLC + firstLC;
	}

}
