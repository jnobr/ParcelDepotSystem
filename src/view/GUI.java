package view;

import controller.*;
import model.Customer;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI {
    private JList CustomerList,ParcelList,WorkerList;
    private final Manager SystemManager = new Manager();

    public GUI() throws IOException {
        SystemManager.initialize();
        SystemManager.readCustomersFromFile("Custs.csv");
        SystemManager.readParcelsFromFile("Parcels.csv");
        ArrayList<String> customers = SystemManager.returnAllCustomers("NAME");
        ArrayList<String> parcels = SystemManager.returnAllParcels("ID");
        ArrayList<String> workers = SystemManager.returnAllWorkers("NAME");
        CustomerList = new JList(customers.toArray());
        ParcelList = new JList(parcels.toArray());
        WorkerList = new JList(workers.toArray());


        JFrame window = new JFrame("Example GUI");
        window.setBounds(100, 100, 500, 400);
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCentre = new JPanel();

        panelNorth.setLayout(new GridLayout(1,3));
        panelCentre.setLayout(new GridLayout(1,3));
        panelSouth.setLayout(new GridLayout(2,3));

        JLabel CustomerLabel = new JLabel("Customer");
        JLabel ParcelLabel = new JLabel("Parcel");
        JLabel WorkerLabel = new JLabel("Worker");

        JButton logButton = new JButton("Log");
        JButton saveButton = new JButton("Save");

        panelNorth.add(CustomerLabel);
        panelNorth.add(ParcelLabel);
        panelNorth.add(WorkerLabel);
        panelCentre.add(CustomerList);
        panelCentre.add(ParcelList);
        panelCentre.add(WorkerList);



        JButton parcelButton = new JButton("View Parcels in detail");
        JButton workerButton = new JButton("View Workers in detail");
        JButton customerButton = new JButton("View Customers in detail");
        JButton collectButton = new JButton("Collect Order");

// Define the panel to hold the buttons
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

        window.getContentPane().add(panelWest,BorderLayout.WEST);
        window.getContentPane().add(panelNorth, BorderLayout.NORTH);
        window.getContentPane().add(panelSouth, BorderLayout.SOUTH);
        window.getContentPane().add(panelCentre, BorderLayout.CENTER);
        window.setVisible(true);
    }
    public void save_D(String text)
    {
        try {
            FileWriter fw = new FileWriter("Data.txt");
            BufferedWriter b = new BufferedWriter(fw);
            b.write(text);
            b.close();
        }
        catch(IOException e)
        {
        }
    }
    public void actionPerformed(ActionEvent e)
    {

    }

    public void detailedParcelScreen() {
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
        ParcelScreen.setBounds(100,100,500,400);

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

    }

    public void detailedCustomerScreen() {
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
        CustomerScreen.setBounds(100,100,500,400);

        ArrayList<String> ids = SystemManager.returnAllCustomers("ID");
        ArrayList<String> names = SystemManager.returnAllCustomers("NAME");
        JList idList = new JList(ids.toArray());
        JList nameList = new JList(names.toArray());

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



    }

    public void detailedWorkerScreen(){
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
        WorkerScreen.setBounds(100,100,500,400);

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


    }

    public void showLog() {
        JDialog LogWindow = new JDialog();
        JPanel LogPanel = new JPanel();
        JTextArea LogTextArea = new JTextArea();
        String log = SystemManager.returnLog();
        LogTextArea.setText(log);
        LogWindow.setBounds(100, 100, 500, 400);
        LogWindow.setVisible(true);
        LogWindow.setLocationRelativeTo(null);
        LogPanel.add(LogTextArea);
        LogWindow.add(LogPanel);
        JScrollPane scroll = new JScrollPane (LogTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        LogWindow.add(scroll);


    }



    public static void main(String[] args) throws IOException {
        new GUI();
    }
}
