package nlp.event.instance;

import java.util.LinkedList;

import nlp.util.Pair;

public class InstanceToken extends Instance{
	private final String token;
	
	public InstanceToken(String token, LinkedList<Pair<String,String>> featuresList, String label){
		super(featuresList,label);
		this.token = token;
		
		// TODO Auto-generated constructor stub
	}
	public InstanceToken(String token, LinkedList<Pair<String,String>> featuresList){
		super(featuresList);
		this.token = token;
	}
	public String getToken(){
		return token;
	}	
}
