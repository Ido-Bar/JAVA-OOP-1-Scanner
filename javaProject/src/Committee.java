public class Committee {
    private String name;
    private Lecturer[] lecturers;
    private Lecturer chairman;

    public Committee(){}

    public Committee(String name){
        this.name = name;
        this.lecturers = new Lecturer[2];
    }

    public String getName() { return name; }

    public boolean setChairman(Lecturer lec){
        // Check Degree nums with .ordinal() - I really don't know if we learned it, but it's necessary...
        if (lec.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()){
            System.out.println("Chairman must be at lease DR.");
            return false;
        }
        this.chairman = lec;
        // check if exists as lecturers
        removeChairmanFromLecturers(lec.getId());

        return true;
    }

    public void addLecturer(Lecturer lec){
        // TODO: Multiply array size or something and then add lecturer.
    }

    private void removeChairmanFromLecturers(int id){
        // TODO: Check for chairman in lecturers list and remove it (Resize lecturers?).
    }
}