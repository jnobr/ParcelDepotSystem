package view;

import controller.*;
import model.*;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        //Testing class instantiation
        Manager SystemManager = new Manager();
        Customer customer =
                new Customer("C123","J","","Q");

        Parcel parcel =
                new Parcel("C123",1,"2x2x2",60);

        Worker worker =
                new Worker("M","","Q","008","Manager");

        //checking calculateFee method
        var res = SystemManager.calculateFee(parcel,worker);
        System.out.println(res);

        //Testing toString() methods
        System.out.println(customer);
        System.out.println(parcel);
        System.out.println(worker);

        SystemManager.initialize();


        //Checking validate methods before and after objects exist in collections
        //Also checking the response with null values
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

        //Checking file reading methods
        SystemManager.readCustomersFromFile("Custs.csv");
        SystemManager.readParcelsFromFile("Parcels.csv");
        //Checking queue initialisation
        SystemManager.initialize_Queue();

        //Checking to see if Queue works
        Customer real_customer = SystemManager.getCustomer("X009");
        Worker real_worker = SystemManager.getWorker("003");
        Parcel real_parcel = SystemManager.getParcel("X009");
        System.out.println(SystemManager.checkQueue());

        //Checking log updates and returns data
        System.out.println(SystemManager.returnLog());

        //Checking collections return data
        System.out.println(SystemManager.returnAllWorkers("ROLE"));

        //Checking collect order function works as expected
        SystemManager.collectOrder(real_customer,real_parcel,real_worker);

        //Checking that log will output to log.txt
        SystemManager.writeToFile();





    }
}
