public class LecManager {
    private Lecturer[] lecturers;
    private int lecSize;

    public LecManager(int newLecSize) {
        lecturers = new Lecturer[newLecSize];
    }

    public Lecturer[] getLecturers() {
        Lecturer[] activeLecturers = new Lecturer[lecSize];

        for (int i = 0; i < lecSize; i++) {
            activeLecturers[i] = lecturers[i];
        }

        return activeLecturers;
    }

    public Lecturer getLecturerByName(String name) {
        for (int i = 0; i < lecSize; i++) {
            if (lecturers[i].getName().equals(name)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public int getLecLength() {
        return getLecturers().length;
    }

    public void addLecturer(Lecturer lec) {
        boolean isOverSize = lecSize == lecturers.length;
        if (isOverSize) { doubleLecturers(); }

        lecturers[lecSize] = lec;
        lecSize++;
    }

    public void removeLecturer(Lecturer lec){
        for (int i = 0; i < lecSize; i++) {
            if (lecturers[i].equals(lec)) {
                for (int j = i; j < lecSize - 1; j++) {
                    lecturers[j] = lecturers[j + 1];
                }
                lecturers[lecSize - 1] = null;
                lecSize--;

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
}
