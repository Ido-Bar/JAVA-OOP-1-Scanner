public class Manager {
    private String name;
    private LecManager lecMan;
    private DeptManager deptMan;
    private CommManager comMan;

    private int commsSize;

    public Manager(String name){
        this.name = name;
        this.lecMan = new LecManager(2);
        this.deptMan = new DeptManager(2);
        this.comMan = new CommManager(2);
    }


    public String getCollegeName() {
        return name;
    }
  
    ///                  ///
    ///     LECTURER     ///
    ///                  ///
    public void addLecturer(String name, String id, Lecturer.Degree degreeRank, String degreeName, double salary){
        Lecturer newLec = new Lecturer(name, id, degreeRank, degreeName, salary);

        lecMan.addLecturer(newLec);
    }

    public Lecturer[] getLecturers() {
        return lecMan.getLecturers();
    }
  
    public Lecturer getLecturerByName(String name) {
        return lecMan.getLecturerByName(name);
    }

    public static double getAverageSalary(Lecturer[] lecs){
        if (lecs.length == 0) { return 0;}
        double sumSalary = 0;
        for (Lecturer l : lecs){
            sumSalary += l.getSalary();
        }
        return sumSalary/lecs.length;
    }
    ///                  ///
    ///     COMMITTEE    ///
    ///                  ///
    public Committee getCommitteeByName(String commName) {
        return comMan.getCommitteeByName(commName);
    }

    public Committee[] getCommittees() {
        return comMan.getCommittees();
    }

    public void addCommittee(String name, Lecturer lec) {
        comMan.addCommittee(name, lec);
    }

    public void updateCommitteeChairman(Committee comm,Lecturer chairman) {
        comm.updateChairman(chairman);
    }

    public void removeLecFromCommittee(String commName, String lecName) {
        Committee comm = getCommitteeByName(commName);
        Lecturer lec = getLecturerByName(lecName);
        if (comm != null && lec != null) {
            comm.removeLecFromMembers(lec);
        }
    }

    public void addLecToCommittee(String commName, String lecName) {
        Committee comm = getCommitteeByName(commName);
        Lecturer lec = getLecturerByName(lecName);
        comm.addLecturer(lec);
    }
  
    ///                  ///
    ///     Department   ///
    ///                  ///

    public Department[] getDepartments() {
        return deptMan.getDepartments();
    }

    public double getAverageSalaryByDepartment(String depName) {
        Department dep = deptMan.getDepartByName(depName);
        Lecturer[] lecs = dep.getLecturers();
        return getAverageSalary(lecs);
    }

    public void addDepartment(String name, int students) {
        deptMan.addDepartment(name, students);
    }

    public void addLecToDept(String lecName, String deptName) {
        Department dept = deptMan.getDepartByName(deptName);
        Lecturer lec = getLecturerByName(lecName);

        Department oldDept = lec.getDepartment(); // Remove old dep (can be only in 1)
        if (oldDept != null) {
            oldDept.removeLecturer(lec);
        }
        dept.addLecturer(lec);
    }
}
    
