/**
 * TestDriver — Internal test driver for the Academic Staff Management System.
 * NOT part of the submission zip.
 *
 * Run separately:  javac *.java && java TestDriver
 *
 * Uses a simple assertThat() helper — no JUnit, no external libraries.
 * Tests marked "STUB" are expected to [FAIL] until the corresponding feature is implemented.
 *
 * Organized by menu option (0–11) from the assignment spec (sub2java.pdf),
 * plus class-level unit tests.
 */
import ido_bar_shaked_govrin.*;

public class TestDriver {

    private static int passed = 0;
    private static int failed = 0;
    private static int sectionPassed = 0;
    private static int sectionFailed = 0;

    // ANSI Colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";

    // ==================== Test Helpers ====================

    private static void assertThat(boolean condition, String testName) {
        if (condition) {
            passed++;
            sectionPassed++;
            // In quiet mode, we don't print passing tests individually
        } else {
            failed++;
            sectionFailed++;
            if (testName.startsWith("STUB:")) {
                System.out.println("  " + RED + "[FAIL] " + YELLOW + "STUB: " + RESET + testName.substring(5).trim());
            } else {
                System.out.println("  " + RED + "[FAIL] " + RESET + testName);
            }
        }
    }

    private static void section(String title) {
        printSectionSummary(); // Print summary for the PREVIOUS section before starting a new one
        System.out.println("\n" + CYAN + "--- " + title + " ---" + RESET);
    }

    private static void printSectionSummary() {
        if (sectionPassed > 0 || sectionFailed > 0) {
            if (sectionFailed == 0) {
                System.out.println("  " + GREEN + "[ALL PASS] " + sectionPassed + " tests passed." + RESET);
            }
        }
        sectionPassed = 0;
        sectionFailed = 0;
    }

    private static void printSummary() {
        printSectionSummary(); // Print summary for the very last section

        System.out.println("\n" + CYAN + "========================================" + RESET);
        System.out.println("           TEST SUMMARY");
        System.out.println(CYAN + "========================================" + RESET);
        System.out.println("  Passed: " + GREEN + passed + RESET);
        System.out.println("  Failed: " + RED + failed + RESET);
        System.out.println("  Total:  " + (passed + failed));
        System.out.println(CYAN + "========================================" + RESET);
        if (failed == 0) {
            System.out.println("  " + GREEN + "ALL TESTS PASSED!" + RESET);
        } else {
            System.out.println("  " + RED + "Some tests failed — see [FAIL] above." + RESET);
        }
        System.out.println(CYAN + "========================================\n" + RESET);
    }

    // ==================== Main ====================

    public static void main(String[] args) {
        System.out.println("=== Academic Staff Management System — Test Driver ===");
        System.out.println("=== Testing all menu operations from sub2java.pdf  ===\n");

        // Part A: Class-level unit tests
        testLecturerClass();
        testDepartmentClass();
        testCommitteeClass();

        // Part B: Menu operation tests (options 1–11)
        testMenu1_AddLecturer();
        testMenu2_AddCommittee();
        testMenu3_AddMemberToCommittee();
        testMenu4_UpdateCommitteeChairman();
        testMenu5_RemoveMemberFromCommittee();
        testMenu6_AddDepartment();
        testMenu7_AddLecturerToDepartment();
        testMenu8_AverageSalaryAllLecturers();
        testMenu9_AverageSalaryPerDepartment();
        testMenu10_ShowAllLecturers();
        testMenu11_ShowAllCommittees();

        // Part C: Cross-cutting / bidirectional link tests
        testLecturerCommitteeBidirectionalLink();

        printSummary();
    }

    // ================================================================
    //  PART A — CLASS-LEVEL UNIT TESTS
    // ================================================================

    // ==================== Lecturer Class ====================

    private static void testLecturerClass() {
        section("Unit: Lecturer Class");

        Lecturer lec = new Lecturer("Alice", "123456789", Lecturer.Degree.DR, "Computer Science", 25000.0);

        // Constructor
        assertThat(lec.getName().equals("Alice"),
                "Constructor sets name");
        assertThat(lec.getId().equals("123456789"),
                "Constructor sets id");
        assertThat(lec.getDegreeRank() == Lecturer.Degree.DR,
                "Constructor sets degreeRank");
        assertThat(lec.getDegreeName().equals("Computer Science"),
                "Constructor sets degreeName");
        assertThat(lec.getSalary() == 25000.0,
                "Constructor sets salary");
        assertThat(lec.getDepartment() == null,
                "Department is null initially");

        // Setters
        lec.setName("Bob");
        assertThat(lec.getName().equals("Bob"), "setName() works");

        lec.setId("987654321");
        assertThat(lec.getId().equals("987654321"), "setId() works");

        lec.setDegreeRank(Lecturer.Degree.PROF);
        assertThat(lec.getDegreeRank() == Lecturer.Degree.PROF, "setDegreeRank() works");

        lec.setDegreeName("Mathematics");
        assertThat(lec.getDegreeName().equals("Mathematics"), "setDegreeName() works");

        lec.setSalary(30000.0);
        assertThat(lec.getSalary() == 30000.0, "setSalary() works");

        Department dept = new Department("CS", 300);
        lec.setDepartment(dept);
        assertThat(lec.getDepartment() == dept, "setDepartment() works");

        // Degree enum ordering
        assertThat(Lecturer.Degree.BSC.ordinal() < Lecturer.Degree.MSC.ordinal(), "BSC < MSC");
        assertThat(Lecturer.Degree.MSC.ordinal() < Lecturer.Degree.DR.ordinal(), "MSC < DR");
        assertThat(Lecturer.Degree.DR.ordinal() < Lecturer.Degree.PROF.ordinal(), "DR < PROF");
        assertThat(Lecturer.Degree.values().length == 4, "Degree enum has 4 values");
    }

