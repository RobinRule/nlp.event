package nlp.annotator.util;

import nlp.util.Pair;

public class AnnotatedToken {
	private final String token;
	private String pos;
	private String lemma;
	private String tokenNE;
	private Pair<Integer,Integer> offset;
	private AnnotatedSentence parent;
	private MyTree parseNode;
	public AnnotatedToken(String token,AnnotatedSentence parent) {
		this.parent = parent;
		this.token = token;
		this.setPos(null);
		this.setLemma(null);
		this.setTokenNE(null);
		this.offset = null;
		// TODO Auto-generated constructor stub
	}

	public String getTokenNE() {
		return tokenNE;
	}

	public void setTokenNE(String tokenNE) {
		this.tokenNE = tokenNE;
	}

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Pair<Integer,Integer> getOffset() {
		return offset;
	}

	public void setOffset(Pair<Integer,Integer> offset) {
		this.offset = offset;
	}

	public String getToken() {
		return token;
	}

	public AnnotatedSentence getParent() {
		return parent;
	}
	public String toString()
    { 
           return this.token; 
    }

	public MyTree getParseNode() {
		return parseNode;
	}

	public void setParseNode(MyTree parseNode) {
		this.parseNode = parseNode;
	}
	public Boolean equals(AnnotatedToken other){
		return other.parent == this.getParent() && this.getOffset().equals(other.getOffset());
	}

}
