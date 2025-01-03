package view;

import controller.*;
import model.Customer;
import model.Parcel;
import model.Worker;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.*;

public class GUI {
    private JList CustomerList,ParcelList,WorkerList,Queue;
    private final Manager SystemManager = new Manager();

    public GUI() throws IOException {
        SystemManager.readCustomersFromFile("Custs.csv");
        SystemManager.readParcelsFromFile("Parcels.csv");
        SystemManager.initialize_Queue();

        //Filling the global lists with up-to-date data
        refreshModelLists();

        JFrame window = new JFrame("Parcel Depot");
        window.setBounds(100, 100, 600, 700);
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCentre = new JPanel();

        panelNorth.setLayout(new GridLayout(1,3));
        panelCentre.setLayout(new GridLayout(1,4));
        panelSouth.setLayout(new GridLayout(2,3));

        JLabel CustomerLabel = new JLabel("Customer");
        JLabel ParcelLabel = new JLabel("Parcel");
        JLabel WorkerLabel = new JLabel("Worker");
        JLabel QueueLabel = new JLabel("Queue");

        JButton logButton = new JButton("Open Log");
        JButton saveButton = new JButton("Save Log");

        panelNorth.add(CustomerLabel);
        panelNorth.add(ParcelLabel);
        panelNorth.add(WorkerLabel);
        panelNorth.add(QueueLabel);
        panelCentre.add(CustomerList);
        panelCentre.add(ParcelList);
        panelCentre.add(WorkerList);
        panelCentre.add(Queue);

        JButton parcelButton = new JButton("View Parcels in detail");
        JButton workerButton = new JButton("View Workers in detail");
        JButton customerButton = new JButton("View Customers in detail");
        JButton collectButton = new JButton("Collect Order");

        JPanel panelWest = new JPanel();
        panelWest.setLayout(new GridLayout(4, 1));
        panelSouth.add(parcelButton);
        panelSouth.add(workerButton);
        panelSouth.add(customerButton);
        panelSouth.add(collectButton);
        panelSouth.add(saveButton);
        panelSouth.add(logButton);

        logButton.addActionListener(_ -> showLog());
        workerButton.addActionListener(_ -> detailedWorkerScreen());
        parcelButton.addActionListener(_ -> detailedParcelScreen());
        customerButton.addActionListener(_ -> detailedCustomerScreen());
        saveButton.addActionListener(_-> SystemManager.writeToFile());
        collectButton.addActionListener(_-> collectOrder());

        window.getContentPane().add(panelWest,BorderLayout.WEST);
        window.getContentPane().add(panelNorth, BorderLayout.NORTH);
        window.getContentPane().add(panelSouth, BorderLayout.SOUTH);
        window.getContentPane().add(panelCentre, BorderLayout.CENTER);
        window.setVisible(true);

        //Using a timer object to refresh the main screen every
        //second to reflect changes to data
        Timer mainTimer = new Timer(100, _ -> {
            refreshModelLists();
            panelCentre.removeAll();
            panelCentre.repaint();
            panelCentre.revalidate();
            panelCentre.add(CustomerList);
            panelCentre.add(ParcelList);
            panelCentre.add(WorkerList);
            panelCentre.add(Queue);
        });
        mainTimer.start();


    }

    public void refreshModelLists() {
        ArrayList<String> customers = SystemManager.returnAllCustomers("NAME");
        ArrayList<String> parcels = SystemManager.returnAllParcels("ID");
        ArrayList<String> workers = SystemManager.returnAllWorkers("NAME");
        Collection queueOfCustomers = SystemManager.checkQueue().values();
        CustomerList = new JList(customers.toArray());
        ParcelList = new JList(parcels.toArray());
        WorkerList = new JList(workers.toArray());
        Queue = new JList(queueOfCustomers.toArray());
    }

