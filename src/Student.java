import java.io.*;

public class Student implements Serializable{
    private String firstname;
    private String lastname;
    private SchoolYear schoolYear;

    public Student(String firstname, String lastname, SchoolYear schoolYear) {
        setFirstname(firstname);
        setLastname(lastname);

        if (schoolYear == null) {
            throw new IllegalArgumentException("L'année scolaire ne peut pas être null");
        }
        this.schoolYear = schoolYear;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setFirstname(String firstname) {
        if ( firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Le prénom ne peux pas être null");
        }
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        if ( lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Le nom de famille ne peux pas être null");
        }
        this.lastname = lastname;
    }

    public String toString() {
        return firstname + " " + lastname + " est étudiant(e) en " + schoolYear;
    }

}
