package nlp.annotator.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

public class AnnotatedSentence implements Iterable<AnnotatedToken>{
	private ArrayList<AnnotatedToken> tokenlist;
	private AnnotatedDoc parent;
	private LinkedList<MyDependency> deplist;
	private MyTree parsetree;
	
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
	public void setDeplist(Collection<TypedDependency> deplist){
		LinkedList<MyDependency> dl = new LinkedList<MyDependency>();
		for(TypedDependency tp: deplist){
			dl.add(new MyDependency(tokenlist.get(tp.gov().index()),
								this.tokenlist.get(tp.dep().index()),
								tp.reln().getShortName()));
		}
		this.deplist = dl;
	}
	public void setParseTree(MyTree tree){
		this.parsetree = tree;
	}
	private AnnotatedToken getTokenByIndex(int index){
		//System.out.println(this.tokenlist.get(index));
		return this.tokenlist.get(index);
	}
	private Boolean isLeaf(Tree t){
		return t.getChildrenAsList().size() == 1 && t.getChildrenAsList().get(0).isLeaf();
	}
	private int setParseTreeSub(MyTree target, Tree source,int k,int dep){
		int count = k;
		target.setTag(source.label().toString());
		if(this.isLeaf(source)){ 
			AnnotatedToken token = this.getTokenByIndex(count);
			token.setParseNode(target);
			target.setValue(token);
			count++;
			return count;
		}
		for(Tree sChild:source.getChildrenAsList())
		{
			MyTree tChild = new MyTree();
			count = this.setParseTreeSub(tChild, sChild,count,dep+1);
			tChild.setParent(target);
			target.addChild(tChild);
		}
		return count;
	}
	public void setParseTree(Tree tree){
		if(tree == null) return;
		this.parsetree = new MyTree();
		this.setParseTreeSub(this.parsetree, tree,1,0);
	}
	
	public MyTree getParseTree(){
		return this.parsetree;
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