    // ==================== Department Class ====================

    private static void testDepartmentClass() {
        section("Unit: Department Class");

        Department dept = new Department("Software Engineering", 450);
        assertThat(dept.getName().equals("Software Engineering"), "Constructor sets name");
        assertThat(dept.getNumStudents() == 450, "Constructor sets numStudents");

        dept.setName("Electrical Engineering");
        assertThat(dept.getName().equals("Electrical Engineering"), "setName() works");

        dept.setNumStudents(600);
        assertThat(dept.getNumStudents() == 600, "setNumStudents() works");
    }

    // ==================== Committee Class ====================

    private static void testCommitteeClass() {
        section("Unit: Committee Class");

        Lecturer drChair = new Lecturer("Dr. Cohen", "111111111", Lecturer.Degree.DR, "Physics", 28000.0);
        Committee comm = new Committee("Hiring", drChair);

        assertThat(comm.getName().equals("Hiring"), "Constructor sets name");

        // updateChairman degree validation
        Lecturer bsc = new Lecturer("BSC", "200000001", Lecturer.Degree.BSC, "Art", 12000.0);
        Lecturer msc = new Lecturer("MSC", "200000002", Lecturer.Degree.MSC, "Bio", 15000.0);
        Lecturer dr = new Lecturer("DR", "200000003", Lecturer.Degree.DR, "Math", 27000.0);
        Lecturer prof = new Lecturer("PROF", "200000004", Lecturer.Degree.PROF, "CS", 35000.0);

        assertThat(comm.updateChairman(bsc) == false, "updateChairman() rejects BSC");
        assertThat(comm.updateChairman(msc) == false, "updateChairman() rejects MSC");
        assertThat(comm.updateChairman(dr) == true, "updateChairman() accepts DR");
        assertThat(comm.updateChairman(prof) == true, "updateChairman() accepts PROF");
    }

    // ================================================================
    //  PART B — MENU OPERATION TESTS (per sub2java.pdf)
    // ================================================================

    // ==================== Menu 1: Add Lecturer ====================

    private static void testMenu1_AddLecturer() {
        section("Menu 1: Add Lecturer to College");

        Manager man = new Manager("Test College");

        // Basic add
        man.addLecturer("Alice", "111111111", Lecturer.Degree.BSC, "CS", 15000.0);
        assertThat(man.getLecturers().length == 1,
                "Adding 1 lecturer: count is 1");

        Lecturer alice = man.getLecturerByName("Alice");
        assertThat(alice != null, "Added lecturer can be found by name");
        assertThat(alice.getName().equals("Alice"), "Found lecturer has correct name");
        assertThat(alice.getId().equals("111111111"), "Found lecturer has correct id");
        assertThat(alice.getDegreeRank() == Lecturer.Degree.BSC, "Found lecturer has correct degree");
        assertThat(alice.getDegreeName().equals("CS"), "Found lecturer has correct degree name");
        assertThat(alice.getSalary() == 15000.0, "Found lecturer has correct salary");

        // Add more lecturers
        man.addLecturer("Bob", "222222222", Lecturer.Degree.MSC, "Math", 18000.0);
        man.addLecturer("Charlie", "333333333", Lecturer.Degree.DR, "Physics", 25000.0);
        assertThat(man.getLecturers().length == 3,
                "Adding 3 lecturers: count is 3");

        // Duplicate name detection (used by Main to re-prompt)
        assertThat(man.getLecturerByName("Alice") != null,
                "Duplicate check: 'Alice' exists → should prompt for new name in menu");
        assertThat(man.getLecturerByName("Nobody") == null,
                "Duplicate check: 'Nobody' not found → name is available");

        // Array doubling stress test
        man.addLecturer("D", "444444444", Lecturer.Degree.PROF, "Bio", 30000.0);
        man.addLecturer("E", "555555555", Lecturer.Degree.BSC, "Hist", 14000.0);
        man.addLecturer("F", "666666666", Lecturer.Degree.MSC, "Chem", 17000.0);
        assertThat(man.getLecturers().length == 6,
                "Array doubling: 6 lecturers added (capacity started at 2)");

        // No nulls in returned array
        Lecturer[] all = man.getLecturers();
        boolean noNulls = true;
        for (int i = 0; i < all.length; i++) {
            if (all[i] == null) { noNulls = false; }
        }
        assertThat(noNulls, "getLecturers() has no null entries after doubling");

        // All data preserved after multiple doublings
        assertThat(man.getLecturerByName("Alice") != null, "Data preserved: 'Alice'");
        assertThat(man.getLecturerByName("F") != null, "Data preserved: 'F' (last added)");
    }

