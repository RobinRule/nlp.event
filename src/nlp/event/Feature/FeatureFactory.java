package nlp.event.Feature;

import nlp.event.Features.*;

public class FeatureFactory{

	public FeatureFactory() {
		// TODO Auto-generated constructor stub
	}
	public Feature newFeature(String des){
		if(des.equals("DependentType")) return new DependentType();
		else if(des.equals("DependencyType")) return new DependencyType();
		else if(des.equals("FullWord")) return new FullWord();
		else if(des.equals("HeadPos"))	return new HeadPos();
		else if(des.equals("HeadType")) return new HeadType();
		else if(des.equals("HeadWord")) return new HeadWord();
		else if(des.equals("IsAnchorIdentifier")) return new IsAnchorIdentifier();
		else if(des.equals("LcWord")) return new LcWord();
		else if(des.equals("LemmaWord")) return new LemmaWord();
		else if(des.equals("Offset")) return new Offset();
		else if(des.equals("PosTag")) return new PosTag();
		else if(des.equals("WordDepth")) return new WordDepth();
		
		return null;
	}

}
