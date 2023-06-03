import java.util.Arrays;
import java.util.Scanner;
import com.theokanning.openai.*;
import com.theokanning.openai.completion.*;


public class Main{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your theme: ");
    
    String theme = scanner.nextLine();
    

    String word = scanner.nextLine();
    Hangman hangman = new Hangman(word,5);
    hangman.game();
    
  }
}