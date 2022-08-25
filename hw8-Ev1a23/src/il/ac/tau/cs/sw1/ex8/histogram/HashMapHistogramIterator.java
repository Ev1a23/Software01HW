package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
@SuppressWarnings("unchecked")
public class HashMapHistogramIterator<T extends Comparable<T>>
		implements Iterator<Map.Entry<T, Integer>>{

	//add members here
	int ind; ///index for iterator
	List<T> keys; //keys lst
	List<Integer> cnts;
	Comparator<T> comp;//comperator

	//add constructor here, if needed
	public HashMapHistogramIterator(Set<Map.Entry<T, Integer>> set)
	{
		this.ind = 0;
		this.keys = new ArrayList<>();
		this.cnts = new ArrayList<>();
		this.comp = new Comperator();
		for(Map.Entry<T, Integer> var: set)
		{
			this.keys.add(var.getKey());
			this.cnts.add(var.getValue());
		}
		this.comp = new Comperator();
		Collections.sort(this.keys, this.comp);
	}
	@Override
	public boolean hasNext() {
		return this.ind < this.keys.size();
	}

	@Override
	public Map.Entry<T, Integer> next() {
		T nxt = this.keys.get(this.ind);
		int cnt = this.cnts.get(this.ind);
		this.ind +=1;
		return new AbstractMap.SimpleEntry<>(nxt,cnt);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();

	}

	//add private methods here, if needed
	public class Comperator implements Comparator<T>
	{

		@Override
		public int compare(T o1, T o2) {
			return o1.compareTo(o2);
		}
	}
}
