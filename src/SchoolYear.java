import java.io.Serializable;

public class SchoolYear implements Serializable {

    private String section;
    private int year;
    private AcademicDegree academicDegree;

    public SchoolYear(String section, int year, AcademicDegree academicDegree) {
        setSection(section);
        setYear(year);
        this.academicDegree = academicDegree;
    }

    public String getSection() {
        return section;
    }

    public int getYear() {
        return year;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setSection(String section) {
        if (section == null ||section.isEmpty()) {
            throw new IllegalArgumentException("La section ne peux pas être vide");
        }
        this.section = section;
    }

    public void setYear(int year) {
        if (year < 1 || year > 3) {
            throw new IllegalArgumentException("L'année doit être comprise entre 1 et 3");
        }
        this.year = year;
    }

    public String toString() {
        return academicDegree.getLabel() + " " + year + " en " + section;
    }

}