package demo.anagrams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests
 */
public class AnagramsTest
{	
	@Test
    public void testReadAllLines() {
		List<String> dict = Anagrams.readLines("wordlist.txt");
		Assert.assertEquals(338882, dict.stream().count());
    }
	
	@Test
    public void testWriteToFile() {		
		HashMap<String, List<String>> testMap = new HashMap<>();
		//Write first list		
		testMap.put("lambda", Arrays.asList("lambad badlam bdalam"));
		//Write second list		
		testMap.put("rocks", Arrays.asList("rocsk ksroc ocksr"));
		
		Anagrams.writeToFile(testMap);	

		Assert.assertNotNull(Anagrams.readLines("AnagramsFound.txt"));
    }
	
	@Test
	public void testHandleAnagrams() {
		String inputFile = "text.txt";
		List<String> dict = Anagrams.readLines("wordlist.txt");
		
		Anagrams.handleAnagrams(inputFile, dict);
		
		Assert.assertNotNull(Anagrams.anagramsMap);
		
		System.out.println("Anagrams Sets: " + Anagrams.anagramsSets);
		System.out.println("Total words: " + Anagrams.totalWords);
		System.out.println("Longest words: " + Anagrams.longestWords);
		System.out.println("Longest set: " + Anagrams.longestSet);
		System.out.println("It took: " + Anagrams.durationTime + " ms\n");	
	
		System.out.println("CPU: " + System.getenv("PROCESSOR_IDENTIFIER"));
		System.out.println("OS: " + System.getProperty("os.name"));		
	} 
}
