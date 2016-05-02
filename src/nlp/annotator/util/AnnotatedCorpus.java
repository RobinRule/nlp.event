package nlp.annotator.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import nlp.annotator.pipeline.AnnotateAutomator;
import nlp.corpus.ACECorpus;
import nlp.corpus.Corpus;
import nlp.util.Pair;
	
public class AnnotatedCorpus {
	private LinkedList<AnnotatedDoc> doclist;
	
	public Integer size(){
		return doclist.size();
	}
	public void setDoclist(LinkedList<AnnotatedDoc> doclist){
		this.doclist = doclist;
	}
	public LinkedList<AnnotatedDoc> getDoclist(){
		return this.doclist;
	}
	public void save(File f) throws IOException {
		f.mkdirs();
		int i = 0;
		for(AnnotatedDoc aDoc: doclist){
			//metadata
			String p = f.getPath()+'/'+ (i++);
			//System.out.println(p);
			FileWriter fw = new FileWriter(new File(p+".meta"));
			fw.write(aDoc.getMetadata().toString());
			fw.close();
			
			org.jsoup.nodes.Document doc = Jsoup.parse("<doc></doc>");
			Element docu = doc.getElementsByTag("doc").first();
			docu.attr("metapath", p+".meta");
			for(AnnotatedSentence aSen: aDoc){
				Element sen = new Element(Tag.valueOf("sentence"),"");
				//parseTree
				Element parseTree = new Element(Tag.valueOf("parseTree"),"");
				parseTree.appendText(aSen.getParseTree().toString());
				// deplist
				for(MyDependency dep : aSen.getDeplist()){
					Element depTag = new Element(Tag.valueOf("dependency"),"");
					depTag.attr("head", dep.getHead().toStringIndexed());
					depTag.attr("dependent",dep.getDependent().toStringIndexed());
					depTag.attr("type",dep.getType());
					sen.appendChild(depTag);
					}
				sen.appendChild(parseTree);
				
				int K = 1;
				Iterator<AnnotatedToken> it = aSen.iterator();
				it.next();
				while(it.hasNext()){
					AnnotatedToken aToken = it.next();
					Element token = new Element(Tag.valueOf("Token"),"");
					token.attr("token", aToken.getToken());
					token.attr("lemma", aToken.getLemma());
					token.attr("pos", aToken.getPos());
					token.attr("ne", aToken.getTokenNE());
					token.attr("offset", aToken.getOffset().toString());
					token.attr("index", String.valueOf(K++));
					sen.appendChild(token);
				}
				docu.appendChild(sen);
			}
			fw = new FileWriter(new File(p+".data"));
			fw.write(docu.toString());
			fw.close();
		}
	}
	private LinkedList<File> getFileList(File strPath) {
		File[] files = strPath.listFiles(); // 该文件目录下文件全部放入数组
		LinkedList<File> filelist = new LinkedList<File>();
		if (files != null){
			for (File f: files) {
				if (f.isDirectory()) { // 判断是文件还是文件夹
					filelist.addAll(this.getFileList(f));
				} else if (f.getName().endsWith(".data")) { // 判断文件名是否以.sgm结尾
					filelist.add(f);
				}
			}
		}
		return filelist;
	}
	public void read(File f) throws IOException {
		this.doclist = new LinkedList<AnnotatedDoc>();
		LinkedList<File> flist = this.getFileList(f);
		for(File docf : flist){
			org.jsoup.nodes.Document doc = Jsoup.parse(docf,"UTF-8");
			AnnotatedDoc aDoc = new AnnotatedDoc();
			for(Element sen:doc.getElementsByTag("sentence")){
				AnnotatedSentence aSen = new AnnotatedSentence();
				for(Element token:sen.getElementsByTag("token")){
					AnnotatedToken aToken = new AnnotatedToken(token.attr("token"), aSen);
					aToken.setLemma(token.attr("lemma"));
					aToken.setPos(token.attr("pos"));
					aToken.setTokenNE(token.attr("ne"));
					aToken.setIndex(Integer.valueOf(token.attr("index")));
					//TODO offset	
					aToken.setOffset(Pair.valueOf(token.attr("offset")));
					aSen.add(aToken);
					
				}
				//DEP LIST
				for(Element dep:sen.getElementsByTag("dependency")){
					String[] tempH = dep.attr("head").split("-");
					String[] tempD = dep.attr("dependent").split("-");
					MyDependency t = new MyDependency(aSen.getTokenByIndex(Integer.valueOf(tempH[tempH.length-1])), 
							 aSen.getTokenByIndex(Integer.valueOf(tempD[tempD.length-1])), 
							  dep.attr("type"));
					//System.out.println(t);
					aSen.addDependency(t);
				}
				//PARSE TREE
				aSen.setParseTree(sen.getElementsByTag("parsetree").first().text().trim());
				String absP = docf.getAbsolutePath();
				aDoc.setMetadata(Jsoup.parse(new File(absP.substring(0, absP.lastIndexOf('.')) + ".meta"), "UTF-8"));
				aDoc.add(aSen);
			}
			this.doclist.add(aDoc);
		}
	}
	public static void main(String[] args) throws Exception{
		/*PropertyConfigurator.configure("log4j.properties");
		AnnotateAutomator aAutomator = null;
		try {
			aAutomator = new AnnotateAutomator(false);
		} catch (IOException e) {                        
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
		aAutomator.setCorpus(new ACECorpus("./data/ACE_T/Train"));         
		aAutomator.annotate();                                          
		AnnotatedCorpus aC = new AnnotatedCorpus();
		aC.doclist = aAutomator.getaCorpus();
		aC.save(new File("./data/AnnotatedFile/Train"));
		aAutomator.setCorpus(new ACECorpus("./data/ACE_T/Test"));         
		aAutomator.annotate();                                          
		AnnotatedCorpus aCS = new AnnotatedCorpus();
		aCS.doclist = aAutomator.getaCorpus();
		aCS.save(new File("./data/AnnotatedFile/Test"));
		
		AnnotatedCorpus aCR = new AnnotatedCorpus();
		aCR.read(new File("./data/File/"));
		System.out.println(aCR.doclist.getLast());
		aCR.save(new File("./data/File1/"));
		//System.out.println("====================================================================================");
		//System.out.println(aCR.doclist.getLast());*/
	}
}
