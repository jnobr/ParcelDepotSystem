package controller;

import java.util.HashMap;
import model.Worker;

public class WorkerMap {


    private final HashMap<String, Worker> map = new HashMap<>();


    public HashMap<String, Worker> getMap() {
        return map;
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

    public boolean removeWorker(String id) {

        if(map.containsKey(id)) {
            map.remove(id);
            return true;
        }
        return false;
    }
}
