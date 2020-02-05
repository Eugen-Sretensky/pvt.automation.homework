import java.util.Scanner;

public class Task_1_Arrays_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter Array size:");
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        System.out.println("Array size is now: " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10);
        }
        System.out.println("All Array elements:");
        for (int i : array) {
            System.out.print(i + "; ");
        }
        System.out.println();
        int increasingCount = 0;

        for (int firstElement = 0; firstElement <= array.length - 2; firstElement++) {
            if (array[firstElement] < array[firstElement + 1]) {
                increasingCount++;
            }
        }
        System.out.println();
        if (increasingCount == array.length - 1) {
            System.out.println("Array is an increasing sequence!");
        } else {
            System.out.println("Array is NOT an increasing sequence!");
        }
    }
}