package nlp.event.Feature;

import nlp.event.Features.*;

public class FeatureFactory{

	public FeatureFactory() {
		// TODO Auto-generated constructor stub
	}
	Feature newFeature(String des){
		String low = des.toLowerCase();
		if(low.equals("postag")) return new PosTag();
		else if(low.equals("isanchoridentifier")) return new IsAnchorIdentifier();
		return null;
	}

}
