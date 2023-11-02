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

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }
}