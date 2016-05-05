package nlp.event.Features;

import java.io.FileInputStream;

import edu.stanford.nlp.maxent.Features;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;
import nlp.annotator.util.AnnotatedToken;

public class WordNet extends Features{

	public WordNet(){		
	}
	
	public String getName(){
		return "WordNet";
	}
	
	public String getValue(AnnotatedToken t) throws JWNLException{
		String value = "-NULL-";
		POS wordNetPosTag = tagTransfer(t.getPos());
		
		if(!wordNetPosTag.equals(null)){
			value = getWordNetID(wordNetPosTag, t.getToken());
		}
		return value;
	}
	
	public POS tagTransfer (String posTag){
		if(posTag.equals("noun")){
			return POS.NOUN;
		}else if (posTag.equals("verb")){
			return POS.VERB;
		}else if (posTag.equals("adv")){
			return POS.ADVERB;
		}else if (posTag.equals("adj")){
			return POS.ADJECTIVE;
		}else{
			return null;
		}	
	}
	
	private String getWordNetID(POS wordNetPosTag, String token) throws JWNLException{
		Dictionary dictionary = Dictionary.getInstance();
		IndexWord word = dictionary.lookupIndexWord(wordNetPosTag, token);
		Synset[] senses = word.getSenses();
		String firstSenseKey = senses[0].getSenseKey(token);
		return firstSenseKey;
	}
	public static void configureJWordNet() {
		try {
			// initialize JWNL (this must be done before JWNL can be used)
			// See the JWordnet documentation for details on the properties file
			JWNL.initialize(new FileInputStream("data/WordNet/property/file_properties.xml"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			System.exit(-1);
		}
	}
}
