package ido_bar_shaked_govrin;

public class Dr extends Lecturer implements Comparable<Dr> {
    private String[] articles;
    private int articlesSize;

    public Dr(String name, String id, Degree degreeRank, String degreeName, double salary, String[] articles) {
        super(name, id, degreeRank, degreeName, salary);
        if (articles != null) {
            this.articles = new String[articles.length];
            for (int i = 0; i < articles.length; i++) {
                this.articles[i] = articles[i];
            }
            this.articlesSize = articles.length;
        } else {
            this.articles = new String[2];
            this.articlesSize = 0;
        }
    }

    public String[] getArticles() {
        String[] activeArticles = new String[articlesSize];
        for (int i = 0; i < articlesSize; i++) {
            activeArticles[i] = articles[i];
        }
        return activeArticles;
    }

    public int getArticlesCount() {
        return articlesSize;
    }

    public void addArticle(String article) {
        if (articlesSize == articles.length) {
            doubleArticles();
        }
        articles[articlesSize] = article;
        articlesSize++;
    }

    private void doubleArticles() {
        int newSize = articles.length * 2;
        String[] newArticles = new String[newSize];
        for (int i = 0; i < articles.length; i++) {
            newArticles[i] = articles[i];
        }
        articles = newArticles;
    }

    @Override
    public int compareTo(Dr other) {
        return Integer.compare(this.articlesSize, other.articlesSize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dr dr = (Dr) o;
        return getName().equals(dr.getName()) && getId().equals(dr.getId());
    }

    @Override
    public String toString() {
        String depName = "";
        if (getDepartment() != null) {
            depName = getDepartment().getName();
        }
        String articlesStr = "";
        for (int i = 0; i < articlesSize; i++) {
            articlesStr += articles[i];
            if (i < articlesSize - 1) {
                articlesStr += ", ";
            }
        }
        return "Dr{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", degreeRank=" + getDegreeRank() +
                ", degreeName='" + getDegreeName() + '\'' +
                ", salary=" + getSalary() +
                ", department=" + depName +
                ", articles=[" + articlesStr + "]" +
                '}';
    }
}
