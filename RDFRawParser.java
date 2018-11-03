import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

import org.openrdf.model.Statement;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.Rio;
import org.openrdf.rio.helpers.RDFHandlerBase;

public final class RDFRawParser {

	private static class RDFListener extends RDFHandlerBase {
		
		static Dictionnaire dico = new Dictionnaire();
		static DictionnaireInverse dicoInverse = new DictionnaireInverse();
		static List<List<Integer>> index = new ArrayList<List<Integer>>();
		int i = 0;
		String tmp;
		
		static List<Integer> resultat1 = new ArrayList<Integer>();  
		static List<Integer> resultat2 = new ArrayList<Integer>();  
		static List<Integer> resultat3 = new ArrayList<Integer>();

		@Override
		public void handleStatement(Statement st) {	
			
			if(!(dico.containsKey(st.getSubject().toString()))) {
				dico.put(st.getSubject().toString(), dico.size()+1);
				dicoInverse.put(dicoInverse.size()+1, st.getSubject().toString());
				//tmp = (dico.get(st.getSubject()) + ", "  + st.getSubject());
				//System.out.println(tmp);
			}
			
			if(!(dico.containsKey(st.getPredicate().toString()))) {
				dico.put(st.getPredicate().toString(), dico.size()+1);
				dicoInverse.put(dicoInverse.size()+1, st.getPredicate().toString());
				//tmp = (dico.get(st.getPredicate()) + ", "  + st.getPredicate());
				//System.out.println(tmp);
			}
			
			if(!(dico.containsKey(st.getObject().toString()))) {
				dico.put(st.getObject().toString(), dico.size()+1);
				dicoInverse.put(dicoInverse.size()+1, st.getObject().toString());
				//tmp = (dico.get(st.getObject()) + ", "  + st.getObject());
				//System.out.println(tmp);
			}
			
			List<Integer> listEmpt = new ArrayList<Integer>();
		    
		    listEmpt.add((Integer) dico.get(st.getSubject().toString()));
		    listEmpt.add((Integer) dico.get(st.getPredicate().toString()));
		    listEmpt.add((Integer) dico.get(st.getObject().toString()));
		    
		    //System.out.println(i + ": <" + listEmpt.get(0) + ", " + listEmpt.get(1) + ", " + listEmpt.get(2) + ">");
		    
		    index.add(listEmpt);
		    i++;
		}
		
		public static Dictionnaire getDico() {
			return dico;
		}
		
		public static DictionnaireInverse getDicoInv() {
			return dicoInverse;
		}
		
		public static List<List<Integer>> getIndex(){
			return index;
		}
		
		//les 3 methodes pour acceder aux donn√©es
				//fonction qui retrouve le numero des triplets qui ont idSujet comme subject
		public static List<Integer> AvoirSubjectById(int idSujet){
			for(int j=1; j<= index.size(); j++){
				if(index.get(j-1).get(0) == idSujet){
					resultat1.add(j-1);
				}				}
			return resultat1;

		}
				
				
		public static List<Integer> AvoirPredicatById(int idPredicat){
			for(int j=1; j<= index.size(); j++){
				if(index.get(j-1).get(1) == idPredicat){
					resultat2.add(j-1);
				}
			}
			return resultat2;
		}
				
		public static List<Integer> AvoirObjectById(int idObject){
			for(int j=1; j<= index.size(); j++){
				if(index.get(j-1).get(2) == idObject){
					resultat3.add(j-1);
				}
			}
			return resultat3;
		}

	};

