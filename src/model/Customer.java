package model;

public class Customer extends Name {

    private String customerID;

    public Customer(String customerID, String firstname, String middlename, String surname) {
        super(firstname,middlename,surname);
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String toString() {
        String name = this.getName();
        return "\nName: " + name + "\nID: " + this.customerID;
    }
}
