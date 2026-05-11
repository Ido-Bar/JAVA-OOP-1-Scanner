public class Lecturer {
    public enum Degree {
        BSC,
        MSC,
        DR,
        PROF
    }
    private String name;
    private int id;
    private Degree degreeRank;
    private String degreeName;
    private double salary;
    private Department department;

    private static int nextId = 1;

    public Lecturer() {
        this.id = nextId++;
    }

    public Lecturer(String name, Degree degreeRank, String degreeName, double salary) {
        this.name = name;
        this.id = nextId++;
        this.degreeRank = degreeRank;
        this.degreeName = degreeName;
        this.salary = salary;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Degree getDegreeRank() { return degreeRank; }
    public void setDegreeRank(Degree degreeRank) { this.degreeRank = degreeRank; }

    public String getDegreeName() { return degreeName; }
    public void setDegreeName(String degreeName) { this.degreeName = degreeName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
