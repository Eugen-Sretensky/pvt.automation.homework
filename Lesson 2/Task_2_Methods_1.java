public class Task_2_Methods_1 {
    public static void main(String[] args) {
        int first = (int) (Math.random() * 100);
        int second = (int) (Math.random() * 100);
        System.out.println("The first number = " + first);
        System.out.println("The second number = " + second);
        Task_2_Methods_1 example = new Task_2_Methods_1();
        System.out.println("Minimal number = " + example.MinNumber(first, second));
        System.out.println("Is the first number even? Answer: " + example.IsNumberEven(first));
        System.out.println("Is the second number even? Answer: " + example.IsNumberEven(second));
        System.out.println("The first number square = " + example.SquaredNumber(first));
        System.out.println("The second number cube = " + example.CubedNumber(second));
    }

    public int MinNumber(int a, int b) {
        int minNumber = 0;
        if (a < b) {
            minNumber = a;
        } else {
            minNumber = b;
        }
        return minNumber;
    }

    public boolean IsNumberEven(int a) {
        boolean result;
        result = a % 2 == 0;
        return result;
    }

    public int SquaredNumber(int a) {
        return a * a;
    }

    public int CubedNumber(int a) {
        return a * a * a;
    }
}
