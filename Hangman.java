import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
  private char[] word;
  private char[] guesses;
  private int guessed=0;
  private int numGuess;
  
  public Hangman(String word, int numGuess) {
    this.word = word.toUpperCase().toCharArray();
    this.guesses = new char[word.length()];
    Arrays.fill(guesses, '_');
    this.numGuess = numGuess;
  }
  public boolean guess(char c) {
    boolean correct = false;
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
        return (guessed>numGuess && !hasWon());
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
  public void game(){
    Scanner scanner = new Scanner(System.in);
    display();
    while (!hasWon() && !hasLost()) {
      System.out.print("Guess a letter: ");
      char c = scanner.nextLine().charAt(0);
      if (!guess(c)) {
        System.out.println("Incorrect!");
      }
      display();
    }
    if (hasWon()) {
      System.out.println("You win!");
    } else {
      System.out.println("You lose!");
    }
  }

    
}
