import java.util.Arrays;

public class Department {
    private String name;
    private int numStudents;
    private LecManager lecMan;

    public Department(String name, int numStudents) {
        this.name = name;
        this.numStudents = numStudents;
        this.lecMan = new LecManager(2);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getNumStudents() { return numStudents; }
    public void setNumStudents(int numStudents) { this.numStudents = numStudents; }

    public void addLecturer(Lecturer lec) {
        lecMan.addLecturer(lec);

        lec.setDepartment(this); // Add department to lec
    }

    public void removeLecturer(Lecturer lec) {
        for (int i = 0; i < numLecturers; i++) {
            if (lecturers[i].equals(lec)) {
                for (int j = i; j < numLecturers - 1; j++) {
                    lecturers[j] = lecturers[j + 1];
                }
                lecturers[numLecturers - 1] = null;
                numLecturers--;
                break;
            }
        }
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
        Lecturer[] activeLecturers = new Lecturer[numLecturers];

        for (int i = 0; i < numLecturers; i++) {
            activeLecturers[i] = lecturers[i];
        }

        return activeLecturers;
    }

    public Lecturer[] getLecturers() {
        return lecMan.getLecturers();
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
        return "Department{" +
                "name='" + name + '\'' +
                ", numStudents=" + numStudents +
                ", lecturers=" + lecNames +
                ", numLecturers='" + numLecturers + '\'' +
                '}';
    }
}
