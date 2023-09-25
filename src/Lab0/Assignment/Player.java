package Lab0.Assignment;

// Assignment:
// Create a class in a language and editor of your choice (Java, Python, C++, C#) that:
// • Stores information about the object (to represent an object’s state - moving/standing, it’s speed or color);
// • Has a method for informing the user of it’s state (printing it’s state to the console);
class Player {
    private boolean movingState;
    private String race;
    private float speed;
    private float damage;
    private float HP;
    private int lvl;

    public Player (float damage, float HP, String race, float speed, int lvl) {
        this.damage = damage;
        this.HP = HP;
        this.race = race;
        this.lvl = lvl;
        this.speed = speed;
        if (speed < 0) {
            throw new ArithmeticException("ERROR Speed less than 0!");
        }
        this.movingState = speed > 0;
    }

    public void setMovingState (boolean movingState) {
        this.movingState = movingState;
    }
    public boolean getMovingState () {
        return movingState;
    }
    public void setRace (String race) {
        this.race = race;
    }
    public String getRace () {
        return race;
    }
    public void setSpeed (float speed) {
        this.speed = speed;
    }
    public float getSpeed () {
        return speed;
    }
    public void setDamage (float damage) {
        this.damage = damage;
    }
    public float getDamage () {
        return damage;
    }
    public void setHP (float HP) {
        this.HP = HP;
    }
    public float getHP () {
        return HP;
    }
    public void setLvl (int lvl) {
        this.lvl = lvl;
    }
    public float getLvl () {
        return lvl;
    }

    public void printCharacteristics () {
        System.out.println("Race: " + getRace());
        if (getMovingState()) {
            System.out.println("State: Moving");
        }
        else {
            System.out.println("State: Idle");
        }
        System.out.println("HP: " + getHP());
        System.out.println("LVL: " + getLvl());
        System.out.println("Damage: " + getDamage());
        System.out.println("Speed: " + getSpeed());
    }
}

