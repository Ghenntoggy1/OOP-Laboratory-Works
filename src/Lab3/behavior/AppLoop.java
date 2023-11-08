package Lab3.behavior;

import Lab3.Menus.GeneralMenus.ExitMenu;
import Lab3.interfaces.MenuInterface;
import Lab3.Menus.GeneralMenus.MainMenu;

import java.util.Scanner;

public class AppLoop {
    private final Scanner scanner;
    private MenuInterface activeMenu;

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

    public void setActiveMenu(MenuInterface activeMenu) {
        this.activeMenu = activeMenu;
    }
}