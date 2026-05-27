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
            System.out.println("3: Assign Lecturer To a Committee.");
            System.out.println("4: Update Committee Chairman.");
            System.out.println("5: Remove Lecturer From a Committee.");
            System.out.println("6: Add Department.");
            System.out.println("7: Assign Lecturer To a Department.");
            System.out.println("8: View Average Salary of All Lecturers In College.");
            System.out.println("9: View Average Salary of All Lecturers In Chosen Deptartments.");
            System.out.println("10: View All Lecturers Details.");
            System.out.println("11: View All Committees Details.");
            System.out.print("Please choose your next action out of the preceding actions: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // flush buffer

            switch (choice) {
                case 0:
                    break;
                case 1:
                    addLecturerMenu(man, scanner);
                    break;
                case 2:
                    addCommitteeMenu(man, scanner);
                    break;
                case 3:
                    addLecturerToCommitteeMenu(man, scanner);
                    break;
                case 4:
                    changeCommitteeChairmanMenu(man, scanner);
                    break;
                case 5:
                    removeLecturerFromCommitteeMenu(man, scanner);
                    break;
                case 6:
                    addDepartmentMenu(man, scanner);
                    break;
                case 7:
                    addLecToDepMenu(man, scanner);
                    break;
                case 8:
                    displayAvgSalary(man);
                    break;
                case 9:
                    displayAvgSalaryInDepartment(man, scanner);
                    break;
                case 10:
                    displayLecturers(man);
                    break;
                case 11:
                    displayCommittee(man);
                    break;
            }
        } while (choice != 0);
        System.out.println("Yallah bye");
        scanner.close();
    }

    ///                  ///
    ///     LECTURER     ///
    ///                  ///
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
            isValid = (choice >= 0 && choice < degrees.length);
        }
        degreeRank = degrees[choice];

        scanner.nextLine();

        System.out.print("Provide Lecturer Degree Name: ");
        degreeName = scanner.nextLine();

        System.out.print("Provide Lecturer Salary: ");
        salary = scanner.nextDouble();

        man.addLecturer(name, id, degreeRank, degreeName, salary);
        System.out.println("Lecturer '" + name + "' added successfully!");
    }

    private static boolean getIsLecturerExists(String name, Lecturer[] lecs) {
        boolean nameExist = false;
        for (Lecturer lec : lecs) {
            if (lec.getName().equals(name)) {
                nameExist = true;
                break;
            }
        }

        return nameExist;
    }

    private static void displayLecturers(Manager man){
        Lecturer[] lecs = man.getLecturers();
        for (Lecturer l : lecs){
            System.out.println(l.toString());
        }
    }

    private static void displayAvgSalary(Manager man){
        Lecturer[] lecs = man.getLecturers();
        double avg = Manager.getAverageSalary(lecs);
        System.out.println("The Average Salary is: " + avg);
    }

    ///                  ///
    ///     COMMITTEE    ///
    ///                  ///
    private static void addLecturerToCommitteeMenu(Manager man, Scanner scanner){
        String[] inputs = getCommLecInputs(man, scanner);
        String commName = inputs[0];
        String lecName = inputs[1];

        man.addLecToCommittee(commName, lecName);
        System.out.println("Lecturer '" + lecName + "' added successfully to Committe " + commName + "!");
    }

    private static void addCommitteeMenu(Manager man, Scanner scanner) {
        String[] inputs = getCommChairmanInputs(man, scanner, false);
        String name = inputs[0];
        String chairmanName = inputs[1];
        Lecturer chairman = man.getLecturerByName(chairmanName);

        man.addCommittee(name, chairman);
        System.out.println("Committee '" + name + "' added successfully with Chairman " + chairman.getName() + "!");
    }

    private static String[] getCommLecInputs(Manager man, Scanner scanner){
        System.out.print("Provide Committee Name: ");
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
        boolean lecNameExist = getIsLecturerExists(lecName, lecs);
        while (!lecNameExist){
            System.out.print("Lecturer Does not Exists, Provide an existing Lecturer name: ");
            lecName = scanner.nextLine();
            lecNameExist = getIsLecturerExists(lecName, lecs);
        }
        return new String[]{commName, lecName};
    }

    private static String[] getCommChairmanInputs(Manager man, Scanner scanner, boolean exist){
        String name;
        String chairmanName;

        System.out.print("Provide Committee Name: ");
        name = scanner.nextLine();
        Committee[] comms = man.getCommittees();

        boolean nameExist = commExist(name, comms);
        while (exist != nameExist) {
            if (exist) System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            else System.out.print("Committee Exists, Provide a new Committee name: ");
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

        boolean isDegreeOk = chairman.getDegreeRank().ordinal() >= Lecturer.Degree.DR.ordinal();
        while (!isDegreeOk) {
            System.out.print("Chairman must be at least DR. Provide a different Chairman name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);

            while (chairman == null) {
                System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
                chairmanName = scanner.nextLine();
                chairman = man.getLecturerByName(chairmanName);
            }
            isDegreeOk = chairman.getDegreeRank().ordinal() >= Lecturer.Degree.DR.ordinal();
        }
        return new String[]{name, chairmanName};
    }

    private static boolean commExist(String name,Committee[] comms) {
        boolean nameExist = false;
        for (Committee comm : comms) {
            if (comm.getName().equals(name)) {
                nameExist = true;
                break;
            }
        }
        return nameExist;
    }

    private static void changeCommitteeChairmanMenu(Manager man, Scanner scanner){
        String[] inputs = getCommChairmanInputs(man, scanner, true);
        String commName = inputs[0];
        Committee comm = man.getCommitteeByName(commName);
        String chairmanName = inputs[1];
        Lecturer chairman = man.getLecturerByName(chairmanName);

        man.updateCommitteeChairman(comm, chairman);
    }

    private static void removeLecturerFromCommitteeMenu(Manager man, Scanner scanner) {
        String[] inputs = getCommLecInputs(man, scanner);
        String commName = inputs[0];
        String lecName = inputs[1];

        man.removeLecFromCommittee(commName, lecName);
        System.out.println("Lecturer '" + lecName + "' removed successfully from Committee "+ commName + "!");
    }

    private static void displayCommittee(Manager man){
        Committee[] comms = man.getCommittees();
        for (Committee c : comms){
            System.out.println(c.toString());
        }
    }

    ///                  ///
    ///     Department   ///
    ///                  ///
    private static void addDepartmentMenu(Manager man, Scanner scanner) {
        String name;
        int numOfStudents;
        Department[] depts = man.getDepartments();

        System.out.print("Provide Department's Name: ");
        name = scanner.nextLine();
        boolean isDepExists = getIsDepExists(name, depts);

        while (isDepExists) {
            System.out.print("Department Exists, Provide a Valid Department Name: ");
            name = scanner.nextLine();
            isDepExists = getIsDepExists(name, depts);
        }

        System.out.print("Provide Number Of Students In Department: ");
        numOfStudents = scanner.nextInt();

        man.addDepartment(name, numOfStudents);
        System.out.println("Department '" + name + "' added successfully!");

    }

    private static void addLecToDepMenu(Manager man, Scanner scanner) {
        String lecName;
        String deptName;
        Lecturer[] lecs = man.getLecturers();
        Department[] depts = man.getDepartments();


        System.out.print("Provide Department name: ");
        deptName = scanner.nextLine();
        boolean isDeptExists = getIsDepExists(deptName, depts);

        while (!isDeptExists) {
            System.out.print("Department Does Not Exists, Please Provide a Valid Department Name: ");
            deptName = scanner.nextLine();
            isDeptExists = getIsDepExists(deptName, depts);
        }

        System.out.print("Provide Lecturer name: ");
        lecName = scanner.nextLine();
        boolean isLecExists = getIsLecturerExists(lecName, lecs);

        while (!isLecExists) {
            System.out.print("Lecturer Does Not Exists, Please Provide a Valid Lecturer Name: ");
            lecName = scanner.nextLine();
            isLecExists = getIsLecturerExists(lecName, lecs);
        }

        man.addLecToDept(lecName, deptName);
    }

    private static boolean getIsDepExists(String name, Department[] depts) {
        boolean isNameExists = false;

        for (Department dept : depts) {
            if (dept.getName().equals(name)) {
                isNameExists = true;
                break;
            }
        }

        return isNameExists;
    }

    private static void displayAvgSalaryInDepartment(Manager man, Scanner scanner){
        String name;
        Department[] depts = man.getDepartments();

        System.out.print("Provide Department's Name: ");
        name = scanner.nextLine();
        boolean isDepExists = getIsDepExists(name, depts);

        while (!isDepExists) {
            System.out.print("Department Does Note Exists, Provide an Existing Department Name: ");
            name = scanner.nextLine();
            isDepExists = getIsDepExists(name, depts);
        }
        double avg = man.getAverageSalaryByDepartment(name);
        System.out.println("The Average Salary In Department " + name + " is: " + avg);
    }

}
