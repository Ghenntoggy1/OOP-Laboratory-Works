package Lab0.Challenge3;// Challenge 2: Multiples of 3 or 5
// You are to write a method inside MathUtils class that will take as a parameter a number, and you’re to
// print the sum of all multiples of 3 or 5.
// 10 -> 23 (3, 5, 9)

import java.util.ArrayList;
public class MathUtils {
    private ArrayList<Integer> multiplesList;
    private int numLimit;
    private int sum;

    public MathUtils(int numLimit) {
        this.numLimit = numLimit;
    }

    public void addMultipliersAndSum() {
        if (multiplesList != null) {
            multiplesList.clear();
        }
        else {
            multiplesList = new ArrayList<>();
        }
        int sum = 0;
        for(int i = 1; i < getNumLimit(); i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
                multiplesList.add(i);
            }
        }
        setSum(sum);
    }

    public int getNumLimit() {
        return numLimit;
    }
    private void setSum(int sum) {
        this.sum = sum;
    }
    public int getSum() {
        return this.sum;
    }
    public ArrayList<Integer> getMultiplesList() {
        return multiplesList;
    }
}
