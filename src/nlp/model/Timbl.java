package nlp.model;

import nlp.model.util.UnixScript;

public class Timbl implements Model{
	UnixScript exe;
	String command = "timbl -d IL -k 5 ";
	public Timbl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void train(String trainfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void predict(String testfile) {
		// TODO Auto-generated method stub
		
	}

}
