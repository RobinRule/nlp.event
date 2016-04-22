package nlp.event.Feature;

import org.jsoup.nodes.Document;

import nlp.annotator.AnnotatedToken;

public abstract class Feature {

	public Feature() {
		// TODO Auto-generated constructor stub
	}
	abstract public String getname();
	abstract public String getValue(AnnotatedToken t);
	abstract public String getValue(AnnotatedToken t,Document metadata);
}
