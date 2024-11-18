public class Customer extends Name {

    private String email;
    private String phone;
    private String customerID;

    public Customer(String email, String phone, String customerID, String firstname, String middlename, String surname) {
        super(firstname,middlename,surname);
        this.email = email;
        this.phone = phone;
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String toString() {
        String firstname = getFirstname();
        String middlename= getMiddlename();
        String surname = getLastname();

        return firstname + " " + middlename + " " + surname + " " + this.email + " " + this.phone
                + " " + this.customerID;
    }
}
