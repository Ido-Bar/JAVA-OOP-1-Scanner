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
    private Committee[] committees;
    private int commsSize;

    public Lecturer(String name, String id, Degree degreeRank, String degreeName, double salary) {
        this.name = name;
        this.id = id;
        this.degreeRank = degreeRank;
        this.degreeName = degreeName;
        this.salary = salary;
        this.committees = new Committee[2];

//        this.commsSize = 0;
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
        boolean isOverSize = commsSize == committees.length;
        if (isOverSize) { doubleCommittees(); }

        committees[commsSize] = comm;
        commsSize++;
    }
    public void removeCommittee(Committee comm) {
        for (int i = 0; i < commsSize; i++) {
            if (committees[i].equals(comm)) {
                for (int j = i; j < commsSize - 1; j++) {
                    committees[j] = committees[j + 1];
                }
                committees[commsSize - 1] = null;
                commsSize--;
                break;
            }
        }
    }

    private void doubleCommittees() {
        int elemsExtFactor = 2;
        int elemsSize = committees.length;

        Committee[] newElems = new Committee[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = committees[i];
        }
        committees = newElems;
    }

    @Override
    public String toString() {
        // Todo: Change this - need to display all assigned departments.
        String depName = "";
        String commNames = "";
        if (department != null){
            depName = department.getName();
        }
        if (committees != null){
            for (Committee c : committees){
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
                ", commsSize=" + commsSize +
                '}';
    }
}