    public void detailedParcelScreen() {
        refreshModelLists();

        JDialog ParcelScreen = new JDialog();
        JPanel ButtonPanel = new JPanel();
        JButton addParcel = new JButton("Add Parcel");
        JButton removeParcel = new JButton("Remove Parcel");
        JButton editParcel = new JButton("Edit Parcel");
        JPanel ParcelPanel = new JPanel();
        JPanel LabelPanel = new JPanel();

        ButtonPanel.setLayout(new GridLayout(1, 3));
        ParcelPanel.setLayout(new GridLayout(1,5));
        LabelPanel.setLayout(new GridLayout(1,5));
        ParcelScreen.setBounds(100,100,500,700);

        ArrayList<String> ids = SystemManager.returnAllParcels("ID");
        ArrayList<String> dimensions = SystemManager.returnAllParcels("DIMENSIONS");
        ArrayList<String> time = SystemManager.returnAllParcels("TIME");
        ArrayList<String> weight = SystemManager.returnAllParcels("WEIGHT");

        JList idList = new JList(ids.toArray());
        JList dimensionList = new JList(dimensions.toArray());
        JList timeList = new JList(time.toArray());
        JList weightList = new JList(weight.toArray());

        JLabel parcelLabel = new JLabel("Parcel");
        JLabel idLabel = new JLabel("Id");
        JLabel dimensionLabel = new JLabel("Dimension");
        JLabel timeLabel = new JLabel("Time");
        JLabel weightLabel = new JLabel("Weight");

        ParcelPanel.add(ParcelList);
        ParcelPanel.add(idList);
        ParcelPanel.add(dimensionList);
        ParcelPanel.add(timeList);
        ParcelPanel.add(weightList);

        ButtonPanel.add(addParcel);
        ButtonPanel.add(removeParcel);
        ButtonPanel.add(editParcel);

        LabelPanel.add(parcelLabel);
        LabelPanel.add(idLabel);
        LabelPanel.add(dimensionLabel);
        LabelPanel.add(timeLabel);
        LabelPanel.add(weightLabel);

        ParcelScreen.getContentPane().add(ButtonPanel, BorderLayout.SOUTH);
        ParcelScreen.getContentPane().add(ParcelPanel, BorderLayout.CENTER);
        ParcelScreen.getContentPane().add(LabelPanel, BorderLayout.NORTH);

        ParcelScreen.setVisible(true);

        editParcel.addActionListener(_ -> editParcelScreen(ParcelScreen));
        addParcel.addActionListener(_->addParcelScreen(ParcelScreen));
        removeParcel.addActionListener(_->removeParcelScreen(ParcelScreen));


    }

    public void detailedCustomerScreen() {
        refreshModelLists();

        JDialog CustomerScreen = new JDialog();
        JPanel ButtonPanel = new JPanel();
        JButton addCustomer = new JButton("Add Customer");
        JButton removeCustomer = new JButton("Remove Customer");
        JButton editCustomer = new JButton("Edit Customer");
        JPanel CustomerPanel = new JPanel();
        JPanel LabelPanel = new JPanel();

        ButtonPanel.setLayout(new GridLayout(1, 3));
        CustomerPanel.setLayout(new GridLayout(1,2));
        LabelPanel.setLayout(new GridLayout(1,2));
        CustomerScreen.setBounds(100,100,500,700);

        ArrayList<String> ids = SystemManager.returnAllCustomers("ID");
        JList idList = new JList(ids.toArray());

        JLabel customerLabel = new JLabel("Customer");
        JLabel idLabel = new JLabel("Id");

        CustomerPanel.add(CustomerList);
        CustomerPanel.add(idList);

        LabelPanel.add(customerLabel);
        LabelPanel.add(idLabel);

        CustomerScreen.getContentPane().add(ButtonPanel, BorderLayout.SOUTH);
        CustomerScreen.getContentPane().add(CustomerPanel, BorderLayout.CENTER);
        CustomerScreen.getContentPane().add(LabelPanel, BorderLayout.NORTH);

        ButtonPanel.add(addCustomer);
        ButtonPanel.add(removeCustomer);
        ButtonPanel.add(editCustomer);

        CustomerScreen.setVisible(true);

        editCustomer.addActionListener(_-> editCustomerScreen(CustomerScreen));
        addCustomer.addActionListener(_-> addCustomerScreen(CustomerScreen));
        removeCustomer.addActionListener(_->removeCustomerScreen(CustomerScreen));

    }

