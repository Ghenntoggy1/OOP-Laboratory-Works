package Lab3.behavior;

import Lab3.Menus.ExitMenu;
import Lab3.interfaces.Menu;
import Lab3.Menus.MainMenu;

import java.util.Scanner;

public class AppLoop {
    private final Scanner scanner;
    private Menu activeMenu;

    public AppLoop() {
        this.scanner = new Scanner(System.in);
        this.activeMenu = new MainMenu(this.scanner, this);
    }

    public void run() {
        while (!(this.activeMenu instanceof ExitMenu)) {
            this.activeMenu.printMenu();
            this.activeMenu.handleInput();
        }
        this.activeMenu.printQuit();
        this.scanner.close();
    }

    public Menu getActiveMenu() {
        return activeMenu;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }
}

//        this.mainMenu.printMenu();
//        this.mainMenu.printChoices();
//        boolean flag = true;
//        while(flag) {
//            String input = this.mainMenu.takeUserInput();
//            String[] commandsList = input.split(" ");
//            switch (commandsList[0]) {
//                case "s", "stack" -> {
//                    System.out.println("STACK WIP");
//                }
//                case "q", "queue" -> {
//                    System.out.println("QUEUE WIP");
//                }
//                case "h", "help" -> this.mainMenu.printHelp();
//                case "e", "exit" -> {
//                    this.mainMenu.printExit();
//                    flag = false;
//                }
//                default -> System.out.println("INVALID COMMAND!");
//            }
//        }