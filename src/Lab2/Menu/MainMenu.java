package Lab2.Menu;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner = new Scanner(System.in);

    public String takeUserInput() {
        System.out.println("INPUT CHOICE:");
        String sample = scanner.nextLine();
        System.out.println("YOUR CHOICE: " + sample);
        return sample;
    }

    public void handleInput() {
        String input = takeUserInput();
        String[] commandsList = input.split("/");
    }

    public void printChoices() {
        System.out.println("commit - COMMIT");
        System.out.println("info <filename> - DISPLAY INFORMATION ABOUT <filename>");
        System.out.println("status - DISPLAY CHANGES");
    }

    public void printMenu() {
        System.out.println("WELCOME TO MyGit!");
        System.out.println("WHAT DO YOU WANT TO DO?");
    }

    public void printHelp() {
        System.out.println("CHOICES");
        printChoices();
    }
}
