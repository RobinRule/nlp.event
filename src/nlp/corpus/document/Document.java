package nlp.corpus.document;

public interface Document {
	public String text();
	public String getsource();
	public org.jsoup.nodes.Document getmetadata();
}
