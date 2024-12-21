package controller;

import java.util.HashMap;
import java.util.Objects;
import model.Customer;

public class QueueOfCustomers {

    private final HashMap<Integer,String> customerQueue = new HashMap<>();
    private int currentNumber = 1;
    public QueueOfCustomers() {}
    public HashMap<Integer,String> getCustomerQueue() {
        return this.customerQueue;
    }

    public void addCustomer(String id, Integer num) {
        this.customerQueue.put(num,id);
    }

    public void removeCustomer() {
        this.customerQueue.remove(this.currentNumber);
        this.currentNumber++;

    }

    public String getFirstInQueue() {
        return this.customerQueue.get(this.currentNumber);
    }

    public boolean checkIfFirstInQueue(Customer customer) {
        String currentId = this.customerQueue.get(currentNumber);
        return Objects.equals(currentId, customer.getCustomerID());
    }


}
