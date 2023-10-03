package Lab1.behavior;

import Lab1.Managers.BatchManager;
import Lab1.Managers.FileManager;
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

    public AppLoop() {
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this.printer = new Printer(this.university, this.scanner);
        this.activeMenu = new StartMenu(this.scanner, this.university, this.printer, this);
        this.handler = new Handler(this.scanner);
        this.fileManager = new FileManager(this.handler, this.university);
        this.batchManager = new BatchManager(this.handler, this.university, this.scanner);
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

        System.out.println("+------------------------------------+");
        System.out.println("| DO YOU WANT TO SAVE OR RESET DATA? |");
        System.out.println("| 1. SAVE                            |");
        System.out.println("| 2. RESET                           |");
        System.out.println("+------------------------------------+");
        System.out.println("| INPUT CHOICE:                      |");
        boolean flag = true;
        while (flag) {
            String input = scanner.nextLine();
            switch (input) {
                case "1", "S", "s" -> {
                    fileManager.saveUniversityData(university);
                    flag = false;
                }
                case "2", "R", "r" -> {
                    fileManager.resetDatabases();
                    flag = false;
                }
                default -> System.out.println("| INVALID CHOICE! TRY AGAIN:         |");
            }
        }
        this.activeMenu.printQuit();
        this.scanner.close();
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public BatchManager getBatchManager() {
        return this.batchManager;
    }
}
