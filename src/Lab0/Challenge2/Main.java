package Lab0.Challenge2;

class Main {
    public static void main(String[] args) {
        User user1 = new User("Ion", "Doe");
        user1.abreviate();
        System.out.println("Ion Doe -> " + user1.getAbreviatedName());
        User user2 = new User("Miley", "Cyrus");
        user2.abreviate();
        System.out.println("Miley Cyrus -> " + user2.getAbreviatedName());
    }
}
