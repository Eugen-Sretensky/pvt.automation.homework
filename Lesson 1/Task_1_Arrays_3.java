import java.util.Scanner;

public class Task_1_Arrays_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter Array size:");
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        System.out.println("Array size is now: " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        System.out.println("All Array elements:");
        for (int i : array) {
            System.out.print(i + "; ");
        }
        System.out.println();
        System.out.println("Multiplying each 3rd Array element:");
        for (int i = 0; i < array.length; i++) {
            int count = i + 1;
            if (count % 3 == 0) {
                array[i] = array[i] * 2;
            }
        }
        System.out.println();
        System.out.println("All Array elements (Updated):");
        for (int i : array) {
            System.out.print(i + "; ");
        }
    }
}
