package il.ac.tau.cs.sw1.ex8.tfIdf;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;

import java.io.File;
import java.util.*;

@SuppressWarnings("unchecked")
/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {

	private boolean isInitialized = false;

	//Add members here
	private HashMap<String, HashMapHistogram> files_hmh;
	private HashMap<String, List<Map.Entry<String, Double>>> tf_map;
	private HashMapHistogram words;

	public FileIndex() {
		this.files_hmh = new HashMap<>();
	}

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 * @pre: isInitialized() == false;
	 */
	public void indexDirectory(String folderPath) { //Q1
		//This code iterates over all the files in the folder. add your code wherever is needed

		this.tf_map = new HashMap<>();
		this.words = new HashMapHistogram();
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				try {
					List<String> words = FileUtils.readAllTokens(file);
					HashMapHistogram<String> current = new HashMapHistogram<>();
					current.addAll(words);
					this.words.addAll(words);
					this.files_hmh.put(file.getName(), current);
				} catch (Exception e) {

				}
				// add your code here
				/*******************/
			}
		}
		for (File file: listFiles)
		{
			if(file.isFile())
			{
				try {
					this.tf_map.put(file.getName(), create_tf_idf(file.getName(), this.files_hmh.get(file.getName())));
				}
				catch(Exception E)
				{

				}
			}

		}
		/*******************/
		// add your code here
		/*******************/
		isInitialized = true;
	}


	// Q2

	/* @pre: isInitialized() */
	public int getCountInFile(String word, String fileName) throws FileIndexException {
		if (this.files_hmh.containsKey(fileName)) {
			return this.files_hmh.get(fileName).getCountForItem(word.toLowerCase());
		}
		throw new FileIndexException("The File Wasn't Found");
	}

	/* @pre: isInitialized() */
	public int getNumOfUniqueWordsInFile(String fileName) throws FileIndexException {
		if (this.files_hmh.containsKey(fileName)) {
			return this.files_hmh.get(fileName).len();
		}
		throw new FileIndexException("The File Wasn't Found");
	}

	/* @pre: isInitialized() */
	public int getNumOfFilesInIndex() {
		return this.files_hmh.size();
	}


	/* @pre: isInitialized() */
	public double getTF(String word, String fileName) throws FileIndexException { // Q3
		if (this.files_hmh.containsKey(fileName)) {
			return (double) this.files_hmh.get(fileName).getCountForItem(word.toLowerCase()) / this.files_hmh.get(fileName).total_words();
		}
		throw new FileIndexException("The File Wasn't Found");
	}

	/* @pre: isInitialized()
	 * @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getIDF(String word) { //Q4
		int cnt = 0;
		int total = this.files_hmh.size();
		for (Map.Entry<String, HashMapHistogram> var : this.files_hmh.entrySet()) {
			if (var.getValue().getCountForItem(word.toLowerCase()) > 0) {
				cnt++;
			}
		}
		return (double) Math.log((double) total / cnt);
	}


	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfUniqueWordsInFile(fileName)
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKMostSignificantWords(String fileName, int k)
			throws FileIndexException { //Q5
		List<Map.Entry<String, Double>> lst = new ArrayList<>();
		if (this.files_hmh.containsKey(fileName)) {
			if (this.tf_map.containsKey(fileName))
			{
				for (int i = 0; i < k ; i++) {
					lst.add(this.tf_map.get(fileName).get(i));
				}
			}

			else
			{
				this.tf_map.put(fileName, create_tf_idf(fileName, this.files_hmh.get(fileName)));
				for (int i = 0; i < k ; i++) {
					lst.add(this.tf_map.get(fileName).get(i));
				}
			}
			return lst;
		}
		throw new FileIndexException("The File Wasn't Found");

	}

	public List<Map.Entry<String, Double>> create_tf_idf(String fileName, HashMapHistogram map) throws FileIndexException {
		List<Map.Entry<String, Double>> tf_idf = new ArrayList<>();
		for (Object word: map.getItemsSet()) {
			tf_idf.add(new AbstractMap.SimpleEntry<>((String)word, getTFIDF((String) word , fileName)));
		}
		C comp = new C();
		tf_idf.sort(comp);
		return tf_idf;
	}

	//public class C<String extends Comparable<Double>> implements Comparator<Map.Entry<String, Double>>
	public class C implements Comparator<Map.Entry<String, Double>>
	{
		@Override
		public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
			if((double)o1.getValue() == (double)o2.getValue())
			{
				return (o1.getKey().compareTo(o2.getKey()));
			}
			return - o1.getValue().compareTo(o2.getValue());
		}

	}
	
	/* @pre: isInitialized() */
	public double getCosineSimilarity(String fileName1, String fileName2) throws FileIndexException{ //Q6
		if (this.files_hmh.containsKey(fileName1) && this.files_hmh.containsKey(fileName2) )
		{
			double upper =0;
			double lowerL = 0;
			double lowerR = 0;
			for(Object word: this.words.getItemsSet())
			{
				boolean flag1 = false;
				boolean flag2 = false;
				if(this.files_hmh.get(fileName1).getCountForItem((String)word)>0)
				{
					flag1 = true;
				}
				if(this.files_hmh.get(fileName2).getCountForItem((String)word)>0) {
					flag2 = true;
				}
				if(flag1 && flag2)
				{
					double tmp = 0;
					List<Map.Entry<String, Double>> entries = tf_map.get(fileName1);
					for (Map.Entry<String, Double> entry : entries)
					{
						if(entry.getKey().toString().equals(word))
						{
							tmp += entry.getValue();
							lowerL += Math.pow(entry.getValue(), 2);
						}
					}
					List<Map.Entry<String, Double>> entries2 = tf_map.get(fileName2);
					for (Map.Entry<String, Double> entry : entries2)
					{
						if(entry.getKey().toString().equals(word))
						{
							tmp  *= entry.getValue();
							upper += tmp;
							lowerR += Math.pow(entry.getValue(), 2);
						}
					}


				}
				else if (flag1)
				{
					List<Map.Entry<String, Double>> entries = tf_map.get(fileName1);
					for (Map.Entry<String, Double> entry : entries)
					{
						if(entry.getKey().toString().equals(word))
						{
							lowerL += Math.pow(entry.getValue(), 2);
						}
					}
				}
				else
				{
					List<Map.Entry<String, Double>> entries2 = tf_map.get(fileName2);
					for (Map.Entry<String, Double> entry : entries2)
					{
						if(entry.getKey().toString().equals(word))
						{
							lowerR += Math.pow(entry.getValue(), 2);
						}
					}
				}

			}
			if(upper == 0)
			{
				return 0;
			}
			return (double) upper/Math.sqrt(lowerL*lowerR);

		}
		throw new FileIndexException("The File Wasn't Found");
	}
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfFilesInIndex()-1
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKClosestDocuments(String fileName, int k) 
			throws FileIndexException{ //Q6
		if (this.files_hmh.containsKey(fileName))
		{
			List<Map.Entry<String, Double>> lst = new ArrayList<>();
			for(String s: this.files_hmh.keySet())
			{
				if(s.equals(fileName))
				{
					continue;
				}
				lst.add(new AbstractMap.SimpleEntry<>(s, (double)getCosineSimilarity(fileName,s)));

			}
			C comp = new C();
			lst.sort(comp);
			List<Map.Entry<String, Double>> lst_out = new ArrayList<>();
			for (int i = 0; i < k ; i++)
			{
				lst_out.add(lst.get(i));
			}
			return lst_out;
		}
		throw new FileIndexException("The File Wasn't Found");
	}

	
	
	//add private methods here, if needed

	
	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	
	public boolean isInitialized(){
		return this.isInitialized;
	}
	
	/* @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getTFIDF(String word, String fileName) throws FileIndexException{
		return this.getTF(word, fileName)*this.getIDF(word);
	}
	
	private static double calcTF(int repetitionsForWord, int numOfWordsInDoc){
		return (double)repetitionsForWord/numOfWordsInDoc;
	}
	
	private static double calcIDF(int numOfDocs, int numOfDocsContainingWord){
		return Math.log((double)numOfDocs/numOfDocsContainingWord);
	}
	
}
