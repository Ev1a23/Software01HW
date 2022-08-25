package il.ac.tau.cs.sw1.ex5;

//import com.sun.deploy.security.SelectableSecurityManager;

import java.io.*;
//import java.util.Locale;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
		String str;
		String[] vocab = new String[MAX_VOCABULARY_SIZE];
		int cnt = 0;
		boolean somenum = false;
		String out = "";
		String lower = "";
		while(cnt <= MAX_VOCABULARY_SIZE && ((str = buffReader.readLine()) != null))
		{
			String[] arr = str.split(" ");
			for(String word: arr)
			{
				out = legal_word(word);
				if(out.equals("false"))
				{
					continue;
				}
				else if(out.equals("number"))
				{
					if(somenum == false)
					{
						somenum = true;
						vocab[cnt] = SOME_NUM;
						cnt+=1;
					}
					else
					{
						continue;
					}
				}
				else
				{
					lower = word.toLowerCase();//Locale.ENGLISH);
					if(last_index(vocab,lower,cnt) == -1)
					{
						vocab[cnt] = lower;
						cnt+=1;
					}
					else
					{
						continue;
					}
				}

			}
		}
		String[] output = new String[cnt];
		for(int i = 0; i<cnt; i++)
		{
			output[i] = vocab[i];
		}
		return output;
	}

	public String legal_word(String str)
	{
		if(str.length()==0)
		{
			return "false";
		}
		//check if has a letter in english
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			int ascii = (int)c;
			if((ascii>=65 && ascii<=90)||(ascii>=97 && ascii <=122))
			{
				return "word";
			}
		}
		for(int i = 0; i<str.length(); i++)
		{
			char c = str.charAt(i);
			int ascii = (int)c;
			if(ascii<48 || ascii>57)
			{
				return  "false";
			}
		}
		return "number";

	}

	public int last_index(String[] arr, String str, int limit)
	{
		if(limit == 0)
		{
			return -1;
		}
		for(int i = 0; i<limit; i++)
		{
			if(arr[i].equals(str))
			{
				return i;
			}
		}
		return -1;
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int[][] arr = new int[vocabulary.length][vocabulary.length];
		String str;
		String out1 = "";
		String out2 = "";
		int index1 = 0;
		int index2 = 0;
		BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
		while((str = buffReader.readLine()) != null)
		{
			String[] split = str.split(" ");
			if(split.length ==1)
			{
				continue;
			}
			for (int i = 1; i < split.length ; i++) {
				out1 = legal_word(split[i-1]);
				out2 = legal_word(split[i]);
				if(out1.equals("false")  || out2.equals("false"))
				{
					continue;
				}
				if(out1.equals("number") || out2.equals("number"))
				{
					if(!out2.equals("number"))
					{
						index1 = last_index(vocabulary,SOME_NUM,vocabulary.length);
						index2 = last_index(vocabulary,split[i].toLowerCase(), vocabulary.length);//Locale.ROOT), vocabulary.length);
					}
					else if(!out1.equals("number"))
					{
						index1 = last_index(vocabulary,split[i-1].toLowerCase(), vocabulary.length);//Locale.ROOT), vocabulary.length);
						index2 = last_index(vocabulary,SOME_NUM,vocabulary.length);
					}
					else
					{
						index1 = last_index(vocabulary,SOME_NUM,vocabulary.length);
						index2 = last_index(vocabulary,SOME_NUM,vocabulary.length);
					}
				}
				else
				{
					index1 = last_index(vocabulary,split[i-1].toLowerCase(), vocabulary.length);//Locale.ROOT), vocabulary.length);
					index2 = last_index(vocabulary,split[i].toLowerCase(), vocabulary.length);//Locale.ROOT), vocabulary.length);
				}
				if(index1 == -1 || index2 == -1)
				{
					continue;
				}
				else
				{
					arr[index1][index2] +=1;
				}

			}
		}

		return arr;

	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		FileWriter file = new FileWriter(fileName+VOC_FILE_SUFFIX);
		BufferedWriter output = new BufferedWriter(file);
		output.write(mVocabulary.length+" words");
		output.newLine();
		for(int i = 0; i< mVocabulary.length; i++)
		{
			output.write(i+","+mVocabulary[i]);
			output.newLine();
		}

		output.close();
		FileWriter file2 = new FileWriter(fileName+COUNTS_FILE_SUFFIX);
		BufferedWriter output2 = new BufferedWriter(file2);
		int cnt = 0;
		for (int i = 0; i < mBigramCounts.length ; i++) {
			for (int j = 0; j < mBigramCounts.length; j++) {
				if(mBigramCounts[i][j]!=0)
				{
					output2.write(i+","+j+":"+mBigramCounts[i][j]);
					output2.newLine();
				}

			}

		}
		output2.close();
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		BufferedReader buffReader = new BufferedReader(new FileReader(fileName+VOC_FILE_SUFFIX));
		String str= buffReader.readLine();
		String[] split_f = str.split(" ");
		int length = Integer.parseInt(split_f[0]);
		String[] arr = new String[length];
		int cnt = 0;
		while((str = buffReader.readLine()) != null)
		{
			split_f = str.split(",");
			if(split_f.length == 2)
			{
				arr[cnt] = split_f[1];
				cnt+=1;
			}
			else
			{
				if(cnt>=10)
				{
					arr[cnt] = str.substring(3);
					cnt+=1;
				}
				else
				{
					arr[cnt] = str.substring(2);
					cnt+=1;
				}
			}
		}
		mVocabulary = arr.clone();
		BufferedReader buffReader2 = new BufferedReader(new FileReader(fileName+COUNTS_FILE_SUFFIX));
		String str2 = "";
		int[][] counts = new int[length][length];
		int num1 = 0;
		int num2 = 0;
		int after = 0;
		String [] temp = new String[2];
		String[] temp2 = new String[2];
		while((str = buffReader2.readLine()) != null)
		{
			temp = str.split(",");
			num1 = Integer.parseInt(temp[0]);
			temp2 = temp[1].split(":");
			num2 = Integer.parseInt(temp2[0]);
			after = Integer.parseInt(temp2[1]);
			counts[num1][num2] = after;


		}
		mBigramCounts = counts.clone();
	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		for(int i = 0; i< mVocabulary.length; i++)
		{
			if(mVocabulary[i].equals(word))
			{
				return i;
			}
		}
		return -1;
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int i1 = getWordIndex(word1);
		int i2 = getWordIndex(word2);
		if(i1 == -1 || i2 == -1)
		{
			return 0;
		}
		return mBigramCounts[i1][i2];

	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int index= getWordIndex(word);
		int max = 0;
		String str = "";
		for(int i = 0; i<mBigramCounts.length; i++)
		{
			if(mBigramCounts[index][i]>max)
			{
				max = mBigramCounts[index][i];
				str = mVocabulary[i];
			}
		}
		if (max ==0)
		{
			return null;
		}
		return str;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		if(sentence.equals(""))
		{
			return true;
		}
		String[] arr = sentence.split(" ");
		if(arr.length == 0)
		{
			return false;
		}
		if(arr.length == 1 )
		{
			if(getWordIndex(arr[0]) == -1)
			{
				return false;
			}
			return true;
		}
		for (int i = 1; i < arr.length ; i++) {
			if(getBigramCount(arr[i-1],arr[i]) == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		int[] demo = new int[arr1.length];
		boolean flag1 = true;
		boolean flag2 = true;
		for (int i = 0; i < arr1.length; i++) {
			if(arr1[i] !=demo[i])
			{
				flag1 = false;
				break;
			}
		}
		for (int i = 0; i < arr1.length; i++) {
			if(arr2[i] !=demo[i])
			{
				flag2 = false;
				break;
			}
		}
		if(flag1 || flag2)
		{
			return -1;
		}
		double sum = 0.0;
		double sqrt1 = 0.0;
		double sqrt2 = 0.0;
		for (int i = 0; i <arr1.length ; i++) {
			sum+=(arr1[i]*arr2[i]);
			sqrt1 += arr1[i]*arr1[i];
			sqrt2 += arr2[i]*arr2[i];
		}
		sum /=(Math.sqrt(sqrt1)*Math.sqrt(sqrt2));
		return sum;

	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		if(mVocabulary.length == 1 )
		{
			return mVocabulary[0];
		}
		int index = getWordIndex(word);
		if(mVocabulary.length == 2)
		{
			if(index == 0)
			{
				return mVocabulary[1];
			}
			return mVocabulary[0];
		}
		int[] vector = new int[mVocabulary.length];
		vector = mBigramCounts[index];
		double max_similar = -5;
		int index_similar = 0;
		double calc = 0;
		for (int i = 0; i < mBigramCounts.length; i++) {
			if(i==index)
			{
				continue;
			}
			calc = calcCosineSim(vector, mBigramCounts[i]);
			if(calc>max_similar)
			{
				max_similar = calc;
				index_similar = i;
			}

		}
		return mVocabulary[index_similar];
	}

	
}
