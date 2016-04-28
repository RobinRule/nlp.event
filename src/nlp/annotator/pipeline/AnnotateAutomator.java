package nlp.annotator.pipeline;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import nlp.annotator.util.AnnotatedDoc;
import nlp.corpus.Corpus;
import nlp.corpus.document.Document;
import nlp.util.MyLogger;

public class AnnotateAutomator {
	private Pipeline pipe;
	private Corpus corpus;
	private AnnotatedDoc aDoc_now;
	private LinkedList<AnnotatedDoc> aCorpus;
	private Boolean AnnotateLevel;
	//private MyLogger logger;
	private final Logger log = Logger.getLogger(AnnotateAutomator.class.getName());
	/**
	 * @param corpus corpus waiting to be annotated
	 * @param sentence set the annotation process as doc by doc
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public AnnotateAutomator(Corpus corpus,Boolean doc) throws ClassNotFoundException, IOException {
		this.pipe = new Pipeline();
		this.corpus = corpus;
		this.AnnotateLevel = doc;
		//logger = new MyLogger();
		PropertyConfigurator.configure("log4j.properties");
	}
	/**
	 * @param sentence set the annotation process as doc by doc
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public AnnotateAutomator(Boolean doc) throws ClassNotFoundException, IOException {
		this.pipe = new Pipeline();
		this.AnnotateLevel = doc;
	}
	public void setCorpus(Corpus corpus){
		this.corpus = corpus;
	}
	
	public void setAnnotateLevel(Boolean level){
		this.AnnotateLevel = level;
	}
	/** Annotate the corpus as presetting, if AnnotateLevel == false, then annotate the whole corpus, if true, then
	 * only annotate one document at a time
	 * @return true if success, false if failed. Failed situation including no more unannotated document when
	 * annotateLeve == true or corpus waiting to be annotate is empty.
	 */
	public Boolean annotate(){
		log.info("Start to annotate.");
		if(this.AnnotateLevel){
			log.info("Work in log level.");
			if(!corpus.empty())
				this.aDoc_now = this.pipe.annotate(this.corpus.nextDocument());
			else{
				log.info("Fail to annotate.");
				return false;
			}
		}else{
			log.info("Work in Corpus level.");
			aCorpus = new LinkedList<AnnotatedDoc>();
			while(!corpus.empty())
				aCorpus.add(this.pipe.annotate(this.corpus.nextDocument()));
		}
		log.info("Annotate Finished.");
		return true;
	}
	/**return the whole annotated corpus, works only when annotate has been executed and AnnotateLevel is false.
	 * @return the whole annotated corpus
	 */
	public LinkedList<AnnotatedDoc> getaCorpus(){
		if(!this.AnnotateLevel && corpus.empty())
			return aCorpus;
		return null;
	}
	public AnnotatedDoc getAnnotatedDoc(){
		return this.aDoc_now;
	}
}
