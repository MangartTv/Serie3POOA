public class CommonZone {

    private Student student;
    private boolean available = false;

    // PRODUCER
    public synchronized void put(Student s) {
        while (available) {
            try {
                wait(); // attend que le CONSUMER lise
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.student = s;
        available = true;
        System.out.println("Producteur : étudiant écrit -> " + s);
        notify(); // réveille CONSUMER
    }

    // CONSUMER
    public synchronized Student get() {
        while (!available) {
            try {
                wait(); // attend que le PRODUCER écrive
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        System.out.println("Consommateur : étudiant lu");
        notify(); // réveille PRODUCER
        return student;
    }

}
