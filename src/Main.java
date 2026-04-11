import java.io.*;

public class Main {
    public static void main(String[] args){
        SchoolYear sy1 = new SchoolYear("Informatique", 1, AcademicDegree.BACHELOR);
        SchoolYear sy2 = new SchoolYear("Informatique", 2, AcademicDegree.BACHELOR);
        SchoolYear sy3 = new SchoolYear("Informatique", 3, AcademicDegree.BACHELOR);

        SchoolYear[] years = {sy1, sy2, sy3};

        String[] firstnames = {
                "Tom", "Lucas", "Emma", "Léa", "Noah",
                "Jules", "Anna", "Louis", "Sarah", "Hugo"
        };

        String[] lastnames = {
                "Jacobs", "Dubois", "Martin", "Bernard", "Leroy",
                "Moreau", "Simon", "Laurent", "Michel", "Garcia"
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("students.ser"))) {

            for (int i = 0; i < 10; i++) {

                Student s = new Student(
                        firstnames[i],
                        lastnames[i],
                        years[i % 3]
                );

                oos.writeObject(s);
            }

            System.out.println("Étudiants enregistrés !");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("students.ser"))) {

            System.out.println("\nLecture des étudiants :");

            for (int i = 0; i < 10; i++) {
                Student s = (Student) ois.readObject();
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
