public class Task_2_Classes_3 {
    public static class Student extends Task_2_Classes_2.Person {
        private double CalculateScholarship() {
            return 137.5;
        }
        public double getScholarship(){
            double scholarship = CalculateScholarship();
            return scholarship;
        }

        protected int AverageGrades() {
            return 7;
        }

        boolean IsEligibleForMilitary() {
            return true;
        }

        public String Roommates() {
            return "Student Z, Student X, Student O";
        }
    }
}
