import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Index implements List<List<Integer>>{
	
	public void write() throws IOException {
		
		FileWriter fichier = new FileWriter("index.txt");
		
		for(int i=0; i<this.size(); i++) {
			fichier.write(i + " : " + this.get(i) + "\n");
		}
		
		fichier.close();
		
	}
	
	public List<Integer> getTriplesByPredicate(int idPredicate){
		
		List<Integer> Results = new ArrayList<Integer>();		
		List<Integer> ListTemp = new ArrayList<Integer>();
		
		for(int i=0; i<this.size(); i++) {
			ListTemp = this.get(i);
			if(ListTemp.get(0) == idPredicate){
				Results.add(i);
			}
		}
		
		return Results;
		
	}
	
	

	@Override
	public boolean add(List<Integer> e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int index, List<Integer> element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection<? extends List<Integer>> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends List<Integer>> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<List<Integer>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<List<Integer>> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<List<Integer>> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> set(int index, List<Integer> element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<List<Integer>> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
