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

        System.out.print("Name of college: ");
        String college = scanner.nextLine();
        clearScreen();
//        System.out.println("Your college is: " + college);

        do {
            System.out.println("--- " + college.toUpperCase() + " ACADEMIC STAFF MANAGEMENT SYSTEM ---");
            System.out.println("0: Exit.");
            System.out.println("1: Add Lecturer.");
            System.out.println("2: Add Committee.");
            System.out.println("3: Add Department.");
            System.out.println("4: Assign Lecturer To a Committee.");
            System.out.println("5: View Average Salary of All Lecturers In College.");
            System.out.println("6: View Average Salary of All Lecturers In Chosen Deptartments.");
            System.out.println("7: View All Lecturers Details.");
            System.out.println("8: View All Committees Details.");
            System.out.print("Please choose your next action out of the preceding actions: ");

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
                    clearScreen(true);
                    break;
                case 2:
                    String committee = getValidInput("committee", comms, commsSize, scanner);
                    if (committee != null) {
                        comms = addElement(committee, comms, commsSize);
                        commsSize++;
                    }
                    clearScreen(true);
                    break;
                case 3:
                    String department = getValidInput("department", depts, deptsSize, scanner);
                    if (department != null) {
                        depts = addElement(department, depts, deptsSize);
                        deptsSize++;
                    }
                    clearScreen();
                    break;
                case 4:
                    System.out.print("Provide lecturer name: ");
                    String addedLecturer = scanner.nextLine();
                    System.out.print("Provide committee name: ");
                    String addedCommittee = scanner.nextLine();
                    assignLecturer(addedLecturer, lecturers, addedCommittee, comms);

                    clearScreen();
                    break;
                case 5, 6:
                    WIP();
                    break;
                case 7:
                    clearScreen(); // Clear Before display data
                    viewElementsDetails(lecturers, lecSize, "Lecturers");
                    break;
                case 8:
                    clearScreen();
                    viewElementsDetails(comms, commsSize, "Committees");
                    break;
            }

//            try {
//                Thread.sleep(1000); // Sleep 2 sec
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }

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

        System.out.println();
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

    private static void viewElementsDetails(String[] elements, int elemSize, String... elemName) {
        String name = (elemName.length > 0) ? elemName[0] : "Elements";
        if (elemSize == 0) {
            System.out.println("No " + name + " available right now!");
            return;
        }

        System.out.print(name + ": ");
        for (int i = 0; i < elemSize; i++) {
            System.out.print(elements[i]);

            if (i < elemSize - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
        System.out.println();
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
            System.out.print("Provide " + inputType + " name (0 = cancel): ");
            String input = sc.nextLine();
            boolean isNameExists = getIsElemExists(input, elems);

            if (input.equals("0")) {
                return null;
            }

            if (!isNameExists) {
                return input;
            }

            System.out.println();
            System.out.println(inputType + ": '" + input + "' already exists! Please choose another...");
        }
    }

    // Clears terminal - (Pretty sure if only for linux tho \m/)
    private static void clearScreen(boolean wait){ // Wait is true, For feedback like "Added blabla"
        if (wait) {
            try {
                Thread.sleep(1000); // Sleep 1 sec
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        clearScreen();
    }
    private static void clearScreen(){ // To clear screen before display data
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
