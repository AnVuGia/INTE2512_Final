package com.example.inte2512_group4.Controller;

import com.example.inte2512_group4.Model.TableView;
import com.example.inte2512_group4.Controller.ErrorHandlerController;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import javafx.fxml.Initializable;
import com.example.inte2512_group4.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.util.ArrayList;

import static com.example.inte2512_group4.Controller.ErrorHandlerController.*;
import static java.lang.Integer.parseInt;

public class CustomerController {
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static Customer selectedCustomer ;

    public static void setSelectedCustomer(Customer customer) {
        selectedCustomer = customer;
    }
    public static void showData(){
        if(customers.size() == 0){
            System.out.println("No customer");
        } else {
            for(Customer cus: customers){
                cus.showData();
            }
        }
    }

    private String username;
    private String password;
    private ArrayList<Customer> customerListSortedID;
    private ArrayList<Customer> customerListSortedName;


    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button findButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextField customerNameField;
    @FXML private TextField numOfRentField;
    @FXML private TextField phoneField;
    @FXML
    ChoiceBox <String> accountTypeField;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField id;
    @FXML
    private TextField customer_type;
    @FXML
    private TextField user_name;
    @FXML
    private TextField user_password;
    @FXML
    private TextField numberRentals;
    @FXML
    private TextField searchField;
    @FXML
    private ScrollPane rentalList;

    @FXML
    private Label errorLabel,successLabel,updateSuc;
    @FXML
    private ChoiceBox choice;
    @FXML private TextField findItem_Field;
    @FXML private  ChoiceBox<String> find_Item_ChoiceBox;
    @FXML private  ScrollPane item_container;
    @FXML private Label NumOfReturn;
    @FXML private Label nameError, phoneError;
    @FXML private Button promoteButton;
    public TableView tableView = new TableView();

    ObservableList<String> searchChoices = FXCollections.observableArrayList("Id", "Name");

    @FXML public void initialize(){
        if((accountTypeField != null)){
            loadAccountTypeChoiceBox();
        }
        if(choice != null){
            choice.setItems(searchChoices);
        }
        if(item_container != null){
            item_container.setContent(showTable());
            loadFindItemType();

        }
        if(rentalList != null){
            promoteButton.setVisible(false);
            setCustomerFieldDetails(selectedCustomer);
            System.out.println(LogIn_Controller.isAdmin);
            if(LogIn_Controller.isAdmin){
                promoteButton.setVisible(true);
            }
        }
    }
    public VBox showTable(){
        return tableView.createItemFromListContainer(ItemController.itemList,"rent");
    }
    public void findItemButtonHandler(ActionEvent e){
        String search_type = find_Item_ChoiceBox.getValue();
        String search_value = findItem_Field.getText().trim();
        ArrayList<Item> item_list = new ArrayList<>();
        boolean isValid = false;
        if(search_value.equals("")){
            item_container.setContent(showTable());
            return;
        }
        if(search_type.equals("Name")){
            for(Item item: ItemController.items){
                if (item.getItemTitle().trim().toUpperCase().contains(search_value.toUpperCase())){
                    item_list.add(item);
                    isValid = true;
                }
            }
        } else if(search_type.equals("ID")){
            for(Item item: ItemController.items){
                if (item.getItemID().trim().toUpperCase().contains(search_value.toUpperCase())){
                    item_list.add(item);
                    isValid = true;
                }
            }
        }
        if (isValid){
            item_container.setContent(tableView.createItemFromListContainer(item_list,"rent"));
        } else {
            //err handling
            System.out.println("None");
        }

    }
    ObservableList list = FXCollections.observableArrayList();
    @FXML private void loadAccountTypeChoiceBox(){
        String[] type = {"Regular", "VIP", "Guest"};
        list.removeAll(list);
        list.addAll(type);
        accountTypeField.getItems().addAll(list);
    }
    ObservableList list1 = FXCollections.observableArrayList();
    @FXML private void loadFindItemType(){
        String[] type = {"Name", "ID"};
        list1.removeAll(list1);
        list1.addAll(type);
        find_Item_ChoiceBox.getItems().addAll(list1);
        find_Item_ChoiceBox.setValue(type[0]);
    }
    private String generateNewID(){
        String ID = "", temp;
        Customer last_customer = customers.get(customers.size() -1);
        temp = last_customer.getCustomerID();
        String numberOnly= temp.replaceAll("[^0-9]", "");
        int temp_num = Integer.parseInt(numberOnly) + 1;
        if(temp_num < 10){
            ID += "C00" + temp_num;
        } else if (temp_num < 100) {
            ID += "C0" + temp_num;
        } else if (temp_num <=999) {
            ID += "C" + temp_num;
        }
        return ID;
    }
    @FXML
    private void registerButtonHandler(ActionEvent event) throws IOException {

            String ID = generateNewID();
            String name = nameField.getText();
            String address = addressField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String phone = phoneField.getText();
            String accountType = "Guest";
            int numOfRental = 0;
            if (!checkCustomerName(name)){
                errorPopup("Name can only contain characters and white spaces!");
            }
            if(!checkCustomerPhone(phone)){
                errorPopup("Phone should have 9 to 12 numbers!");
            }
            else if (checkCustomerName(name) && checkCustomerPhone(phone)){
                nameError.setVisible(false);
                phoneError.setVisible(false);
                customers.add(Guest.createNewGuest(ID, name, address, phone, numOfRental, accountType,
                        username, password));
                selectedCustomer = customers.get(customers.size() - 1);
                Router.toCustomerUI(event);
            }
    }

