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
                case 2:
                    addCommitteeMenu(man, scanner);
                    break;
               case 3:
                   addLecturerToCommittee(man, scanner);
                   break;
//                case 4:
//                    System.out.print("Provide lecturer name: ");
//                    String addedLecturer = scanner.nextLine();
//                    System.out.print("Provide committee name: ");
//                    String addedCommittee = scanner.nextLine();
//                    Manager.assignLecturer(addedLecturer, lecturers, addedCommittee, comms);
//                    break;
//                case 5:
//                    break;
                case 6:
                    addDepartmentMenu(man, scanner);
                    Department[] depts = man.getDepartments();

                    for (Department l : depts){
                        System.out.println(l.toString());
                    }
                    break;
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

    private static void addLecturerMenu(Manager man, Scanner scanner){
        String name;
        String id;
        Lecturer.Degree degreeRank;
        String degreeName;
        double salary;

        Lecturer[] lecs = man.getLecturers();
        System.out.print("Provide Lecturer name: ");
        name = scanner.nextLine();
        boolean nameExist = lecExist(name, lecs);

        while (nameExist){
            System.out.print("Lecturer Exists, Provide a Lecturer name: ");
            name = scanner.nextLine();
            nameExist = lecExist(name, lecs);
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

    private static boolean lecExist(String name,Lecturer[] lecs) {
        boolean nameExist = false;

        for (int i = 0; i < lecs.length; i++) {
            if (lecs[i].getName().equals(name)){
                nameExist = true;
            }
        }

        return nameExist;
    }

    private static void addLecturerToCommittee(Manager man, Scanner scanner){
        System.out.println("Provide Committee Name: ");
        String commName = scanner.nextLine();

        Committee[] comms = man.getCommittees();
        boolean commNameExist = commExist(commName, comms);
        while (!commNameExist) {
            System.out.print("Committee Does not Exists, Provide an existing Committee name: ");
            commName = scanner.nextLine();
            commNameExist = commExist(commName, comms);
        }

        System.out.print("Provide Lecturer Name: ");
        String lecName = scanner.nextLine();

        Lecturer[] lecs = man.getLecturers();
        boolean lecNameExist = lecExist(lecName, lecs);
        while (!lecNameExist){
            System.out.print("Lecturer Does not Exists, Provide an existing Lecturer name: ");
            lecName = scanner.nextLine();
            lecNameExist = lecExist(lecName, lecs);
        }
        man.addToCommittee(commName, lecName);
    }

    private static void addCommitteeMenu(Manager man, Scanner scanner) {
        String name;
        String chairmanName;

        System.out.print("Provide Committee Name: ");
        name = scanner.nextLine();
        Committee[] comms = man.getCommittees();

        boolean nameExist = commExist(name, comms);
        while (nameExist) {
            System.out.print("Committee Exists, Provide a new Committee name: ");
            name = scanner.nextLine();
            nameExist = commExist(name, comms);
        }

        System.out.print("Provide Lecturer Chairman name: ");
        chairmanName = scanner.nextLine();

        Lecturer chairman = man.getLecturerByName(chairmanName);
        while (chairman == null) {
            System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);
        }

        // TODO: Check if okay to do this check here or if need to be in manager.
        boolean isDegreeOk = chairman.getDegreeRank().ordinal() >= Lecturer.Degree.DR.ordinal();
        while (!isDegreeOk) {
            System.out.print("Chairman must be at least DR. Provide a different Chairman name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);

            // Check Lecturer exist
            while (chairman == null) {
                System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
                chairmanName = scanner.nextLine();
                chairman = man.getLecturerByName(chairmanName);
            }
            isDegreeOk = chairman.getDegreeRank().ordinal() >= Lecturer.Degree.DR.ordinal();
        }

        man.addCommittee(name, chairman);

        System.out.println("Committee '" + name + "' added successfully with Chairman " + chairman.getName() + "!");
    }

    private static boolean commExist(String name,Committee[] comms) {
        boolean nameExist = false;
        for (int i = 0; i < comms.length; i++) {
            if (comms[i].getName().equals(name)){
                nameExist = true;
            }
        }
        return nameExist;
    }

    private static void addDepartmentMenu(Manager man, Scanner scanner) {
        String name;
        int numOfStudents;
        Department[] depts = man.getDepartments();
        System.out.println(depts.length);

        System.out.println("Provide Department's Name...");
        //System.out.println("Provide Department's Name...");
        name = scanner.nextLine();
        boolean isDepExists = getIsDepExists(name, depts);

        while (isDepExists) {
            System.out.print("Department Exists, Provide a Valid Department Name...");
            name = scanner.nextLine();
            isDepExists = getIsDepExists(name, depts);
        }

        System.out.print("Provide Number Of Students In Department...");
        numOfStudents = scanner.nextInt();

        man.addDepartment(name, numOfStudents);
    }
    private static boolean getIsDepExists(String name, Department[] depts) {
        boolean isNameExists = false;

        for (int i = 0; i < depts.length; i++ ) {
            if (depts[i].getName().equals(name)) {
                isNameExists = true;
            }
        }

        return isNameExists;
    }

}
