import java.util.ArrayList;
import java.util.HashMap;

public class CustomerMap {

    private final HashMap<String,Customer> map = new HashMap<>();


    public HashMap<String, Customer> getMap() {
        return map;
    }

    public void initialiseMap(ArrayList<Customer> Customers) {
        for(Customer Customer : Customers) {
            this.map.put(Customer.getCustomerID(),Customer);
        }
    }

    public Customer findCustomer(String id) {
        Customer Customer = map.get(id);
        if (Customer == null) {
            return null;
        }
        return map.get(id);
    }

    public boolean addCustomer(Customer Customer) {

        if(this.map.containsKey(Customer.getCustomerID())) {
            return false;

        }
            this.map.put(Customer.getCustomerID(),Customer);
            return true;

    }

    public boolean removeCustomer(Customer Customer) {
        if(this.map.containsKey(Customer.getCustomerID())) {
            this.map.remove(Customer.getCustomerID());
            return true;
        }
        return false;
    }
}
