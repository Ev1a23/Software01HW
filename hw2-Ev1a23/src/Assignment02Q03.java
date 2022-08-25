
public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfOdd = 0;
		int n = -1;
		// *** your code goes here below ***
		n = Integer.parseInt(args[0]);
		int n1 = 0;
		int n2 = 1;
		int tmp = 0;
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		// *** your code goes here below ***
		for(int i = 0; i<n; i++)
		{
			System.out.print(n2+" ");
			if (n2 %2 == 1)
			{
				numOfOdd++;
			}
			tmp = n1;
			n1 = n2;
			n2 = tmp+n1;

		}
		System.out.println();
		System.out.println("The number of odd numbers is: "+numOfOdd);

	}

}
