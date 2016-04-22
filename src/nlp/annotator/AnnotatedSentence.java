package nlp.annotator;

import java.util.Iterator;
import java.util.LinkedList;

public class AnnotatedSentence implements Iterable<AnnotatedToken>{
	private LinkedList<AnnotatedToken> tokenlist;
	private AnnotatedDoc parent;
	public AnnotatedSentence(AnnotatedDoc p) {
		this.parent = p;
		tokenlist = new LinkedList<AnnotatedToken>();
	}
	public Boolean add(AnnotatedToken token){
		return tokenlist.add(token);
	}
	@Override
	public Iterator<AnnotatedToken> iterator() {
		// TODO Auto-generated method stub
		return tokenlist.iterator();
	}
}
