import java.util.Scanner;

public class Main {
    // מגישים: עידו בר, שקד גוברין

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;

        String[] lecturers = new String[2];
        String[] depts = new String[2];
        String[] comms = new String[2];

        int lecSize = 0;
        int commsSize = 0;
        int deptsSize = 0;

        System.out.println("Name you college...");
        String college = scanner.nextLine();
        System.out.println("Your college is: " + college);

        do {

            System.out.println("--- ACADEMIC STAFF MANAGEMENT SYSTEM ---");
            System.out.println("0: Exit.");
            System.out.println("1: Add Lecturer.");
            System.out.println("2: Add Committee.");
            System.out.println("3: Add Department.");
            System.out.println("4: Assign Lecturer To a Department.");
            System.out.println("5: View Average Salary of All Lecturers In College.");
            System.out.println("6: View Average Salary of All Lecturers In Chosen Deptartments.");
            System.out.println("7: View All Lecturers' Details.");
            System.out.println("8: View All Departmentrs Details.");
            System.out.println("Please choose your next action out of the preceding actions...");

            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Provide lecturer name...");
                    String newLecturer = scanner.nextLine();
                    lecturers = addLecturer(newLecturer, lecturers, lecSize);
                    break;
                case 2:
                    lecturers = addCommittee();
                    break;
                case 3:
                    addDept();
                    break;
                case 4:
                    assignLecturer();
                    break;
                case 5:
                    WIP();
                    break;
                case 6:
                    WIP();
                    break;
                case 7:
                    viewLecturerDetails();
                    break;
                case 8:
                    viewDeptsDetails();
                    break;
            }

        } while (choice != 0);
        System.out.println("Yallah bye");
        scanner.close();
    }

    private static String[] addLecturer(String newLecturer, String[] lecturers, int lecSize) {
        if (lecSize == lecturers.length) {
            lecturers = doubleArray(lecturers);
        }

        lecturers[lecSize] = newLecturer;
        return lecturers;
    }

    private static String[] addCommittee() {

    }

    private static String[] addDept() {

    }

    private static void assignLecturer() {

    }

    private static void WIP() {

    }

    private static void viewLecturerDetails() {

    }

    private static void viewDeptsDetails() {

    }

    private static String[] doubleArray(String[] arr) {
        int arrExtFactor = 2;
        int arrSize = arr.length;

        String[] newArr = new String[arrExtFactor * arrSize];

        for (int i = 0; i < arrSize; i++) {
            newArr[i] = arr[i];
        }

        return newArr;
    }
}
