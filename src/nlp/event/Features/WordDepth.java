package nlp.event.Features;
import nlp.annotator.util.AnnotatedToken;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.MyTree;
import nlp.event.Feature.Feature;


public class WordDepth extends Feature {

	public WordDepth (){
		
	}
	@Override
	public String getname() {
		
		return "WordDepth";
	}
	
	@Override
	public String getValue(AnnotatedToken t) {
		AnnotatedSentence Sen=t.getParent();
		MyTree mr=Sen.getParseTree();
		int depth=0;
		while(!mr.getParent().getValue().equals("ROOT")){
			depth++;
		}
		
		return Integer.toString(depth);
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> master
=======
}
>>>>>>> master
