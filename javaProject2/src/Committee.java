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

    public boolean updateChairman(Lecturer lec){
        // Check Degree nums with .ordinal() - I really don't know if we learned it, but it's necessary...
        if (lec.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()){
            System.out.println("Chairman must be at lease DR.");
            return false;
        }
        Lecturer oldChairman = this.chairman;
        this.chairman = lec;

        removeLecFromMembers(lec);

        if (oldChairman != null) {
            addLecturer(oldChairman);
            oldChairman.addCommittee(this);
        }

        return true;
    }

    public Lecturer getChairman(){ return chairman; }

    public void addLecturer(Lecturer lec){
        Lecturer[] lecs = getLecturersInCommittee();
        if (lec.equals(getChairman())) return; // Lecturere is a chairman
        for (Lecturer l : lecs){
            if (l.equals(lec)) return; // Lecturer already exist
        }

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

    public Lecturer[] getLecturersInCommittee() {
        Lecturer[] activeLecturers = new Lecturer[lecSize];

        for (int i = 0; i < lecSize; i++) {
            activeLecturers[i] = lecturers[i];
        }

        return activeLecturers;
    }

    public void removeLecFromMembers(Lecturer lec){
        for (int i = 0; i < lecSize; i++) {
            if (lecturers[i].equals(lec)) {
                for (int j = i; j < lecSize - 1; j++) {
                    lecturers[j] = lecturers[j + 1];
                }
                lecturers[lecSize - 1] = null; // Remove chairman from position.
                lecSize--;

                lec.removeCommittee(this);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String lecNames = "";
        if (lecturers != null){
            for (Lecturer l : lecturers){
                if (l != null){
                    lecNames += l.getName() + ", ";
                }
            }
        }
        return "Committee{" +
                "name='" + name + '\'' +
                ", lecturers=" + lecNames +
                ", chairman=" + chairman.getName() +
                ", lecSize=" + lecSize +
                '}';
    }
}
