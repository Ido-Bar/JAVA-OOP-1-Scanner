public class Committee {
    private String name;
    private Lecturer[] lecturers;
    private Lecturer chairman;
    private int lecSize;

    public Committee(String name, Lecturer chairman) {
        this.name = name;
        this.lecturers = new Lecturer[2];
        this.chairman = chairman;
        lecSize = 0;
    }

    public String getName() { return name; }

    // TODO: Check if needed
    public boolean setChairman(Lecturer lec){
        // Check Degree nums with .ordinal() - I really don't know if we learned it, but it's necessary...
        if (lec.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()){
            System.out.println("Chairman must be at lease DR.");
            return false;
        }
        this.chairman = lec;
        // check if exists as lecturers
        removeChairmanFromLecturers(lec.getId());

        return true;
    }

    public void addLecturer(Lecturer lec){
        boolean isOverSize = lecSize == lecturers.length;
        if (isOverSize) { doubleLecturers(); }

        lecturers[lecSize] = lec;
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

    private void removeChairmanFromLecturers(String id){
        // TODO: Check for chairman in lecturers list and remove it (Resize lecturers?).
    }
}