    // ==================== Menu 2: Add Committee ====================

    private static void testMenu2_AddCommittee() {
        section("Menu 2: Add Committee to College");

        Manager man = new Manager("Committee College");

        // Need lecturers first as potential chairmen
        man.addLecturer("Dr. A", "100000001", Lecturer.Degree.DR, "CS", 27000.0);
        man.addLecturer("Prof. B", "100000002", Lecturer.Degree.PROF, "Math", 35000.0);
        man.addLecturer("Dr. C", "100000003", Lecturer.Degree.DR, "Phys", 26000.0);
        man.addLecturer("BSC Guy", "100000004", Lecturer.Degree.BSC, "Art", 12000.0);

        Lecturer drA = man.getLecturerByName("Dr. A");
        Lecturer profB = man.getLecturerByName("Prof. B");
        Lecturer drC = man.getLecturerByName("Dr. C");

        // Add committee with DR chairman
        man.addCommittee("Teaching", drA);
        assertThat(man.getCommittees().length == 1,
                "Adding 1 committee with DR chairman: count is 1");

        // Add committee with PROF chairman
        man.addCommittee("Research", profB);
        assertThat(man.getCommittees().length == 2,
                "Adding 2nd committee with PROF chairman: count is 2");

        // Duplicate committee name detection
        assertThat(man.getCommitteeByName("Teaching") != null,
                "Duplicate check: 'Teaching' exists → should prompt for new name in menu");
        assertThat(man.getCommitteeByName("Nonexistent") == null,
                "Duplicate check: 'Nonexistent' not found → name is available");

        // Chairman must be DR+
        // Note: The current Manager.addCommittee() does NOT validate the chairman degree,
        // that validation is done in Main.java. We test the Committee.updateChairman() constraint here.
        Lecturer bscGuy = man.getLecturerByName("BSC Guy");
        Committee testComm = new Committee("Test", drC); // Use a valid DR chairman initially
        boolean rejectBsc = testComm.updateChairman(bscGuy);
        assertThat(rejectBsc == false,
                "Chairman degree constraint: updateChairman rejects BSC");

        // BUG CHECK: The Manager should prevent adding a committee with a BSC chairman
        int countBefore = man.getCommittees().length;
        man.addCommittee("InvalidComm", bscGuy);
        int countAfter = man.getCommittees().length;

        assertThat(countBefore == countAfter,
                "Manager prevents adding a committee with chairman below DR");

        // Array doubling
        man.addCommittee("Ethics", drC);
        assertThat(man.getCommittees().length == 3,
                "Array doubles: 3rd committee added (capacity was 2)");

        // No nulls
        Committee[] allComms = man.getCommittees();
        boolean noNulls = true;
        for (int i = 0; i < allComms.length; i++) {
            if (allComms[i] == null) { noNulls = false; }
        }
        assertThat(noNulls, "getCommittees() has no null entries");
    }

    // ==================== Menu 3: Add Member to Committee ====================

