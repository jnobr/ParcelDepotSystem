package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import model.*;

//TODO: Output to log file, comments and GUI

public class Manager {
    private ParcelMap AllParcels;
    private CustomerMap AllCustomers;
    private WorkerMap AllWorkers;
    private QueueOfCustomers Queue;
    private final Log log = new Log();
    private BufferedReader fileReader;

    public Manager(){
        initialize();
        //initialize_Queue();
    }

    public Customer getCustomer(String id){
        //returns an empty customer if no corresponding customer can be found for error handling purposes
        Customer customer = AllCustomers.findCustomer(id);
        if(customer == null){
            return new Customer("","","","");
        }
        return customer;
    }

    public Worker getWorker(String id){
        //returns an empty worker if no corresponding worker can be found for error handling purposes
        Worker worker = AllWorkers.findWorker(id);
        if(worker == null){
            return new Worker("","","","","");
        }
        return worker;
    }

    public Parcel getParcel(String id){
        //returns an empty parcel if no corresponding parcel can be found for error handling purposes
        Parcel parcel = AllParcels.findParcel(id);
        if(parcel == null){
            return new Parcel("",0,"",0);
        }
        return parcel;
    }

    public boolean validateCustomer(Customer customer) {
        if (customer != null) {
            boolean found = AllCustomers.findCustomer(customer.getCustomerID()) != null;
            if (!found) {
                log.append("Customer " + customer.getCustomerID() + " not found\n");
            }
            else {
                log.append("Customer " + customer.getCustomerID() + " found\n");
            }
            return found;
        } else {
            return false;
        }
    }

    public boolean validateParcel(Parcel parcel) {
        if (parcel != null) {
            boolean found =AllParcels.findParcel(parcel.getParcelID()) != null;
            if (!found) {
                log.append("Failed to find parcel " + parcel.getParcelID() + " in system\n");
            }
            else {
                log.append("Found parcel " + parcel.getParcelID() + " in system\n");
            }
            return found;
        } else {
            log.append("Failed to find parcel\n");
            return false;
        }

    }

    public boolean validateWorker(Worker worker) {
        if (worker != null) {
            boolean found = AllWorkers.findWorker(worker.getWorkerID()) != null;
            if (!found) {
                log.append("Failed to find worker " + worker.getWorkerID() + " in system\n");
            }
            else {
                log.append("Found worker " + worker.getWorkerID() + " in system\n");
            }
            return found;

        } else {
            log.append("Failed to find worker in system \n");
            return false;
        }
    }

    public boolean updateCustomerRecord(Customer customer) {
        if (customer == null) {
            log.append("Failed to add a new customer to the system \n");
            return false;
        }
        if(AllCustomers.findCustomer(customer.getCustomerID()) != null){
            Customer current = AllCustomers.findCustomer(customer.getCustomerID());
            current.setCustomerID(customer.getCustomerID());
            current.setFirstname(customer.getFirstname());
            current.setMiddlename(customer.getMiddlename());
            current.setLastname(customer.getLastname());
            log.append("Successfully updated customer " + customer.getCustomerID() + " \n");
            return true;
        }
        log.append("Successfully added a new customer (" + customer.getCustomerID() + ") to the system \n");
        return AllCustomers.addCustomer(customer);
    }

    public boolean updateParcelRecord(Parcel parcel) {
        if (parcel == null) {
            log.append("Failed to add a new parcel to the system \n");
            return false;
        }
        if(AllParcels.findParcel(parcel.getParcelID()) != null){
            Parcel current = AllParcels.findParcel(parcel.getParcelID());
            current.setParcelID(parcel.getParcelID());
            current.setDimensions(parcel.getDimensions());
            current.setWeight(parcel.getWeight());
            current.setStorageTime(parcel.getStorageTime());
            log.append("Successfully updated parcel " + parcel.getParcelID() + " \n");
            return true;

        }
        log.append("Successfully added a new parcel (" + parcel.getParcelID() + ") to the system \n");
        return AllParcels.addParcel(parcel);
    }

