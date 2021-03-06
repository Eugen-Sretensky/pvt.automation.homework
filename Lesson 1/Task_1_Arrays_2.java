import java.util.Scanner;

public class Task_1_Arrays_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the Array size:");
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        System.out.println("Array size is now: " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        System.out.println("All Array elements (ASC):");
        for (int i : array) {
            System.out.print(i + "; ");
        }
        int allArrayElementsProduct = 1;
        for (int i = 0; i < array.length; i++) {
            allArrayElementsProduct = allArrayElementsProduct * array[i];
        }
        System.out.println();
        System.out.println("All Array elements product = " + allArrayElementsProduct);
    }
}

