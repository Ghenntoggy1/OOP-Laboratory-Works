package Lab2.filesExample;

public abstract class Shape {
    public double area = 0;
    public double perimeter = 0;

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public String toString(String shape) {
        return  shape + "\nArea: " + area + "\nPerimeter: " + perimeter + "\n";
    }
}
