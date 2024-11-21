import java.util.ArrayList;
import java.util.HashMap;

public class CustomerMap {

    private HashMap<String,Customer> map = new HashMap<>();


    public HashMap<String, Customer> getMap() {
        return map;
    }

    public void initialiseMap(ArrayList<Customer> Customers) {
        for(Customer Customer : Customers) {
            this.map.put(Customer.getCustomerID(),Customer);
        }
    }

    public Customer findCustomer() {

        return map.get("123");
    }

    public boolean addCustomer(Customer Customer) {


        return false;

    }

    public boolean removeCustomer(Customer Customer) {
        return false;
    }
}
