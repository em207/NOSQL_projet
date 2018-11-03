import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Dictionnaire extends HashMap<String, Integer>{
	
	public void write() throws IOException {
		
		FileWriter fichier = new FileWriter("dictionnaire.txt");
		
		for(int i=1; i<this.size(); i++) {
			fichier.write(i + " : " + this.get(i) + "\n");
		}
		
		fichier.close();
		
	}

}
