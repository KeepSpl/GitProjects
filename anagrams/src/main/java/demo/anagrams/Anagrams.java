package demo.anagrams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Anagrams demo
 *
 */
public class Anagrams {
	public static String wordList = "wordlist.txt";	 	
	public static String inputFile = "text.txt";
	public static String distFile = "AnagramsFound.txt";
	
	public static List<String> dictionary = readLines(wordList);	
	public static HashMap<String, List<String>> anagramsMap = new HashMap<>();
	public static int anagramsSets = 0;
	public static int totalWords = 0;	
	public static List<String> longestWords = new ArrayList<>();;
	public static List<String> longestSet = new ArrayList<>();
	public static long durationTime = 0;
	public static String systemDetail = "";
	
	public static void main(String[] args) {	
		if(args != null && args.length != 0) {
			handleAnagrams(args[0], dictionary); //Input file given from terminal
		} else {
			handleAnagrams(inputFile, dictionary);
		}
		
		System.out.println( "********* Anagrams *********" );    	
    	System.out.println("Anagrams Sets: " + anagramsSets);
		System.out.println("Total words: " + totalWords);
		System.out.println("Longest words: " + longestWords);
		System.out.println("Longest set: " + longestSet);
		System.out.println("It took: " + durationTime + " ms\n");
		System.out.println(distFile +" file is generated\n");		
	
		System.out.println("CPU: " + System.getenv("PROCESSOR_IDENTIFIER"));
		System.out.println("OS: " + System.getProperty("os.name"));		
    }
	
    public static void handleAnagrams(String inputFile, List<String> wordL) {    	
    	List<String> textfile = readLines(inputFile);
    	longestWords.add(""); //To be used later, when finding the longest words
    	
    	Instant start = Instant.now();    	
    	textfile.stream().forEach((str) -> findResults(str, wordL)); //Run through all words from input file ON dictionary(wordList)
    	
    	Instant end = Instant.now(); 
    	Duration duration = Duration.between(start, end);
    	durationTime = duration.toMillis();
    	
    	writeToFile(anagramsMap); //Writing to a the output file is not part of the duration time.
    }
    
    private static void findResults(String str, List<String> wordL) {   	    	
    	//This the longest word from the wordlist file. But this not where longest words should be found as I understood the task.
//    	Optional<String> longest = wordL.stream().sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1).findFirst();
//    	String longestWord = longest.get();
    	
    	//Sorting the word from input file
    	char[] arStr = str.toCharArray();
		Arrays.sort(arStr);
		StringBuilder sbStr = new StringBuilder(String.valueOf(arStr));   	
		
    	List<String> tempLength = wordL.parallelStream().filter(e -> e.replace("'", "").length() == sbStr.length()) //First find by length
    			.filter((ne) -> findByChars(sbStr, ne)) //Compare every text word with dictionary word
    			.collect(Collectors.toList());   	   	 
    	
    	if(tempLength != null && !tempLength.isEmpty()) {
    		anagramsSets++;
        	totalWords += tempLength.size();
        	
        	longestSet = tempLength.size() > longestSet.size() ? tempLength : longestSet;
        	longestWords = tempLength.get(0).length() > longestWords.get(0).length() ? tempLength : longestWords;
        	
    		anagramsMap.put(str, tempLength);
    	}    	   	
    } 
    
    private static boolean findByChars(StringBuilder textW, String dictionaryW) {
    	boolean test = false;
    	
    	//Sorting the word from dictionary
    	String nStr = dictionaryW.replace("'", ""); //This might create new string. But if the same string found in string pool, it will be reused
    	char[] str = nStr.toCharArray();
		Arrays.sort(str);
		StringBuilder sbStr = new StringBuilder(String.valueOf(str));
		
		test = textW.toString().equalsIgnoreCase(sbStr.toString());
    	
    	return test;
    }
    
    public static List<String> readLines(String filename) {
    	List<String> lines = null;
    	
		try {			
			lines = Files.readAllLines(Paths.get(filename), Charset.forName("ISO-8859-1"));
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
    	return lines;
    }
    
    public static void writeToFile(HashMap<String, List<String>> resultMap) {
    	List<String> linesList = new ArrayList<>();
    	resultMap.entrySet().stream().forEach(e -> formatLines(e.getValue(), linesList));   	
    	
    	try {
    		Path path = Paths.get(distFile); 
    		Files.deleteIfExists(path);
    		Files.write(path, linesList, StandardOpenOption.APPEND, StandardOpenOption.CREATE);			
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
    }
    
    private static void formatLines(List<String> lines, List<String> linesList) {
    	//Just to write line by line to the output file    	
    	StringBuilder sb = new StringBuilder();
    	lines.stream().forEach(e -> sb.append(e).append(" "));
    	sb.append("\n");
    	linesList.add(sb.toString());   	
    }
}