    public void findCustomer(String name) {
        for (Customer customer : customers) {
            if (name.equals(customer.getCustomerName())) {
                setSelectedCustomer(customer);
                System.out.println(customer.getCustomerName());
            }
        }
    }




    @FXML
    private void updateButtonHandler(ActionEvent e) throws IOException {
        String ID = selectedCustomer.getCustomerID();
        String name_value = name.getText();
        String address_value = address.getText();
        String password = user_password.getText();
        String phone_value = phone.getText();
        String accountType = customer_type.getText();
        if (!checkCustomerName(name_value)){
            errorPopup("Name can only contain characters and white spaces!");
        }
        if(!checkCustomerPhone(phone_value)){
            errorPopup("Phone should have 9 to 12 numbers!");
        }else if (checkCustomerName(name_value) && checkCustomerPhone(phone_value)) {
            selectedCustomer.updateCustomerInfo(ID, name_value, address_value, phone_value, selectedCustomer.getNumberOfRental(),
                    accountType,
                    selectedCustomer.getUserName(),
                    password);
            CustomerController.showData();
            updateSuc.setVisible(true);
        }
    }

    Comparator<Customer> customerComparatorID = new Comparator<Customer>() {
        public int compare(Customer C1, Customer C2){
            return C1.getCustomerID().compareTo(C2.getCustomerID());
        }
    };

    Comparator<Customer> customerComparatorName = new Comparator<Customer>() {
        public int compare(Customer C1, Customer C2){
            return C1.getCustomerName().compareTo(C2.getCustomerName());
        }
    };

    public int customerIdBinarySearch(ArrayList<Customer>customerList, int l, int r, String key){
        if(r>=l){
            int mid = l+ (r-l)/2;
            if(customerList.get(mid).getCustomerID().equals(key))
                return mid;
            if(customerList.get(mid).getCustomerID().compareTo(key)>0)
                return customerIdBinarySearch(customerList, l, mid-1, key);

            return customerIdBinarySearch(customerList, mid+1, r, key);

        }
        return -1;
    }

    public int customerNameBinarySearch(ArrayList<Customer>customerList, int l, int r, String key){
        if(r>=l){
            System.out.println("here");
            int mid = l+ (r-l)/2;
            if(customerList.get(mid).getCustomerName().equals(key))
                return mid;
            else if(customerList.get(mid).getCustomerName().compareTo(key)>0)
                return customerNameBinarySearch(customerList, l, mid-1, key);
            else if(customerList.get(mid).getCustomerName().compareTo(key)<0)
                return customerNameBinarySearch(customerList, mid+1, r, key);

        }
        return -1;
    }

