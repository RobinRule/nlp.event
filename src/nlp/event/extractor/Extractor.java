package nlp.event.extractor;

import java.io.File;
import java.io.IOException; 

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.annotator.util.AnnotatedCorpus;
import nlp.corpus.document.format.TimblFormat;
import nlp.event.Feature.FeatureBuilder;
import nlp.model.Timbl;

public class Extractor {
	AnchorIdentifier ai;
	ArgumentIdentifier argI;
	private final Logger log = Logger.getLogger(Extractor.class.getName());
	public Extractor() {
		this.ai = null;
		try {
			ai = new AnchorIdentifier(new Timbl(),new Timbl());
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	private void AnchorIdentification() throws ClassNotFoundException, IOException{

		AnnotateAutomator aAutomator = null;
		PropertyConfigurator.configure("log4j.properties");
		/*try {
			aAutomator = new AnnotateAutomator(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AnnotatedCorpus aCorp = new AnnotatedCorpus();
		
		aAutomator.setCorpus(new ACECorpus("./data/ACE_T/Train"));
		
		log.info("Begin for training file annotation.");
		aAutomator.annotate();
		log.info("Training file annotation completed.");
		aCorp.setDoclist(aAutomator.getaCorpus());
		log.info("Annotated Training file saving.");
		aCorp.save(new File("./data/AnnotatedFile/Train"));
		
		log.info("Begin for test file annotation");
		aAutomator.setCorpus(new ACECorpus("./data/ACE_T/Test"));
		aAutomator.annotate();
		log.info("Test file annotation completed.");
		log.info("Annotated test file saving.");
		aCorp.setDoclist(aAutomator.getaCorpus());
		aCorp.save(new File("./data/AnnotatedFile/Test"));
		
		*/
		log.info("Begin for feature building");
		String[] fs= {"FullWord","LcWord","LemmaWord","PosTag","WordDepth"//lexical features
						,"DependencyType","HeadWord","HeadPos","HeadType"//dependency features
						//wordnet feature
						//left context
						//right context
						//related entity features
						};
		FeatureBuilder fb = new FeatureBuilder(fs,"IsAnchorIdentifier",new TimblFormat());
		
		log.info("Reading begin.");
		AnnotatedCorpus aCorp = new AnnotatedCorpus();
		aCorp.read(new File("./data/AnnotatedFile/Train"));
		log.info("Reading Finished.");
		log.info("Feature Generating.");
		fb.output("./data/annotatedCorpus_train", true, aCorp.getDoclist());
		log.info("Feature Generating finshed");
		
		log.info("Reading begin.");
		aCorp.read(new File("./data/AnnotatedFile/Test"));
		log.info("Reading Finished.");
		log.info("Feature Generating.");
		fb.output("./data/annotatedCorpus_test", true, aCorp.getDoclist());
		log.info("Feature Generating finshed");
		
		
		log.info("Training:");
		ai.trainB("data/annotatedCorpus_train");
		log.info("Testing:");
		ai.predictB("data/annotatedCorpus_test");
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		new Extractor().AnchorIdentification();
		
		/*AnnotatedCorpus aCorp = new AnnotatedCorpus();
		aCorp.read(new File("./data/AnnotatedFile/Test"));
		//aCorp.save(new File("./data/AnnotatedFile/T"));
		LinkedList<AnnotatedDoc> d = aCorp.getDoclist();
		System.out.println(d.size());
		Iterator<AnnotatedSentence> it = d.get(0).iterator();
		AnnotatedSentence aSen = it.next();
		
		System.out.println(aSen.getParseTree());
		System.out.println(aSen);
		AnnotatedToken atoken = aSen.getTokenByIndex(1);
		System.out.println(atoken.getParseNode().getParent());
		
		FeatureFactory fb = new FeatureFactory();
		Feature f = fb.newFeature("WordDepth");
		System.out.println(f.getValue(atoken));
		File f = new File("./data/ACE_T/Train/un/alt.atheism_20041104.2428.sgm");
		FileInputStream fis = new FileInputStream(f);
		byte[] data = new byte[(int) f.length()];
		fis.read(data);
		fis.close();
		String doc = new String(data, "UTF-8");
		doc = doc.replaceAll("<(\\s|.)*?>", "");
		System.out.println(doc);
		//new ACEDocument(new File("./data/ACE_T/Train/un/rec.boats_20050130.1006.sgm"));*/
	}
}
