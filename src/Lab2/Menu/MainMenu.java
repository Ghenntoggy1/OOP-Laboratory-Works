package Lab2.Menu;

import java.util.Scanner;

public class MainMenu implements MenuInterface {
    Scanner scanner;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String takeUserInput() {
        System.out.println("INPUT CHOICE:");
        String sample = scanner.nextLine();
        System.out.println("YOUR CHOICE: " + sample);
        return sample;
    }

    @Override
    public void printChoices() {
        System.out.println("commit, c - COMMIT");
        System.out.println("info <filename>, i <filename> - DISPLAY INFORMATION ABOUT <filename>");
        System.out.println("status, s - DISPLAY CHANGES");
        System.out.println("help, h - CHOICES");
        System.out.println("quit, q - EXIT PROGRAM");
    }

    @Override
    public void printMenu() {
        System.out.println("WELCOME TO MyGit!");
        System.out.println("WHAT DO YOU WANT TO DO?");
    }

    @Override
    public void printHelp() {
        System.out.println("CHOICES");
        printChoices();
    }

    @Override
    public void printExit() {
        System.out.println("EXITTING PROGRAM...");
    }
}
