package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item>{
    int capacity;
    List<Item> lst;

    FractionalKnapSack(int c, List<Item> lst1){
        capacity = c;
        lst = lst1;
    }

    public static class Item {
        double weight, value;
        Item(double w, double v) {
            weight = w;
            value = v;
        }

        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }
    }

    @Override
    public Iterator<Item> selection() {
       List<Item> list = new ArrayList<>();
       List<Double> val = new ArrayList<>();
        for (Item item: lst ) {
            val.add(item.value/item.weight);
        }
        for (int i = 0; i < val.size() ; i++) {
            int max = max(val);
            list.add(lst.get(max));
            val.set(max, -5.0);
        }
        return list.iterator();


    }
    public int max(List<Double> lst)
    {
        int i = 0;
        double max = lst.get(0);
        for (int j = 0; j< lst.size(); j++)
        {
            if(lst.get(j)>max)
            {
                max = lst.get(j);
                i = j;
            }
        }
        return i;
    }

    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element)
    {
        return capacity-occupied(candidates_lst)>0;
    }
    public int occupied(List<Item> lst)
    {
        int cnt = 0;
        for (Item item: lst)
        {
            cnt += item.weight;
        }
        return cnt;
    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {
        double quantity = Math.min(element.weight, capacity-occupied(candidates_lst));
        Item n = new Item(quantity,element.value*quantity/ element.weight);
        candidates_lst.add(n);
    }

    @Override
    public boolean solution(List<Item> candidates_lst) {

        if(occupied(candidates_lst) == capacity || candidates_lst.size() == lst.size())
        {
            return true;
        }
        return false;
    }
}
