import java.util.ArrayList;

public class QueueOfCustomers {

    private ArrayList<Customer> customers;
    public QueueOfCustomers() {

    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public boolean addCustomer(Customer customer) {
        return this.customers.add(customer);
    }

    public boolean removeCustomer(Customer customer) {
        return this.customers.remove(customer);
    }



}