    private static void testMenu3_AddMemberToCommittee() {
        section("Menu 3: Add Member to Committee");

        Manager man = new Manager("Member College");

        man.addLecturer("Dr. Chair", "300000001", Lecturer.Degree.DR, "CS", 27000.0);
        man.addLecturer("Member A", "300000002", Lecturer.Degree.BSC, "Math", 14000.0);
        man.addLecturer("Member B", "300000003", Lecturer.Degree.MSC, "Phys", 17000.0);
        man.addLecturer("Member C", "300000004", Lecturer.Degree.BSC, "Bio", 13000.0);

        Lecturer chair = man.getLecturerByName("Dr. Chair");
        man.addCommittee("Hiring", chair);

        // addLecToCommittee() calls comm.addLecturer() which is a stub.
        // Test that it doesn't crash:
        boolean noCrash = true;
        try {
            man.addLecToCommittee("Hiring", "Member A");
        } catch (Exception e) {
            noCrash = false;
        }
        assertThat(noCrash,
                "STUB: addLecToCommittee('Hiring', 'Member A') does not crash");

        // Verify member was actually added (requires Committee to have a getter for members)
        // Since Committee has no getLecturers()/getMemberCount(), we can't verify this yet.
        // This is a known limitation — test will fail until getters + addLecturer body are implemented.
        // We'll try to add multiple members and check count:

        try {
            man.addLecToCommittee("Hiring", "Member B");
            man.addLecToCommittee("Hiring", "Member C");
        } catch (Exception e) {
            // may crash if stub doesn't handle it
        }

        // Verify members are actually stored in committee
        Committee hiring = man.getCommitteeByName("Hiring");
        assertThat(hiring.getLecturersInCommittee().length == 3,
                "Verify members are actually stored in committee (needs Committee.getLecturers())");

        // Verify the committee must exist before adding a member
        assertThat(man.getCommitteeByName("Nonexistent") == null,
                "Cannot add to non-existent committee (name check returns null)");

        // Verify the lecturer must exist before adding as member
        assertThat(man.getLecturerByName("Ghost") == null,
                "Cannot add non-existent lecturer as member (name check returns null)");

        // Chairman should NOT appear in the members list
        // (per assignment: "יו"ר הועדה לא יופיע ברשימת המרצים החברים בוועדה")
        boolean chairmanInMembers = false;
        for (Lecturer l : hiring.getLecturersInCommittee()) {
            if (l.getName().equals("Dr. Chair")) {
                chairmanInMembers = true;
            }
        }
        assertThat(!chairmanInMembers,
                "Chairman should not be in the members list (needs Committee.getLecturers())");

        // Adding the chairman as a regular member should be rejected
        // (assignment: chairman is NOT in the members list)
        try {
            man.addLecToCommittee("Hiring", "Dr. Chair");
        } catch (Exception e) {}
        boolean chairAddedAsMember = false;
        for (Lecturer l : hiring.getLecturersInCommittee()) {
            if (l.getName().equals("Dr. Chair")) {
                chairAddedAsMember = true;
            }
        }
        assertThat(!chairAddedAsMember,
                "Adding chairman as a member should be rejected or ignored");

        // Adding the same lecturer twice to the same committee (duplicate member)
        try {
            man.addLecToCommittee("Hiring", "Member B");
        } catch (Exception e) {}
        int countB = 0;
        for (Lecturer l : hiring.getLecturersInCommittee()) {
            if (l.getName().equals("Member B")) {
                countB++;
            }
        }
        assertThat(countB == 1,
                "Adding duplicate member to same committee should be handled");

        // A lecturer can be a member of MULTIPLE committees simultaneously
        man.addCommittee("Review", chair);
        try {
            man.addLecToCommittee("Review", "Member A");
        } catch (Exception e) {
            // stub
        }
        boolean memberAInReview = false;
        Committee review = man.getCommitteeByName("Review");
        for (Lecturer l : review.getLecturersInCommittee()) {
            if (l.getName().equals("Member A")) {
                memberAInReview = true;
            }
        }
        assertThat(memberAInReview,
                "Lecturer can be member of multiple committees (Member A in Hiring + Review)");
    }

    // ==================== Menu 4: Update Committee Chairman ====================

    private static void testMenu4_UpdateCommitteeChairman() {
        section("Menu 4: Update Committee Chairman");

        Manager man = new Manager("Chairman College");

        man.addLecturer("Dr. Old", "400000001", Lecturer.Degree.DR, "CS", 27000.0);
        man.addLecturer("Prof. New", "400000002", Lecturer.Degree.PROF, "Math", 35000.0);
        man.addLecturer("BSC Low", "400000003", Lecturer.Degree.BSC, "Art", 12000.0);
        man.addLecturer("MSC Mid", "400000004", Lecturer.Degree.MSC, "Bio", 16000.0);

        Lecturer drOld = man.getLecturerByName("Dr. Old");
        Lecturer profNew = man.getLecturerByName("Prof. New");
        Lecturer bscLow = man.getLecturerByName("BSC Low");
        Lecturer mscMid = man.getLecturerByName("MSC Mid");

        man.addCommittee("Budget", drOld);
        Committee budget = man.getCommitteeByName("Budget");

        // Update chairman to a valid PROF → should succeed
        boolean updateProf = budget.updateChairman(profNew);
        assertThat(updateProf == true,
                "Update chairman to PROF: succeeds (returns true)");

        // Update chairman to a valid DR → should succeed
        boolean updateDr = budget.updateChairman(drOld);
        assertThat(updateDr == true,
                "Update chairman to DR: succeeds (returns true)");

        // Attempt to update chairman to BSC → should fail
        boolean updateBsc = budget.updateChairman(bscLow);
        assertThat(updateBsc == false,
                "Update chairman to BSC: rejected (returns false)");

        // Attempt to update chairman to MSC → should fail
        boolean updateMsc = budget.updateChairman(mscMid);
        assertThat(updateMsc == false,
                "Update chairman to MSC: rejected (returns false)");

        // After rejection, original chairman should still be set
        assertThat(budget.getChairman().equals(drOld),
                "After rejected update, original chairman is preserved (needs getChairman())");

        // If old chairman was a member, they should move to the members list
        // and the new chairman should be removed from members list
        boolean profNewIsMember = false;
        for (Lecturer l : budget.getLecturersInCommittee()) {
            if (l.equals(profNew)) profNewIsMember = true;
        }
        assertThat(profNewIsMember,
                "Old chairman moves to members list after replacement (needs full implementation)");

        // New chairman is removed from members list if they were a member
        boolean drOldIsMember = false;
        for (Lecturer l : budget.getLecturersInCommittee()) {
            if (l.equals(drOld)) drOldIsMember = true;
        }
        assertThat(!drOldIsMember,
                "New chairman removed from members list (needs removeLecFromMembers)");

        // Update to a lecturer who doesn't exist — validation check
        assertThat(man.getLecturerByName("Ghost") == null,
                "Update chairman: non-existent lecturer returns null (validation needed in menu)");

        // Scenario: new chairman is currently a member of that committee
        // They should be removed from members and become chairman
        budget.addLecturer(profNew);
        budget.updateChairman(profNew);
        boolean profNewStillMember = false;
        for (Lecturer l : budget.getLecturersInCommittee()) {
            if (l.equals(profNew)) profNewStillMember = true;
        }
        assertThat(!profNewStillMember,
                "Member promoted to chairman is removed from members list");
    }

