package Lab1.interfaces;

import Lab1.behavior.Printer;
import Lab1.models.University;

import java.util.Scanner;

public interface Menu {
    void handleInput();
    String takeUserInput();
    void printMenu();
    void printChoices();
    void printHelp();
    void printQuit();
    void printInvalid();
    void printGreetings();
}