    public boolean updateWorkerRecord(Worker worker) {
        if (worker == null) {
            log.append("Failed to add a new worker to the system \n");
            return false;
        }
        if(AllWorkers.findWorker(worker.getWorkerID()) != null){
            Worker current = AllWorkers.findWorker(worker.getWorkerID());
            current.setWorkerID(worker.getWorkerID());
            current.setFirstname(worker.getFirstname());
            current.setMiddlename(worker.getMiddlename());
            current.setLastname(worker.getLastname());
            current.setRole(worker.getRole());
            log.append("Successfully updated worker " + worker.getWorkerID() + " \n");
            return true;
        }
        log.append("Successfully added a new worker (" + worker.getWorkerID() + ") to the system \n");
        return AllWorkers.addWorker(worker);
    }

    public ArrayList<String> returnAllCustomers(String type) {
        ArrayList<String> customers = new ArrayList<>();
        if(Objects.equals(type, "NAME")) {
            for (Customer customer : AllCustomers.getMap().values()) {
                customers.add(customer.getName());
            }
        }
        if(Objects.equals(type, "ID")) {
            for(Customer customer : AllCustomers.getMap().values()) {
                customers.add(customer.getCustomerID());
            }
        }
        return customers;
    }

    public ArrayList<String> returnAllParcels(String type) {
        ArrayList<String> parcels = new ArrayList<>();
        if(Objects.equals(type, "ID")) {
            for (Parcel parcel : AllParcels.getMap().values()) {
                parcels.add(parcel.getParcelID());
            }
        }
        if(Objects.equals(type, "TIME")) {
            for (Parcel parcel : AllParcels.getMap().values()) {
                parcels.add(String.valueOf(parcel.getStorageTime()));
            }
        }
        if(Objects.equals(type, "DIMENSIONS")) {
            for (Parcel parcel : AllParcels.getMap().values()) {
                parcels.add(String.valueOf(parcel.getDimensions()));
            }
        }
        if(Objects.equals(type, "WEIGHT")) {
            for (Parcel parcel : AllParcels.getMap().values()) {
                parcels.add(String.valueOf(parcel.getWeight()));
            }
        }
        return parcels;
    }

    public ArrayList<String> returnAllWorkers(String type) {
        ArrayList<String> workers = new ArrayList<>();
        if(Objects.equals(type, "NAME")) {
            for (Worker worker : AllWorkers.getMap().values()) {
                workers.add(worker.getName());
            }
        }
        if (Objects.equals(type, "ID")) {
            for(Worker worker : AllWorkers.getMap().values()) {
                workers.add(worker.getWorkerID());
            }
        }
        if (Objects.equals(type, "ROLE")) {
            for(Worker worker : AllWorkers.getMap().values()) {
                workers.add(worker.getRole());
            }
        }
        return workers;
    }

    public void initialize() {
        AllCustomers = new CustomerMap();
        AllParcels = new ParcelMap();
        AllWorkers = new WorkerMap();
        Queue = new QueueOfCustomers();
        initialize_Queue();
        initialise_Workers();
        log.append("System fully initialised\n");
    }

    public void initialize_Queue()
    {
        Integer count = 1;
        for(String id: AllCustomers.getMap().keySet())
        {
            System.out.println(id);
            Queue.addCustomer(id,count);
            count++;
        }

    }

    public void initialise_Workers()
    {
        Worker worker1 = new Worker("J.","M","Loop","001","Counter");
        Worker worker2 = new Worker("A.","B","Variable","002","Janitor");
        Worker worker3 = new Worker("K.","C","Field","003","Manager");
        Worker worker4 = new Worker("L.","D","Field","004","Manager");

        AllWorkers.addWorker(worker1);
        AllWorkers.addWorker(worker2);
        AllWorkers.addWorker(worker3);
        AllWorkers.addWorker(worker4);
    }

    public HashMap checkQueue()
    {
        return Queue.getCustomerQueue();
    }

    public double calculateFee(Parcel parcel, Worker worker) {
        //Rules for calculating cost
        // weight * days * (dimensions added together)
        //Discounts:
        //10% off for any ideas in the range of 500-999
        //20% off for parcel ids starting with "C","J","K" or "M"
        //5% off for any parcels stored for 1 day
        int dimensionsTotal;
        double cost;
        String[] individualDimensions = parcel.getDimensions().split("x");
        dimensionsTotal = Integer.parseInt(individualDimensions[0])
                + Integer.parseInt(individualDimensions[1])
                + Integer.parseInt(individualDimensions[2]);
        cost = dimensionsTotal * parcel.getStorageTime() * parcel.getWeight();
        double discount = checkForDiscount(parcel,worker);
        if(discount != 0) {
            cost = cost * discount;
        }

        return cost;


    }

