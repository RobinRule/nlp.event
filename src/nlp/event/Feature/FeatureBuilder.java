package nlp.event.Feature;

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
	private Instance buildfeature(AnnotatedToken t,String label){
		LinkedList<Pair<String,String>> tline = new LinkedList<Pair<String,String>>();
		for(int i = 0; i < fs.length; i++){
			tline.add(new Pair<String,String>(fs[i].getname(),fs[i].getValue(t)));
		}
		InstanceToken iToken = new InstanceToken(t.getToken(),tline,label);
		return iToken;
	}
	public LinkedList<Instance> buildfeature(AnnotatedSentence aSen){
		LinkedList<Instance> iList = new LinkedList<Instance>();
		for(AnnotatedToken aToken: aSen){
			iList.add(this.buildfeature(aToken,label.getValue(aToken)));
		}
		return iList;
	}
	public LinkedList<LinkedList<Instance>> buildfeature(AnnotatedDoc aDoc){
		this.metadata = aDoc.getMetadata();
		LinkedList<LinkedList<Instance>> ilList = new LinkedList<LinkedList<Instance>>();
		for(AnnotatedSentence aSen: aDoc){
			ilList.add(buildfeature(aSen));
		}
		return ilList;
	}
}
