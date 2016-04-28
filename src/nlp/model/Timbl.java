package nlp.model;

import java.io.File;
import java.io.IOException;

import nlp.model.util.UnixScript;

public class Timbl implements Model{
	UnixScript exe;
	String command = "./timbl -a1 -d IL -k 5 ";
	public Timbl() throws IOException {
		// TODO Auto-generated constructor stub
		exe = new UnixScript(new File("/Users/Robin/lamachine/bin"));
	}

	@Override
	public void train(String trainfile, String modelpath) throws IOException {
		// TODO Auto-generated method stub
		String trainS = "-f " + trainfile + " ";
		exe.execute(command + trainS + "-I " + modelpath);
		System.out.println(exe.getStdout());
		System.out.println(exe.getStderr());
	}

	@Override
	public void predict(String modelpath, String testfile) throws IOException {
		// TODO Auto-generated method stub
		String testS = "-t " + testfile + " -i " + modelpath;
		exe.execute(command + testS);
		System.out.println(exe.getStdout());
		System.out.println(exe.getStderr());
	}
}
