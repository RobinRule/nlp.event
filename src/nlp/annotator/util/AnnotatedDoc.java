package nlp.annotator.util;

import java.util.Iterator;
import java.util.LinkedList;
import org.jsoup.nodes.Document;
public class AnnotatedDoc implements Iterable<AnnotatedSentence> {
	private LinkedList<AnnotatedSentence> senlist;
	private org.jsoup.nodes.Document metadata;
	public AnnotatedDoc() {
		senlist = new LinkedList<AnnotatedSentence>();
	}
	public AnnotatedDoc(org.jsoup.nodes.Document metadata) {
		this.metadata = metadata;
		senlist = new LinkedList<AnnotatedSentence>();
	}
	public Boolean add(AnnotatedSentence sen){
		return senlist.add(sen);
	}
	public int size(){
		return senlist.size();
	}
	@Override
	public Iterator<AnnotatedSentence> iterator() {
		// TODO Auto-generated method stub
		return senlist.iterator();
	}
	public org.jsoup.nodes.Document getMetadata() {
		return metadata;
	}
	public void setMetadata(org.jsoup.nodes.Document metadata) {
		this.metadata = metadata;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer("{");
		for(AnnotatedSentence aSen: senlist){
			sb.append(aSen.toString()+'\n');
		}
		sb.append('\n');
		return sb.toString();
	}
}
