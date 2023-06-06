import java.util.Arrays;
import java.util.Scanner;
import com.theokanning.openai.*;
import com.theokanning.openai.completion.*;
import com.theokanning.openai.service.OpenAiService;

public class Main{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int selection =0;
    while (selection!=3){
      selection = menu();
      System.out.println( "\u001b[2J");
      if (selection==1){
        System.out.println("Guessers, look away!");
        System.out.print("Enter your word: ");
        String word = scanner.nextLine();
        System.out.println( "\u001b[2J");
        Hangman hangman = new Hangman(word,5);
        hangman.game();
      }
      if (selection==2){
        System.out.print("Enter your theme: ");
        String theme = scanner.nextLine();
        option2(theme);
      }
    }
  }
  public static int menu(){
    System.out.println(" _   _\n"+
"| | | |\n"+                                       
"| |_| | __ _ _ __   __ _ _ __ ___   __ _ _ __\n"+
"|  _  |/ _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\"+"\n"+
"| | | | (_| | | | | (_| | | | | | | (_| | | | |\n"+
"\\_| |_/\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n"+
"                    __/ |                      \n"+
"                   |___/                       ");
    Scanner scanner = new Scanner(System.in);
    System.out.println("1 - Play with own word");
    System.out.println("2 - Play with generated words");
    System.out.println("3 - Quit");
    return (scanner.nextInt());
  }
  
  public static void option2(String theme){
    System.out.println("Retrieving words...");
    OpenAiService service = new OpenAiService(System.getenv("OPENAI_TOKEN"));
    CompletionRequest completionRequest = CompletionRequest.builder()
      .model("curie")
      .prompt("10 words or phrases that relate to the theme of planets: solar system, mars, neptune, earth, terrestrial planet, gas giant, mercury, jupiter, uranus, ice giant\n"+
"10 words or phrases that relate to the theme of vegetables: carrot, broccoli, onion, kale, celery, broccoli, cabbage, turnip, lettuce, brussels sprouts\n"+
"10 words or phrases that relate to the theme of chemistry: hydrogen, molar mass, entropy, carbon, heat of fusion, redox reactions, oxygen, hydrogen, molecules, titration\n"+
"10 words or phrases that relate to the theme of fruit: pineapple, cherry, grape, honeydew, kiwi, lemon, orange, peach, lime, banana\n"+
"10 words or phrases that relate to the theme of " + theme +":")
      .n(1)
      .build();
    String text = service.createCompletion(completionRequest).getChoices().get(0).getText();
    String[] words = text.strip().split(", ");
    int correct=0;
    for (int w=0; w<words.length;w++){
      Hangman hangman = new Hangman(words[w],5);
      hangman.game();
      if (hangman.hasWon()) correct++;
      
    }
    System.out.println("You got " + correct + " out of " + words.length + " correct.");
  }
}