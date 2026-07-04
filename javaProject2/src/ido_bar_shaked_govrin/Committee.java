package ido_bar_shaked_govrin;

import java.util.Comparator;

public class Committee implements Comparable<Committee> {
    private String name;
    private LecManager lecMan;
    private Dr chairman;

    public Committee(String name, Dr chairman) {
        this.name = name;
        this.lecMan = new LecManager(2);
        this.chairman = chairman;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void updateChairman(Dr lec) throws InvalidChairmanException, LecturerAlreadyInCommitteeException {
        if (this.chairman != null && this.chairman.equals(lec)) { return; }
        if (lec.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()){
            throw new InvalidChairmanException("Chairman must be at least DR. Lecturer '" + lec.getName() + "' has degree " + lec.getDegreeRank());
        }
        Dr oldChairman = this.chairman;
        this.chairman = lec;

        removeLecFromMembers(lec);
        if (oldChairman != null) {
            addLecturer(oldChairman);
        }
    }

    public Dr getChairman(){
        return chairman;
    }

    public void addLecturer(Lecturer lec) throws LecturerAlreadyInCommitteeException {
        for (Lecturer lecExist : lecMan.getLecturers()) {
            if (lecExist.equals(lec)) {
                throw new LecturerAlreadyInCommitteeException("Lecturer '"+ lec.getName() + "' is already a member of committee '" + name + "'");
            }
        }
        if (lec.equals(getChairman())) {
            throw new LecturerAlreadyInCommitteeException("Lecturer '" + lec.getName()+ "' is already the chairman of committee '" + name + "'");
        }
        lecMan.addLecturer(lec);
        lec.addCommittee(this);
    }

    public Lecturer[] getLecturersInCommittee() {
        return lecMan.getLecturers();
    }

    public int getMemberCount() {
        return lecMan.getLecturers().length;
    }

    public int getTotalArticles() {
        int total = 0;

        if (chairman != null) {
            total += chairman.getArticlesCount();
        }
        for (Lecturer l : lecMan.getLecturers()) {
            if (l instanceof Dr dr) {
                total += dr.getArticlesCount();
            }
        }
        return total;
    }

    public void removeLecFromMembers(Lecturer lec){
        lecMan.removeLecturer(lec);
        lec.removeCommittee(this);
    }

    @Override
    public int compareTo(Committee other) {
        return Integer.compare(this.getMemberCount(), other.getMemberCount());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Committee)) return false;
        Committee committee = (Committee) o;
        return name.equals(committee.name);
    }

    @Override
    public String toString() {
        String lecNames = " ";
        if (lecMan.getLecturers() != null){
            for (Lecturer l : lecMan.getLecturers()){
                if (l != null){
                    lecNames += l.getName() + ", ";
                }
            }
        }
        String chairmanName = (chairman != null) ? chairman.getName() : "None";
        return "Committee{" +
                "name='" + name + '\'' +
                ", lecturers=" + lecNames +
                "chairman=" + chairmanName +
                '}';
    }

    public static class ArticleComparator implements Comparator<Committee> {
        @Override
        public int compare(Committee c1, Committee c2) {
            return Integer.compare(c1.getTotalArticles(), c2.getTotalArticles());
        }
    }
}
