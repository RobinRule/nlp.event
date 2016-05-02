package nlp.event.Features;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedToken;
import nlp.annotator.util.MyDependency;
import nlp.annotator.util.AnnotatedSentence;
import nlp.corpus.ACECorpus;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		AnnotateAutomator aAutomator = new AnnotateAutomator(true);
		aAutomator.setCorpus(new ACECorpus("./data/ACE/bc/1"));
		aAutomator.annotate();
		AnnotatedDoc aDoc = aAutomator.getAnnotatedDoc();
		Iterator<AnnotatedSentence> it = aDoc.iterator();
		while(it.hasNext()){
			AnnotatedSentence sen = it.next();
			Iterator<AnnotatedToken> itt=sen.iterator();
			itt.next();
			while(itt.hasNext()){
				AnnotatedToken toke=itt.next();
				HeadType fw = new HeadType();
				String tmp=fw.getValue(toke);
				System.out.println(tmp);
			}
			//LinkedList<MyDependency> depList=sen.getDeplist();
			//System.out.println(depList.toString());
			
			
		}	
		
		 
	}
}
