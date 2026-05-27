public class Department {
    private String name;
    private int numStudents;
    private Lecturer[] lecturers;
    private int numLecturers;

    public Department(String name, int numStudents) {
        this.name = name;
        this.numStudents = numStudents;
        this.numLecturers = 0;
        this.lecturers = new Lecturer[2];
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getNumStudents() { return numStudents; }
    public void setNumStudents(int numStudents) { this.numStudents = numStudents; }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", numStudents=" + numStudents +
                ", lecturers=" + lecturers +
                ", numLecturers='" + numLecturers + '\'' +
                '}';
    }

    public void addLecturer(Lecturer lec) {
        boolean isOverSize = numLecturers == lecturers.length;
        if (isOverSize) { doubleLecturers(); }

        lecturers[numLecturers] = lec;
        numLecturers++;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
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
}
