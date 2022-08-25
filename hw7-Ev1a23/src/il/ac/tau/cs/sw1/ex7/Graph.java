package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class Graph implements Greedy<Graph.Edge>{
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,..., n]

    Graph(int n1, List<Edge> lst1){
        lst = lst1;
        n = n1;
    }

    public static class Edge{
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }
    }

    @Override
    public Iterator<Edge> selection() {
        List<Edge> list = new ArrayList<>();
        List<Double> weights = new ArrayList<>();
        for (Edge edge: lst)
        {
            weights.add(edge.weight);
        }
        for (int i = 0; i < lst.size() ; i++)
        {
            int min = min(weights);
            list.add(lst.get(min));
            weights.set(min, 100000000.0);
        }
        return list.iterator();
    }
    public int min(List<Double> weights)
    {
        int i = 0;
        double min = weights.get(0);
        int n1 = lst.get(0).node1;
        int n2 = lst.get(0).node2;
        for (int j = 0; j< weights.size(); j++)
        {
            if(weights.get(j)<min)
            {
                min = weights.get(j);
                n1 = lst.get(j).node1;
                n2 = lst.get(j).node2;
                i = j;
                continue;
            }
            else if(weights.get(j)== min)
            {
                if(lst.get(j).node1 < n1)
                {
                    min = weights.get(j);
                    n1 = lst.get(j).node1;
                    n2 = lst.get(j).node2;
                    i = j;
                    continue;
                }
                else if (lst.get(j).node1 == n1)
                {
                    if(lst.get(j).node2<n2)
                    {
                        min = weights.get(j);
                        n1 = lst.get(j).node1;
                        n2 = lst.get(j).node2;
                        i = j;
                        continue;
                    }
                }
            }
        }
        return i;
    }

    @Override
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
        if(element.node1 == element.node2)
        {
            return false;
        }
        candidates_lst.add(element);
        for(Edge edg: candidates_lst)
        {
            int n1 = edg.node1;
            int n2 = edg.node2;
            boolean b1 = checkCircle(candidates_lst,n1,edg);
            boolean b2 = checkCircle(candidates_lst,n2,edg);
            if(b1 == true || b2 == true)
            {
                candidates_lst.remove(element);
                return false;
            }
        }
        candidates_lst.remove(element);
        return true;
    }

    public boolean checkCircle(List<Edge> lst, int n, Edge edge)
    {
        int source = n;
        int help = 0;
        if(edge.node1 ==n)
        {
            help = edge.node2;
        }
        else
        {
            help = edge.node1;
        }
        boolean bool = false;
        List<Edge> visited = new ArrayList<>();
        visited.add(edge);
        for (Edge check: lst)
        {
            if(check.node1 == help && visited.lastIndexOf(check)==-1)
            {
                bool = true;
                help = check.node2;
                visited.add(check);
            }
            else if(check.node2 == help&& (visited.lastIndexOf(check)==-1))
            {
                bool = true;
                help = check.node1;
                visited.add(check);
            }
            if(help == n && bool == true)
            {
                return true;
            }
        }
        return false;

    }

    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
        candidates_lst.add(element);
    }


    @Override
    public boolean solution(List<Edge> candidates_lst) {
        return candidates_lst.size() == n;
    }
}
