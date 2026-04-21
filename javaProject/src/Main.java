import java.util.Scanner;
import java.util.Arrays;

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
            scanner.nextLine(); // flusing buffer, probably can find a better solution...

            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Provide lecturer name...");
                    String newLecturer = scanner.nextLine();
                    lecturers = addElement(newLecturer, lecturers, lecSize);
                    lecSize++;
                    break;
                case 2:
                    System.out.println("Provide committee name...");
                    String newCommittee = scanner.nextLine();
                    comms = addElement(newCommittee, comms, commsSize);
                    commsSize++;
                    break;
                case 3:
                    System.out.println("Provide department name...");
                    String newDepartment = scanner.nextLine();
                    depts = addElement(newDepartment, depts, deptsSize);
                    deptsSize++;
                    break;
                case 4:
                    System.out.println("Provide lecturer name...");
                    String addedLecturer = scanner.nextLine();
                    System.out.println("Provide committee name...");
                    String addedCommittee = scanner.nextLine();
                    assignLecturer(addedLecturer, lecturers, addedCommittee, comms);
                    break;
                case 5:
                    WIP();
                    break;
                case 6:
                    WIP();
                    break;
                case 7:
                    viewElementsDetails(lecturers, lecSize);
                    break;
                case 8:
                    viewElementsDetails(comms, commsSize);
                    break;
            }

        } while (choice != 0);
        System.out.println("Yallah bye");
        scanner.close();
    }

    private static String[] addElement(String newElem, String[] elements, int elemsSize) {
        boolean isOverSize = elemsSize == elements.length;
        boolean isElemTaken = getIsElemExists(newElem, elements);

        if (isOverSize) {
            elements = doubleArray(elements);
        }

        if (isElemTaken) {
            System.out.println("Value is taken, please try another...");
            return elements;
        }

        elements[elemsSize] = newElem;
        return elements;
    }

    private static void assignLecturer(String lecturer, String[] lecturers, String committee, String[] comms) {
        boolean isLecturerExists = getIsElemExists(lecturer, lecturers);
        boolean isCommExists = getIsElemExists(committee, comms);

        if (!isLecturerExists) {
            System.out.println("Lecturer was not found!");
        }

        if (!isCommExists) {
            System.out.println("Committee was not found!");
        }
    }

    private static void WIP() {
        System.out.println("Not available, try another option...");
    }

    private static void viewElementsDetails(String[] elements, int elemSize) {
        for (int i = 0; i < elemSize; i++) {
            System.out.print(elements[i]);

            if (i < elemSize - 1) {
                System.out.print(", ");
            } else {
                System.out.print("\n");
            }
        }
    }

    private static String[] doubleArray(String[] elements) {
        int elemsExtFactor = 2;
        int elemsSize = elements.length;

        String[] newElems = new String[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = elements[i];
        }

        return newElems;
    }

    private static boolean getIsElemExists(String newElem, String[] elements) {
        for (String element : elements) {
            if (newElem.equals(element)) {
                return true;
            }
        }

        return false;
    }
}
