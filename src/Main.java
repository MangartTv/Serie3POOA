import java.io.*;
// Import de toutes les classes nécessaires pour la sérialisation :
// FileOutputStream, ObjectOutputStream, FileInputStream, ObjectInputStream, IOException, etc.

public class Main {

    public static void main(String[] args){

        // =========================================================
        // 1. CRÉATION DES OBJETS SchoolYear (les années d'étude)
        // =========================================================

        // On crée 3 années scolaires différentes
        SchoolYear sy1 = new SchoolYear("Informatique", 1, AcademicDegree.BACHELOR);
        SchoolYear sy2 = new SchoolYear("Informatique", 2, AcademicDegree.BACHELOR);
        SchoolYear sy3 = new SchoolYear("Informatique", 3, AcademicDegree.BACHELOR);

        // On les met dans un tableau pour pouvoir les réutiliser facilement
        SchoolYear[] years = {sy1, sy2, sy3};

        // =========================================================
        // 2. DONNÉES DES ÉTUDIANTS (prénoms + noms)
        // =========================================================

        // Tableau des prénoms (10 étudiants)
        String[] firstnames = {
                "Tom", "Lucas", "Emma", "Léa", "Noah",
                "Jules", "Anna", "Louis", "Sarah", "Hugo"
        };

        // Tableau des noms de famille (10 étudiants)
        String[] lastnames = {
                "Jacobs", "Dubois", "Martin", "Bernard", "Leroy",
                "Moreau", "Simon", "Laurent", "Michel", "Garcia"
        };

        // =========================================================
        // 3. SÉRIALISATION : ÉCRITURE DES OBJETS DANS UN FICHIER
        // =========================================================

        // try-with-resources :
        // - crée le fichier students.ser
        // - ouvre un flux d'écriture d'objets
        // - ferme automatiquement le fichier à la fin (très important)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("students.ser"))) {

            // On crée 10 étudiants
            for (int i = 0; i < 10; i++) {

                // Création d'un étudiant avec :
                // - prénom i
                // - nom i
                // - une année d'étude cyclique (0,1,2 → sy1, sy2, sy3)
                Student s = new Student(
                        firstnames[i],
                        lastnames[i],
                        years[i % 3] // permet de répartir les étudiants sur 3 années
                );

                // Écriture de l'objet dans le fichier (sérialisation)
                oos.writeObject(s);
            }

            // Message de confirmation
            System.out.println("Étudiants enregistrés !");

        } catch (IOException e) {
            // En cas d'erreur d'écriture ou fichier
            e.printStackTrace();
        }

        // =========================================================
        // 4. DÉSÉRIALISATION : LECTURE DES OBJETS DU FICHIER
        // =========================================================

        // try-with-resources :
        // - ouvre le fichier students.ser en lecture
        // - ferme automatiquement le flux après utilisation
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("students.ser"))) {

            // Message pour séparer les étapes
            System.out.println("\nLecture des étudiants :");

            // On lit exactement 10 objets (car on en a écrit 10)
            for (int i = 0; i < 10; i++) {

                // Lecture d'un objet depuis le fichier
                // (cast obligatoire car readObject retourne un Object)
                Student s = (Student) ois.readObject();

                // Affichage de l'étudiant via toString()
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            // IOException : problème fichier
            // ClassNotFoundException : problème de classe lors de la lecture
            e.printStackTrace();
        }

        // =====================================================
        // ÉTAPE 5 : THREADS
        // =====================================================

        CommonZone zone = new CommonZone();

        Producer producer = new Producer(zone);
        Consumer consumer = new Consumer(zone);

        producer.start();
        consumer.start();

    }
}