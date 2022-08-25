package il.ac.tau.cs.sw1.ex4;

import java.util.Random;
import java.util.Scanner;


public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';

	/*
	 * @pre: template is legal for word
	 */
	public static char[] createPuzzleFromTemplate(String word, boolean[] template) { // Q - 1
		char[] arr = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			if (template[i] == false) {
				arr[i] = word.charAt(i);
			} else {
				arr[i] = HIDDEN_CHAR;
			}
		}
		return arr;
	}

	public static boolean checkLegalTemplate(String word, boolean[] template) { // Q - 2
		if (word.length() != template.length) // Check if the length is equal
		{
			return false;
		}
		//check if template array contains true and false
		boolean flagF = false;
		boolean flagT = false;
		for (int i = 0; i < template.length; i++) {
			if (template[i]) {
				flagT = true;
			} else {
				flagF = true;
			}
		}
		if (!(flagF && flagT)) {
			return false;
		}
		for (int i = 0; i < template.length; i++) {
			for (int j = i + 1; j < template.length; j++) {
				if (word.charAt(i) == word.charAt(j)) {
					if (template[i] != template[j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/*
	 * @pre: 0 < k < word.length(), word.length() <= 10
	 */
	public static boolean[][] getAllLegalTemplates(String word, int k) {  // Q - 3
		boolean[][] arr = new boolean[(int) Math.pow(2, word.length())][];
		boolean[] tmp = new boolean[word.length()];
		int count = 0;
		int[] index = new int[n_choose_k(word.length(), k)];

		for (int i = 1; i < (int) Math.pow(2, word.length()); i++) {
			tmp = new boolean[word.length()];
			String check = Integer.toBinaryString(i);
			int cnt = check.length() - check.replace("1", "").length();
			if (cnt == k) {
				String str = pad_left_zeros(Integer.toBinaryString(i), word.length());

				for (int j = 0; j < tmp.length; j++) {
					if (str.charAt(j) == '1') {
						tmp[j] = true;
					} else {
						tmp[j] = false;
					}
				}

			}
			if (checkLegalTemplate(word, tmp)) {
				arr[i - 1] = tmp;
				index[count] = i - 1;
				count++;
			}


		}
		boolean[][] out = new boolean[count][word.length()];
		boolean[] demo = new boolean[word.length()];

		for (int i = 0; i < count; i++) {
			out[i] = arr[index[i]];
		}
		return out;
	}


	/*
	 * @pre: puzzle is a legal puzzle constructed from word, guess is in [a...z]
	 */
	public static int applyGuess(char guess, String word, char[] puzzle) { // Q - 4
		int cnt = 0;
		for (int i = 0; i < puzzle.length; i++) {
			if (word.charAt(i) == guess && puzzle[i] == HIDDEN_CHAR) {
				cnt += 1;
				puzzle[i] = guess;
			}
		}
		return cnt;
	}


	/*
	 * @pre: puzzle is a legal puzzle constructed from word
	 * @pre: puzzle contains at least one hidden character.
	 * @pre: there are at least 2 letters that don't appear in word, and the user didn't guess
	 */
	public static char[] getHint(String word, char[] puzzle, boolean[] already_guessed) { // Q - 5
		char[] out = new char[2];
		Random rnd = new Random();
		int cnt = 0;
		int[] arr = new int[26];
		for (int i = 0; i < arr.length; i++) {
			char c = (char) (i + 97);
			if (!already_guessed[i] && word.lastIndexOf(c) == -1) {
				arr[cnt] = (char) (c);
				cnt++;
			}
		}
		out[0] = (char) arr[rnd.nextInt(cnt)];
		char[] array = new char[word.length()];
		cnt = 0;
		for (int i = 0; i < word.length(); i++) {
			if (puzzle[i] == HIDDEN_CHAR) {
				array[cnt] = (char) word.charAt(i);
				cnt++;
			}
		}
		out[1] = (char) array[rnd.nextInt(cnt)];
		if (rnd.nextInt(2) == 1) {
			char tmp = out[0];
			out[0] = out[1];
			out[1] = tmp;
		}
		return out;
	}


	public static char[] mainTemplateSettings(String word, Scanner inputScanner) { // Q - 6
		printSettingsMessage();
		printSelectTemplate();
		while (inputScanner.hasNext()) {

			int template = inputScanner.nextInt();
			if (template == 1) {
				printSelectNumberOfHiddenChars();
				int hidden = inputScanner.nextInt();
				boolean[][] arr = getAllLegalTemplates(word, hidden);
				if (arr.length == 0) {
					printWrongTemplateParameters();
				} else {
					Random rnd = new Random();
					int index = rnd.nextInt(arr.length);
					return createPuzzleFromTemplate(word, arr[index]);
				}
			} else
			{
				printEnterPuzzleTemplate();
				String[] puz = inputScanner.next().split(",");
				boolean[] puzzle = new boolean[word.length()];
				for (int i = 0; i < puzzle.length; i++) {
					if (puz[i].equals("_")) {
						puzzle[i] = true;
					}
				}
				if (checkLegalTemplate(word, puzzle)) {
					return createPuzzleFromTemplate(word, puzzle);
				} else {
					printWrongTemplateParameters();
				}
			}
		}
		char[] demo = new char['e'];
		return demo;


	}

	public static void mainGame(String word, char[] puzzle, Scanner inputScanner) { // Q - 7
		printGameStageMessage();
		boolean[] guesses = new boolean[26];
		int chances = missing_chars(word, puzzle) + 3;
		boolean m_flag = true;
		boolean win_flag = false;
		while (m_flag) {
			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			String input = inputScanner.next();
			if (input.equals("H")) {
				char[] hint = getHint(word, puzzle, guesses);
				if (hint[0] < hint[1]) {
					printHint(hint);
				} else {
					char tmp = hint[0];
					hint[0] = hint[1];
					hint[1] = tmp;
					printHint(hint);
				}
			} else {
				char c = input.charAt(0);
				int ascii = (int) c - 97;
				guesses[ascii] = true;
				if (applyGuess(c, word, puzzle) != 0) {
					boolean flag = true;
					for (int i = 0; i < puzzle.length; i++) {
						if (puzzle[i] == '_') {
							flag = false;
							break;
						}
					}
					if (flag) {
						win_flag = true;
						printWinMessage();
						break;
					} else {
						chances -= 1;
						printCorrectGuess(chances);
						if (chances == 0) {
							m_flag = false;
						}
					}
				} else {
					chances -= 1;
					printWrongGuess(chances);
					if (chances == 0) {
						m_flag = false;
					}

				}

			}
		}
		if (!win_flag) {
			printGameOver();
		}
		m_flag = false;
	}


				
				


/*************************************************************/
/********************* Don't change this ********************/
/*************************************************************/

	public static void main(String[] args) throws Exception {

		if (args.length < 1){
			throw new Exception("You must specify one argument to this program");
		}
		String wordForPuzzle = args[0].toLowerCase();
		if (wordForPuzzle.length() > 10){
			throw new Exception("The word should not contain more than 10 characters");
		}
		Scanner inputScanner = new Scanner(System.in);
		char[] puzzle = mainTemplateSettings(wordForPuzzle, inputScanner);
		mainGame(wordForPuzzle, puzzle, inputScanner);
		inputScanner.close();
	}


	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterWord() {
		System.out.println("Enter word:");
	}
	
	public static void printSelectNumberOfHiddenChars(){
		System.out.println("Enter number of hidden characters:");
	}
	public static void printSelectTemplate() {
		System.out.println("Choose a (1) random or (2) manual template:");
	}
	
	public static void printWrongTemplateParameters() {
		System.out.println("Cannot generate puzzle, try again.");
	}
	
	public static void printEnterPuzzleTemplate() {
		System.out.println("Enter your puzzle template:");
	}


	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}


	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printHint(char[] hist){
		System.out.println(String.format("Here's a hint for you: choose either %s or %s.", hist[0] ,hist[1]));

	}
	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle!");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

	//Side Functions
	public static int factorial(int n)
	{
		if (n<=1)
		{
			return 1;
		}
		int num = 1;
		for (int i = 2; i<=n; i++)
		{
			num *=i;
		}
		return num;
	}
	public static int n_choose_k(int n, int k)
	{
		return factorial(n)/(factorial(k)*factorial(n-k));
	}
	public static String pad_left_zeros(String str, int len)
	{
		if(str.length() == len)
		{
			return str;
		}
		StringBuilder sb = new StringBuilder();
		while(sb.length() <len-str.length())
		{
			sb.append('0');
		}
		sb.append(str);
		return sb.toString();
	}
	public static int missing_chars(String word, char[] puzzle)
	{
		int cnt = 0;
		String  check = "";
		for (int i = 0; i < puzzle.length; i++)
		{
			if(puzzle[i] == '_' && check.lastIndexOf(word.charAt(i)) == -1)
			{
				cnt+=1;
				check+=word.charAt(i);
			}
		}
		return cnt;
	}

}
