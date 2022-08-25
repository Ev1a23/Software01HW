package il.ac.tau.cs.sw1.bufferedIO;

import javax.lang.model.type.NullType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTester {
	public static final String INPUT_FOLDER = "../resources/";

	public static void main(String[] args) throws IOException{
		System.out.println("First check");
		String inputFileName = INPUT_FOLDER + "rocky1.txt";
		FileReader fReader = new FileReader(new File(inputFileName));
		IBufferedReader bR = new MyBufferReader(fReader, 1000);
		String out = bR.getNextLine();
		if (!out.equals("Now somewhere in the Black mining Hills of Dakota")){
			System.out.println("Reader Error: wrong firstLine");
		}
		out = bR.getNextLine();
		if (!out.equals("There lived a young boy named Rocky Raccoon, ")){
			System.out.println("Reader Error: wrong 2nd");
		}
		out = bR.getNextLine();
		if (!out.equals("And one day his woman ran off with another guy, ")){
			System.out.println("Reader Error: wrong 3rd");
		}
		out = bR.getNextLine();
		if (!out.equals("Hit young Rocky in the eye.")){
			System.out.println("Reader Error: wrong 4th");
		}
		out = bR.getNextLine();
		if (!out.equals("Rocky didn't like that")){
			System.out.println("Reader Error: wrong 5th");
		}

		out = bR.getNextLine();
		if (!out.equals("")){
			System.out.println("Reader Error: wrong 6th");
		}

		out = bR.getNextLine();
		if (!out.equals("He said, I'm gonna get that boy.")){
			System.out.println("Reader Error: wrong 7th");
		}

		out = bR.getNextLine();
		if (!out.equals("So one day he walked into town")){
			System.out.println("Reader Error: wrong 8th");
		}

		out = bR.getNextLine();
		if (!out.equals("Booked himself a room in the local saloon.")){
			System.out.println("Reader Error: wrong 9th");
		}

		out = bR.getNextLine();
		if (!out.equals("in the rocky rocky")){
			System.out.println("Reader Error: wrong 10th");
		}

		out = bR.getNextLine();

		if (out != ""){
			System.out.println("Reader Error: wrong 11th");
		}

		for(int i = 0; i< 10; i++)
		{
			if(bR.getNextLine() == null)
			{
				continue;
			}
			else
			{
				System.out.println("err end of file not as expected");
			}
		}
		System.out.println("Second check");
		String inputFileName2 = INPUT_FOLDER + "rocky1.txt";
		FileReader fReader2 = new FileReader(new File(inputFileName2));
		IBufferedReader bR2 = new MyBufferReader(fReader2, 10);
		out = bR2.getNextLine();

		if (!out.equals("Now somewhere in the Black mining Hills of Dakota")){
			System.out.println("Reader Error: wrong firstLine");
		}
		out = bR2.getNextLine();
		if (!out.equals("There lived a young boy named Rocky Raccoon, ")){
			System.out.println("Reader Error: wrong 2nd");
		}
		out = bR2.getNextLine();
		if (!out.equals("And one day his woman ran off with another guy, ")){
			System.out.println("Reader Error: wrong 3rd");
		}
		out = bR2.getNextLine();
		if (!out.equals("Hit young Rocky in the eye.")){
			System.out.println("Reader Error: wrong 4th");
		}
		out = bR2.getNextLine();
		if (!out.equals("Rocky didn't like that")){
			System.out.println("Reader Error: wrong 5th");
		}

		out = bR2.getNextLine();
		if (!out.equals("")){
			System.out.println("Reader Error: wrong 6th");
		}

		out = bR2.getNextLine();
		if (!out.equals("He said, I'm gonna get that boy.")){
			System.out.println("Reader Error: wrong 7th");
		}

		out = bR2.getNextLine();
		if (!out.equals("So one day he walked into town")){
			System.out.println("Reader Error: wrong 8th");
		}

		out = bR2.getNextLine();
		if (!out.equals("Booked himself a room in the local saloon.")){
			System.out.println("Reader Error: wrong 9th");
		}

		out = bR2.getNextLine();
		if (!out.equals("in the rocky rocky")){
			System.out.println("Reader Error: wrong 10th");
		}

		out = bR2.getNextLine();

		if (out != ""){
			System.out.println("Reader Error: wrong 11th");
		}

		for(int i = 0; i< 10; i++)
		{
			if(bR2.getNextLine() == null)
			{
				continue;
			}
			else
			{
				System.out.println("err end of file not as expected");
			}
		}
		fReader.close();
		bR.close();
		System.out.println("Done!");
	}
}
