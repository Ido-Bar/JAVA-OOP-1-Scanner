package ido_bar_shaked_govrin;
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
            System.out.println("12: Compare Dr/Prof by Number of Articles.");
            System.out.println("13: Compare Committees.");
            System.out.println("14: Duplicate Committee.");
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
                case 12:
                    compareDrProfMenu(man, scanner);
                    break;
                case 13:
                    compareCommitteesMenu(man, scanner);
                    break;
                case 14:
                    duplicateCommitteeMenu(man, scanner);
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

        System.out.print("Provide Lecturer name: ");
        name = scanner.nextLine();

        while (man.getLecturerByName(name) != null) {
            System.out.print("Lecturer Exists, Provide a Lecturer name: ");
            name = scanner.nextLine();
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
        scanner.nextLine();

        if (degreeRank == Lecturer.Degree.DR || degreeRank == Lecturer.Degree.PROF) {
            System.out.print("How many articles does the lecturer have? ");
            int numArticles = scanner.nextInt();
            scanner.nextLine();

            String[] articles = new String[numArticles];
            for (int j = 0; j < numArticles; j++) {
                System.out.print("Enter article " + (j+1) + " name: ");
                articles[j] = scanner.nextLine();
            }

            if (degreeRank == Lecturer.Degree.PROF) {
                System.out.print("Provide Professorship Body name: ");
                String professorshipBody = scanner.nextLine();
                Prof prof = new Prof(name, id, degreeRank, degreeName, salary, articles, professorshipBody);

                try {
                    man.addLecturer(prof);
                    System.out.println("Professor '" + name + "' added successfully!");
                } catch (ItemAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Dr dr = new Dr(name, id, degreeRank, degreeName, salary, articles);
                try {
                    man.addLecturer(dr);
                    System.out.println("Dr '" + name + "' added successfully!");
                } catch (ItemAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            Lecturer lec = new Lecturer(name, id, degreeRank, degreeName, salary);
            try {
                man.addLecturer(lec);
                System.out.println("Lecturer '" + name + "' added successfully");
            } catch (ItemAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }
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
        System.out.print("Provide Committee Name: ");
        String commName = scanner.nextLine();

        Committee comm = man.getCommitteeByName(commName);
        while (comm == null) {
            System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            commName = scanner.nextLine();
            comm = man.getCommitteeByName(commName);
        }

        System.out.print("Provide Lecturer Name: ");
        String lecName = scanner.nextLine();

        Lecturer lec = man.getLecturerByName(lecName);
        while (lec == null) {
            System.out.print("Lecturer Does not Exist, Provide an existing Lecturer name: ");
            lecName = scanner.nextLine();
            lec = man.getLecturerByName(lecName);
        }

        try {
            man.addLecToCommittee(commName, lecName);
            System.out.println("Lecturer '" + lecName + "' added successfully to Committee " + commName + "!");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (LecturerAlreadyInCommitteeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addCommitteeMenu(Manager man, Scanner scanner) {
        System.out.print("Provide Committee Name: ");
        String name = scanner.nextLine();

        while (man.getCommitteeByName(name) != null) {
            System.out.print("Committee Exists, Provide a new Committee name: ");
            name = scanner.nextLine();
        }
        System.out.print("Provide Lecturer Chairman name: ");
        String chairmanName = scanner.nextLine();

        Lecturer chairman = man.getLecturerByName(chairmanName);
        while (chairman == null) {
            System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);
        }
        while (!(chairman instanceof Dr)) {
            System.out.print("Chairman must be at least DR. Provide a different Chairman name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);
            while (chairman == null) {
                System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
                chairmanName = scanner.nextLine();
                chairman = man.getLecturerByName(chairmanName);
            }
        }

        try {
            man.addCommittee(name, (Dr) chairman);
            System.out.println("Committee '" + name + "' added successfully with Chairman " + chairman.getName() + "!");
        } catch (InvalidChairmanException e) {
            System.out.println(e.getMessage());
        } catch (ItemAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeCommitteeChairmanMenu(Manager man, Scanner scanner){
        System.out.print("Provide Committee Name: ");
        String commName = scanner.nextLine();

        Committee comm = man.getCommitteeByName(commName);
        while (comm == null) {
            System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            commName = scanner.nextLine();
            comm = man.getCommitteeByName(commName);
        }

        System.out.print("Provide Lecturer Chairman name: ");
        String chairmanName = scanner.nextLine();

        Lecturer chairman = man.getLecturerByName(chairmanName);
        while (chairman == null) {
            System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);
        }
        while (!(chairman instanceof Dr)) {
            System.out.print("Chairman must be at least DR. Provide a different Chairman name: ");
            chairmanName = scanner.nextLine();
            chairman = man.getLecturerByName(chairmanName);
            while (chairman == null) {
                System.out.print("Lecturer Doesn't Exist, Provide a valid Lecturer name: ");
                chairmanName = scanner.nextLine();
                chairman = man.getLecturerByName(chairmanName);
            }
        }

        try {
            man.updateCommitteeChairman(comm, (Dr) chairman);
            System.out.println("Chairman of Committee '" + commName + "' updated to " + chairmanName + "!");
        } catch (InvalidChairmanException | LecturerAlreadyInCommitteeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeLecturerFromCommitteeMenu(Manager man, Scanner scanner) {
        System.out.print("Provide Committee Name: ");
        String commName = scanner.nextLine();

        Committee comm = man.getCommitteeByName(commName);
        while (comm == null) {
            System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            commName = scanner.nextLine();
            comm = man.getCommitteeByName(commName);
        }

        System.out.print("Provide Lecturer Name: ");
        String lecName = scanner.nextLine();

        Lecturer lec = man.getLecturerByName(lecName);
        while (lec == null) {
            System.out.print("Lecturer Does not Exist, Provide an existing Lecturer name: ");
            lecName = scanner.nextLine();
            lec = man.getLecturerByName(lecName);
        }

        try {
            man.removeLecFromCommittee(commName, lecName);
            System.out.println("Lecturer '" + lecName + "' removed successfully from Committee " + commName + "!");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayCommittee(Manager man){
        Committee[] comms = man.getCommittees();
        for (Committee c : comms){
            System.out.println(c.toString());
        }
    }

    ///                          ///
    ///     COMPARE DR/PROF      ///
    ///                          ///
    private static void compareDrProfMenu(Manager man, Scanner scanner) {
        System.out.print("Provide first Dr/Professor name: ");
        String name1 = scanner.nextLine();
        Lecturer lec1 = man.getLecturerByName(name1);
        while (lec1 == null || !(lec1 instanceof Dr)) {
            if (lec1 == null) {
                System.out.print("Lecturer Does not Exist, Provide a valid Dr/Professor name: ");
            } else {
                System.out.print("Lecturer is not a Dr/Professor, Provide a valid Dr/Professor name: ");
            }
            name1 = scanner.nextLine();
            lec1 = man.getLecturerByName(name1);
        }

        System.out.print("Provide second Dr/Professor name: ");
        String name2 = scanner.nextLine();
        Lecturer lec2 = man.getLecturerByName(name2);
        while (lec2 == null || !(lec2 instanceof Dr)) {
            if (lec2 == null) {
                System.out.print("Lecturer Does not Exist, Provide a valid Dr/Professor name: ");
            } else {
                System.out.print("Lecturer is not a Dr/Professor, Provide a valid Dr/Professor name: ");
            }
            name2 = scanner.nextLine();
            lec2 = man.getLecturerByName(name2);
        }

        Dr dr1 = (Dr) lec1;
        Dr dr2 = (Dr) lec2;
        int result = dr1.compareTo(dr2);

        System.out.println(dr1.getName() + " has " + dr1.getArticlesCount() + " articles.");
        System.out.println(dr2.getName() + " has " + dr2.getArticlesCount() + " articles.");

        if (result > 0) {
            System.out.println(dr1.getName() + " has more articles than " + dr2.getName() + ".");
        } else if (result < 0) {
            System.out.println(dr2.getName() + " has more articles than " + dr1.getName() + ".");
        } else {
            System.out.println("Both have the same number of articles.");
        }
    }

    ///                              ///
    ///     COMPARE COMMITTEES       ///
    ///                              ///
    private static void compareCommitteesMenu(Manager man, Scanner scanner) {
        System.out.println("Compare Committees by:");
        System.out.println("1: Number of members");
        System.out.println("2: Total number of articles");
        System.out.print("Choose: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine();

        while (subChoice != 1 && subChoice != 2) {
            System.out.print("Invalid choice. Choose 1 or 2: ");
            subChoice = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Provide first Committee name: ");
        String commName1 = scanner.nextLine();
        Committee comm1 = man.getCommitteeByName(commName1);
        while (comm1 == null) {
            System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            commName1 = scanner.nextLine();
            comm1 = man.getCommitteeByName(commName1);
        }

        System.out.print("Provide second Committee name: ");
        String commName2 = scanner.nextLine();
        Committee comm2 = man.getCommitteeByName(commName2);
        while (comm2 == null) {
            System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            commName2 = scanner.nextLine();
            comm2 = man.getCommitteeByName(commName2);
        }

        if (subChoice == 1) {
            int result = comm1.compareTo(comm2);
            System.out.println(comm1.getName() + " has " + comm1.getMemberCount() + " members.");
            System.out.println(comm2.getName() + " has " + comm2.getMemberCount() + " members.");

            if (result > 0) {
                System.out.println(comm1.getName() + " has more members than " + comm2.getName() + ".");
            } else if (result < 0) {
                System.out.println(comm2.getName() + " has more members than " + comm1.getName() + ".");
            } else {
                System.out.println("Both committees have the same number of members.");
            }
        } else {
            Committee.ArticleComparator comparator = new Committee.ArticleComparator();
            int result = comparator.compare(comm1, comm2);
            System.out.println(comm1.getName() + " has " + comm1.getTotalArticles() + " total articles.");
            System.out.println(comm2.getName() + " has " + comm2.getTotalArticles() + " total articles.");
            if (result > 0) {
                System.out.println(comm1.getName() + " has more total articles than " + comm2.getName() + ".");
            } else if (result < 0) {
                System.out.println(comm2.getName() + " has more total articles than " + comm1.getName() + ".");
            } else {
                System.out.println("Both committees have the same total number of articles.");
            }
        }
    }

    ///                              ///
    ///     DUPLICATE COMMITTEE      ///
    ///                              ///
    private static void duplicateCommitteeMenu(Manager man, Scanner scanner) {
        System.out.print("Provide Committee Name to duplicate: ");
        String commName = scanner.nextLine();

        Committee comm = man.getCommitteeByName(commName);
        while (comm == null) {
            System.out.print("Committee Does not Exist, Provide an existing Committee name: ");
            commName = scanner.nextLine();
            comm = man.getCommitteeByName(commName);
        }

        try {
            Committee duplicate = man.duplicateCommittee(commName);
            System.out.println("Committee '" + commName + "' duplicated successfully as '" + duplicate.getName() + "'!");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ItemAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (LecturerAlreadyInCommitteeException e) {
            System.out.println(e.getMessage());
        }
    }

    ///                  ///
    ///     Department   ///
    ///                  ///
    private static void addDepartmentMenu(Manager man, Scanner scanner) {
        String name;
        int numOfStudents;

        System.out.print("Provide Department's Name: ");
        name = scanner.nextLine();

        Department[] depts = man.getDepartments();
        boolean isDepExists = getIsDepExists(name, depts);
        while (isDepExists) {
            System.out.print("Department Exists, Provide a Valid Department Name: ");
            name = scanner.nextLine();
            isDepExists = getIsDepExists(name, depts);
        }
        System.out.print("Provide Number Of Students In Department: ");
        numOfStudents = scanner.nextInt();
        scanner.nextLine();
        try {
            man.addDepartment(name, numOfStudents);
            System.out.println("Department '" + name + "' added successfully!");
        } catch (ItemAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addLecToDepMenu(Manager man, Scanner scanner) {
        String lecName;
        String deptName;

        System.out.print("Provide Department name: ");
        deptName = scanner.nextLine();
        Department[] depts = man.getDepartments();
        boolean isDeptExists = getIsDepExists(deptName, depts);

        while (!isDeptExists) {
            System.out.print("Department Does Note Exists, Please Provide a Valid Department Name: ");
            deptName = scanner.nextLine();
            isDeptExists = getIsDepExists(deptName, depts);
        }

        System.out.print("Provide Lecturer name: ");
        lecName = scanner.nextLine();
        Lecturer[] lecs = man.getLecturers();
        boolean isLecExists = getIsLecturerExists(lecName, lecs);

        while (!isLecExists) {
            System.out.print("Lecturer Does Not Exist, Please Provide a Valid Lecturer Name: ");
            lecName = scanner.nextLine();
            isLecExists = getIsLecturerExists(lecName, lecs);
        }

        try {
            man.addLecToDept(lecName, deptName);
            System.out.println("Lecturer '" + lecName + "' assigned to Department '" + deptName + "'!");
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
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

    private static void displayAvgSalaryInDepartment(Manager man, Scanner scanner){
        String name;
        Department[] depts = man.getDepartments();

        System.out.print("Provide Department's Name: ");
        name = scanner.nextLine();
        boolean isDepExists = getIsDepExists(name, depts);

        while (!isDepExists) {
            System.out.print("Department Does Not Exist, Provide an Existing Department Name: ");
            name = scanner.nextLine();
            isDepExists = getIsDepExists(name, depts);
        }
        try {
            double avg = man.getAverageSalaryByDepartment(name);
            System.out.println("The Average Salary In Department " + name + " is: " + avg);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
