public class DeptManager {
    private Department[] depts;
    private int deptsSize;

    public DeptManager(int newSize) {
        depts = new Department[newSize];
        deptsSize = 0;
    }

    private void doubleDepts() {
        int elemsExtFactor = 2;
        int elemsSize = depts.length;

        Department[] newElems = new Department[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = depts[i];
        }
        depts = newElems;
    }

    public void addDepartment(String name, int numOfStudents) {
        Department dep = new Department(name, numOfStudents);

        boolean isOverSize = deptsSize == depts.length;
        if (isOverSize) { doubleDepts(); }

        depts[deptsSize] = dep;
        deptsSize++;
    }

    public Department[] getDepartments() {
        Department[] activeDepartments = new Department[deptsSize];
        for (int i = 0; i < deptsSize; i++) {
            activeDepartments[i] = depts[i];
        }
        return activeDepartments;
    }

    public Department getDepartByName(String name){
        for (int i = 0; i < deptsSize; i++) {
            if (depts[i].getName().equals(name)) {
                return depts[i];
            }
        }
        return null; // null if no lecturer matches that name
    }
}
