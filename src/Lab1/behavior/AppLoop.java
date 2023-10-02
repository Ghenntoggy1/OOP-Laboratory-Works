package Lab1.behavior;

import Lab1.models.Faculty;
import Lab1.models.StudyField;
import Lab1.models.University;

import java.util.Scanner;

public class AppLoop {
    private University university;
    private Scanner scanner;
    private Printer printer;
    private Handler handler;

    public AppLoop() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        this.printer = new Printer(this.university, this.scanner);
        this.handler = new Handler(this.printer, this.scanner, this.university);
    }

    public void run() {
        printer.startMenu();
        handler.handleStartMenu();

        // vTODO injtergartte wjile loop here
        this.scanner.close();
    }
}
