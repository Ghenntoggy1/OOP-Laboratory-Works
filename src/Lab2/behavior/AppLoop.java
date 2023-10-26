package Lab2.behavior;

import Lab2.Menu.MainMenu;

import java.util.Scanner;

public class AppLoop {
    Scanner scanner;
    MainMenu mainMenu;
    public AppLoop() {
        this.scanner = new Scanner(System.in);
        this.mainMenu = new MainMenu(this.scanner);
    }
    public void run() {
        mainMenu.printMenu();
        mainMenu.printChoices();
        mainMenu.handleInput();
        mainMenu.printExit();
        scanner.close();
    }
}
