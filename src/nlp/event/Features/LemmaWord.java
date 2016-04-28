package nlp.event.Features;

import org.jsoup.nodes.Document;

import nlp.annotator.util.AnnotatedToken;
import org.jsoup.nodes.Document;
import nlp.event.Feature.Feature;

public class LemmaWord extends Feature {
	public LemmaWord(){
		
	}

	@Override
	public String getname() {
		
		return "lemmaWord";
	}
	@Override
	public String getValue(AnnotatedToken t) {
		
		return t.getLemma();
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
