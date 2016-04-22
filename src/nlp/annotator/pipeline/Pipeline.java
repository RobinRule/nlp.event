package nlp.annotator.pipeline;

import java.io.IOException;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.MorphaAnnotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.ParserAnnotator;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import nlp.annotator.AnnotatedDoc;
import nlp.annotator.AnnotatedSentence;
import nlp.annotator.AnnotatedToken;
import nlp.corpus.ACECorpus;
import nlp.corpus.document.Document;
import nlp.event.extractor.AnchorIdentifier;
import nlp.model.Megam;
import nlp.util.Pair;

public class Pipeline {
	
	private AnnotationPipeline pipeline;
	
	public Pipeline() throws ClassNotFoundException, IOException {
		pipeline = new AnnotationPipeline();
		pipeline.addAnnotator(new TokenizerAnnotator(false, "en"));
		pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
		pipeline.addAnnotator(new POSTaggerAnnotator(false));
		pipeline.addAnnotator(new MorphaAnnotator(false));
		pipeline.addAnnotator(new NERCombinerAnnotator(false));
		pipeline.addAnnotator(new ParserAnnotator(false, -1));
	}
	
	/**Annotate a document, one document at a time.
	 * @param doc
	 * @return AnnotatedDoc
	 * @throws Exception
	 */
	public AnnotatedDoc annotate(Document doc){
		String text = doc.text();
		// create annotation with text
		Annotation document = new Annotation(text);
		AnnotatedDoc adoc = new AnnotatedDoc();
		// annotate text with pipeline
		pipeline.annotate(document);
		// demonstrate typical usage
		for (CoreMap sentence: document.get(CoreAnnotations.SentencesAnnotation.class)) {
			// get the tree for the sentence
			Tree tree = sentence.get(TreeAnnotation.class);
			// get the tokens for the sentence and iterate over them
			AnnotatedSentence asen = new AnnotatedSentence();
			for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				// get token attributes
				String tokenText = token.get(TextAnnotation.class);
				String tokenPOS = token.get(PartOfSpeechAnnotation.class);
				String tokenLemma = token.get(LemmaAnnotation.class);
				String tokenNE = token.get(NamedEntityTagAnnotation.class);
				AnnotatedToken at = new AnnotatedToken(tokenText);
				at.setPos(tokenPOS);
				at.setLemma(tokenLemma);
				at.setTokenNE(tokenNE);
				at.setOffset(new Pair<Integer,Integer>(token.beginPosition(),token.endPosition()));
				asen.add(at);
			}
			adoc.add(asen);
		}
		return adoc;
	}
	public static void main(String[] args) throws Exception{
		Pipeline p = new Pipeline();
		//p.annotate("I love you, do you know what i means?");
	}
}
