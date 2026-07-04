package ido_bar_shaked_govrin;

public class Lecturer {
    public enum Degree {
        BSC,
        MSC,
        DR,
        PROF
    }
    private String name;
    private String id;
    private Degree degreeRank;
    private String degreeName;
    private double salary;
    private Department department;
    private CommManager comMan;

    public Lecturer(String name, String id, Degree degreeRank, String degreeName, double salary) {
        this.name = name;
        this.id = id;
        this.degreeRank = degreeRank;
        this.degreeName = degreeName;
        this.salary = salary;
        this.comMan = new CommManager(2);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Degree getDegreeRank() { return degreeRank; }
    public void setDegreeRank(Degree degreeRank) { this.degreeRank = degreeRank; }

    public String getDegreeName() { return degreeName; }
    public void setDegreeName(String degreeName) { this.degreeName = degreeName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public void addCommittee(Committee comm){
        comMan.addExistingCommittee(comm);
    }

    public void removeCommittee(Committee comm) {
        comMan.removeCommittee(comm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return name.equals(lecturer.name) && id.equals(lecturer.id);
    }

    @Override
    public String toString() {
        String depName = "";
        if (department != null){
            depName = department.getName();
        }
        String commNames = "";
        if (comMan.getCommittees() != null){
            for (Committee c : comMan.getCommittees()){
                if (c != null){
                    commNames += c.getName() + ", ";
                }
            }
        }
        return "Lecturer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", degreeRank=" + degreeRank +
                ", degreeName='" + degreeName + '\'' +
                ", salary=" + salary +
                ", department=" + depName +
                ", committees=" + commNames +
                '}';
    }
}
