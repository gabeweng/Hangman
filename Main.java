import java.util.Arrays;
import java.util.Scanner;
import com.theokanning.openai.*;
import com.theokanning.openai.completion.*;
import com.theokanning.openai.service.OpenAiService;

public class Main{
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter your theme: ");
    
    String theme = scanner.nextLine();
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
    for (int w=0; w<words.length;w++){
      Hangman hangman = new Hangman(words[w],5);
      hangman.game();
    }

    
  }
}