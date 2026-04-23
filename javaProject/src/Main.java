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
            scanner.nextLine(); // flusing buffer, can probably find a better solution...

            switch (choice) {
                case 0:
                    break;
                case 1:
                    String lecturer = getValidInput("lecturer", lecturers, lecSize, scanner);
                    if (lecturer != null) {
                        lecturers = addElement(lecturer, lecturers, lecSize);
                        lecSize++;
                    }
                    break;
                case 2:
                    String committee = getValidInput("committee", lecturers, lecSize, scanner);
                    if (committee != null) {
                        lecturers = addElement(committee, comms, commsSize);
                        commsSize++;
                    }
                    break;
                case 3:
                    String department = getValidInput("department", depts, deptsSize, scanner);
                    if (department != null) {
                        lecturers = addElement(department, depts, deptsSize);
                        lecSize++;
                    }
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

        if (isOverSize) {
            elements = doubleArray(elements);
        }

        elements[elemsSize] = newElem;
        System.out.println("Added value: " + newElem);
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

    private static String getValidInput(String inputType, String[] elems, int size, Scanner sc) {
        while (true) {
            System.out.println("Provide " + inputType + "name ( or 0 to cancel )");
            String input = sc.nextLine();
            boolean isNameExists = getIsElemExists(input, elems);

            if (input.equals("0")) {
                return null;
            }

            if (!isNameExists) {
                return input;
            }

            System.out.println(inputType + ": '" + input + "'' already exists! Please choose another...");
        }
    }
}
