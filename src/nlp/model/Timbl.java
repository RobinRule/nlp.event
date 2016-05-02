package nlp.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import nlp.model.util.UnixScript;

public class Timbl implements Model{
	UnixScript exe;
	ArrayList<String> traincommand; ;
	ArrayList<String> testcommand;
	public Timbl() throws IOException {
		exe = new UnixScript("/Users/Robin/lamachine/bin/");
		String[] a= {"timbl","-m","O", "-d", "ID", "-k", "5"};
		
		traincommand = new ArrayList<String>();
		traincommand.add("timbl");traincommand.add("-m");traincommand.add("O");
		traincommand.add("-d");traincommand.add("ID");
		traincommand.add("-k");traincommand.add("5");
		
		testcommand = new ArrayList<String>();
		testcommand.add("timbl");
	}

	@Override
	public void train(String trainfile, String modelpath) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>(traincommand);
		temp.add("-f");temp.add(trainfile);
		temp.add("-I");temp.add(modelpath);
		
		exe.execute(temp);
		
		System.out.println(exe.getStdout());
		System.out.println(exe.getStderr());
	}

	@Override
	public void predict(String modelpath, String testfile) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> temp = new ArrayList<String>(testcommand);
		temp.add("-i");temp.add(modelpath);
		temp.add("-t");temp.add(testfile);
		
		exe.execute(temp);
		
		System.out.println(exe.getStdout());
		System.out.println(exe.getStderr());
	}
}
