package controller;

import java.util.HashMap;
import java.util.Objects;
import model.Customer;

public class QueueOfCustomers {

    private HashMap<Integer,String> customerQueue = new HashMap<>();
    private int currentNumber = 1;
    public QueueOfCustomers() {}
    public HashMap<Integer,String> getCustomerQueue() {
        return customerQueue;
    }

    public void addCustomer(String id, Integer num) {
        this.customerQueue.put(num,id);
    }

    public void removeCustomer() {
        this.customerQueue.remove(this.currentNumber);
        this.currentNumber++;

    }

    public boolean checkIfFirstInQueue(Customer customer) {
        String currentId = this.customerQueue.get(currentNumber);
        return Objects.equals(currentId, customer.getCustomerID());
    }


}
