package nlp.corpus.document.format;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

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
		StringBuffer response = new StringBuffer("");
		Iterator<Pair<String,String>> it = line.iterator();
		response.append(it.next());
		while(it.hasNext()){
			response.append(","+it.next());
		}
		response.append(","+label);
		return response.toString();
	}
	@Override
	public void wholeFormat(LinkedList<Pair<String, String>> line) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void append(File f, String instance, String label, LinkedList<Pair<String, String>> line) {
		// TODO Auto-generated method stub
		
	}

}
