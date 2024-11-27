
/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String stringOneWithSpace = preProcess(str1);
		String stringTwoWithSpace = preProcess(str2);

		String stringOne = "";
		String stringTwo = "";
		
		// checking if there are spaces in the stringOneWithSpace or stringOneWithSpace and skiping the space while adding the string to stringOne/stringTwo:
		for (int i = 0; i < stringOneWithSpace.length(); i++){
			if (stringOneWithSpace.charAt(i) != ' '){
				stringOne += stringOneWithSpace.charAt(i);
			}
		}
		for (int i = 0; i < stringTwoWithSpace.length(); i++){
			if (stringTwoWithSpace.charAt(i) != ' '){
				stringTwo += stringTwoWithSpace.charAt(i);
			}
		}
		
		// if the length in the two string dont match we can return false:
		if (stringOne.length() != stringTwo.length()) {
            return false;
        }

        // Iterate over each character of stringOne and check if it exists in stringTwo:
        for (int i = 0; i < stringOne.length(); i++) {
            char currentChar = stringOne.charAt(i);
            int indexInStr2 = stringTwo.indexOf(currentChar);

            // If the character doesn't exist in stringTwo, or no more such characters remain, return false
            if (indexInStr2 == -1) {
                return false;
            }

            // Remove the matched character from stringTwo by slicing:
            stringTwo = stringTwo.substring(0, indexInStr2) + stringTwo.substring(indexInStr2 + 1);
        }

        // true if all characters were matched and removed correctly
        return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {

		if (str == null || str.length() == 0) {
			return ""; // Return empty string if input is null or empty
		}
	
		String lowerCase = ""; // Initialize an empty string to store the result
	
		// Loop through each character of the input string:
		for (int i = 0; i < str.length(); i++) {
			// If the character is not a space, append it to the lowerCase string:
			if ( str.charAt(i) != '!' && str.charAt(i) != '?') {
					lowerCase += str.charAt(i);
			}
		}
	
		// Convert the result to lowercase and return it:
		return lowerCase.toLowerCase();
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String randomword = "";
		String stringSubstring = str;

		for (int i = 0; i < str.length(); i++){
			double randomIndex = Math.floor(Math.random() * stringSubstring.length());
			randomword += stringSubstring.charAt((int)randomIndex);
			stringSubstring = stringSubstring.substring(0,(int)randomIndex) + stringSubstring.substring((int)randomIndex+1);
		}
		return randomword;
	}
}