

public class Assignment02Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		
		int input = Integer.parseInt(args[0]);
		for (double i = 0; i<input; i++)
		{
			double num = 1/((2*i)+1);
			if (i % 2 == 0)
			{
				piEstimation += num;
			}
			else
			{
				piEstimation -= num;
			}
		}
		piEstimation *=4;
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
