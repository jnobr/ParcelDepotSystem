import java.util.ArrayList;
import java.util.HashMap;

public class WorkerMap {


    private HashMap<String,Worker> map = new HashMap<>();


    public HashMap<String, Worker> getMap() {
        return map;
    }

    public void initialiseMap(ArrayList<Worker> Workers) {
        for(Worker Worker : Workers) {
            this.map.put(Worker.getWorkerID(),Worker);
        }
    }

    public Worker findWorker() {

        return map.get("123");
    }

    public boolean addWorker(Worker Worker) {


        return false;

    }

    public boolean removeWorker(Worker Worker) {
        return false;
    }
}
