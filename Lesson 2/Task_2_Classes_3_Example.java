public class Task_2_Classes_3_Example {
    public static void main(String[] args) {
        Task_2_Classes_2.Person person = new Task_2_Classes_2.Person("Bob");
        System.out.println("New person's name = " + person.getName());
        person.setName("Jimbo");
        System.out.println("Person's name was changed to " + person.getName());
        Task_2_Classes_3.Student student = new Task_2_Classes_3.Student();
        student.setName("Noah");
        System.out.println("Student is born!");
        System.out.println("Student's name is " + student.getName());
        System.out.println(student.getName() + "'s monthly scholarship = " + student.getScholarship());
        System.out.println(student.getName() + "'s average grade = " + student.AverageGrades());
        System.out.println(student.getName() + " is currently living with: " + student.Roommates());
        System.out.println("Is " + student.getName() + " eligible for military service?" + " Answer: " + student.IsEligibleForMilitary());
    }
}
