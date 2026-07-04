package ido_bar_shaked_govrin;

public class Prof extends Dr {
    private String professorshipBody;

    public Prof(String name, String id, Degree degreeRank, String degreeName, double salary, String[] articles, String professorshipBody) {
        super(name, id, degreeRank, degreeName, salary, articles);
        this.professorshipBody = professorshipBody;
    }

    public String getProfessorshipBody() {
        return professorshipBody;
    }

    public void setProfessorshipBody(String professorshipBody) {
        this.professorshipBody = professorshipBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prof prof = (Prof) o;
        return getName().equals(prof.getName()) && getId().equals(prof.getId());
    }

    @Override
    public String toString() {
        String depName = "";
        if (getDepartment() != null) {
            depName = getDepartment().getName();
        }
        String articlesStr = "";
        String[] arts = getArticles();
        for (int i = 0; i < arts.length; i++) {
            articlesStr += arts[i];
            if (i < arts.length - 1) {
                articlesStr += ", ";
            }
        }
        return "Prof{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", degreeRank=" + getDegreeRank() +
                ", degreeName='" + getDegreeName() + '\'' +
                ", salary=" + getSalary() +
                ", department=" + depName +
                ", articles=[" + articlesStr + "]" +
                ", professorshipBody='" + professorshipBody + '\'' +
                '}';
    }
}
