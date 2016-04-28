package nlp.event.Feature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.jsoup.nodes.Document;

import nlp.annotator.util.AnnotatedDoc;
import nlp.annotator.util.AnnotatedSentence;
import nlp.annotator.util.AnnotatedToken;
import nlp.corpus.document.format.Format;
import nlp.event.instance.Instance;
import nlp.event.instance.InstanceToken;
import nlp.util.Pair;

public class FeatureBuilder {
	Feature[] fs;
	Feature label;
	Format format;
	Document metadata;
	public FeatureBuilder(String[] featureset,String label,Format format) {
		this.format = format;
		this.fs = new Feature[featureset.length];
		FeatureFactory ff = new FeatureFactory();
		for(int i = 0; i < featureset.length; i++){
			fs[i] = ff.newFeature(featureset[i]);
		}
		this.label = ff.newFeature(label);
	}
	public Instance buildfeature(AnnotatedToken t,Boolean addlabel){
		LinkedList<Pair<String,String>> tline = new LinkedList<Pair<String,String>>();
		for(int i = 0; i < fs.length; i++){
			tline.add(new Pair<String,String>(fs[i].getname(),fs[i].getValue(t)));
		}
		InstanceToken iToken;
		if(addlabel)
			iToken = new InstanceToken(t.getToken(),tline,label.getValue(t));
		else
			iToken = new InstanceToken(t.getToken(),tline);
		return iToken;
	}
	public LinkedList<Instance> buildfeature(AnnotatedSentence aSen,Boolean addlabel){
		LinkedList<Instance> iList = new LinkedList<Instance>();
		Iterator<AnnotatedToken> it = aSen.iterator();
		it.next();//skip the first root token
		while(it.hasNext()){
			iList.add(this.buildfeature(it.next(),addlabel));
		}
		return iList;
	}
	
	public LinkedList<LinkedList<Instance>> buildfeature(AnnotatedDoc aDoc,Boolean addlabel){
		this.metadata = aDoc.getMetadata();
		LinkedList<LinkedList<Instance>> ilList = new LinkedList<LinkedList<Instance>>();
		for(AnnotatedSentence aSen: aDoc){
			ilList.add(buildfeature(aSen,addlabel));
		}
		return ilList;
	}
	
	
	/** Need Modification
	 * @param aCorpus
	 * @return
	 */
	// TODO This is just a test，Need Modification
	public LinkedList<LinkedList<Instance>> buildfeature(LinkedList<AnnotatedDoc> aCorpus, Boolean addlabel){
		LinkedList<LinkedList<Instance>> ilList = new LinkedList<LinkedList<Instance>>();
		for(AnnotatedDoc aDoc: aCorpus){
			ilList.addAll(this.buildfeature(aDoc,addlabel));
		}
		return ilList;
	}

	/** Build feature and output it to file, format of the file is defined by format field.
	 * @param des output destination file directory
	 * @param label boolean indicate final output will or will not have label field
	 */
	public void output(String des, Boolean addlabel,LinkedList<AnnotatedDoc> aCorpus){
		FileWriter fw = null;
		System.out.println(des);
		System.out.println(aCorpus.size());
		try {
			fw = new FileWriter(new File(des));
			BufferedWriter bw = new BufferedWriter(fw);
			for(AnnotatedDoc aDoc: aCorpus){
				for(AnnotatedSentence aSen: aDoc){
					String s = format.wholeFormat(this.buildfeature(aSen, addlabel));
					System.out.println(s);
					System.in.read();
					bw.append(s);
					}
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