    public void detailedWorkerScreen(){
        refreshModelLists();

        JDialog WorkerScreen = new JDialog();
        JPanel ButtonPanel = new JPanel();
        JButton addWorker = new JButton("Add Worker");
        JButton removeWorker = new JButton("Remove Worker");
        JButton editWorker = new JButton("Edit Worker");
        JPanel WorkersPanel = new JPanel();
        JPanel LabelPanel = new JPanel();

        WorkersPanel.setLayout(new GridLayout(1,3));
        LabelPanel.setLayout(new GridLayout(1,3));
        ButtonPanel.setLayout(new GridLayout(1,3));
        WorkerScreen.setBounds(100,100,500,700);

        ArrayList<String> ids = SystemManager.returnAllWorkers("ID");
        ArrayList<String> roles = SystemManager.returnAllWorkers("ROLE");
        JList IDList = new JList(ids.toArray());
        JList RoleList = new JList(roles.toArray());

        JLabel workerLabel = new JLabel("Name");
        JLabel idLabel = new JLabel("Id");
        JLabel roleLabel = new JLabel("Role");

        WorkersPanel.add(WorkerList);
        WorkersPanel.add(IDList);
        WorkersPanel.add(RoleList);

        LabelPanel.add(workerLabel);
        LabelPanel.add(idLabel);
        LabelPanel.add(roleLabel);


        WorkerScreen.getContentPane().add(WorkersPanel,BorderLayout.CENTER);
        WorkerScreen.getContentPane().add(ButtonPanel, BorderLayout.SOUTH);
        WorkerScreen.getContentPane().add(LabelPanel, BorderLayout.NORTH);

        ButtonPanel.add(addWorker);
        ButtonPanel.add(removeWorker);
        ButtonPanel.add(editWorker);

        WorkerScreen.setVisible(true);

        editWorker.addActionListener(_-> editWorkerScreen(WorkerScreen));
        addWorker.addActionListener(_-> addWorkerScreen(WorkerScreen));
        removeWorker.addActionListener(_-> removeWorkerScreen(WorkerScreen));
    }

