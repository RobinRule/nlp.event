package nlp.annotator.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.trees.TypedDependency;

public class AnnotatedSentence implements Iterable<AnnotatedToken>{
	private ArrayList<AnnotatedToken> tokenlist;
	private AnnotatedDoc parent;
	private LinkedList<MyDependency> deplist;
	
	public AnnotatedSentence(AnnotatedDoc p) {
		this.parent = p;
		tokenlist = new ArrayList<AnnotatedToken>();
		tokenlist.add(new AnnotatedToken("ROOT",this));
	}
	public Boolean add(AnnotatedToken token){
		return tokenlist.add(token);
	}
	@Override
	public Iterator<AnnotatedToken> iterator() {
		// TODO Auto-generated method stub
		return tokenlist.iterator();
	}
	public AnnotatedDoc getParent() {
		return parent;
	}
	public LinkedList<MyDependency> getDeplist() {
		return deplist;
	}
	public void setDeplist(LinkedList<MyDependency> deplist) {
		this.deplist = deplist;
	}
	public void setDeplistConv(Collection<TypedDependency> deplist){
		LinkedList<MyDependency> dl = new LinkedList<MyDependency>();
		for(TypedDependency tp: deplist){
			dl.add(new MyDependency(tokenlist.get(tp.gov().index()),
								this.tokenlist.get(tp.dep().index()),
								tp.reln().getShortName()));
		}
		this.deplist = dl;
	}
	public String toString()
    { 
		StringBuffer ans = new StringBuffer("[ ");
		for(AnnotatedToken at:tokenlist){
			ans.append(at.toString()+", ");
		}
		ans.append("]");
		return ans.toString();
    }
}
