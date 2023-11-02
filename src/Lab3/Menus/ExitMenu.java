package Lab3.Menus;

import Lab3.interfaces.Menu;

public class ExitMenu implements Menu {

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
        System.out.println("EXITING PROGRAM...");
    }

    @Override
    public void printInvalid() {

    }

    @Override
    public void printGreetings() {

    }
}