    public void showLog() {
        refreshModelLists();

        JDialog LogWindow = new JDialog();
        JPanel LogPanel = new JPanel();
        JTextArea LogTextArea = new JTextArea();
        String log = SystemManager.returnLog();
        LogTextArea.setText(log);
        LogWindow.setBounds(100, 100, 500, 700);
        LogWindow.setVisible(true);
        LogWindow.setLocationRelativeTo(null);
        LogPanel.add(LogTextArea);
        LogWindow.add(LogPanel);
        JScrollPane scroll = new JScrollPane (LogTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        LogWindow.add(scroll);
    }

    public void collectOrder() {
        refreshModelLists();

        JDialog OrderWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        JTextField CustomerTextField = new JTextField(20);
        JTextField WorkerTextField = new JTextField(20);
        JTextField ParcelTextField = new JTextField(20);

        JLabel CustomerLabel = new JLabel("Customer ID");
        JLabel WorkerLabel = new JLabel("Worker ID");
        JLabel ParcelLabel = new JLabel("Parcel ID");
        JButton submitButton = new JButton("Submit all IDs");

        LabelPanel.setLayout(new GridLayout(3,1));

        LabelPanel.add(CustomerLabel);
        LabelPanel.add(WorkerLabel);
        LabelPanel.add(ParcelLabel);

        TextPanel.add(CustomerTextField);
        TextPanel.add(WorkerTextField);
        TextPanel.add(ParcelTextField);

        TextPanel.setLayout(new GridLayout(3,0));
        ButtonPanel.add(submitButton);

        OrderWindow.setBounds(100, 100, 500, 400);
        OrderWindow.setVisible(true);
        OrderWindow.setLocationRelativeTo(null);
        OrderWindow.getContentPane().add(TextPanel, BorderLayout.EAST);
        OrderWindow.getContentPane().add(LabelPanel, BorderLayout.CENTER);
        OrderWindow.getContentPane().add(ButtonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(_ -> {
            String customerID = CustomerTextField.getText();
            String workerID = WorkerTextField.getText();
            String parcelID = ParcelTextField.getText();

            Customer current_customer = SystemManager.getCustomer(customerID);
            Worker current_worker = SystemManager.getWorker(workerID);
            Parcel current_parcel = SystemManager.getParcel(parcelID);

            boolean result = SystemManager.collectOrder(current_customer,current_parcel,current_worker);

            if(!result) {
                System.out.println("Order not collected, check the log for more detail");
            }
            else {
                System.out.println("Order collected");
            }
        });

    }

    public void editParcelScreen(JDialog parent){
        refreshModelLists();

        JDialog ParcelScreen = new JDialog();
        JPanel ButtonPanel = new JPanel();
        JPanel LabelPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        ParcelScreen.setBounds(100, 100, 500, 400);
        ParcelScreen.setVisible(true);
        ParcelScreen.setLocationRelativeTo(null);

        LabelPanel.setLayout(new GridLayout(4,0));
        ButtonPanel.setLayout(new GridLayout(4,0));
        TextPanel.setLayout(new GridLayout(4,0));

        JLabel InfoLabel = new JLabel("ID field must be filled in to update other fields");
        JLabel IDLabel = new JLabel("Parcel ID");
        JLabel TimeLabel = new JLabel("Storage Time");
        JLabel WeightLabel = new JLabel("Weight");
        JLabel DimensionLabel = new JLabel("Dimensions");

        JButton IDButton = new JButton("Update ID");
        JButton TimeButton = new JButton("Update Time");
        JButton WeightButton = new JButton("Update Weight");
        JButton DimensionButton = new JButton("Update Dimensions");

        JTextField IDTextField = new JTextField(20);
        JTextField TimeTextField = new JTextField(20);
        JTextField WeightTextField = new JTextField(20);
        JTextField DimensionTextField = new JTextField(20);

        LabelPanel.add(IDLabel);
        LabelPanel.add(TimeLabel);
        LabelPanel.add(WeightLabel);
        LabelPanel.add(DimensionLabel);

        ButtonPanel.add(IDButton);
        ButtonPanel.add(TimeButton);
        ButtonPanel.add(WeightButton);
        ButtonPanel.add(DimensionButton);

        TextPanel.add(IDTextField);
        TextPanel.add(TimeTextField);
        TextPanel.add(WeightTextField);
        TextPanel.add(DimensionTextField);

        ParcelScreen.getContentPane().add(InfoLabel,BorderLayout.NORTH);
        ParcelScreen.getContentPane().add(LabelPanel, BorderLayout.WEST);
        ParcelScreen.getContentPane().add(TextPanel, BorderLayout.CENTER);
        ParcelScreen.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        IDButton.addActionListener(_ -> {
           String id = IDTextField.getText();
           Parcel parcel = SystemManager.getParcel(id);
           boolean result = SystemManager.validateParcel(parcel);
           if(!result) {
               System.out.println("Parcel not valid");
           }
           else {
               parcel.setParcelID(id);
               result = SystemManager.validateParcel(parcel);
               if(!result) {
                   System.out.println("Parcel not added");
               }
           }
           parent.dispose();
           ParcelScreen.dispose();
        });

        TimeButton.addActionListener(_ -> {
           String time = TimeTextField.getText();
           String id = IDTextField.getText();
           Parcel parcel = SystemManager.getParcel(id);
           boolean result = SystemManager.validateParcel(parcel);
           if(!result) {
               System.out.println("Parcel not valid");
           }
           else {
               parcel.setStorageTime(Integer.parseInt(time));
               result = SystemManager.updateParcelRecord(parcel);
               if(!result) {
                   System.out.println("Parcel not updated");
               }
           }
            parent.dispose();
            ParcelScreen.dispose();
        });

        WeightButton.addActionListener(_ -> {
            String weight = WeightTextField.getText();
            String id = IDTextField.getText();
            Parcel parcel = SystemManager.getParcel(id);
            boolean result = SystemManager.validateParcel(parcel);
            if(!result) {
                System.out.println("Parcel not valid");
            }
            else {
                parcel.setWeight(Integer.parseInt(weight));
                result = SystemManager.updateParcelRecord(parcel);
                if(!result) {
                    System.out.println("Parcel not updated");
                }
            }
            parent.dispose();
            ParcelScreen.dispose();
        });

        DimensionButton.addActionListener(_ -> {
            String dimension = DimensionTextField.getText();
            String id = IDTextField.getText();
            Parcel parcel = SystemManager.getParcel(id);
            boolean result = SystemManager.validateParcel(parcel);
            if(!result) {
                System.out.println("Parcel not valid");
            }
            else {
                parcel.setDimensions(dimension);
                result = SystemManager.updateParcelRecord(parcel);
                if(!result) {
                    System.out.println("Parcel not updated");
                }
            }
            parent.dispose();
            ParcelScreen.dispose();
        });

    }

    public void editWorkerScreen(JDialog parent){
        refreshModelLists();

        JDialog WorkersWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        WorkersWindow.setBounds(100, 100, 500, 400);
        WorkersWindow.setVisible(true);
        WorkersWindow.setLocationRelativeTo(null);

        LabelPanel.setLayout(new GridLayout(5,0));
        ButtonPanel.setLayout(new GridLayout(5,0));
        TextPanel.setLayout(new GridLayout(5,0));

        JLabel InfoLabel = new JLabel("Worker ID must be filled in to update other fields");
        JLabel FirstNameLabel = new JLabel("First Name");
        JLabel MiddleNameLabel = new JLabel("Middle Name");
        JLabel LastNameLabel = new JLabel("Last Name");
        JLabel IDLabel = new JLabel("ID");
        JLabel RoleLabel = new JLabel("Role");

        JButton FirstNameButton = new JButton("Update First Name");
        JButton MiddleNameButton = new JButton("Update Middle Name");
        JButton LastNameButton = new JButton("Update Last Name");
        JButton IDButton = new JButton("Update ID");
        JButton RoleButton = new JButton("Update Role");

        JTextField FirstNameTextField = new JTextField(20);
        JTextField MiddleNameTextField = new JTextField(20);
        JTextField LastNameTextField = new JTextField(20);
        JTextField IDTextField = new JTextField(20);
        JTextField RoleTextField = new JTextField(20);

        LabelPanel.add(FirstNameLabel);
        LabelPanel.add(MiddleNameLabel);
        LabelPanel.add(LastNameLabel);
        LabelPanel.add(IDLabel);
        LabelPanel.add(RoleLabel);

        ButtonPanel.add(FirstNameButton);
        ButtonPanel.add(MiddleNameButton);
        ButtonPanel.add(LastNameButton);
        ButtonPanel.add(IDButton);
        ButtonPanel.add(RoleButton);

        TextPanel.add(FirstNameTextField);
        TextPanel.add(MiddleNameTextField);
        TextPanel.add(LastNameTextField);
        TextPanel.add(IDTextField);
        TextPanel.add(RoleTextField);

        WorkersWindow.getContentPane().add(InfoLabel, BorderLayout.NORTH);
        WorkersWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        WorkersWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        WorkersWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        FirstNameButton.addActionListener(_ -> {
           String firstName = FirstNameTextField.getText();
           String id = IDTextField.getText();
           Worker current_worker = SystemManager.getWorker(id);
           boolean result = SystemManager.validateWorker(current_worker);
           if(!result) {
               System.out.println("Worker not valid");
           }
           else {
               current_worker.setFirstname(firstName);
               result = SystemManager.updateWorkerRecord(current_worker);
               if(!result) {
                   System.out.println("Worker not updated");
               }
           }
           parent.dispose();
           WorkersWindow.dispose();
        });

        MiddleNameButton.addActionListener(_ -> {
           String middleName = MiddleNameTextField.getText();
           String id = IDTextField.getText();
           Worker current_worker = SystemManager.getWorker(id);
           boolean result = SystemManager.validateWorker(current_worker);
           if(!result) {
               System.out.println("Worker not valid");
           }
           else {
               current_worker.setMiddlename(middleName);
               result = SystemManager.updateWorkerRecord(current_worker);
               if(!result) {
                   System.out.println("Worker not updated");
               }
           }
            parent.dispose();
            WorkersWindow.dispose();
        });

        LastNameButton.addActionListener(_ -> {
            String lastName = LastNameTextField.getText();
            String id = IDTextField.getText();
            Worker current_worker = SystemManager.getWorker(id);
            boolean result = SystemManager.validateWorker(current_worker);
            if(!result) {
                System.out.println("Worker not valid");
            }
            else {
                current_worker.setLastname(lastName);
                result = SystemManager.updateWorkerRecord(current_worker);
                if(!result) {
                    System.out.println("Worker not updated");
                }
            }
            parent.dispose();
            WorkersWindow.dispose();
        });

        IDButton.addActionListener(_ -> {
            String id = IDTextField.getText();
            Worker current_worker = SystemManager.getWorker(id);
            boolean result = SystemManager.validateWorker(current_worker);
            if(!result) {
                System.out.println("Worker not valid");
            }
            else {
                current_worker.setWorkerID(id);
                result = SystemManager.updateWorkerRecord(current_worker);
                if(!result) {
                    System.out.println("Worker not added");
                }
            }
            parent.dispose();
            WorkersWindow.dispose();
        });

        RoleButton.addActionListener(_ -> {
           String role = RoleTextField.getText();
           String id = IDTextField.getText();
           Worker current_worker = SystemManager.getWorker(id);
           boolean result = SystemManager.validateWorker(current_worker);
           if(!result) {
               System.out.println("Worker not valid");
           }
           else {
               current_worker.setRole(role);
               result = SystemManager.updateWorkerRecord(current_worker);
               if(!result) {
                   System.out.println("Worker not updated");
               }
           }
            parent.dispose();
            WorkersWindow.dispose();
        });

    }

    public void editCustomerScreen(JDialog parent){
        refreshModelLists();

        JDialog CustomerWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        CustomerWindow.setBounds(100, 100, 500, 400);
        CustomerWindow.setVisible(true);
        CustomerWindow.setLocationRelativeTo(null);

        LabelPanel.setLayout(new GridLayout(4,0));
        ButtonPanel.setLayout(new GridLayout(4,0));
        TextPanel.setLayout(new GridLayout(4,0));

        JLabel InfoLabel = new JLabel("Customer ID field must be filled in to update any other fields");
        JLabel FirstNameLabel = new JLabel("First Name");
        JLabel MiddleNameLabel = new JLabel("Middle Name");
        JLabel LastNameLabel = new JLabel("Last Name");
        JLabel IDLabel = new JLabel("ID");

        JButton FirstNameButton = new JButton("Update First Name");
        JButton MiddleNameButton = new JButton("Update Middle Name");
        JButton LastNameButton = new JButton("Update Last Name");
        JButton IDButton = new JButton("Update ID");

        JTextField FirstNameTextField = new JTextField(20);
        JTextField MiddleNameTextField = new JTextField(20);
        JTextField LastNameTextField = new JTextField(20);
        JTextField IDTextField = new JTextField(20);

        ButtonPanel.add(FirstNameButton);
        ButtonPanel.add(MiddleNameButton);
        ButtonPanel.add(LastNameButton);
        ButtonPanel.add(IDButton);

        LabelPanel.add(FirstNameLabel);
        LabelPanel.add(MiddleNameLabel);
        LabelPanel.add(LastNameLabel);
        LabelPanel.add(IDLabel);

        TextPanel.add(FirstNameTextField);
        TextPanel.add(MiddleNameTextField);
        TextPanel.add(LastNameTextField);
        TextPanel.add(IDTextField);

        CustomerWindow.getContentPane().add(InfoLabel, BorderLayout.NORTH);
        CustomerWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        CustomerWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        CustomerWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        FirstNameButton.addActionListener(_ -> {
            String name = FirstNameTextField.getText();
            String id = IDTextField.getText();
            Customer customer = SystemManager.getCustomer(id);
            boolean result = SystemManager.validateCustomer(customer);
            if(!result) {
                System.out.println("Customer not valid");
            }
            else {
                customer.setFirstname(name);
                result = SystemManager.updateCustomerRecord(customer);
                if(!result) {
                    System.out.println("Customer not updated");
                }
            }
            parent.dispose();
            CustomerWindow.dispose();
        });

        MiddleNameButton.addActionListener(_ -> {
            String name = MiddleNameTextField.getText();
            String id = IDTextField.getText();
            Customer customer = SystemManager.getCustomer(id);
            boolean result = SystemManager.validateCustomer(customer);
            if(!result) {
                System.out.println("Customer not valid");
            }
            else {
                customer.setMiddlename(name);
                result = SystemManager.updateCustomerRecord(customer);
                if(!result) {
                    System.out.println("Customer not updated");
                }
            }
            parent.dispose();
            CustomerWindow.dispose();
        });

        LastNameButton.addActionListener(_ -> {
            String name = LastNameTextField.getText();
            String id = IDTextField.getText();
            Customer customer = SystemManager.getCustomer(id);
            boolean result = SystemManager.validateCustomer(customer);
            if(!result) {
                System.out.println("Customer not valid");
            }
            else {
                customer.setLastname(name);
                result = SystemManager.updateCustomerRecord(customer);
                if(!result) {
                    System.out.println("Customer not updated");
                }
            }
            parent.dispose();
            CustomerWindow.dispose();
        });

        IDButton.addActionListener(_ -> {
            String id = IDTextField.getText();
            Customer customer = SystemManager.getCustomer(id);
            boolean result = SystemManager.validateCustomer(customer);
            if(!result) {
                System.out.println("Customer not valid");
            }
            else {
                customer.setCustomerID(id);
                result = SystemManager.updateCustomerRecord(customer);
                if(!result) {
                    System.out.println("Customer not added");
                }

            }
            parent.dispose();
            CustomerWindow.dispose();
        });

    }

    public void addCustomerScreen(JDialog parent) {
        refreshModelLists();

        JDialog CustomerWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();
        CustomerWindow.setBounds(100, 100, 500, 400);
        CustomerWindow.setVisible(true);
        CustomerWindow.setLocationRelativeTo(null);
        LabelPanel.setLayout(new GridLayout(4,0));
        TextPanel.setLayout(new GridLayout(4,0));

        JLabel IdLabel = new JLabel("ID");
        JLabel FirstNameLabel = new JLabel("First Name");
        JLabel MiddleNameLabel = new JLabel("Middle Name");
        JLabel LastNameLabel = new JLabel("Last Name");

        JTextField IdTextField = new JTextField(20);
        JTextField FirstNameTextField = new JTextField(20);
        JTextField MiddleNameTextField = new JTextField(20);
        JTextField LastNameTextField = new JTextField(20);

        LabelPanel.add(IdLabel);
        LabelPanel.add(FirstNameLabel);
        LabelPanel.add(MiddleNameLabel);
        LabelPanel.add(LastNameLabel);

        TextPanel.add(IdTextField);
        TextPanel.add(FirstNameTextField);
        TextPanel.add(MiddleNameTextField);
        TextPanel.add(LastNameTextField);

        JButton submitButton = new JButton("Add Customer");

        ButtonPanel.add(submitButton);

        CustomerWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        CustomerWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        CustomerWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        submitButton.addActionListener(_->{
            String id = IdTextField.getText();
            String firstname = FirstNameTextField.getText();
            String middlename = MiddleNameTextField.getText();
            String lastname = LastNameTextField.getText();
            Customer customer = new Customer(id, firstname, middlename, lastname);
            boolean result = SystemManager.updateCustomerRecord(customer);
            if(!result) {
                System.out.println("Customer not added");
            }
            parent.dispose();
            CustomerWindow.dispose();
        });

    }

    public void addWorkerScreen(JDialog parent) {
        refreshModelLists();

        JDialog WorkersWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();
        WorkersWindow.setBounds(100, 100, 500, 400);
        WorkersWindow.setVisible(true);
        WorkersWindow.setLocationRelativeTo(null);
        LabelPanel.setLayout(new GridLayout(5,0));
        TextPanel.setLayout(new GridLayout(5,0));

        JLabel IdLabel = new JLabel("ID");
        JLabel FirstNameLabel = new JLabel("First Name");
        JLabel MiddleNameLabel = new JLabel("Middle Name");
        JLabel LastNameLabel = new JLabel("Last Name");
        JLabel RoleLabel = new JLabel("Role");

        JTextField IdTextField = new JTextField(20);
        JTextField FirstNameTextField = new JTextField(20);
        JTextField MiddleNameTextField = new JTextField(20);
        JTextField LastNameTextField = new JTextField(20);
        JTextField RoleTextField = new JTextField(20);

        LabelPanel.add(IdLabel);
        LabelPanel.add(FirstNameLabel);
        LabelPanel.add(MiddleNameLabel);
        LabelPanel.add(LastNameLabel);
        LabelPanel.add(RoleLabel);

        TextPanel.add(IdTextField);
        TextPanel.add(FirstNameTextField);
        TextPanel.add(MiddleNameTextField);
        TextPanel.add(LastNameTextField);
        TextPanel.add(RoleTextField);

        JButton addWorkerButton = new JButton("Add Worker");
        ButtonPanel.add(addWorkerButton);

        WorkersWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        WorkersWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        WorkersWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        addWorkerButton.addActionListener(_->{
           String id = IdTextField.getText();
           String firstname = FirstNameTextField.getText();
           String middlename = MiddleNameTextField.getText();
           String lastname = LastNameTextField.getText();
           String role = RoleTextField.getText();
           Worker worker = new Worker(id, firstname, middlename, lastname, role);
           boolean result = SystemManager.updateWorkerRecord(worker);
           if(!result) {
               System.out.println("Worker not added");
           }
           parent.dispose();
           WorkersWindow.dispose();
        });


    }

    public void addParcelScreen(JDialog parent){
        refreshModelLists();

        JDialog ParcelWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        ParcelWindow.setBounds(100, 100, 500, 400);
        ParcelWindow.setVisible(true);
        ParcelWindow.setLocationRelativeTo(null);

        LabelPanel.setLayout(new GridLayout(4,0));
        TextPanel.setLayout(new GridLayout(4,0));

        JLabel title = new JLabel("Weight and time must be numbers, dimensions " +
                "should be written as 0x0x0");
        JLabel IdLabel = new JLabel("ID");
        JLabel TimeLabel = new JLabel("Time");
        JLabel DimensionLabel = new JLabel("Dimensions");
        JLabel WeightLabel = new JLabel("Weight");

        JTextField IdTextField = new JTextField(20);
        JTextField TimeTextField = new JTextField(20);
        JTextField WeightTextField = new JTextField(20);
        JTextField DimensionsTextField = new JTextField(20);

        JButton addParcelButton = new JButton("Add Parcel");
        ButtonPanel.add(addParcelButton);

        LabelPanel.add(IdLabel);
        LabelPanel.add(TimeLabel);
        LabelPanel.add(DimensionLabel);
        LabelPanel.add(WeightLabel);

        TextPanel.add(IdTextField);
        TextPanel.add(TimeTextField);
        TextPanel.add(DimensionsTextField);
        TextPanel.add(WeightTextField);

        ParcelWindow.getContentPane().add(title, BorderLayout.NORTH);
        ParcelWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        ParcelWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        ParcelWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        addParcelButton.addActionListener(_->{
            String id = IdTextField.getText();
            int time = Integer.parseInt(TimeTextField.getText());
            String dimensions = DimensionsTextField.getText();
            int weight = Integer.parseInt(WeightTextField.getText());

            Parcel parcel = new Parcel(id, time, dimensions, weight);
            boolean result = SystemManager.updateParcelRecord(parcel);
            if(!result) {
                System.out.println("Parcel not added");
            }
            parent.dispose();
            ParcelWindow.dispose();
        });
    }

    public void removeCustomerScreen(JDialog parent){
        refreshModelLists();

        JDialog CustomerWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();
        CustomerWindow.setBounds(100, 100, 500, 100);
        CustomerWindow.setVisible(true);
        CustomerWindow.setLocationRelativeTo(null);

        JLabel IdLabel = new JLabel("ID");
        JTextField IdTextField = new JTextField(20);
        JButton removeButton = new JButton("Remove customer");

        ButtonPanel.add(removeButton);
        LabelPanel.add(IdLabel);
        TextPanel.add(IdTextField);

        CustomerWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        CustomerWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        CustomerWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        removeButton.addActionListener(_->{
            String id = IdTextField.getText();
            SystemManager.removeCustomer(id);
            parent.dispose();
            CustomerWindow.dispose();
        });
    }

    public void removeWorkerScreen(JDialog parent){
        refreshModelLists();

        JDialog WorkersWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        WorkersWindow.setBounds(100, 100, 500, 100);
        WorkersWindow.setVisible(true);
        WorkersWindow.setLocationRelativeTo(null);

        JLabel IdLabel = new JLabel("ID");
        JTextField IdTextField = new JTextField(20);
        JButton removeButton = new JButton("Remove worker");

        ButtonPanel.add(removeButton);
        LabelPanel.add(IdLabel);
        TextPanel.add(IdTextField);

        WorkersWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        WorkersWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        WorkersWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        removeButton.addActionListener(_->{
            String id = IdTextField.getText();
            SystemManager.removeWorker(id);
            parent.dispose();
            WorkersWindow.dispose();
        });

    }

    public void removeParcelScreen(JDialog parent){
        refreshModelLists();

        JDialog ParcelWindow = new JDialog();
        JPanel LabelPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JPanel TextPanel = new JPanel();

        ParcelWindow.setBounds(100, 100, 500, 100);
        ParcelWindow.setVisible(true);
        ParcelWindow.setLocationRelativeTo(null);

        JLabel IdLabel = new JLabel("ID");
        JTextField IdTextField = new JTextField(20);
        JButton removeButton = new JButton("Remove parcel");
        ButtonPanel.add(removeButton);
        LabelPanel.add(IdLabel);
        TextPanel.add(IdTextField);

        ParcelWindow.getContentPane().add(LabelPanel, BorderLayout.WEST);
        ParcelWindow.getContentPane().add(TextPanel, BorderLayout.CENTER);
        ParcelWindow.getContentPane().add(ButtonPanel, BorderLayout.EAST);

        removeButton.addActionListener(_->{
            String id = IdTextField.getText();
            SystemManager.removeParcel(id);
            parent.dispose();
            ParcelWindow.dispose();
        });
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }
}
