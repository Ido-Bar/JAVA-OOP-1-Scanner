public class Committee {
    private String name;
    private LecManager lecMan;
    private Lecturer chairman;

    public Committee(String name, Lecturer chairman) {
        this.name = name;
        this.lecMan = new LecManager(2);
        this.chairman = chairman;
    }

    public String getName() { return name; }

    public boolean updateChairman(Lecturer lec){
        // Check Degree nums with .ordinal() - I really don't know if we learned it, but it's necessary...
        if (lec.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()){
            System.out.println("Chairman must be at lease DR.");
            return false;
        }
        Lecturer oldChairman = this.chairman;
        this.chairman = lec;

        removeLecFromMembers(lec);

        if (oldChairman != null) {
            addLecturer(oldChairman);
            oldChairman.addCommittee(this);
        }

        return true;
    }

    public Lecturer getChairman(){ return chairman; }

    public void addLecturer(Lecturer lec){
        if (lec.equals(getChairman())) return; // Lecturere is a chairman

        lecMan.addLecturer(lec);
    }

    public Lecturer[] getLecturersInCommittee() {
        return lecMan.getLecturers();
    }

    public void removeLecFromMembers(Lecturer lec){
            lecMan.removeLecturer(lec);
            lec.removeCommittee(this);
    }

    @Override
    public String toString() {
        String lecNames = "";
        if (lecMan.getLecturers() != null){
            for (Lecturer l : lecMan.getLecturers()){
                if (l != null){
                    lecNames += l.getName() + ", ";
                }
            }
        }
        return "Committee{" +
                "name='" + name + '\'' +
                ", lecturers=" + lecNames +
                ", chairman=" + chairman.getName() +
                ", lecSize=" + lecMan.getLecLength() +
                '}';
    }
}
