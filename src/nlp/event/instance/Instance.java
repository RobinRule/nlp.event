package nlp.event.instance;

import java.util.LinkedList;

import nlp.util.Pair;

public class Instance {
	protected final LinkedList<Pair<String,String>> featuresList;
	protected final String label;
	public Instance(LinkedList<Pair<String,String>> featuresList, String label){
		this.label = label;
		this.featuresList = featuresList;
	}
	public Instance(LinkedList<Pair<String,String>> featuresList){
		this.label = null;
		this.featuresList = featuresList;
	}
	public LinkedList<Pair<String,String>> getFeaturesList() {
		return featuresList;
	}
	public String getLabel() {
		if(label!=null)
			return label;
		return null;
	}
}
