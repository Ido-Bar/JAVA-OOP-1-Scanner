
public class Manager {
    private String name;
    private Lecturer[] lecturers;
    private Department[] depts;
    private Committee[] comms;

    int lecSize;
    int commsSize;
    int deptsSize;

    public Manager(String name){
        this.name = name;
        this.lecturers = new Lecturer[2];
        this.depts = new Department[2];
        this.comms = new Committee[2];
    }

    public String getCollegeName() {
        return name;
    }
    public void addDepartment(String name, int numOfStudents) {
        Department dep = new Department(name, numOfStudents);

        boolean isOverSize = deptsSize == depts.length;
        if (!isOverSize) { doubleDepts(); }

        depts[deptsSize] = dep;
        deptsSize++;
    }

    public void addLecturer(String name, String id, Lecturer.Degree degreeRank, String degreeName, double salary){
        Lecturer newLec = new Lecturer(name, id, degreeRank, degreeName, salary);

        boolean isOverSize = lecSize == lecturers.length;
        if (isOverSize) { doubleLecturers(); }

        lecturers[lecSize] = newLec;
        lecSize++;
    }

    private void doubleLecturers() {
        int elemsExtFactor = 2;
        int elemsSize = lecturers.length;

        Lecturer[] newElems = new Lecturer[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = lecturers[i];
        }
        lecturers = newElems;
    }

    private void doubleDepts() {
        int elemsExtFactor = 2;
        int elemsSize = depts.length;

        Department[] newElems = new Department[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = depts[i];
        }
        depts = newElems;
    }

    public Lecturer[] getLecturers() {
        Lecturer[] activeLecturers = new Lecturer[lecSize];

        for (int i = 0; i < lecSize; i++) {
            activeLecturers[i] = lecturers[i];
        }

        return activeLecturers;
    }
  
    public Lecturer getLecturerByName(String name) {
        for (int i = 0; i < lecSize; i++) {
            if (lecturers[i].getName().equals(name)) {
                return lecturers[i];
            }
        }
        return null; // null if no lecturer matches that name
    }

    public void addCommittee(String name, Lecturer chairman) {
        Committee newComm = new Committee(name, chairman);

        boolean isOverSize = commsSize == comms.length;
        if (isOverSize) { doubleCommittees(); }

        comms[commsSize] = newComm;
        commsSize++;
    }

    private void doubleCommittees() {
        int elemsExtFactor = 2;
        int elemsSize = comms.length;

        Committee[] newElems = new Committee[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = comms[i];
        }
        comms = newElems;
    }

    public Committee[] getCommittees(){
        Committee[] activeCommitees = new Committee[commsSize];

        // Copy only the valid Commitees over
        for (int i = 0; i < commsSize; i++) {
            activeCommitees[i] = comms[i];
        }

        return activeCommitees;
    }

    public boolean setCommitteeChairman(Committee comm, Lecturer chairman) {
        boolean chairmanSuccess = comm.setChairman(chairman);
        return chairmanSuccess;
    }

    public Department[] getDepartments() {
        Department[] activeDepartments = new Department[deptsSize];
        for (int i = 0; i < deptsSize; i++) {
            activeDepartments[i] = depts[i];
        }

        return activeDepartments;
    }

    private Department getDepartmentByName(String deptName) {
        depts = getDepartments();

        for (Department d: depts) {
            System.out.println(d.getName());
            if (d.getName() == deptName) {
                return d;
            }
        }

        System.out.println("did not find dept: " + deptName);
        return null;
    };

    public void setLecturerToDepartment(String lecName, String deptName) {
        Department dept = getDepartmentByName(deptName);
        Lecturer lec = getLecturerByName(lecName);
        dept.addLecturer(lec);
    }
}
    
