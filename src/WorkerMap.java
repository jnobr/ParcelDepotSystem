import java.util.ArrayList;
import java.util.HashMap;

public class WorkerMap {


    private final HashMap<String,Worker> map = new HashMap<>();


    public HashMap<String, Worker> getMap() {
        return map;
    }

    public void initialiseMap(ArrayList<Worker> Workers) {
        for(Worker Worker : Workers) {
            this.map.put(Worker.getWorkerID(),Worker);
        }
    }

    public Worker findWorker(String id) {
        Worker Worker = map.get(id);
        if (Worker == null) {
            return null;
        }
        return map.get(id);
    }

    public boolean addWorker(Worker Worker) {

        if(map.containsKey(Worker.getWorkerID())) {
            return false;
        }
        map.put(Worker.getWorkerID(),Worker);
        return true;

    }

    public boolean removeWorker(Worker Worker) {

        if(map.containsKey(Worker.getWorkerID())) {
            map.remove(Worker.getWorkerID());
            return true;
        }
        return false;
    }
}
