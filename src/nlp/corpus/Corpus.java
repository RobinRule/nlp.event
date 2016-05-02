package nlp.corpus;

import java.io.File;

import nlp.corpus.document.Document;

public interface Corpus {
	public Document nextDocument();
	public Boolean empty();
	public Integer size();
}
