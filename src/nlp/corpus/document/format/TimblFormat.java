package nlp.corpus.document.format;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import nlp.event.instance.Instance;
import nlp.util.Pair;

public class TimblFormat implements Format {

	public TimblFormat() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public String oneFormat(String instance, String label, LinkedList<Pair<String, String>> line) {
		StringBuffer response = new StringBuffer(instance);
		Iterator<Pair<String,String>> it = line.iterator();
		response.append(it.next().getSecond());
		while(it.hasNext()){
			response.append(","+it.next().getSecond());
		}
		response.append(","+label);
		return response.toString();
	}
	
	@Override
	public void append(File f, String instance, String label, LinkedList<Pair<String, String>> line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String oneFormat(Instance instance) {
		// TODO Auto-generated method stub
		StringBuffer response = new StringBuffer(instance.getValue()+',');
		Iterator<Pair<String,String>> it = instance.getFeaturesList().iterator();
		response.append(it.next().getSecond());
		while(it.hasNext()){
			response.append(","+it.next().getSecond());
		}
		if(instance.getLabel()!=null)
			response.append(","+instance.getLabel());
		return response.toString();
	}
	public String wholeFormat(LinkedList<Instance> instanceList){
		Iterator<Instance> it = instanceList.iterator();
		StringBuffer response = new StringBuffer(this.oneFormat(it.next()));
		while(it.hasNext())
			response.append('\n'+this.oneFormat(it.next()));
		return response.toString();
	}
}
