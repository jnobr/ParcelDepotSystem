package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.Parcel;

public class ParcelMap  {

    private final HashMap<String, Parcel> map = new HashMap<>();


    public HashMap<String, Parcel> getMap() {
        return map;
    }



    public Parcel findParcel(String id) {
        Parcel Parcel = map.get(id);
        if (Parcel == null) {
            return null;
        }
        return map.get(id);
    }

    public boolean addParcel(Parcel parcel) {
        if(this.map.containsKey(parcel.getParcelID())) {
            return false;
        }
        this.map.put(parcel.getParcelID(),parcel);
        return true;

    }

    public boolean removeParcel(String id) {
        if(this.map.containsKey(id)) {
            this.map.remove(id);
            return true;
        }
        return false;
    }
}
