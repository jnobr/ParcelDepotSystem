import java.util.ArrayList;
import java.util.HashMap;

public class ParcelMap  {

    private HashMap<String,Parcel> map = new HashMap<>();


    public HashMap<String, Parcel> getMap() {
        return map;
    }

    public void initialiseMap(ArrayList<Parcel> parcels) {
        for(Parcel parcel : parcels) {
            this.map.put(parcel.getParcelID(),parcel);
        }
    }

    public Parcel findParcel() {

        return map.get("123");
    }

    public boolean addParcel(Parcel parcel) {


        return false;

    }

    public boolean removeParcel(Parcel parcel) {
        return false;
    }
}
