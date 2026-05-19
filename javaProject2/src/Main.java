import java.util.Scanner;

public class Main {
    // מגישים: עידו בר - 211541149, שקד גוברין - 322920190

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;

        System.out.print("Name of college: ");
        String collegeName = scanner.nextLine();

        Manager man = new Manager(collegeName);

        do {
            System.out.println("--- " + man.getCollegeName().toUpperCase() + " ACADEMIC STAFF MANAGEMENT SYSTEM ---");
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
            scanner.nextLine(); // flush buffer

            switch (choice) {
                case 0:
                    break;
                case 1:
                    addLecturerMenu(man, scanner);
                    Lecturer[] lecs = man.getLecturers();
                    for (Lecturer l : lecs){
                        System.out.println(l.toString());
                    }
                    break;
            //    case 2:
            //         Manager.addCommittee();
            //         break;
               case 3:
                   addDepartmentMenu(man, scanner);
                   Department[] depts = man.getDepartments();

                   for (Department l : depts){
                       System.out.println(l.toString());
                   }
                   break;
//                case 4:
//                    System.out.print("Provide lecturer name: ");
//                    String addedLecturer = scanner.nextLine();
//                    System.out.print("Provide committee name: ");
//                    String addedCommittee = scanner.nextLine();
//                    Manager.assignLecturer(addedLecturer, lecturers, addedCommittee, comms);
//                    break;
//                case 5, 6:
////                    TODO: add func
//                    WIP();
//                    break;
//                case 7:
//                    Manager.viewElementsDetails(lecturers, lecSize, "Lecturers");
//                    break;
//                case 8:
//                    Manager.viewElementsDetails(comms, commsSize, "Committees");
//                    break;
            }
        } while (choice != 0);
        System.out.println("Yallah bye");
        scanner.close();
    }

    private static void addDepartmentMenu(Manager man, Scanner scanner) {
        String name;
        int numOfStudents;
        Department[] depts = man.getDepartments();
        System.out.println(depts.length);

        System.out.println("Provide Department's Name...");
        name = scanner.nextLine();
        boolean isDepExists = getIsDeptExists(name, depts);

        while (isDepExists) {
            System.out.print("Department Exists, Provide a Valid Department Name...");
            name = scanner.nextLine();
            isDepExists = getIsDeptExists(name, depts);
        }

        System.out.print("Provide Number Of Students In Department...");
        numOfStudents = scanner.nextInt();

        man.addDepartment(name, numOfStudents);
    }

    private static void addLecturerToDepartmentMenu(Manager man, Scanner scanner) {
        Lecturer[] lecs = man.getLecturers();
        Department[] depts = man.getDepartments();

        System.out.println("Provide Lecturer name: ");
        String lecName = scanner.nextLine();
        boolean isLecNameExists = getIsLecturerExists(lecName, lecs);

        while(!isLecNameExists) {
            System.out.println("Lecturer Not Found, Please Provide a Valid Lecturer Name");
            lecName = scanner.nextLine();
            isLecNameExists = getIsLecturerExists(lecName, lecs);
        }

        System.out.println("Provide Department Name: ");
        String deptName = scanner.nextLine();
        boolean isDeptNameExists = getIsDeptExists(deptName, depts);

        while(!isDeptNameExists) {
            System.out.println("Department Not Found, Please Provide a Valid Department Name");
            deptName = scanner.nextLine();
            isDeptNameExists = getIsLecturerExists(deptName, lecs);
        }


    }

    private static void addLecturerMenu(Manager man, Scanner scanner){
        String name;
        String id;
        Lecturer.Degree degreeRank;
        String degreeName;
        double salary;

        Lecturer[] lecs = man.getLecturers();
        System.out.print("Provide Lecturer name: ");
        name = scanner.nextLine();
        boolean nameExist = getIsLecturerExists(name, lecs);

        while (nameExist){
            System.out.print("Lecturer Exists, Provide a Lecturer name: ");
            name = scanner.nextLine();
            nameExist = getIsLecturerExists(name, lecs);
        }

        System.out.print("Provide Lecturer ID (Teudat Zehut): ");
        id = scanner.nextLine();

        System.out.print("Provide Lecturer Degree Rank: ");
        Lecturer.Degree[] degrees = Lecturer.Degree.values();
        int i = 0;
        for (Lecturer.Degree d : degrees) {
            System.out.println(i+ ": " + d);
            i++;
        }

        int choice = scanner.nextInt();
        boolean isValid = (choice >= 0 && choice < degrees.length);
        while(!isValid) {
            System.out.println("Invalid input!");
            choice = scanner.nextInt();
        }
        degreeRank = degrees[choice];

        scanner.nextLine();

        System.out.print("Provide Lecturer Degree Name: ");
        degreeName = scanner.nextLine();

        System.out.print("Provide Lecturer Salary: ");
        salary = scanner.nextDouble();

        man.addLecturer(name, id, degreeRank, degreeName, salary);
    }

    private static boolean getIsLecturerExists(String name, Lecturer[] lecs) {
        boolean isNameExists = false;

        for (int i = 0; i < lecs.length; i++) {
            if (lecs[i].getName().equals(name)){
                isNameExists = true;
            }
        }

        return isNameExists;
    }

    private static boolean getIsDeptExists(String name, Department[] depts) {
        boolean isNameExists = false;

        for (int i = 0; i < depts.length; i++ ) {
            if (depts[i].getName().equals(name)) {
                isNameExists = true;
            }
        }

        return isNameExists;
    }

