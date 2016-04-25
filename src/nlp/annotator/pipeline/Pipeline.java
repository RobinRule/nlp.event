package nlp.annotator.pipeline;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.DependencyParseAnnotator;
import edu.stanford.nlp.pipeline.MorphaAnnotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.ParserAnnotator;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

import nlp.annotator.util.*;
import nlp.corpus.document.ACEDocument;
import nlp.corpus.document.Document;
import nlp.event.Feature.Feature;
import nlp.event.Features.IsAnchorIdentifier;
import nlp.util.Pair;

/**
 * @author Robin
 * @version 1.0
 * @since	April 23 2016
 */
public class Pipeline {
	
	private AnnotationPipeline pipeline;
	
	public Pipeline() throws ClassNotFoundException, IOException {
		pipeline = new AnnotationPipeline();
		pipeline.addAnnotator(new TokenizerAnnotator(false, "en"));
		pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
		pipeline.addAnnotator(new POSTaggerAnnotator(false));
		pipeline.addAnnotator(new MorphaAnnotator(false));
		/**/
		pipeline.addAnnotator(new DependencyParseAnnotator());
		/**/
		pipeline.addAnnotator(new NERCombinerAnnotator(false));
		pipeline.addAnnotator(new ParserAnnotator(false, -1));
	}
	
	/**Annotate a document, one document at a time.
	 * @param doc
	 * @return AnnotatedDoc
	 * @throws IOException 
	 * @throws Exception
	 */
	public AnnotatedDoc annotate(Document doc){
		String text = doc.text();
		// create annotation with text
		
		Annotation document = new Annotation(text);
		AnnotatedDoc adoc = new AnnotatedDoc(doc.getmetadata());
		// annotate text with pipeline
		pipeline.annotate(document);
		// demonstrate typical usage
		//HeadFinder hf = new CollinsHeadFinder();//UniversalSemanticHeadFinder();
		for (CoreMap sentence: document.get(CoreAnnotations.SentencesAnnotation.class)) {
			//get the tree for the sentence
			Tree tree = sentence.get(TreeAnnotation.class);
			System.out.println(tree);
			
			SemanticGraph dep = sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);
			
			AnnotatedSentence asen = new AnnotatedSentence(adoc);
			
			// get the tokens for the sentence and iterate over them
			for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				// get token attributes
				String tokenText = token.get(TextAnnotation.class);
				String tokenPOS = token.get(PartOfSpeechAnnotation.class);
				String tokenLemma = token.get(LemmaAnnotation.class);
				String tokenNE = token.get(NamedEntityTagAnnotation.class);
				AnnotatedToken at = new AnnotatedToken(tokenText,asen);
				at.setPos(tokenPOS);
				at.setLemma(tokenLemma);
				at.setTokenNE(tokenNE);
				at.setOffset(new Pair<Integer,Integer>(token.beginPosition(),token.endPosition()-1));
				asen.add(at);
			}
			asen.setDeplist(dep.typedDependencies());
			asen.setParseTree(tree);
			adoc.add(asen);
		}
		return adoc;
	}
	public static void main(String[] args) throws Exception{
		Pipeline p = new Pipeline();
		Document d = new ACEDocument(new File("./data/ACE/bc/CNN_CF_20030303.1900.00.sgm"));
		AnnotatedDoc aDoc = p.annotate(d);
		Feature f = new IsAnchorIdentifier();
		
		Iterator<AnnotatedSentence> it = aDoc.iterator();
		AnnotatedSentence aSen = it.next();
		aSen = it.next();
		System.out.println(aSen.getParseTree());
		//System.out.println(aDoc);
		//for(AnnotatedToken aToken:aSen){
		//	if(aToken.getToken().equals("election")){
		//		System.out.println(aToken.getOffset());
		//		System.out.println(f.getValue(aToken));
		//	}
		//}
		//System.out.print(aSen.getDeplist());
		/*
		LexicalizedParser lp = LexicalizedParser.loadModel(
				"edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
				"-maxLength", "80", "-retainTmpSubcategories");
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		// Uncomment the following line to obtain original Stanford Dependencies
		// tlp.setGenerateOriginalDependencies(true);
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		String[] sent ={ "This", "is", "an", "easy", "sentence", "." };
		Tree parse = lp.apply(Sentence.toWordList(sent));
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
		for(TypedDependency td:tdl){
			System.out.println(td);
			System.out.println(td.reln().getShortName());
			System.out.println(td.gov().index());
			System.out.println("============");
		}*/
	}
}
