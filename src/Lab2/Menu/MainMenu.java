package Lab2.Menu;

import java.util.Scanner;

public class MainMenu {
    Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeUserInput() {
        System.out.println("INPUT CHOICE:");
        String sample = scanner.nextLine();
        System.out.println("YOUR CHOICE: " + sample);
        return sample;
    }

    public void printChoices() {
        System.out.println("commit, c - COMMIT");
        System.out.println("info <filename>, i <filename> - DISPLAY INFORMATION ABOUT <filename>");
        System.out.println("status, s - DISPLAY CHANGES");
        System.out.println("help, h - CHOICES");
        System.out.println("quit, q - EXIT PROGRAM");
    }

    public void printMenu() {
        System.out.println("WELCOME TO MyGit!");
        System.out.println("WHAT DO YOU WANT TO DO?");
    }

    public void printHelp() {
        System.out.println("CHOICES");
        printChoices();
    }

    public void printExit() {
        System.out.println("EXITTING PROGRAM...");
    }
}
