
public class ArrayUtils {

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		if (!(direction=='R' || direction =='L') || array.length == 0)
		{
			return array;
		}
		if(move < 0)
		{
			while (move<0)
			{
				move+= array.length;
			}
		}
		move = move% array.length;
		if (direction == 'L')
			move = array.length-move;
		int[] arr_help = new int[array.length];
		for (int i = 0; i<array.length; i++)
		{
			arr_help[(move+i)% array.length] = array[i];
		}
		array = arr_help;
		return array;

	}

	public static int findShortestPath(int[][] m, int i, int j)
	{
		int [] help = new int[m.length];
		return shortest_path_rec(m,i,j,help);
	}
	public static int shortest_path_rec(int[][] m,int i, int j, int[] help)
	{
		if (i==j)
		{
			return 0;
		}
		if (m[i][j] == 1)
		{
			return 1;
		}
		int cnt_min = m.length*m.length+1;
		int cnt = 0;
		boolean flag = false;
		for(int ind = 0; ind<m.length; ind++)
		{
			if(m[i][ind] ==1 && i!= ind && help[ind]== 0 )
			{
				help[ind] = 1;
				cnt = shortest_path_rec(m,ind,j,help)+1;
				if (cnt<cnt_min &&cnt !=0)
				{
					flag = true;
					cnt_min = cnt;
				}
				help[ind] = 0;
			}
		}
		if (flag)
		{
			return cnt_min;
		}
		return -1;
	}

}

