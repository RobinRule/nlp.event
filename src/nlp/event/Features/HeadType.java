package nlp.event.Features;
import java.util.LinkedList;
import nlp.annotator.util.AnnotatedToken;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.MyDependency;
import nlp.event.Feature.Feature;

public class HeadType extends Feature {

	
	public HeadType (){
			
		
	}
	@Override
	public String getname() {
		
		return "HeadType";
	}
	@Override
	public String getValue(AnnotatedToken t) {
		AnnotatedSentence Sen=t.getParent();
		LinkedList<MyDependency> depList=Sen.getDeplist();
		for (int i=0;i<depList.size();i++){
			MyDependency md=depList.get(i);
			if (md.getDependent().equals(t)){
				return md.getHead().getTokenNE();	
			}
		}
		
		return "NULL";
	}


	
	public void main(String[] args){
		
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
