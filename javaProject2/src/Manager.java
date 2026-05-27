
public class Manager {
    private String name;
    private Lecturer[] lecturers;
    private Department[] depts;
    private Committee[] comms;

    private int lecSize;
    private int commsSize;
    private int deptsSize;

    public Manager(String name){
        this.name = name;
        this.lecturers = new Lecturer[2];
        this.depts = new Department[2];
        this.comms = new Committee[2];
    }


    public String getCollegeName() {
        return name;
    }
  
    ///                  ///
    ///     LECTURER     ///
    ///                  ///
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
  
    ///                  ///
    ///     COMMITTEE    ///
    ///                  ///
    public void addCommittee(String name, Lecturer chairman) {
        // Check again for safety
        if (chairman.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()) {
            System.out.println("Error: Chairman must be at least DR. Committee '" + name + "' was not created.");
            return;
        }
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

    public Committee getCommitteeByName(String name){
        for (int i = 0; i < commsSize; i++) {
            if (comms[i].getName().equals(name)) {
                return comms[i];
            }
        }
        return null; // null if no lecturer matches that name
    }

    public void updateCommitteeChairman(Committee comm,Lecturer chairman){
        comm.updateChairman(chairman);
    }

    public void removeLecFromCommittee(String commName, String lecName){
        Committee comm = getCommitteeByName(commName);
        Lecturer lec = getLecturerByName(lecName);
        if (comm != null && lec != null) {
            comm.removeLecFromMembers(lec);
        }
    }

    public void addLecToCommittee(String commName, String lecName){
        Committee comm = getCommitteeByName(commName);
        Lecturer lec = getLecturerByName(lecName);
        comm.addLecturer(lec);
        lec.addCommittee(comm);
    }
  
    ///                  ///
    ///     Department   ///
    ///                  ///
    public void addDepartment(String name, int numOfStudents) {
        Department dep = new Department(name, numOfStudents);

        boolean isOverSize = deptsSize == depts.length;
        if (isOverSize) { doubleDepts(); }

        depts[deptsSize] = dep;
        deptsSize++;
    }

    public Department[] getDepartments() {
        Department[] activeDepartments = new Department[deptsSize];
        for (int i = 0; i < deptsSize; i++) {
            activeDepartments[i] = depts[i];
        }
        return activeDepartments;
    }
}
    
