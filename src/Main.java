import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter filename");

        Scanner reader = new Scanner(System.in);

        String path = reader.nextLine();

        FiniteAutomation fa = new FiniteAutomation(path);


        System.out.println("Enter word");

        String word = reader.nextLine();
        if(fa.CheckWord(word))
            System.out.println("The word is allowed");
        else
            System.out.println("The word is not allowed");
    }
}
