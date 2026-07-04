package ido_bar_shaked_govrin;

public class Manager {
    private String name;
    private LecManager lecMan;
    private DeptManager deptMan;
    private CommManager comMan;

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
    public void addLecturer(Lecturer lec) throws ItemAlreadyExistsException {
        Lecturer existing = lecMan.getLecturerByName(lec.getName());
        if (existing != null) {
            throw new ItemAlreadyExistsException("Lecturer '" + lec.getName() + "' already exists");
        }
        lecMan.addLecturer(lec);
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

    public void addCommittee(String name, Dr chairman) throws InvalidChairmanException, ItemAlreadyExistsException {
        comMan.addCommittee(name, chairman);
    }

    public void updateCommitteeChairman(Committee comm, Dr chairman) throws InvalidChairmanException, LecturerAlreadyInCommitteeException {
        comm.updateChairman(chairman);
    }

    public void removeLecFromCommittee(String commName, String lecName) throws ItemNotFoundException {
        Committee comm = getCommitteeByName(commName);
        Lecturer lec = getLecturerByName(lecName);
        if (comm == null) {
            throw new ItemNotFoundException("Committee '" + commName + "' not found");
        }
        if (lec == null) {
            throw new ItemNotFoundException("Lecturer '" + lecName + "' not found");
        }
        comm.removeLecFromMembers(lec);
    }

    public void addLecToCommittee(String commName, String lecName) throws ItemNotFoundException, LecturerAlreadyInCommitteeException {
        Committee comm = getCommitteeByName(commName);
        Lecturer lec = getLecturerByName(lecName);
        if (comm == null) {
            throw new ItemNotFoundException("Committee '" + commName + "' not found");
        }
        if (lec == null) {
            throw new ItemNotFoundException("Lecturer '" + lecName + "' not found");
        }
        comm.addLecturer(lec);
    }

    public Committee duplicateCommittee(String commName) throws ItemNotFoundException, ItemAlreadyExistsException, LecturerAlreadyInCommitteeException {
        Committee original = getCommitteeByName(commName);
        if (original == null) {
            throw new ItemNotFoundException("Committee '" + commName + "' not found");
        }

        String newName = commName + "-new";
        if (getCommitteeByName(newName) != null) {
            throw new ItemAlreadyExistsException("Committee '" + newName + "' already exists");
        }
        Committee duplicate = new Committee(newName, original.getChairman());

        Lecturer[] members = original.getLecturersInCommittee();
        for (Lecturer member : members) {
            if (member != null) {
                duplicate.addLecturer(member);
            }
        }
        comMan.addExistingCommittee(duplicate);

        return duplicate;
    }

    ///                  ///
    ///     Department   ///
    ///                  ///
    public Department[] getDepartments() {
        return deptMan.getDepartments();
    }

    public double getAverageSalaryByDepartment(String depName) throws ItemNotFoundException {
        Department dep = deptMan.getDepartByName(depName);
        if (dep == null) {
            throw new ItemNotFoundException("Department '" + depName + "' not found");
        }
        Lecturer[] lecs = dep.getLecturers();
        return getAverageSalary(lecs);
    }

    public void addDepartment(String name, int students) throws ItemAlreadyExistsException {
        Department existing = deptMan.getDepartByName(name);
        if (existing != null) {
            throw new ItemAlreadyExistsException("Department '" + name + "' already exists");
        }
        deptMan.addDepartment(name, students);
    }

    public void addLecToDept(String lecName, String deptName) throws ItemNotFoundException {
        Department dept = deptMan.getDepartByName(deptName);
        Lecturer lec = getLecturerByName(lecName);

        if (dept == null) {
            throw new ItemNotFoundException("Department '" + deptName + "' not found");
        }
        if (lec == null) {
            throw new ItemNotFoundException("Lecturer '" + lecName + "' not found");
        }

        Department oldDept = lec.getDepartment();
        if (oldDept != null) {
            oldDept.removeLecturer(lec);
        }
        dept.addLecturer(lec);
    }
}
    
