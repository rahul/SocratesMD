package code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.gui.MainFrame;
import gate.util.GateException;

public class ExtractFacts {

	static String fileName = null;
	static String fileName2 = null;
	static String fileName3 = null;
	static CorpusController controller ;

	public static void main(String[] args) throws IOException, ResourceInstantiationException{
		
		Properties props = System.getProperties();

    	props.setProperty("gate.home", "F:/gate/");
        props.setProperty("gate.plugins.home", "F:/gate/plugins");
         	
     	System.setProperties(props);
    	try {
			gate.Gate.init();
		} catch (GateException e) {
			e.printStackTrace();
		}
    	MainFrame.getInstance().setVisible(true);

    	System.out.println("GATE initialized !!!");
    	
		fileName = "resources/35581-Bro-UltiMate-3000-LC-Systems-04Aug2010-LPN1820-05.pdf";
		fileName2 = "resources/61352-DS-ACC3000-PS70472.pdf";
		fileName3 = "resources/62475-Bro-UltiMate-3000-Basic-Automated-BR70152_E.pdf";
		 
        try {
        	controller = GetCreoleController.createController();
        } catch (GateException e) {
			e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
        }
        
    	readFromExcel();
		System.out.println("DONE");
	}
	
	public static void readFromExcel() throws IOException, ResourceInstantiationException{
        
        Corpus corpus;
		
		corpus = Factory.newCorpus("Corpus");
        
		FeatureMap docParams = Factory.newFeatureMap();
		docParams.put(Document.DOCUMENT_URL_PARAMETER_NAME, new File(fileName).toURI().toURL());
		docParams.put(Document.DOCUMENT_MIME_TYPE_PARAMETER_NAME,"application/pdf");
		Document doc = (Document)Factory.createResource("gate.corpora.DocumentImpl", docParams);
				
		docParams.put(Document.DOCUMENT_URL_PARAMETER_NAME, new File(fileName2).toURI().toURL());
		docParams.put(Document.DOCUMENT_MIME_TYPE_PARAMETER_NAME,"application/pdf");
		Document doc2 = (Document)Factory.createResource("gate.corpora.DocumentImpl", docParams);
				
		docParams.put(Document.DOCUMENT_URL_PARAMETER_NAME, new File(fileName3).toURI().toURL());
		docParams.put(Document.DOCUMENT_MIME_TYPE_PARAMETER_NAME,"application/pdf");
		Document doc3 = (Document)Factory.createResource("gate.corpora.DocumentImpl", docParams);
			

		corpus.add(doc);
		corpus.add(doc2);
		corpus.add(doc3);
		
        process(corpus,controller);         
        corpus.cleanup();
  	}

	private static void process(Corpus corpus, CorpusController controller) {

		controller.setCorpus(corpus);
    	try {
			controller.execute();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    	
		System.out.println("Annie run !!");
		List<Document> docs = new ArrayList<Document>();

		Iterator<Document> it = corpus.iterator();		
		
		while(it.hasNext()){

			Document doc = it.next();
			docs.add(doc);
		}
		corpus.cleanup();
		cleanUp(corpus, docs);
	}
	
	public static void cleanUp(Corpus corp, List<Document> docs){
		
		Iterator<Document> it = docs.iterator();
		while(it.hasNext()){
			
			Document doc = it.next();
			Factory.deleteResource(doc);
		}
		Factory.deleteResource(corp);
	}
}

