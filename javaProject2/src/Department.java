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
    }

    public Lecturer[] getLecturers() {
        return lecMan.getLecturers();
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", numStudents=" + numStudents +
                ", lecturers=" + Arrays.toString(getLecturers()) +
                ", numLecturers='" + lecMan.getLecLength() + '\'' +
                '}';
    }
}
