import java.util.Scanner;

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
        this.deptsSize = 0;
        this.comms = new Committee[2];
        this.commsSize = 0;
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

    public Lecturer[] getLecturers() {
        // Create a new array exactly the size of the active elements
        Lecturer[] activeLecturers = new Lecturer[lecSize];

        // Copy only the valid lecturers over
        for (int i = 0; i < lecSize; i++) {
            activeLecturers[i] = lecturers[i];
        }

        return activeLecturers;
    }

    public Lecturer getLecturerByName(String name) {
        // Iterate only up to lecSize to avoid NullPointerExceptions
        for (int i = 0; i < lecSize; i++) {
            if (lecturers[i].getName().equals(name)) {
                return lecturers[i];
            }
        }
        return null; // Return null if no lecturer matches that name
    }
    ///                  ///
    ///     COMMITTEE    ///
    ///                  ///
    public void addCommittee(String name){
        Committee newComm = new Committee(name);

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

    public boolean setCommitteeChairman(Committee comm, Lecturer chairman){
        boolean chairmanSuccess = comm.setChairman(chairman);
        return chairmanSuccess;
    }
}
