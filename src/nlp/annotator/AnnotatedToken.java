package nlp.annotator;

import nlp.util.Pair;

public class AnnotatedToken {
	private final String token;
	private String pos;
	private String lemma;
	private String tokenNE;
	private Pair<Integer,Integer> offset;
	public AnnotatedToken(String token) {
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

}
