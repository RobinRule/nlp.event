package nlp.event.Feature;

import nlp.event.Features.PosTag;

public class FeatureFactory{

	public FeatureFactory() {
		// TODO Auto-generated constructor stub
	}
	Feature newFeature(String des){
		String low = des.toLowerCase();
		if(des.equals("postag")) return new PosTag();
		return null;
	}

}
