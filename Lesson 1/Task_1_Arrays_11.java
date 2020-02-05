import java.util.Scanner;

public class Task_1_Arrays_11 {
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

        int[] arrayOldValues = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            arrayOldValues[i] = array[i];
        }
        int i = 0;
        while (i < array.length) {
            if (i == 0) {
                array[i] = (int) (arrayOldValues[i + 1] * 0.5);
            } else if (i == array.length - 1) {
                array[i] = (int) (arrayOldValues[i - 1] * 0.5);
            } else {
                array[i] = (int) ((arrayOldValues[i + 1] + arrayOldValues[i - 1]) * 0.5);
            }
            i++;
        }
        System.out.println();
        System.out.println("All Array elements (Updated):");
        for (int l : array) {
            System.out.print(l + "; ");
        }
    }
}