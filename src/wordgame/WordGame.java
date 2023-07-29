package wordgame;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGame {
    private ArrayList<String> wordList;
    private char secret[];
    private boolean used[];
    private int guesses;
    private boolean isGameDone;
    private String revealed;
    
    public WordGame() throws Exception {
      wordList = new ArrayList<String>();
      used = new boolean[26];
      Scanner input = new Scanner(new File("words.txt"));
      while(input.hasNext()) {
          String str = input.next();
          wordList.add(str);
      }
      startGame();
    }
    
    public void startGame() {
        for(int n = 0;n < 26;n++) {
          used[n] = false;
      }
        Random rnd = new Random();
        int N = rnd.nextInt(wordList.size());
        secret = wordList.get(N).toCharArray();
        revealed = "*****";
        isGameDone = false;
        guesses = 0;
    }
    
    public String getWord() {
        return revealed;
    }
    
    public boolean isGameOver() {
        return isGameDone;
    }
    
    public int getGuessCount() {
        return guesses;
    }
    
    public boolean checkGuess(String guess) {
        // Start with validity checks
        // A valid guess is a word that appears in the word list
        if(isGameDone == true)
            return false;
        boolean inDictionary = false;
        for(String word : wordList) {
            if(word.equalsIgnoreCase(guess))
                inDictionary = true;
        }
        if(!inDictionary)
            return false;
        
        // Mark each letter in the guess as a used letter
        char chs[] = guess.toCharArray();
        for(int n = 0;n < 5;n++)
            used[chs[n]-'a'] = true;
        
        // Now build the masked version of the secret word
        char revChs[] = new char[5];
        boolean done = true;
        // For each letter in the secret word...
        for(int n = 0;n < 5;n++) {
           // ...check to see if the user has used that letter.
           if(used[secret[n]-'a']==true)
                revChs[n] = secret[n];
            else {
                revChs[n] = '*';
                done = false; // If there is at least one letter
                // in the secret word that that user has not used
                // are not done with the game.
            }
        }
        revealed = new String(revChs);
        isGameDone = done;
        guesses++;
        return true;
    }
    
    public static void main(String[] args) {
         WordGame game = null;
         try {
             game = new WordGame();
         } catch(Exception ex) {
             System.out.println("Could not initialize game.");
             System.exit(1);
         }
         System.out.println("The secret word is "+game.getWord());

         Scanner input = new Scanner(System.in);
         while(!game.isGameOver()) {
             System.out.print("Enter your guess: ");
             String guess = input.nextLine();
             boolean valid = game.checkGuess(guess);
             if(!valid)
                 System.out.println("Invalid guess.");
             else
                 System.out.println("The secret word is "+game.getWord());
         }
         System.out.println("Game over after "+game.getGuessCount()+" guesses.");
     }
    
}
