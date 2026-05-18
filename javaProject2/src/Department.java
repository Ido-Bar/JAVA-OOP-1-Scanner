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
}
