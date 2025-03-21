import java.util.ArrayList;
import java.util.Scanner;

/**
 * Javadoc example
 * @param parameter1 the first parameter
 * @return what is returned from this method
 */
public class Translator {

  private ArrayList<String> english;
  private ArrayList<String> german;
  private String englishSentence;
  private String germanSentence;

  public Translator() {
    createEnglishGermanList();
  }
/**
 * Splits text file between English and German lists
 */
  public void createEnglishGermanList() {
    ArrayList<String> allWords = FileReader.toStringList("englishToGerman.txt");
    english = new ArrayList<String>();
    german = new ArrayList<String>();
    
    for (String line : allWords) {
      String[] words = line.split(",");
      english.add(words[0]);
      german.add(words[1]);
    }
  }
/**
 * Attempts to tranlate the sentence witht the words avalible in the list. If no word exists, it is replaced with an [ERROR]
 * @param sentence user's typed out sentence
 * @return the sentence in German
 */
  public String translateSentence(String sentence) {
    // 1. Split sentence up in to words
    String[] words = sentence.split(" ");
        
    // 2. Remove any period or commas
    String[] cleanedWords = removeTailPeriodOrComma(words);
    
    // 3. Traverse the cleanedWords (USE INDEX FOR-LOOP)
    for (int i = 0; i < cleanedWords.length; i++) {
      // 4. Determine if the cleanedWord is a english word
      String currentWord = cleanedWords[i];
      // 5. findEnglishWordIndex will >= 0 if the translator know the english wor
      int wordIndex = findEnglishWordIndex(currentWord);
      if (wordIndex != -1) {
        // 6. Replace german word with cleanedWord
        cleanedWords[i] = german.get(wordIndex);
      } else {
        // Put [ERROR] if it didnt know the word
        cleanedWords[i] = "[ERROR]";
      }
    }
    // 7. Return the cleanedWords, where some are in spanish 
    //    and some in english (if didnt get replaced)
    return String.join(" ", cleanedWords);
  }
/**
 * removes punctuation at end of sentence
 * @param words; individual words of user's typed sentence
 * @return wors without punctuation
 */
  public String[] removeTailPeriodOrComma(String[] words) {
    // 1. Traverse the words word list
    for (int i = 0; i < words.length; i++) {
      // 2. Replace all "." with an empty string
      words[i] = words[i].replace(".", "");
      // 3. Replacer all "," with an empty string
      words[i] = words[i].replace(",", "");
      words[i] = words[i].replace("!", "");
      words[i] = words[i].replace("?", "");
      words[i] = words[i].replace(":", "");
      words[i] = words[i].replace(";", "");
    }
    return words;
  }
/**
 * Finds the English words and then grabs the German equivelant
 * @param word english word
 * @return the index of the word
 */
  public int findEnglishWordIndex(String word) {
    // 1. Iterate over the english ArrayList (INDEX FOR-LOOP)
    for (int i = 0; i < english.size(); i++) {
      // 2. Get the current english word
      String current = english.get(i);
      // 3. If the current english word equals the word parameter
      if (current.equals(word)) {
        // 4. Return the index (i)
        return i;
      }
    }
    // 5. If not found, return -1
    return -1;
  }

  /**
   * Starts the app and gets user input
   */
  public void prompt() {
    Scanner input = new Scanner(System.in);

    System.out.println("Welcome to my translation app.");
    System.out.println("Type a sentence:");
    
    englishSentence = input.nextLine();
    
    System.out.println("\nTranslating...");
    
    germanSentence = translateSentence(englishSentence);
    
    printSummary();

    input.close();
  }


  /**
   * Prints the summary of my NLP project
   */
  public void printSummary() {
    System.out.println("\nEnglish Sentence: ");
    System.out.println(englishSentence);
    System.out.println("\nGerman Sentence: ");
    System.out.println(germanSentence);
  }

}