    private void setCustomerFieldDetails(Customer foundItem){
        name.setText(foundItem.getCustomerName());
        address.setText(foundItem.getAddress());
        user_name.setText(foundItem.getUserName());
        user_password.setText(foundItem.getPassword());
        phone.setText(foundItem.getPhone());
        address.setText(foundItem.getAddress());
        id.setText(foundItem.getCustomerID());
        customer_type.setText(foundItem.getCustomerType());
        NumOfReturn.setText(Integer.toString(foundItem.getNumberOfReturn()));
        rentalList.setContent(tableView.createItemFromListContainer(selectedCustomer.getRentalList(),"none"));
    }

    /*@FXML
    public void searchCustomer(ActionEvent event) {

        String idNameChoice = (String) choice.getSelectionModel().getSelectedItem();
        String key = searchField.getText();
        System.out.println("here");
        int l = 0;
        int r = customers.size();
        System.out.println("here1");
        if (idNameChoice.toUpperCase().equals("id")) {

            Collections.sort(customers, customerComparatorID);
            customerListSortedID = customers;
            try{
                Customer foundItem = customerListSortedID.get(customerIdBinarySearch(customerListSortedID, l, r, key));
                setCustomerFieldDetails(foundItem);}
            catch (Exception e) {
              errorLabel.setText("Sorry, the customer doesn't exist");}
            }


        else if (idNameChoice.toUpperCase().equals("name")) {

            Collections.sort(customers, customerComparatorName);
            customerListSortedName = customers;
            try{
                Customer foundItem = customerListSortedName.get(customerNameBinarySearch(customerListSortedName, l, r, key));
                setCustomerFieldDetails(foundItem);}
            catch(Exception e){
                errorLabel.setText("Sorry, the customer doesn't exist");
            }

        }
    }*/
    @FXML
    protected void Promote() {
        try {
            if (customer_type.getText().compareTo("Guest") == 0 && Integer.parseInt(NumOfReturn.getText()) > 3) {
            new Regular();
            Regular temp;

            temp = Regular.createnewRegular(selectedCustomer.getCustomerID(), name.getText(),
                    address.getText(), phone.getText(),
                    selectedCustomer.getNumberOfRental(), "Regular", user_name.getText(), user_password.getText());

            temp.setRentalListStr(selectedCustomer.getRentalList_String());
                customers.add(temp);
            customers.remove(selectedCustomer);
            //selectedCustomer = customers.get(customers.size()-1);
                successLabel.setVisible(true);

            } else if (customer_type.getText().compareTo("Regular") == 0 && Integer.parseInt(NumOfReturn.getText()) > 5) {
                new VIP();
                VIP temp;

                temp = VIP.createNewVIP(selectedCustomer.getCustomerID(), name.getText(),
                        address.getText(), phone.getText(),
                        selectedCustomer.getNumberOfRental(), "VIP", user_name.getText(), user_password.getText());

                temp.setRentalListStr(selectedCustomer.getRentalList_String());
                customers.add(temp);
                customers.remove(selectedCustomer);
                //selectedCustomer = customers.get(customers.size()-1);
                successLabel.setVisible(true);


            }
            else{
                errorLabel.setVisible(true);
            }
        } catch (Exception e) {
            errorLabel.setVisible(true);
        }
    }
    @FXML private void onBack(ActionEvent e) throws IOException {
        if(LogIn_Controller.isAdmin){
            Router.toAdminCustomer(e);
        }else {
            Router.toCustomerUI(e);
        }
    }
    public static ArrayList<Customer> nameAsc(){
        ArrayList<Customer> temp = CustomerController.customers;
        Collections.sort(temp, Customer.sortName);
        return temp;
    }
    public static ArrayList<Customer> nameDesc(){
        ArrayList<Customer> temp = CustomerController.customers;
        Collections.sort(temp, Collections.reverseOrder(Customer.sortName));
        return temp;
    }
    public static ArrayList<Customer> IdAsc(){
        ArrayList<Customer> temp = CustomerController.customers;
        Collections.sort(temp, Customer.sortID);
        return temp;
    }
    public static ArrayList<Customer> IdDesc(){
        ArrayList<Customer> temp = CustomerController.customers;
        Collections.sort(temp, Collections.reverseOrder(Customer.sortID));
        return temp;
    }
    @FXML private void registerBack(ActionEvent e) throws IOException{
        Router.toCustomerLogin(e);
    }


}
