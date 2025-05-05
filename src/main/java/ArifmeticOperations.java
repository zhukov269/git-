public class ArifmeticOperations {

    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 7;
        Double c = 2.5;

        // Арифметические операторы + - / * % ++ --
        System.out.println("a+b=" + (a + b));
        System.out.println("a-b=" + (a - b));
        System.out.println("a*b=" + (a * b));
        System.out.println("a/b=" + (a / b));
        System.out.println("a%b=" + (a % b));
        System.out.println("a++=" + a++ + ", ++a=" + ++a);
        System.out.println("b--=" + b-- + ", --b=" + --b);

    // Int+ Double
        System.out.println("a+c=" + (a + c));
        System.out.println("c++=" + c++ + ", ++c=" + ++c);
    // Логические
        System.out.println(a > b && b <= 10);
        System.out.println(a != b || b >= a);

        //Диапазоны
        System.out.println("Максимальное значение float: " + Float.MAX_VALUE);
        System.out.println("Минимальное положительное значение float (ближайшее к нулю): " + Float.MIN_VALUE);

        System.out.println("Максимальное значение double: " + Double.MAX_VALUE);
        System.out.println("Минимальное положительное значение double (ближайшее к нулю): " + Double.MIN_VALUE);

        // Переполнение
        double d = Double.MAX_VALUE;
        System.out.println("Переполнение: " + d * 2);
    }

}