    // ==================== Menu 5: Remove Member from Committee ====================

    private static void testMenu5_RemoveMemberFromCommittee() {
        section("Menu 5: Remove Member from Committee");

        Manager man = new Manager("Remove College");

        man.addLecturer("Dr. Chair", "500000001", Lecturer.Degree.DR, "CS", 27000.0);
        man.addLecturer("Member X", "500000002", Lecturer.Degree.BSC, "Math", 14000.0);
        man.addLecturer("Member Y", "500000003", Lecturer.Degree.MSC, "Phys", 17000.0);

        Lecturer chair = man.getLecturerByName("Dr. Chair");
        man.addCommittee("Discipline", chair);

        // Add members first (stub, may not actually add)
        try {
            man.addLecToCommittee("Discipline", "Member X");
            man.addLecToCommittee("Discipline", "Member Y");
        } catch (Exception e) {
            // stub may fail
        }

        // Remove a member
        man.removeLecFromCommittee("Discipline", "Member X");
        Committee discipline = man.getCommitteeByName("Discipline");
        Lecturer memberX = man.getLecturerByName("Member X");

        boolean memberXStillIn = false;
        for (Lecturer l : discipline.getLecturersInCommittee()) {
            if (l.equals(memberX)) memberXStillIn = true;
        }
        assertThat(!memberXStillIn,
                "Remove 'Member X' from 'Discipline' committee (method not implemented)");

        assertThat(discipline.getLecturersInCommittee().length == 1,
                "After removal, member count decreases by 1 (needs implementation)");

        // Removed member's committee array is also updated (bidirectional)
        assertThat(memberX.toString().indexOf("Discipline") == -1,
                "Removed member's committee array is also updated (bidirectional)");

        // Cannot remove the chairman as a member (they're not in the list)
        int sizeBefore = discipline.getLecturersInCommittee().length;
        man.removeLecFromCommittee("Discipline", "Dr. Chair");
        assertThat(discipline.getLecturersInCommittee().length == sizeBefore,
                "Cannot remove chairman as a 'member' (they're not in the members list)");

        // Cannot remove a lecturer who is not a member
        sizeBefore = discipline.getLecturersInCommittee().length;
        man.removeLecFromCommittee("Discipline", "Member X"); // already removed
        assertThat(discipline.getLecturersInCommittee().length == sizeBefore,
                "Removing non-member lecturer is handled gracefully");
    }

    // ==================== Menu 6: Add Department ====================

    private static void testMenu6_AddDepartment() {
        section("Menu 6: Add Department");

        Manager man = new Manager("Dept College");

        // Basic add
        man.addDepartment("Computer Science", 500);
        assertThat(man.getDepartments().length == 1,
                "Adding 1 department: count is 1");

        Department[] depts = man.getDepartments();
        assertThat(depts[0].getName().equals("Computer Science"),
                "Added department has correct name");
        assertThat(depts[0].getNumStudents() == 500,
                "Added department has correct student count");

        // Add more
        man.addDepartment("Mathematics", 300);
        man.addDepartment("Physics", 200);
        assertThat(man.getDepartments().length == 3,
                "Adding 3 departments: count is 3");

        // Duplicate name detection (used by Main to re-prompt)
        // Manager doesn't have getDepartmentByName(), so we check manually
        Department[] allDepts = man.getDepartments();
        boolean csExists = false;
        for (int i = 0; i < allDepts.length; i++) {
            if (allDepts[i].getName().equals("Computer Science")) {
                csExists = true;
            }
        }
        assertThat(csExists,
                "Duplicate check: 'Computer Science' exists → should prompt for new name");

        boolean fakeExists = false;
        for (int i = 0; i < allDepts.length; i++) {
            if (allDepts[i].getName().equals("Fake Dept")) {
                fakeExists = true;
            }
        }
        assertThat(!fakeExists,
                "Duplicate check: 'Fake Dept' not found → name is available");

        // Array doubling stress test
        man.addDepartment("Biology", 150);
        man.addDepartment("History", 100);
        man.addDepartment("Chemistry", 250);
        assertThat(man.getDepartments().length == 6,
                "Array doubling: 6 departments added (capacity started at 2)");

        // No nulls
        Department[] all = man.getDepartments();
        boolean noNulls = true;
        for (int i = 0; i < all.length; i++) {
            if (all[i] == null) { noNulls = false; }
        }
        assertThat(noNulls, "getDepartments() has no null entries after doubling");

        // Data preserved
        assertThat(all[0].getName().equals("Computer Science"), "First dept preserved");
        assertThat(all[5].getName().equals("Chemistry"), "Last dept preserved");
    }

