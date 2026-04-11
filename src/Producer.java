import java.io.*;

public class Producer extends Thread {

    private CommonZone zone;

    public Producer(CommonZone zone) {
        this.zone = zone;
    }


    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.ser"))) {
            while (true) {
                try{
                    Student s = (Student) ois.readObject();
                    zone.put(s); // envoie au CONSUMER
                } catch (EOFException e){
                    break; // fin du fichier
                }
            }
        }  catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Producteur : terminé");
    }

}
