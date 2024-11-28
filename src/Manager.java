public class Manager {
    private ParcelMap AllParcels;
    private CustomerMap AllCustomers;
    private WorkerMap AllWorkers;
    private final Log log = new Log();


    public boolean validateCustomer(Customer customer) {
        if (customer == null) {
            return false;
        } else {
            return AllCustomers.findCustomer(customer.getCustomerID()) != null;
        }
    }
    public boolean validateParcel(Parcel parcel) {
        if (parcel == null) {
            return false;
        }
        else {
            return AllParcels.findParcel(parcel.getParcelID()) != null;
        }

    }
    public boolean validateWorker(Worker worker) {
        if (worker == null) {
            return false;
        }
        else {
            return AllWorkers.findWorker(worker.getWorkerID()) != null;
        }
    }

    public boolean updateCustomerRecord(Customer customer) {
        AllCustomers.removeCustomer(customer.getCustomerID());
        AllCustomers.addCustomer(customer);
        return true;
    }
    public boolean updateParcelRecord(Parcel parcel) {
        return true;
    }
    public boolean updateWorkerRecord(Worker worker) {
        return true;
    }

    public void initialize() {}

    public void calculateCost() {

    }

    public void collectOrder() {}

    public void cancelOrder() {}
}
