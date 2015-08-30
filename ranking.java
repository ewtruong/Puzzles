import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Ranking 
{
	public static int letterCount = 0;
	public static long rank = 1;
	
	public static void main ( String [] arguments)
	{
		//ask the user to enter a word
		Scanner wordScanner = new Scanner(System.in);
		System.out.print("Enter a word to see where it ranks among words with the same letters: ");	
		String yahWord = wordScanner.nextLine(); //yahWord...because I miss bahston
		
		//Problem summary says 20 letters or less, so accounting for that, then prints the rank
		if(yahWord.length() <=  20)
		{
			System.out.print(rank(yahWord));	
			wordScanner.close();
		}
		else
		{
			System.out.print("Word has to be 20 characters or less, enter new word: ");
			String yahWordTwo = wordScanner.nextLine();
			System.out.print(rank(yahWordTwo));
			wordScanner.close();
		}
	}
	public static long rank(String specialWord)
	{		
		/*
		 * Long currnetPermCount
		 * 
		 * Description: keeps track of the current number of permutations as it cycles through the word.
		 * Example: Entered word is Bookkeeper, recent letter is p which means it has been through
		 * r and e. So current characters in the bellow hashmap would be P E R. There are 6 different
		 * permutations of it, so the value of currentPermCount would be 6
		 */
	    long currentPermCount = 1; 
	    
	    
	    /*
	     * Map Charcounts
	     * 
	     * Description: Keeps track of characters and their current counts.
	     */
	    Map<Character, Integer> charCounts = new HashMap<>();
	    
	   //Goes though each character of the entered word
	    for (int i = specialWord.length() - 1; i > -1; i--) 
	    {
	        char x = specialWord.charAt(i);
	         
	        //If the letter exists already, get the current count and add 1.
	        //Otherwise, just set the count of the character at 1
	        if(charCounts.containsKey(x))
	        {
	        	letterCount =  charCounts.get(x) + 1;   
	        }
	        else
	        {
	        	letterCount = 1;
	        }

	        //adds the the character and its current count to the hashmap
	        charCounts.put(x, letterCount);
	        
	        //iterates through the hashmap
	        for (Iterator<Entry<Character, Integer>> it = charCounts.entrySet().iterator(); it.hasNext(); ) 
	        {
	        	  Entry<Character, Integer> entry = it.next();
	        	  
	        	  //only update the rank when a character in the map comes before the current letter
	        	  //alphabetically. This allows the rank to be more accurate. 
	        	  //example: If a character in the map is "F" and the current letter is "P", it will update/add
	        	  //to the 
	        	  if(entry.getKey() < x)
	        	  {
	        		  rank += currentPermCount * entry.getValue() / letterCount;
	        		  
	        	  }
	        }
			
	        currentPermCount *= specialWord.length() - i;
	        //Divide it by the letter count to help with duplicates....
	        currentPermCount /= letterCount; 
	    }
	    return rank;
	}

}
