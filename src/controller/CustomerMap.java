package controller;

import java.util.HashMap;
import model.Customer;

public class CustomerMap {

    private final HashMap<String, Customer> map = new HashMap<>();


    public HashMap<String, Customer> getMap() {
        return map;
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

    public boolean removeCustomer(String id) {
        if(this.map.containsKey(id)) {
            this.map.remove(id);
            return true;
        }
        return false;
    }
}
