import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        Manager SystemManager = new Manager();
        Customer customer =
                new Customer("123","J","","Q");

        Parcel parcel =
                new Parcel("ABC",1,"2x2x2",60);

        Worker worker =
                new Worker("M","","Q","LKJ","Manager");


        System.out.println(customer.toString());
        System.out.println(parcel.toString());
        System.out.println(worker.toString());



        SystemManager.initialize();

        SystemManager.validateCustomer(customer);
        SystemManager.validateParcel(parcel);
        SystemManager.validateWorker(worker);

        SystemManager.updateCustomerRecord(customer);
        SystemManager.updateCustomerRecord(null);
        SystemManager.updateParcelRecord(parcel);
        SystemManager.updateParcelRecord(null);
        SystemManager.updateWorkerRecord(worker);
        SystemManager.updateWorkerRecord(null);

        SystemManager.validateCustomer(customer);
        SystemManager.validateParcel(parcel);
        SystemManager.validateWorker(worker);
        SystemManager.validateCustomer(null);
        SystemManager.validateParcel(null);
        SystemManager.validateWorker(null);

        SystemManager.readCustomersFromFile();
        SystemManager.readParcelsFromFile();


        System.out.println(SystemManager.returnLog());




    }
}
