package Lab1.Challenge1;

class Main {
    public static void main(String[] args) {
        FCMember member1 = new FCMember("Mellisa", 23, 5);
        FCMember member2 = new FCMember("Andrew", 56, 9);
        FCMember member3 = new FCMember("John", 19, 12);
        FCMember member4 = new FCMember("Michael", 60, 1);
        member1.printCategory();
        System.out.println();
        member2.printCategory();
        System.out.println();
        member3.printCategory();
        System.out.println();
        member4.printCategory();
    }
}
