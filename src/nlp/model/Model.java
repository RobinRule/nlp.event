package nlp.model;

public interface Model {
	public void train(String trainfile);
	public void predict(String testfile);
}
