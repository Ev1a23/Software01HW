
public class StringUtils {

	public static String findSortedSequence(String str) {
		if (str.length() == 0) {
			return "";
		}
		int max_seq = 0;
		String[] arr_str = str.split(" ");
		if (arr_str.length == 1)
		{
			return str;
		}
		if(arr_str.length == 2)
		{
			if (arr_str[0].compareTo(arr_str[1])<=0)
			{
				return arr_str[0]+arr_str[1];
			}
			return arr_str[1];
		}
		String[] arr_out = new String[arr_str.length];
		int cnt = 0;
		String[] check = new String[arr_str.length];
		for (int i = 1; i<arr_str.length; i++)
		{
			if (arr_str[i-1].compareTo(arr_str[i]) <= 0)
			{
				cnt+=1;
				check[i-1] = arr_str[i-1];
				check[i] = arr_str[i];
			}
			else
			{
				if (cnt >= max_seq && i!=1)
				{
					max_seq = cnt;
					arr_out = check;
					cnt = 0;
					check = new String[arr_str.length];
				}
			}
			if(i == arr_str.length-1)
			{
				if (cnt >= max_seq && cnt!=0)
				{
					max_seq = cnt;
					arr_out = check;
				} else if (cnt == 0 && max_seq == 0) {
					max_seq = 1;
					arr_out = new String[1];
					arr_out[0] = arr_str[i];
				}
			}

		}
		String str_out = "";
		for (String s:arr_out)
		{
			if (!(s==" "||s== null))
			{
				str_out = str_out +" "+ s;
			}

		}
		if(str_out.length()>0)
		{
			return str_out.substring(1);
		}
		return "";

	}

	
	public static boolean isEditDistanceOne(String a, String b)
	{
		if(a.equals(b))
		{
			return true;
		}
		if(Math.abs(a.length()-b.length())>=2)
		{
			return false;
		}
		if(a.length() == b.length())
		{
			for (int i = 0; i < a.length(); i++)
			{
				if(i==0)
				{
					if(b.equals(b.charAt(0)+a.substring(1)))
					{
						return true;
					}
				}
				else if (i==a.length()-1)
				{
					if(b.equals(a.substring(0,a.length()-1)+b.charAt(b.length()-1)))
					{
						return true;
					}
				}
				else
				{
					if(b.equals(a.substring(0,i)+b.charAt(i)+a.substring(i+1)))
					{
						return true;
					}
				}
			}
			return false;
		}
		else if  (b.length() >a.length())
		{
			a = a+b;
			b = a.substring(0,(a.length()-b.length()));
			a = a.substring((b.length()));
		}
		if (a.equals(a.charAt(0)+b)){
			return true;}
		for (int i = 1; i<b.length(); i++)
		{
			if(a.equals(b.substring(0,i)+a.charAt(i)+b.substring(i,b.length())))
			{
				return true;
			}
		}
		if (a.equals(b+a.charAt(b.length())))
		{
			return true;
		}
		return false;
	}

}
