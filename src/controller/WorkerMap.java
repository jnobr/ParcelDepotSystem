package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.Worker;

public class WorkerMap {


    private final HashMap<String, Worker> map = new HashMap<>();


    public HashMap<String, Worker> getMap() {
        return map;
    }

    public void initialiseMap(ArrayList<Worker> Workers) {
        //TODO: Create 3 dummy employees and add them to the list here
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

    public boolean removeWorker(String id) {

        if(map.containsKey(id)) {
            map.remove(id);
            return true;
        }
        return false;
    }
}
