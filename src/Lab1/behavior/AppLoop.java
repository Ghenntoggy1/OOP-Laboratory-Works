package Lab1.behavior;

import Lab1.FileManager.FileManager;
import Lab1.Menus.ExitMenu;
import Lab1.Menus.StartMenu;
import Lab1.interfaces.Menu;
import Lab1.models.University;

import java.util.Scanner;

public class AppLoop {
    private University university;
    private Scanner scanner;
    private Printer printer;
    private Menu activeMenu;
    private Handler handler;
    private FileManager fileManager;

    public AppLoop() {
        this.scanner = new Scanner(System.in);
        this.printer = new Printer(this.university, this.scanner);
        this.activeMenu = new StartMenu(this.scanner, this.university, this.printer, this);
        this.handler = new Handler(this.scanner);
    }

    public void run() {
        try {
            university = FileManager.loadUniversityData();
        } catch (Exception e) {
            university = new University();
        }

        while (!(activeMenu instanceof ExitMenu)) {
            activeMenu.printMenu();
            activeMenu.handleInput();
        }
        FileManager.saveUniversityData(university);
        this.scanner.close();
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }
}
