

public class Assignment1 {
	public static void main(String[] args){
		// uncomment the following line to print your output
		char ch = args[0].charAt(0);
		int val = ch - 0;
		char newChar = (char) (val+Integer.parseInt(args[1]));
		System.out.println("New char is " + newChar + ".");
	}
}
