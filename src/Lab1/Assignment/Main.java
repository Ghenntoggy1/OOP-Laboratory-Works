package Lab1.Assignment;

class Main {
    public static void main(String[] args) {
        Player Human1 = new Player(56, 67.8F, "Human", 43, 26);
        Human1.printCharacteristics();
        Player Human2 = new Player(10, 32.8F, "Human", 0, 54);
        Human2.printCharacteristics();
        Player Ork = new Player(10, 12.8F, "Ork", 21, 1);
        Ork.printCharacteristics();
        Player Ork2 = new Player(23, 11.8F, "Ork", -2, 1);
        Ork2.printCharacteristics();
    }
}
