package nlp.event.Features;

import org.jsoup.nodes.Document;

import nlp.annotator.AnnotatedToken;
import nlp.event.Feature.Feature;

public class PosTag extends Feature{

	public PosTag() {
		// TODO Auto-generated constructor stub
	}
	public String getname(){
		return "Postag";
	}
	public String getValue(AnnotatedToken t){
		return t.getPos();
	}
	@Override
	public String getValue(AnnotatedToken t, Document metadata) {
		return t.getPos();
	}
	public void main(String[] args){
		
	}
}
