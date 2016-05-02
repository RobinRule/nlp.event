package nlp.event.Features;
import java.util.LinkedList;
import nlp.annotator.util.AnnotatedToken;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.MyDependency;
import nlp.event.Feature.Feature;

/**
 * @author Robin
 * feature for dependency head's entity type
 */
public class HeadType extends Feature {

	
	public HeadType (){
			
		
	}
	@Override
	public String getname() {
		
		return "HeadType";
	}
	@Override
	public String getValue(AnnotatedToken t) {
<<<<<<< HEAD
		if (t.getToken().toLowerCase().equals("root")) return "-NULL-";
=======
		if(t.getIndex().equals(0))	return "-NULL-";
>>>>>>> master
		AnnotatedSentence Sen=t.getParent();
		LinkedList<MyDependency> depList=Sen.getDeplist();
		for (int i=0;i<depList.size();i++){
			MyDependency md=depList.get(i);
			if (md.getDependent().equals(t) && md.getHead().getTokenNE()!=null ){
				return md.getHead().getTokenNE();	
			}
		}
<<<<<<< HEAD
		
=======
>>>>>>> master
		return "-NULL-";
	}


	
	public void main(String[] args){
		
	}	
	
}
