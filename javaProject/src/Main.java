import java.util.Scanner;

public class Main {
    //מגישים: עידו בר, שקד גוברין

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

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
                    System.out.println("Yallah bye");
                    break;

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;                

                case 6:
                    break;
                    
                case 7:
                    break;
                    
                case 8:
                    break;
            }

        } while (choice != 0); 
            scanner.close();
        } 
}
