package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
@SuppressWarnings("unchecked")
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	// add members here
	Map<T, Integer> map;
	
	//add constructor here, if needed
	//check2
	
	public HashMapHistogram(){
		this.map = new HashMap<T,Integer>();
	}
	
	@Override
	public void addItem(T item) {
		if (this.map.containsKey(item))
		{
			this.map.put(item, map.get(item)+1);
		}
		else
		{
			this.map.put(item, 1);
		}
	}
	
	@Override
	public boolean removeItem(T item)  {
		if(this.map.containsKey(item))
		{
			if(this.map.get(item) == 1)
			{
				this.map.remove(item);
			}
			else
			{
				this.map.put(item, this.map.get(item)-1);
			}
			return true;

		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void addAll(Collection<T> items) {
		for(T item: items)
		{
			addItem(item);
		}
	}

	@Override
	public int getCountForItem(T item) {
		if(this.map.containsKey(item))
		{
			return this.map.get(item);
		}
		return 0;
	}

	@Override
	public void clear() {
		this.map = new HashMap<T, Integer>();
	}

	@Override
	public Set<T> getItemsSet() {
		return this.map.keySet();
	}
	
	@Override
	public int getCountsSum() {
		int sum = 0;
		for(T item: this.map.keySet())
		{
			sum += this.map.get(item);
		}
		return sum;
	}

	@Override
	public Iterator<Map.Entry<T, Integer>> iterator() {
		return new HashMapHistogramIterator<T>(this.map.entrySet());
	}
	
	//add private methods here, if needed
	public int len()
	{
		return this.map.size();
	}

	public int total_words()
	{
		int sum = 0;
		for(Map.Entry<T, Integer> var: this.map.entrySet())
		{
			sum += var.getValue();
		}
		return sum;
	}
}
