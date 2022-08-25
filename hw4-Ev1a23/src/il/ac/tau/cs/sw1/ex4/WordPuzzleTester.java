package il.ac.tau.cs.sw1.ex4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class WordPuzzleTester {
	public static void main(String[] args) {
		// puzzle1 = {w,h,_,i,_}
		char[] puzzle1 = WordPuzzle.createPuzzleFromTemplate("while",
				new boolean[] { false, false, true, false, true });
		if (!Arrays.equals(puzzle1, new char[] { 'w', 'h', WordPuzzle.HIDDEN_CHAR, 'l', WordPuzzle.HIDDEN_CHAR })) {
			System.err.println("Error 1.1");
		}
		char[] puzzle2 = WordPuzzle.createPuzzleFromTemplate("a",
				new boolean[] { true });
		if (!Arrays.equals(puzzle2, new char[] { WordPuzzle.HIDDEN_CHAR })) {
			System.err.println("Error 1.2");
		}

		char[] puzzle3 = WordPuzzle.createPuzzleFromTemplate("abcd",
				new boolean[] { false, false, false, false });
		if (!Arrays.equals(puzzle3, new char[] { 'a','b','c','d' })) {
			System.err.println("Error 1.3");
		}

		char[] puzzle4 = WordPuzzle.createPuzzleFromTemplate("abcd",
				new boolean[] { true, false, false, false });
		if (!Arrays.equals(puzzle4, new char[] { WordPuzzle.HIDDEN_CHAR,'b','c','d' })) {
			System.err.println("Error 1.4");
		}

		char[] puzzle5 = WordPuzzle.createPuzzleFromTemplate("abcd",
				new boolean[] { true, true, true, true });
		if (!Arrays.equals(puzzle5, new char[] { WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR })) {
			System.err.println("Error 1.4");
		}

		boolean[] template1 = { true, true, true, false, true, true };
		boolean[] template2 = { true, true, false, false, true, true };
		boolean[] template3 = { true, true, true, true, true, true };
		boolean[] template4 = { false };
		boolean[] template5 = { true };
		boolean[] template6 = { false, true };
		boolean[] template7 = { true, false };
		boolean[] template8 = { true, true, false };
		boolean[] template9 = { true, true, true, true, false, false };
		boolean[] template10 = { false, false, true, true, false, false };
		boolean[] template11 = { false, false, false, false, false, false };
		boolean[] template12 = { false, true };
		boolean[] template13 = { true, false };
		boolean[] template14 = { true, true, false, false };

		// puzzle = {_,_,_,e,_,_}
		if (WordPuzzle.checkLegalTemplate("wheeps", template1)) {
			System.err.println("Error 2.1");
		}

		// puzzle = {_,_,e,e,_,_}
		if (!WordPuzzle.checkLegalTemplate("wheeps", template2)) {
			System.err.println("Error 2.2");
		}

		// puzzle = {_,_,_,_,_,_}
		if (WordPuzzle.checkLegalTemplate("wheeps", template3)) {
			System.err.println("Error 2.3");
		}

		// puzzle = {'e'}
		if (WordPuzzle.checkLegalTemplate("e", template4)) {
			System.err.println("Error 2.4");
		}

		// puzzle = {'_'}
		if (WordPuzzle.checkLegalTemplate("e", template5)) {
			System.err.println("Error 2.5");
		}

		// puzzle = {'e','_'}
		if (!WordPuzzle.checkLegalTemplate("ep", template6)) {
			System.err.println("Error 2.6");
		}

		// puzzle = {'_','e'}
		if (!WordPuzzle.checkLegalTemplate("pe", template7)) {
			System.err.println("Error 2.7");
		}

		// puzzle = {'_','_','e'}
		if (WordPuzzle.checkLegalTemplate("wee", template8)) {
			System.err.println("Error 2.8");
		}

		// puzzle = {'_','_','_','_','p','s'}
		if (!WordPuzzle.checkLegalTemplate("wheeps", template9)) {
			System.err.println("Error 2.9");
		}

		// puzzle = {'w','h','_','_','p','s'}
		if (!WordPuzzle.checkLegalTemplate("wheeps", template10)) {
			System.err.println("Error 2.10");
		}

		// puzzle = {'w','h','e','e','p','s'}
		if (WordPuzzle.checkLegalTemplate("wheeps", template11)) {
			System.err.println("Error 2.11");
		}

		// puzzle = {'e','_'}
		if (WordPuzzle.checkLegalTemplate("ee", template12)) {
			System.err.println("Error 2.12");
		}

		// puzzle = {'_','e'}
		if (WordPuzzle.checkLegalTemplate("ee", template13)) {
			System.err.println("Error 2.13");
		}

		// puzzle = {'','_','e','l'}
		if (WordPuzzle.checkLegalTemplate("elel", template14)) {
			System.err.println("Error 2.14");
		}

		boolean[][] templates = WordPuzzle.getAllLegalTemplates("look", 1);
		if (templates.length != 2) {
			System.err.println("Error 3.1");
		}

		if (!Arrays.equals(templates[0],(new boolean[] { false, false, false, true }))) {
			System.err.println("Error 3.2");
		}

		boolean[][] templates2 = WordPuzzle.getAllLegalTemplates("look", 4);
		if (templates2.length != 0) {
			System.err.println("Error 3.3");
		}

		boolean[][] templates3 = WordPuzzle.getAllLegalTemplates("look", 0);
		if (templates3.length != 0) {
			System.err.println("Error 3.4");
		}

		boolean[][] templates4 = WordPuzzle.getAllLegalTemplates("oooo", 1);
		if (templates4.length != 0) {
			System.err.println("Error 3.5");
		}

		boolean[][] templates5 = WordPuzzle.getAllLegalTemplates("oo", 1);
		if (templates5.length != 0) {
			System.err.println("Error 3.6");
		}

		boolean[][] templates6 = WordPuzzle.getAllLegalTemplates("oe", 1);
		if (templates6.length != 2) {
			System.err.println("Error 3.7");
		}
		boolean[][] templates7 = WordPuzzle.getAllLegalTemplates("abcdefg", 2);
		if (templates7.length != 21) {
			System.err.println("Error 3.8");
		}
		boolean[] res7 = new boolean[7];
		res7[0] = true;
		res7[1] = true;
		if (!Arrays.equals(templates7[20], res7)) {
			System.err.println("Error 3.9");
		}
//		 UNCOMMENT IF YOU FAIL 3.10 to check yourself
//		 for (boolean[] template: templates7) {
//			System.out.println(Arrays.toString(template));
//		}
		res7[0] = false;
		res7[2] = true;
		if (!Arrays.equals(templates7[14], res7)) {
			System.err.println("Error 3.10");
		}

		boolean[][] templates8 = WordPuzzle.getAllLegalTemplates("nylcpeodce", 3); // 10 letters long
		if (templates8.length != 32) {
			System.err.println("Error 3.11");
		}
//		 UNCOMMENT IF YOU FAIL 3.12 to check yourself
//		 for (boolean[] template: templates8) {
//			System.out.println(Arrays.toString(template));
//		}
		if (!Arrays.equals(templates8[0], new boolean[] {false, false, false, false, false, true, false, true, false, true})) {
			System.err.println("Error 3.12");
		}

		// puzzle = {w,_,_,_,_s}
		char[] puzzle = WordPuzzle.createPuzzleFromTemplate("wheeps",
				new boolean[] { false, true, true, true, true, false });

		int numOfChangedLetters = WordPuzzle.applyGuess('h', "wheeps", puzzle);
		if (numOfChangedLetters != 1) {
			System.err.println("Error 4.1");
		}
		numOfChangedLetters = WordPuzzle.applyGuess('e', "wheeps", puzzle);
		if (numOfChangedLetters != 2) {
			System.err.println("Error 4.2");
		}
		numOfChangedLetters = WordPuzzle.applyGuess('s', "wheeps", puzzle);
		if (numOfChangedLetters != 0) {
			System.err.println("Error 4.3");
		}
		numOfChangedLetters = WordPuzzle.applyGuess('j', "wheeps", puzzle);
		if (numOfChangedLetters != 0) {
			System.err.println("Error 4.4");
		}
		numOfChangedLetters = WordPuzzle.applyGuess('e', "wheeps", puzzle);
		if (numOfChangedLetters != 0) {
			System.err.println("Error 4.5");
		}

		boolean[] already_guessed = new boolean[26];

		// already guessed [a,b,c,d]
		for (int i = 0; i <4; i++){
			already_guessed[i] = true;
		}
		boolean[] copyAlreadyGuessed = Arrays.copyOf(already_guessed, 26);
		// puzzle = {w,_,_,_,_s}
		puzzle = WordPuzzle.createPuzzleFromTemplate("wheeps",
				new boolean[] { false, true, true, true, true, false });
		char[] puzzleCopy = Arrays.copyOf(puzzle, puzzle.length);
		for (int j = 0; j < 100; j++){
			char[] hint = WordPuzzle.getHint("wheeps", puzzle, already_guessed);
			/*
			 * correctGuesses - all those letters are a "correct" hint.
			 * incorrectGuesses - none of those letters should be the "misleading" hint. Some
			 * of them have been guessed already (a,b,c,d), and the rest appear in the word
			 */
			String correctGuesses = "hep";
			String incorrectGuesses = "whepsabcd";
			boolean case1 = (correctGuesses.contains("" + hint[0]) &&
					!incorrectGuesses.contains("" + hint[1]));  //first char is correct
			boolean case2 = (correctGuesses.contains("" + hint[1]) &&
					!incorrectGuesses.contains("" + hint[0]));  //first char is incorrect
			// already_guessed shouldn't be changed
			boolean case3 = already_guessed.equals(copyAlreadyGuessed);
			// puzzle shouldn't be changed
			boolean case4 = puzzle.equals(puzzleCopy);
			if ( !case1 && !case2 && case3 && case4){
				System.err.println("Error 5.1");
			}
		}


		/*
		 * In order for these tests to run properly, your scanner should only use: nextInt(), nextChar() and next()
		 * to read integerts, characters and strings. Alternatively, you should only use nextLine() to read each line.
		 * mixing nextLine() with the rest of the functions will fail this test (and is more problematic in general).
		 */


		testMainTemplate();
		testMainGame();
		System.out.println("Done!");
	}

	public static void testMainTemplate(){
		PrintStream sysOut = System.out; //backup System.in
		try{
			redirectOutput(); // no need to save output

			Scanner inputScanner1 = getInputScanner("2", "_,X,X,_,_", "someOtherString");

			char[] puzzle = WordPuzzle.mainTemplateSettings("print", inputScanner1);
			System.setOut(sysOut);
			if (!Arrays.equals(puzzle, new char[] {'_', 'r', 'i', '_', '_'})){
				System.err.println("Error 6.1");
			}
			checkScannerIsNotClosed(inputScanner1, "6");

			redirectOutput();
			Scanner inputScanner2 = getInputScanner("2", "_,X,X,_,_,_", "2", "_,X,X,_,_", "someOtherString");

			char[] puzzle_2 = WordPuzzle.mainTemplateSettings("print", inputScanner2);
			System.setOut(sysOut);
//			UNCOMMENT IF YOU FAIL to check yourself
//			System.out.println(puzzle_2);
//			System.out.println(puzzle_2.length);
			if (!Arrays.equals(puzzle_2, new char[] {'_', 'r', 'i', '_', '_'})){
				System.err.println("Error 6.2");
			}
			checkScannerIsNotClosed(inputScanner2, "6");

			redirectOutput();
			//Scanner inputScanner3 = getInputScanner("1", "as", "2", "_,X,X", "2", "_,X,X,_,_", "someOtherString");
			Scanner inputScanner3 = getInputScanner("2", "_,X,X,_,_", "someOtherString");
			char[] puzzle_3 = WordPuzzle.mainTemplateSettings("print", inputScanner3);
			System.setOut(sysOut);
//			UNCOMMENT IF YOU FAIL to check yourself
//			System.out.println(puzzle_3);
//			System.out.println(puzzle_3.length);
			if (!Arrays.equals(puzzle_3, new char[] {'_', 'r', 'i', '_', '_'})){
				System.err.println("Error 6.3");
			}
			checkScannerIsNotClosed(inputScanner3, "6");

			redirectOutput();
			Scanner inputScanner4 = getInputScanner("1", "2", "1", "0","2","_,X,X,_", "2","_,X,x,_", "2","_,X,X,X", "2", "_,_,_,X", "someOtherString");

			char[] puzzle_4 = WordPuzzle.mainTemplateSettings("oooa", inputScanner4);
			System.setOut(sysOut);
//			UNCOMMENT IF YOU FAIL to check yourself
//			System.out.println(puzzle_4);
//			System.out.println(puzzle_4.length);
			if (!Arrays.equals(puzzle_4, new char[] {'_', '_', '_', 'a'})){
				System.err.println("Error 6.4");
			}
			checkScannerIsNotClosed(inputScanner4, "6");

			redirectOutput();
			Scanner inputScanner5 = getInputScanner("2","_,_,X,X", "2", "X,X,X,X","2", "_,_,_,_", "2", "_,_,_,X", "someOtherString");

			char[] puzzle_5 = WordPuzzle.mainTemplateSettings("oooa", inputScanner5);
			System.setOut(sysOut);
//			UNCOMMENT IF YOU FAIL to check yourself
//			System.out.println(puzzle_5);
//			System.out.println(puzzle_5.length);
			if (!Arrays.equals(puzzle_5, new char[] {'_', '_', '_', 'a'})){
				System.err.println("Error 6.5");
			}
			checkScannerIsNotClosed(inputScanner5, "6");

		}
		finally{
			System.setOut(sysOut); //restore System.out
		}
	}

	public static void testMainGame(){
		PrintStream sysOut = System.out; //backup System.in
		try{
			ByteArrayOutputStream baos = redirectOutput();

			Scanner inputScanner = getInputScanner("p", "n", "t", "someOtherString");

			WordPuzzle.mainGame("print", new char[] {'_', 'r', 'i', '_', '_'}, inputScanner);
			System.out.flush();
			System.setOut(sysOut);
			if (! baos.toString().contains("Congratulations")){
				System.err.println("Error 7.1");
			}
			checkScannerIsNotClosed(inputScanner, "7");

			ByteArrayOutputStream baos2 = redirectOutput();

			Scanner inputScanner2 = getInputScanner("a", "b", "c", "d", "e", "f", "g", "h", "i", "H", "H", "H", "H", "H","H","H","H","H","H","H","H","H","H","a", "b", "someOtherString");

			WordPuzzle.mainGame("printttttt", new char[] {'_', 'r', 'i', '_', '_', '_', '_', '_', '_', '_'}, inputScanner2);
			System.out.flush();
			System.setOut(sysOut);
//			UNCOMMENT IF YOU FAIL 7.2 - checks if you don't give a hint on an already guessed char
//			System.out.println(baos2.toString());
			if (baos2.toString().contains("Here's a hint for you: choose either a") ||
					baos2.toString().contains("Here's a hint for you: choose either b") ||
					baos2.toString().contains("Here's a hint for you: choose either c") ||
					baos2.toString().contains("Here's a hint for you: choose either d") ||
					baos2.toString().contains("Here's a hint for you: choose either e") ||
					baos2.toString().contains("Here's a hint for you: choose either f") ||
					baos2.toString().contains("Here's a hint for you: choose either g") ||
					baos2.toString().contains("Here's a hint for you: choose either h") ||
					baos2.toString().contains("Here's a hint for you: choose either i")){
				System.err.println("Error 7.2");
			}
			checkScannerIsNotClosed(inputScanner2, "7");

			ByteArrayOutputStream baos3 = redirectOutput();

			Scanner inputScanner3 = getInputScanner("p", "g", "x", "y", "k", "l", "n","t", "someOtherString");

			WordPuzzle.mainGame("print", new char[] {'_', 'r', 'i', '_', '_'}, inputScanner3);
			System.out.flush();
			System.setOut(sysOut);
//			System.out.println(baos3.toString());
			if (! baos3.toString().contains("Game over!")){
				System.err.println("Error 7.3");
			}
			checkScannerIsNotClosed(inputScanner3, "7");

			ByteArrayOutputStream baos4 = redirectOutput();

			Scanner inputScanner4 = getInputScanner("e", "someOtherString");

			WordPuzzle.mainGame("eriee", new char[] {'_', 'r', 'i', '_', '_'}, inputScanner4);
			System.out.flush();
			System.setOut(sysOut);
//			System.out.println(baos4.toString());
			if (baos4.toString().contains("Game over!")){
				System.err.println("Error 7.4");
			}
			checkScannerIsNotClosed(inputScanner4, "7");

			ByteArrayOutputStream baos5 = redirectOutput();

			Scanner inputScanner5 = getInputScanner("a", "H", "H","H", "H", "H", "H", "e", "someOtherString");

			WordPuzzle.mainGame("eriee", new char[] {'_', 'r', 'i', '_', '_'}, inputScanner5);
			System.out.flush();
			System.setOut(sysOut);
//			System.out.println(baos5.toString());
			if (!baos5.toString().contains("Congratulations")){
				System.err.println("Error 7.5");
			}
			checkScannerIsNotClosed(inputScanner5, "7");


		}
		finally{
			System.setOut(sysOut); //restore System.out

		}
	}

	private static void checkScannerIsNotClosed(Scanner s, String errorPrefix){
		try{
			if (!s.hasNext()){
				System.err.println(String.format("Error %s.2", errorPrefix));
			}
		}
		catch (IllegalStateException exp){
			// scanner should not be closed
			System.err.println(String.format("Error %s.2", errorPrefix));
		}
	}
	private static ByteArrayOutputStream redirectOutput(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);
		return baos;
	}

	private static Scanner getInputScanner(String... arr){
		String inputString = String.join(System.lineSeparator(), arr);
		InputStream inStream = new ByteArrayInputStream(inputString.getBytes());
		Scanner inputScanner = new Scanner(inputString);
		return inputScanner;
	}

}
