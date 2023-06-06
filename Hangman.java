import java.util.Arrays;
import java.util.Scanner;
// import com.theokanning.openai.*;
public class Hangman {
  
  private char[] word;
  private char[] guesses;
  private int guessed=0;
  private int numGuess;
  private String letters = "";
  
  public Hangman(String word, int numGuess) {
    this.word = word.toUpperCase().toCharArray();
    this.guesses = new char[word.length()];
    Arrays.fill(guesses, '_');
    guess(' ');
    guess('!');
    guess('.');
    guess(',');
    guessed=0;
    this.numGuess = numGuess;
  }
  public boolean guess(char c) {
    boolean correct = false;
    letters=letters+Character.toUpperCase(c);
    for (int i = 0; i < word.length; i++) {
      if (word[i] == Character.toUpperCase(c)) {
                guesses[i] = Character.toUpperCase(c);
                correct = true;
      }
    }
    if (!correct) guessed++;
    return correct;
  }

    public boolean hasWon() {
        return Arrays.equals(word, guesses);
    }

    public boolean hasLost() {
        return (guessed>=numGuess);
    }

    public void display() {
      System.out.println(guesses);
      if (!hasWon()){
        System.out.print(numGuess + " strikes and you're out: ");
        for (int i=0; i<guessed; i++){
          System.out.print("X");
        }
        for (int i=0; i<numGuess-guessed; i++){
          System.out.print("0");
        }
        System.out.println();
      }
    }
  // public void hangman(){
  //   switch (guessed){
      
  //   }
  // }
  public void game(){
    Scanner scanner = new Scanner(System.in);
    display();
    while (!hasWon() && !hasLost()) {
      System.out.print("Guess a letter: ");
      char c = scanner.nextLine().charAt(0);
      if (letters.indexOf(Character.toUpperCase(c))!=-1){
        System.out.println("This has been guessed before");
      }
      else{
        if (!guess(c)) {
          System.out.println("Incorrect!");
        }
      }
      display();
      System.out.println();
    }
    if (hasWon()) {
      System.out.print("You win!");
    } else {
      System.out.print("You lose! Answer was ");
      for (char c: word){ System.out.print(c);}
    }
    System.out.println();
    System.out.println();
    System.out.println();
  }

    
}