//    private static String[] addElement(String newElem, String[] elements, int elemsSize) {
//        boolean isOverSize = elemsSize == elements.length;
//
//        if (isOverSize) {
//            elements = doubleArray(elements);
//        }
//
//        elements[elemsSize] = newElem;
//
//        System.out.println();
//        System.out.println("Added value: " + newElem);
//        return elements;
//    }
//
//    private static void assignLecturer(String lecturer, String[] lecturers, String committee, String[] comms) {
//        boolean isLecturerExists = getIsElemExists(lecturer, lecturers);
//        boolean isCommExists = getIsElemExists(committee, comms);
//
//        if (!isLecturerExists) {
//            System.out.println("Lecturer was not found!");
//        }
//
//        if (!isCommExists) {
//            System.out.println("Committee was not found!");
//        }
//    }
//
//    private static void WIP() {
//        System.out.println("Not available, try another option...");
//    }
//
//    private static void viewElementsDetails(String[] elements, int elemSize, String... elemName) {
//        String name = (elemName.length > 0) ? elemName[0] : "Elements";
//        if (elemSize == 0) {
//            System.out.println("No " + name + " available right now!");
//            return;
//        }
//
//        System.out.print(name + ": ");
//        for (int i = 0; i < elemSize; i++) {
//            System.out.print(elements[i]);
//
//            if (i < elemSize - 1) {
//                System.out.print(", ");
//            } else {
//                System.out.println();
//            }
//        }
//        System.out.println();
//    }
//
//    private static String[] doubleArray(String[] elements) {
//        int elemsExtFactor = 2;
//        int elemsSize = elements.length;
//
//        String[] newElems = new String[elemsExtFactor * elemsSize];
//
//        for (int i = 0; i < elemsSize; i++) {
//            newElems[i] = elements[i];
//        }
//
//        return newElems;
//    }
//
//    private static boolean getIsElemExists(String newElem, String[] elements) {
//        for (String element : elements) {
//            if (newElem.equals(element)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private static String getValidInput(String inputType, String[] elems, int size, Scanner sc) {
//        while (true) {
//            System.out.print("Provide " + inputType + " name (0 = cancel): ");
//            String input = sc.nextLine();
//            boolean isNameExists = getIsElemExists(input, elems);
//
//            if (input.equals("0")) {
//                return null;
//            }
//
//            if (!isNameExists) {
//                return input;
//            }
//
//            System.out.println();
//            System.out.println(inputType + ": '" + input + "' already exists! Please choose another...");
//        }
//    }
}
