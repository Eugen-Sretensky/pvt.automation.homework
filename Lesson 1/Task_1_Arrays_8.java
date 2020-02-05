import java.util.Scanner;

public class Task_1_Arrays_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter Array size:");
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        System.out.println("Array size is now: " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        System.out.println("All Array elements:");
        for (int i : array) {
            System.out.print(i + "; ");
        }
        System.out.println();
        int maxElement = 0;
        int maxElementSequenceNumber = 0;
        int minElement = 10000;
        int minElementSequenceNumber = 0;

        for (int firstElement = 0; firstElement <= array.length - 1; firstElement++) {
            if (maxElement < array[firstElement]) {
                maxElement = array[firstElement];
                maxElementSequenceNumber = firstElement;
            }
            if (minElement > array[firstElement]) {
                minElement = array[firstElement];
                minElementSequenceNumber = firstElement;
            }
        }
        System.out.println();
        System.out.println("Maximal Array element sequence number = " + maxElementSequenceNumber);
        System.out.println("Minimal Array element sequence number = " + minElementSequenceNumber);

    }
}


