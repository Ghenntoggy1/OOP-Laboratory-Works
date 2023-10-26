package Lab2.filesExample;

public class Blender {
    private int speed;
    private boolean isFull;
    public int getSpeed() {
        return this.speed;
    }
    public void setSpeed(int speed) {
            if (speed >= 0 && speed <= 5) {
                this.speed = speed;
            }
            else {
                System.out.println("WRONG SPEED!");
            }
    }
    public void fill() {
        this.isFull = true;
    }
    public void empty() {
        this.isFull = false;
    }
}