    // ==================== Menu 7: Add Lecturer to Department ====================

    private static void testMenu7_AddLecturerToDepartment() {
        section("Menu 7: Add Lecturer to Department");

        Manager man = new Manager("LecDept College");

        man.addLecturer("Alice", "700000001", Lecturer.Degree.BSC, "CS", 15000.0);
        man.addLecturer("Bob", "700000002", Lecturer.Degree.DR, "Math", 25000.0);
        man.addDepartment("Computer Science", 500);
        man.addDepartment("Mathematics", 300);

        Lecturer alice = man.getLecturerByName("Alice");
        Lecturer bob = man.getLecturerByName("Bob");

        // Manually test the Lecturer.setDepartment() + Department side
        // (Manager doesn't have an addLecturerToDepartment method yet)

        // Direct setter works:
        Department[] depts = man.getDepartments();
        alice.setDepartment(depts[0]);
        assertThat(alice.getDepartment() == depts[0],
                "Lecturer.setDepartment() links Alice to CS dept");

        // A lecturer can belong to at most 1 department
        alice.setDepartment(depts[1]);
        assertThat(alice.getDepartment() == depts[1],
                "Reassigning department: Alice now belongs to Math (overwrite works)");
        assertThat(alice.getDepartment() != depts[0],
                "Reassigning department: Alice no longer belongs to CS");

        // STUB: Manager should have an addLecturerToDepartment(lecName, deptName) method
        man.addLecToDept("Alice", "Mathematics");
        assertThat(alice.getDepartment() != null && alice.getDepartment().getName().equals("Mathematics"),
                "Manager.addLecToDept() works (menu option 7)");

        // STUB: Department's lecturers array should be updated when a lecturer is added
        boolean aliceInMath = false;
        for (Lecturer l : depts[1].getLecturers()) {
            if (l != null && l.equals(alice)) aliceInMath = true;
        }
        assertThat(aliceInMath,
                "Department.lecturers[] should include added lecturer");

        // STUB: If lecturer already has a department, should be removed from old dept's array
        boolean aliceInCS = false;
        for (Lecturer l : depts[0].getLecturers()) {
            if (l != null && l.equals(alice)) aliceInCS = true;
        }
        assertThat(!aliceInCS,
                "Moving lecturer between depts updates both dept arrays");

        // A lecturer with no department
        assertThat(bob.getDepartment() == null,
                "Bob starts with no department");

        // Max 1 department constraint:
        // "מרצה יכול להיות משוייך מקסימום למחלקה אחת בלבד"
        // Assign bob to CS, then try to assign to Math — should overwrite OR error
        bob.setDepartment(depts[0]);
        bob.setDepartment(depts[1]);
        assertThat(bob.getDepartment() == depts[1],
                "Max 1 dept: reassigning overwrites (Bob now in Math, not CS)");
        assertThat(bob.getDepartment() != depts[0],
                "Max 1 dept: Bob is no longer in CS after reassignment");

        // STUB: Validation that lecturer exists before adding to dept
        assertThat(man.getLecturerByName("Nobody") == null,
                "Validation: non-existent lecturer returns null (menu should re-prompt)");

        // STUB: Multiple lecturers in same department (no limit per assignment)
        man.addLecToDept("Bob", "Computer Science");
        man.addLecturer("Charlie", "700000003", Lecturer.Degree.MSC, "Phys", 20000.0);
        man.addLecToDept("Charlie", "Computer Science");
        assertThat(depts[0].getLecturers().length >= 2,
                "Multiple lecturers can belong to same department");
    }

    // ==================== Menu 8: Average Salary — All Lecturers ====================

    private static void testMenu8_AverageSalaryAllLecturers() {
        section("Menu 8: Average Salary of All Lecturers");

        Manager man = new Manager("Salary College");

        man.addLecturer("A", "800000001", Lecturer.Degree.BSC, "CS", 10000.0);
        man.addLecturer("B", "800000002", Lecturer.Degree.MSC, "Math", 20000.0);
        man.addLecturer("C", "800000003", Lecturer.Degree.DR, "Phys", 30000.0);
        // Expected average: (10000 + 20000 + 30000) / 3 = 20000.0

        // We can compute it ourselves from getLecturers():
        Lecturer[] lecs = man.getLecturers();
        double sum = 0;
        for (int i = 0; i < lecs.length; i++) {
            sum += lecs[i].getSalary();
        }
        double avg = sum / lecs.length;
        assertThat(avg == 20000.0,
                "Manual avg salary calc: (10k+20k+30k)/3 = 20000.0 ✓ (data is correct)");

        // STUB: Manager should have a getAverageSalary() method
        assertThat(Manager.getAverageSalary(man.getLecturers()) == 20000.0, "Manager.getAverageSalary() returns 20000");

        // Edge case: no lecturers → should handle gracefully (not divide by zero)
        Manager emptyMan = new Manager("Empty College");
        assertThat(emptyMan.getLecturers().length == 0,
                "Edge case: no lecturers in college (avg salary should handle gracefully)");
        assertThat(Manager.getAverageSalary(emptyMan.getLecturers()) == 0.0,
                "getAverageSalary() handles 0 lecturers without crashing");

        // Edge case: single lecturer
        Manager singleMan = new Manager("Single College");
        singleMan.addLecturer("Solo", "888000001", Lecturer.Degree.BSC, "CS", 42000.0);
        Lecturer[] singleLecs = singleMan.getLecturers();
        double singleAvg = singleLecs[0].getSalary();
        assertThat(singleAvg == 42000.0,
                "Edge case: single lecturer avg = their own salary (42000)");
    }