	public static void main(String args[]) throws IOException {
		
	    for (String arg:args)
	        System.out.println(arg);

		Reader reader = new FileReader("C:\\Users\\Emery\\Desktop\\M2\\NOSQL\\DonnÈes RDFXML\\500K.rdfxml");

		org.openrdf.rio.RDFParser rdfParser = Rio
				.createParser(RDFFormat.RDFXML);
		rdfParser.setRDFHandler(new RDFListener());
		
		try {
			rdfParser.parse(reader, "");
		} catch (Exception e) {

		}

		try {
			reader.close();
		} catch (IOException e) {
		}
		
		
		Dictionnaire dictionnaire = RDFListener.getDico();
		DictionnaireInverse dictionnaireInverse = RDFListener.getDicoInv();
		List<List<Integer>> index = RDFListener.getIndex();
		
		//List<Integer> results= RDFListener.AvoirPredicatById(2);
		
		//System.out.println(results);
		
		System.out.println("Ecriture du dictionnaire dans \"dictionnaire.txt\"");
		
		dictionnaireInverse.write();
		
		System.out.println("Le dictionnaire est Ècrit !");
		
		System.out.println("Ecriture de l'index dans \"index.txt\"");
		
		FileWriter fichier = new FileWriter("index.txt");		
		
		for(int i=0; i<index.size(); i++) {
			fichier.write(i + ": " + index.get(i) + " \n");
		}
		
		System.out.println("L'index est Ècrit !\n");
		
		fichier.close();
		
		//////////////////////////////////////////////////////////////////////////
		
		FileReader f = new FileReader("C:\\Users\\Emery\\Desktop\\M2\\NOSQL\\watdiv-mini-projet\\watdiv-mini-projet\\testsuite\\queries\\Q_4_location_nationality_gender_type.queryset");
		BufferedReader r = new BufferedReader(f);
		
		String s;
		
		int i = 0;
		int j = 0;
		
		List<Integer> ResultsPredicate = new ArrayList<Integer>();
		List<Integer> ResultsObject = new ArrayList<Integer>();
		
		FileWriter fichier2 = new FileWriter("sortieTest.txt");
		
		while ((s = r.readLine()) != null) {
			
			if(s.contains("SELECT")) {
				i++;
				System.out.println("Requete " + i + " en cour de traitement.");
			}
			
			s = s.replace("SELECT", Integer.toString(i));
			
			s = s.replace("WHERE", "");
			
			s = s.replace("{", "");
			
			s = s.replace("?v0", "");		
			
			s = s.replace("<", "");
			
			s = s.replace(">", "");
			
			s = s.replace(" .", "");
			
			s = s.replace("}", " }");
			
			for(String string: s.split(" ")) {
				
				if(string.contains("http")) {	
					//System.out.println(string);
					if(j==0) {
						ResultsPredicate = RDFListener.AvoirPredicatById(dictionnaire.get(string));						
						fichier2.write("\n \n");
						fichier2.write("predicate : " + string + " : " + dictionnaire.get(string) + "\n");						
						
						j++;
					} else {
						ResultsObject = RDFListener.AvoirObjectById(dictionnaire.get(string));
						fichier2.write("object : " + string + " : " + dictionnaire.get(string) + "\n");	
						
						j--;
					}
					
				}
				
				if(string.contains("}")) {
					
					fichier2.write("\n");
					fichier2.write("resultat(s) de la requete " + i + " : \n");					
					
					ResultsObject.retainAll(ResultsPredicate);
					
					Set<Integer> mySet = new LinkedHashSet<Integer>();
					
					mySet.addAll(ResultsObject);					

					ResultsObject.clear();
					
					ResultsObject.addAll(mySet);
					
					mySet.clear();
					
					for(int k = 0; k<ResultsObject.size(); k++) {
						mySet.add(index.get(ResultsObject.get(k)).get(0));
					}
					
					for (int s1 : mySet) {
					    fichier2.write( s1 + "\n");
					}
					
					ResultsPredicate.clear();
					ResultsObject.clear();
					
					System.out.println("Requete " + i + " achevÈe.");
					
					fichier2.write(mySet.size() + " resultat(s) retournÈ(s)\n");		
					System.out.println(mySet.size() + " resultat(s) retournÈ(s). \n");
					
				}
			}
				
		}
		
		fichier2.close();
		
		f.close();

	}

}