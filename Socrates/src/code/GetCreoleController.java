package code;

import java.io.File;
import java.io.IOException;

import gate.CorpusController;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.LanguageAnalyser;
import gate.ProcessingResource;
import gate.creole.ANNIEConstants;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;

public class GetCreoleController {

	public static CorpusController createController() throws GateException, IOException {
		
		Gate.getCreoleRegister().registerDirectories(
				new File(Gate.getPluginsHome(), "Tools").toURI().toURL());
		
		Gate.getCreoleRegister().registerDirectories(
				new File(Gate.getPluginsHome(), "LingPipe").toURI().toURL());
		
		SerialAnalyserController controller = (SerialAnalyserController) 
				PersistenceManager.loadObjectFromFile(new File(new File( 
				Gate.getPluginsHome(), ANNIEConstants.PLUGIN_DIR),ANNIEConstants.DEFAULT_FILE));
		
		controller.remove(3); // removing the ANNIE Sentence Splitter
		
		 FeatureMap paramsg = Factory.newFeatureMap();  
		 paramsg.put("listsURL", new File("resources/rules/abr.def").toURI().toURL());  
		 LanguageAnalyser mainGazetteer = (LanguageAnalyser)Factory.createResource(  
		     "gate.creole.gazetteer.DefaultGazetteer", paramsg);
			
		 controller.add(3,mainGazetteer);

		 FeatureMap paramsss = Factory.newFeatureMap();  
		 ProcessingResource sentenceSplitter = (ProcessingResource)Factory.createResource(  
			     "gate.lingpipe.SentenceSplitterPR", paramsss);
			 
		 controller.add(4,sentenceSplitter);

		ProcessingResource transfer = (ProcessingResource) Factory.
	        		createResource("gate.creole.annotransfer.AnnotationSetTransfer", Factory.newFeatureMap());
		FeatureMap map = Factory.newFeatureMap();
        map.put("inputASName","Original markups");
        
		transfer.setParameterValues(map);
		
		FeatureMap params = Factory.newFeatureMap();  
		params.put("grammarURL", new File("resources/rules/getSentences.jape").toURI().toURL());  
		ProcessingResource transducer = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params);
		
		FeatureMap params1 = Factory.newFeatureMap();  
		params1.put("grammarURL", new File("resources/rules/gets2.jape").toURI().toURL());  
		ProcessingResource transducer1 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params1);
		
		FeatureMap params2 = Factory.newFeatureMap();  
		params2.put("grammarURL", new File("resources/rules/gets3.jape").toURI().toURL());  
		ProcessingResource transducer2 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params2);
		
		FeatureMap params3 = Factory.newFeatureMap();  
		params3.put("grammarURL", new File("resources/rules/gets4.jape").toURI().toURL());  
		ProcessingResource transducer3 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params3);
		
		FeatureMap params4 = Factory.newFeatureMap();  
		params4.put("grammarURL", new File("resources/rules/gets5.jape").toURI().toURL());  
		ProcessingResource transducer4 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params4);
		
		FeatureMap params5 = Factory.newFeatureMap();  
		params5.put("grammarURL", new File("resources/rules/sentence.jape").toURI().toURL());  
		ProcessingResource transducer5 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params5);
		FeatureMap params6 = Factory.newFeatureMap();  
		params6.put("grammarURL", new File("resources/rules/sentence1.jape").toURI().toURL());  
		ProcessingResource transducer6 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params6);
		FeatureMap params7 = Factory.newFeatureMap();  
		params7.put("grammarURL", new File("resources/rules/sentence2.jape").toURI().toURL());  
		ProcessingResource transducer7 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params7);
		FeatureMap params8 = Factory.newFeatureMap();  
		params8.put("grammarURL", new File("resources/rules/sentence3.jape").toURI().toURL());  
		ProcessingResource transducer8 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params8);
		FeatureMap params9 = Factory.newFeatureMap();  
		params9.put("grammarURL", new File("resources/rules/sentence3_1.jape").toURI().toURL());  
		ProcessingResource transducer9 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params9);
		FeatureMap params10 = Factory.newFeatureMap();  
		params10.put("grammarURL", new File("resources/rules/sentence3_2.jape").toURI().toURL());  
		ProcessingResource transducer10 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params10);
		FeatureMap params11 = Factory.newFeatureMap();  
		params11.put("grammarURL", new File("resources/rules/sentence3_3.jape").toURI().toURL());  
		ProcessingResource transducer11 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params11);
		FeatureMap params12 = Factory.newFeatureMap();  
		params12.put("grammarURL", new File("resources/rules/sentence4.jape").toURI().toURL());  
		ProcessingResource transducer12 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params12);
		
		
		FeatureMap params13 = Factory.newFeatureMap();  
		params13.put("grammarURL", new File("resources/rules/1.jape").toURI().toURL());  
		ProcessingResource transducer13 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params13);
		
		FeatureMap params14 = Factory.newFeatureMap();  
		params14.put("grammarURL", new File("resources/rules/2.jape").toURI().toURL());  
		ProcessingResource transducer14 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params14);
		
		FeatureMap params15 = Factory.newFeatureMap();  
		params15.put("grammarURL", new File("resources/rules/3f.jape").toURI().toURL());  
		ProcessingResource transducer15 = (ProcessingResource)Factory.createResource(  
		     "gate.creole.Transducer", params15);
		
		controller.add(transfer);
		controller.add(transducer1);
		controller.add(transducer);
		controller.add(transducer2);
		controller.add(transducer3);
		controller.add(transducer4);
		controller.add(transducer5);
		controller.add(transducer6);
		controller.add(transducer7);
		controller.add(transducer8);
		controller.add(transducer9);
		controller.add(transducer10);
		controller.add(transducer11);
		controller.add(transducer12);
		controller.add(transducer13);
		controller.add(transducer14);
		controller.add(transducer15);

		return controller;
		
	}

}
