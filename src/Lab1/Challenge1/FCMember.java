package Lab1.Challenge1;

// Challenge 4:  Categorize a member
// We are building a member system for a small football club. When adding a new user
// we want to automatically assign a category based on member’s age if it is greater
// than 55 and if he has a handicap greater than 7.
// Member member = new Member(“Don”, 34, 9);
// member.printCategory() // OPEN
// Member member1 = new Member(“Don”, 64, 3);
// member1.printCategory() // OPEN
// Member member2 = new Member(“Don”, 56, 9);
// member2.printCategory() // SENIOR
public class FCMember {
    private String name;
    private int age;
    private int handicap;
    private String category;

    public FCMember(String name, int age, int handicap) {
        this.name = name;
        this.age = age;
        this.handicap = handicap;
        this.category = getCategory();
    }

    private String getCategory() {
        category = "";
        if (getAge() > 55) {
            category += "Age Category: Senior (>55)\n";
        } else {
            category += "Age Category: Young (<=55)\n";
        }
        if (getHandicap() > 7) {
            category += "Handicap Category: Handicap (>7)";
        } else {
            category += "Handicap Category: Healthy (<=7)";
        }

        return category;
    }

    public void printCategory() {
        System.out.println("Name: " + getName());
        System.out.println(category);
    }

    private void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public int getHandicap() {
        return handicap;
    }
}

