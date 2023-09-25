package Lab0.Challenge3;

class Main {
    public static void main(String[] args) {
        MathUtils obj1 = new MathUtils(10);
        obj1.addMultipliersAndSum();
        StringBuilder result = new StringBuilder("10 -> " + obj1.getSum() + " (");
        for (Integer num : obj1.getMultiplesList()) {
            result.append(num).append(" ");
        }
        System.out.println(result.substring(0, result.length() - 1) + ")");

        MathUtils obj2 = new MathUtils(15);
        obj2.addMultipliersAndSum();
        result = new StringBuilder("15 -> " + obj2.getSum() + " (");
        for (Integer num : obj2.getMultiplesList()) {
            result.append(num).append(" ");
        }
        System.out.println(result.substring(0, result.length() - 1) + ")");
    }
}
