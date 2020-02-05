import java.util.Scanner;

public class Task_1_Arrays_6 {
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
        for (int firstElement = 0; firstElement < array.length -1; firstElement++) {
            int secondElement = firstElement + 1;
            int bufferElement = array[firstElement];
            array[firstElement] = array[secondElement];
            array[secondElement] = bufferElement;
            firstElement++;
        }
        System.out.println();
        System.out.println("All Array elements (Updated):");
        for (int i : array) {
            System.out.print(i + "; ");
        }
    }
}

