package Lab1.behavior;

import Lab1.Managers.BatchManager;
import Lab1.Managers.FileManager;
import Lab1.Managers.Logger;
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
    private BatchManager batchManager;
    private Logger logger;
    private String username;

    public AppLoop() {
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this.printer = new Printer(this.university, this.scanner);
        System.out.println("| INPUT YOUR USERNAME: |");
        this.username = scanner.nextLine();
        System.out.println("| LOGGED IN AS " + this.username + " |");
        this.logger = new Logger(this.username);
        logger.saveEntry();
        this.activeMenu = new StartMenu(this.scanner, this.university, this.printer, this);
        this.handler = new Handler(this.scanner);
        this.fileManager = new FileManager(this.handler, this.university);
        this.batchManager = new BatchManager(this.handler, this.university, this.scanner, this.logger);
    }

    public void run() {
        try {
            university = fileManager.loadUniversityData();
            if (university == null) {
                university = new University();
            }
        } catch (Exception e) {
            university = new University();
        }

        while (!(activeMenu instanceof ExitMenu)) {
            activeMenu.printMenu();
            activeMenu.handleInput();
        }

        this.activeMenu.printMenu();
        this.activeMenu.handleInput();
        this.activeMenu.printChoices();
        this.activeMenu.printQuit();
        this.scanner.close();
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }

    public Logger getLogger() {
        return logger;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public BatchManager getBatchManager() {
        return this.batchManager;
    }

}