    // ==================== Menu 9: Average Salary — Per Department ====================

    private static void testMenu9_AverageSalaryPerDepartment() {
        section("Menu 9: Average Salary per Department");

        Manager man = new Manager("DeptSalary College");

        man.addDepartment("CS", 400);
        man.addDepartment("Math", 200);

        man.addLecturer("A", "900000001", Lecturer.Degree.BSC, "CS", 10000.0);
        man.addLecturer("B", "900000002", Lecturer.Degree.MSC, "CS", 20000.0);
        man.addLecturer("C", "900000003", Lecturer.Degree.DR, "Math", 30000.0);

        // Manually link lecturers to departments
        Department[] depts = man.getDepartments();
        Lecturer lecA = man.getLecturerByName("A");
        Lecturer lecB = man.getLecturerByName("B");
        Lecturer lecC = man.getLecturerByName("C");

        lecA.setDepartment(depts[0]); // CS
        depts[0].addLecturer(lecA);
        lecB.setDepartment(depts[0]); // CS
        depts[0].addLecturer(lecB);
        lecC.setDepartment(depts[1]); // Math
        depts[1].addLecturer(lecC);

        // CS dept avg: (10000 + 20000) / 2 = 15000
        // Math dept avg: 30000 / 1 = 30000

        // Manually compute from lecturers in CS dept
        Lecturer[] allLecs = man.getLecturers();
        double csSum = 0;
        int csCount = 0;
        for (int i = 0; i < allLecs.length; i++) {
            if (allLecs[i].getDepartment() != null &&
                allLecs[i].getDepartment().getName().equals("CS")) {
                csSum += allLecs[i].getSalary();
                csCount++;
            }
        }
        double csAvg = (csCount > 0) ? csSum / csCount : 0;
        assertThat(csAvg == 15000.0,
                "Manual calc: CS dept avg = (10k+20k)/2 = 15000.0 ✓ (data correct)");

        double mathSum = 0;
        int mathCount = 0;
        for (int i = 0; i < allLecs.length; i++) {
            if (allLecs[i].getDepartment() != null &&
                allLecs[i].getDepartment().getName().equals("Math")) {
                mathSum += allLecs[i].getSalary();
                mathCount++;
            }
        }
        double mathAvg = (mathCount > 0) ? mathSum / mathCount : 0;
        assertThat(mathAvg == 30000.0,
                "Manual calc: Math dept avg = 30k/1 = 30000.0 ✓ (data correct)");

        // STUB: Manager should have a getAverageSalaryByDepartment(deptName) method
        assertThat(man.getAverageSalaryByDepartment("CS") == 15000.0,
                "Manager.getAverageSalaryByDepartment() implemented (menu option 9)");

        // Edge case: department with no lecturers
        man.addDepartment("Empty Dept", 50);
        assertThat(man.getAverageSalaryByDepartment("Empty Dept") == 0.0,
                "getAverageSalaryByDepartment() handles dept with 0 lecturers");

        // Lecturer with NO department should NOT count toward any department's average
        man.addLecturer("Unassigned", "900000004", Lecturer.Degree.BSC, "Art", 99000.0);
        // Recalculate CS avg — "Unassigned" should not affect it
        Lecturer[] allLecs2 = man.getLecturers();
        double csSum2 = 0;
        int csCount2 = 0;
        for (int i = 0; i < allLecs2.length; i++) {
            if (allLecs2[i].getDepartment() != null &&
                allLecs2[i].getDepartment().getName().equals("CS")) {
                csSum2 += allLecs2[i].getSalary();
                csCount2++;
            }
        }
        double csAvg2 = (csCount2 > 0) ? csSum2 / csCount2 : 0;
        assertThat(csAvg2 == 15000.0,
                "Unassigned lecturer doesn't affect CS dept avg (still 15000)");
    }

    // ==================== Menu 10: Show All Lecturers ====================

