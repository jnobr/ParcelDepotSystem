public class Worker extends Name {

    private String workerID;
    private String role;

    public Worker(String firstname, String middlename, String surname, String workerID,String role) {
        super(firstname,middlename,surname);
        this.role = role;
        this.workerID = workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;

    }

    public String getWorkerID() {
        return workerID;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public String toString() {
        return "\nName: " + this.getName() + "\nWorker ID: " + workerID + "\nRole: " + role;
    }


}