    public double checkForDiscount(Parcel parcel, Worker worker) {
        if (validateWorker(worker)) {
        boolean condition1,condition2,condition3;
        double discount = 0;
        String substring = parcel.getParcelID().substring(1);
        int idNum = Integer.parseInt(substring);
        String idChar = parcel.getParcelID().substring(0, 1);
        condition1 = parcel.getStorageTime() == 1;
        condition2 = idNum >= 500 && idNum <= 999;
        condition3 = idChar.equals("C") || idChar.equals("J")
                || idChar.equals("K") || idChar.equals("M");

        if(condition1) {discount= discount + 0.05;}
        if(condition2) {discount= discount + 0.10;}
        if(condition3) {discount= discount + 0.20;}
        log.append("Worker " + worker.getWorkerID() + " checked to see if parcel "
                + parcel.getParcelID() + " is valid for a discount\n");
        log.append("Parcel " + parcel.getParcelID() + " is valid for a discount of " + discount + "%\n" );

        return discount; }
        log.append("Worker " + worker.getWorkerID() + " does not have authorisation to carry out this task\n");
        return -0;

    }

    public boolean collectOrder(Customer currentCustomer, Parcel currentParcel, Worker currentWorker) {
        boolean validCustomer = validateCustomer(currentCustomer);
        boolean validParcel = validateParcel(currentParcel);
        boolean validWorker = validateWorker(currentWorker);
        if(!validCustomer || !validParcel) {
            log.append("Order could not be collected as either the customer or parcel are invalid\n");
            return false;
        }
        boolean firstInQueue = Queue.checkIfFirstInQueue(currentCustomer);
        if(!validWorker) {
            log.append("Worker " + currentWorker.getWorkerID()
                    + " does not have authorisation to give customer their order\n");
            return false;
        }
        if(!firstInQueue){
            log.append("Customer " + currentCustomer.getCustomerID() + " is not first in the queue\n");
            System.out.println("The current queue is " + Queue.getCustomerQueue()
                    + "First in queue is " + Queue.getFirstInQueue() + "\n");
            return false;

        }

        double price = calculateFee(currentParcel,currentWorker);
        AllParcels.removeParcel(currentParcel.getParcelID());
        Queue.removeCustomer();
        log.append("Parcel " + currentParcel.getParcelID() + "has been collected and cost of "
                + price + " has been paid. Transaction completed by worker "
                + currentWorker.getWorkerID() + "\n");

        return true;
    }

    public void cancelOrder() {
        //TODO: remove customer from queue and remove parcel from parcel list
    }

    public String returnLog() {
        return log.getLog();
    }

    //Logic of this function only works if customer have 2 or 3 names in their full name
    //Function requires the file to be stored in src/data/
    public void readCustomersFromFile(String filename) throws IOException {
        fileReader = new BufferedReader(new FileReader("src/data/" + filename));
        String line = fileReader.readLine();
        Customer temp;
        while(line != null) {

            String[] parts = line.split(",");
            String customerID = parts[1];
            String[] names = parts[0].split(" ");
            if(names.length == 3) {
                String firstname = names[0];
                String middlename = names[1];
                String lastname = names[2];
                temp = new Customer(customerID, firstname, middlename, lastname);
            }
            else {
                String firstname = names[0];
                String lastname = names[1];
                temp = new Customer(customerID,firstname,"",lastname);
            }
            this.updateCustomerRecord(temp);
            line = fileReader.readLine();

        }

    }
//Function requires the file to be stored in src/data/
    public void readParcelsFromFile(String filename) throws IOException {
        fileReader = new BufferedReader(new FileReader("src/data/"+ filename));
        String line = fileReader.readLine();
        Parcel temp;
        //id days weight dimensions
        while(line != null) {
            String[] parts = line.split(",");
            String parcelID = parts[0];
            int days = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            String dimensions = parts[3] + "x" + parts[4] + "x" + parts[5];
            temp = new Parcel(parcelID,days,dimensions,weight);
            this.updateParcelRecord(temp);
            line = fileReader.readLine();

        }


    }

    public void writeToFile() {
        try {
            FileWriter file = new FileWriter("src/log.txt");
            BufferedWriter fileWriter = new BufferedWriter(file);

            fileWriter.write(log.getLog());
            fileWriter.close();
            System.out.println("Log written to log.txt\n");

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}