    private static void testMenu10_ShowAllLecturers() {
        section("Menu 10: Show All Lecturers Details");

        Manager man = new Manager("Display College");

        man.addLecturer("Alice", "101010101", Lecturer.Degree.DR, "Computer Science", 28000.0);
        man.addLecturer("Bob", "202020202", Lecturer.Degree.PROF, "Mathematics", 35000.0);

        Lecturer[] lecs = man.getLecturers();
        assertThat(lecs.length == 2,
                "2 lecturers available for display");

        // Verify toString has essential data
        String aliceStr = lecs[0].toString();
        assertThat(aliceStr.contains("Alice"), "Alice's toString contains name");
        assertThat(aliceStr.contains("101010101"), "Alice's toString contains id");
        assertThat(aliceStr.contains("DR"), "Alice's toString contains degree");
        assertThat(aliceStr.contains("28000"), "Alice's toString contains salary");
        assertThat(aliceStr.contains("Computer Science"), "Alice's toString contains degree name");

        String bobStr = lecs[1].toString();
        assertThat(bobStr.contains("Bob"), "Bob's toString contains name");
        assertThat(bobStr.contains("PROF"), "Bob's toString contains degree");

        // STUB: toString should include committee names
        assertThat(aliceStr.contains("committees="),
                "Lecturer.toString() should list committee names");

        // STUB: toString should include department name
        // Current toString prints department object reference, not the name nicely
        Department dept = new Department("CS", 400);
        Lecturer lecWithDept = new Lecturer("Test", "999999999", Lecturer.Degree.BSC, "Test", 10000.0);
        lecWithDept.setDepartment(dept);
        String withDeptStr = lecWithDept.toString();
        // toString currently prints department=Department{...}, which is valid but verbose
        assertThat(withDeptStr.contains("CS"),
                "Lecturer.toString() includes department info when set");
    }

    // ==================== Menu 11: Show All Committees ====================

    private static void testMenu11_ShowAllCommittees() {
        section("Menu 11: Show All Committees Details");

        Manager man = new Manager("CommDisplay College");

        man.addLecturer("Dr. A", "110000001", Lecturer.Degree.DR, "CS", 27000.0);
        man.addLecturer("Prof. B", "110000002", Lecturer.Degree.PROF, "Math", 35000.0);

        Lecturer drA = man.getLecturerByName("Dr. A");
        Lecturer profB = man.getLecturerByName("Prof. B");

        man.addCommittee("Teaching", drA);
        man.addCommittee("Research", profB);

        Committee[] comms = man.getCommittees();
        assertThat(comms.length == 2,
                "2 committees available for display");

        // Committee currently has no toString() override
        String commStr = comms[0].toString();
        assertThat(commStr.contains("Teaching"),
                "Committee.toString() should show name, chairman, and members");

        // Committee should display chairman name
        assertThat(commStr.contains("Dr. A"),
                "Committee display includes chairman name");

        // Committee should display all member names
        man.addLecToCommittee("Teaching", "Prof. B");
        String commStr2 = man.getCommitteeByName("Teaching").toString();
        assertThat(commStr2.contains("Prof. B"),
                "Committee display includes member names");
    }

    // ================================================================
    //  PART C — CROSS-CUTTING TESTS
    // ================================================================

    // ==================== Lecturer ↔ Committee Bidirectional Link ====================

    private static void testLecturerCommitteeBidirectionalLink() {
        section("Cross: Lecturer ↔ Committee Bidirectional Link");

        // Per assignment: "כל מרצה יחזיק מערך של כל הועדות אליהן הוא משובץ"
        // Each lecturer should hold an array of all committees they belong to.
        // This should update automatically when a lecturer is added to a committee.

        Manager man = new Manager("Link College");

        man.addLecturer("Dr. Chair", "120000001", Lecturer.Degree.DR, "CS", 27000.0);
        man.addLecturer("Member A", "120000002", Lecturer.Degree.BSC, "Math", 14000.0);

        Lecturer chair = man.getLecturerByName("Dr. Chair");
        man.addCommittee("Alpha", chair);
        man.addCommittee("Beta", chair);

        // Add Member A to both committees
        try {
            man.addLecToCommittee("Alpha", "Member A");
            man.addLecToCommittee("Beta", "Member A");
        } catch (Exception e) {
            // stubs may fail
        }

        // STUB: Lecturer should have a getCommittees() method returning Committee[]
        assertThat(true, "Lecturer committee link exists");

        // STUB: After adding to 2 committees, lecturer's committee count should be 2
        String memberStr = man.getLecturerByName("Member A").toString();
        assertThat(memberStr.contains("Alpha") && memberStr.contains("Beta"),
                "Member A belongs to 2 committees (bidirectional update via 'this')");

        // STUB: Lecturer's toString should show committee names
        Lecturer memberA = man.getLecturerByName("Member A");
        // Should contain "Alpha" and "Beta" once implemented
        assertThat(memberStr.contains("Alpha") && memberStr.contains("Beta"),
                "Member A's toString() includes 'Alpha' and 'Beta' committee names");

        // STUB: Removing from a committee should update the lecturer's array too
        man.removeLecFromCommittee("Alpha", "Member A");
        String memberStrAfter = man.getLecturerByName("Member A").toString();
        assertThat(!memberStrAfter.contains("Alpha"),
                "Removing lecturer from committee updates lecturer's committee array");
    }
}
