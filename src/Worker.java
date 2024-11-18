public class Worker {
    private String email;
    private String workerID;

    public Worker(String email, String workerID) {
        this.email = email;
        this.workerID = workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;

    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkerID() {
        return workerID;
    }
    public String getEmail() {
        return email;
    }


}
