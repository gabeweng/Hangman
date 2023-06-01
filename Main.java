import java.util.Arrays;
import java.util.Scanner;

public class Main{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your word: ");
    String word = scanner.nextLine();
    Hangman hangman = new Hangman(word,5);
    hangman.game();
    
  }
}