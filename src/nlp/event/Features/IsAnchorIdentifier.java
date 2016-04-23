package nlp.event.Features;

import org.jsoup.nodes.Document;

import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.event.Feature.Feature;

public class IsAnchorIdentifier extends Feature {

	public IsAnchorIdentifier() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getname() {
		return "IsAnchorIdentifier";
	}

	@Override
	public String getValue(AnnotatedToken t) {
		// TODO Auto-generated method stub
		return null;
	}

}
