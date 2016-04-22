package nlp.corpus.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jsoup.Jsoup;

public class ACEDocument implements nlp.corpus.document.Document {
	String source;
	String doc;
	org.jsoup.nodes.Document metadata;
	public ACEDocument(File f) throws IOException {
		metadata = Jsoup.parse(new File(f.getAbsolutePath().replace(".sgm", ".apf.xml")), "UTF-8");
		FileInputStream fis = new FileInputStream(f);
		byte[] data = new byte[(int) f.length()];
		fis.read(data);
		fis.close();
		doc = new String(data, "UTF-8");
		doc = doc.replaceAll("<.*?>", "");
		source = f.getPath();
	}
	public String text(){
		return doc;
	}
	public String getsource(){
		return source;
	}
	public org.jsoup.nodes.Document getmetadata(){
		return metadata;
	}
	public static void main(String[] args) throws IOException{
		ACEDocument t = new ACEDocument( new File("./data/ACE/bc/CNN_CF_20030303.1900.00.sgm") );
	}
}
