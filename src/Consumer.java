import java.io.*;

// affiche les étudiants
public class Consumer extends Thread{
    private CommonZone zone;

    public Consumer(CommonZone zone){
        this.zone = zone;
    }

    public void run(){
        while(true){
            Student s = zone.get(); // récupère un étudiant depuis la zone commune partagée
            System.out.println("Consommateur : " + s);
            if (s == null) break;
        }
    }

}
