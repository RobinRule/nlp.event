package nlp.event.Features;
import org.jsoup.nodes.Document;
import nlp.annotator.util.AnnotatedToken;
import nlp.event.Feature.Feature;

public class LcWord extends Feature{
	public LcWord(){
		
	}

	@Override
	public String getname() {
		
		return "lcWord";
	}
	@Override
	public String getValue(AnnotatedToken t) {
		String s=t.getToken().toLowerCase();
		return s;
	}
	
	public void main(String[] args){
		
	}
}