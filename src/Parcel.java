public class Parcel {
    private String parcelID;
    private int StorageTime;
    private String dimensions;
    private int Weight;

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public void setStorageTime(int StorageTime) {
        this.StorageTime = StorageTime;
    }
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
    public void setWeight(int weight) {
        this.Weight = weight;
    }
    public String getParcelID() {
        return parcelID;
    }
    public int getStorageTime() {
        return StorageTime;
    }
    public String getDimensions() {
        return dimensions;
    }
    public int getWeight() {
        return Weight;
    }
    public Parcel(String parcelID, int storageTime, String dimensions, int weight) {
        this.parcelID = parcelID;
        this.StorageTime = storageTime;
        this.dimensions = dimensions;
        this.Weight = weight;

    }

    @Override
    public String toString() {
            return "\nParcel ID" + parcelID + "\nStorage length: " + StorageTime + "\nParcel dimensions: " + dimensions + "\nWeight:  " + Weight;
    }
}
