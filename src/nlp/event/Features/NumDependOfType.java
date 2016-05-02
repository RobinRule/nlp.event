package nlp.event.Features;
import java.util.LinkedList;
import nlp.annotator.util.AnnotatedToken;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.MyDependency;
import nlp.event.Feature.Feature;

public class NumDependOfType extends Feature {

	public NumDependOfType(){
		
	}
	@Override
	public String getname() {
		
		return "NumDependOfType";
	}
	@Override
	public String getValue(AnnotatedToken t) {
		
		
		return "-NULL-";
	}
}
