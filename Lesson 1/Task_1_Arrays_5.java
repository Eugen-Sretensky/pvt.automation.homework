import java.util.Scanner;

public class Task_1_Arrays_5 {
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
        int count = 0;
        String zeroElements = "Array element(s) with zero value: ";
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                count++;
                zeroElements = zeroElements + i + "; ";
            }
        }
        System.out.println();
        if (count > 0) {
            System.out.println(zeroElements);
        }
        if (count == 0) {
            System.out.println("Array has NO elements with zero value!");
        }
    }
}

