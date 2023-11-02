package Lab3.Menus;

import Lab3.behavior.AppLoop;
import Lab3.interfaces.Menu;

import java.util.Scanner;

public class StackMenu implements Menu {
    private Scanner scanner;
    private AppLoop appLoop;

    public StackMenu(Scanner scanner, AppLoop appLoop) {
        this.scanner = scanner;
        this.appLoop = appLoop;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public String takeUserInput() {
        return null;
    }

    @Override
    public void printMenu() {

    }

    @Override
    public void printChoices() {

    }

    @Override
    public void printHelp() {

    }

    @Override
    public void printQuit() {

    }

    @Override
    public void printInvalid() {

    }

    @Override
    public void printGreetings() {

    }
}
