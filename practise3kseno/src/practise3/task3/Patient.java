package practise3.task3;

class Patient extends Thread {
    private int id;
    private Clinic clinic;

    public Patient(int id, Clinic clinic) {
        this.id = id;
        this.clinic = clinic;
    }

    public long getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            clinic.addToQueue(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Пациент " + id;
    }
}
