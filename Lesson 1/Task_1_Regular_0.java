public class Task_1_Regular_0 {

    public static void main(String[] args) {
        int i = 0;
        int a = 0;
        a = a + i++;
        System.out.println("Переменная (i++) = " + a);

        System.out.println();
        i = 0;
        a = 0;
        a = a + ++i;
        System.out.println("Переменная (++i) = " + a);
    }
}
