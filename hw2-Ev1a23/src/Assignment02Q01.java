
public class Assignment02Q01 {

	public static void main(String[] args) {
		int cnt = 0;
		for (int i = 0; i< args.length;i++)
		{
			char c1 = args[i].charAt(0);
			int val = c1;
			if (val%5 == 0)
			{
				if (cnt ==0 )
				{
					cnt +=1;
					System.out.print(c1);
				}
				else
				{
					System.out.println();
					System.out.print(c1);
				}
			}
		}
		//for (String str: args)
		//{
		//	char c1 = str.charAt(0);
		//	int val = c1;
		//	if (val % 5 ==0)
		//	{
		//		System.out.println(c1);
		//	}
		}
		
	}


