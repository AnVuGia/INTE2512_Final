package com.example.inte2512_group4.Controller;

import com.example.inte2512_group4.Model.Customer;
import com.example.inte2512_group4.Model.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Admin_CustomerController {
    @FXML
    private TextField findItem_Field;
    @FXML
    private ChoiceBox<String> find_Customer_ChoiceBox;
    @FXML
    private ScrollPane customer_container;
    @FXML private Button sortCustomerButton;
    @FXML Button ascdButton;
    @FXML Button IDsort;
    private TableView tableView = new TableView();
    @FXML public void initialize(){
            loadFindItemType();
            customer_container.setContent(showTable());
    }
    ObservableList list1 = FXCollections.observableArrayList();
    @FXML private void loadFindItemType(){
        String[] type = {"Name", "ID"};
        list1.removeAll(list1);
        list1.addAll(type);
        find_Customer_ChoiceBox.getItems().addAll(list1);
        find_Customer_ChoiceBox.setValue(type[0]);
    }
    public VBox showTable(){
        return tableView.createCustomerContainerFromList(CustomerController.customers);
    }
    public void findCustomerButtonHandler(ActionEvent e){
        String search_type = find_Customer_ChoiceBox.getValue();
        String search_value = findItem_Field.getText().trim();
        ArrayList<Customer> cus_list = new ArrayList<>();
        boolean isValid = false;
        if(search_value.equals("")){
            customer_container.setContent(showTable());
            return;
        }
        if(search_type.equals("Name")){
            for(Customer cus: CustomerController.customers){
                if (cus.getCustomerName().trim().toUpperCase().contains(search_value.toUpperCase())){
                    cus_list.add(cus);
                    isValid = true;
                }
            }
        } else if(search_type.equals("ID")){
            for(Customer cus: CustomerController.customers){
                if (cus.getCustomerID().toUpperCase().contains(search_value.toUpperCase())){
                    cus_list.add(cus);
                    isValid = true;
                }
            }
        }
        if (isValid){
            customer_container.setContent(tableView.createCustomerContainerFromList(cus_list));
        } else {
            //err handling
            customer_container.setContent(new Label("No customer match the search condition"));
        }
    }
    private ArrayList<Customer> sortCustomerType(String type){
        ArrayList<Customer> guests = new ArrayList<>();
        ArrayList<Customer> regulars = new ArrayList<>();
        ArrayList<Customer> vips = new ArrayList<>();
        for(Customer cus: CustomerController.customers){
            if(cus.getCustomerType().equals("Guest")){
                guests.add(cus);
            } else if (cus.getCustomerType().equals("Regular")) {
                regulars.add(cus);
            } else {
                vips.add(cus);
            }
        }
        if(type.equals("guest")) return guests;
        else if (type.equals("regular")) {return regulars;
        } else return vips;
    }
    private String type = "guest";
    @FXML private void onSortCustomer(ActionEvent event){
        if(type.equals("guest")){
            sortCustomerButton.setText("Guest");
            customer_container.setContent(tableView.createCustomerContainerFromList(sortCustomerType("guest")));
            type = "regular";
        } else if (type.equals("regular")) {
            sortCustomerButton.setText("Regular");
            customer_container.setContent(tableView.createCustomerContainerFromList(sortCustomerType("regular")));
            type = "VIPs";
        } else if (type.equals("VIPs")) {
            sortCustomerButton.setText("VIPs");
            customer_container.setContent(tableView.createCustomerContainerFromList(sortCustomerType("VIPs")));
            type = "None";
        } else {
            sortCustomerButton.setText("Sort By Customer Type");
            customer_container.setContent(tableView.createCustomerContainerFromList(CustomerController.customers));
            type="guest";
        }
    }
    private String state_name = "Ascend";
    private String state_id =  "Ascend";
    @FXML private void onNameSort(ActionEvent e){
        if (state_name.equals("Ascend")){
            customer_container.setContent(tableView.createCustomerContainerFromList(CustomerController.nameAsc()));
            ascdButton.setText("Ascending");
            state_name = "Descend";
            CustomerController.IdAsc();
        } else if (state_name.equals("Descend")){
            customer_container.setContent(tableView.createCustomerContainerFromList(CustomerController.nameDesc()));
            ascdButton.setText("Descending");
            state_name = "Normal";
            CustomerController.IdAsc();
        } else if (state_name.equals("Normal")){
            customer_container.setContent(tableView.createCustomerContainerFromList(CustomerController.IdAsc()));
            ascdButton.setText("Sort Item By Name");
            state_name = "Ascend";
            CustomerController.IdAsc();
        }

    }
    @FXML private void onIdSort(ActionEvent e){
        if (state_id.equals("Ascend")){
            customer_container.setContent(tableView.createCustomerContainerFromList(CustomerController.IdAsc()));
            IDsort.setText("ID Ascending");
            state_id = "Descend";
        } else if (state_id.equals("Descend")){
            customer_container.setContent(tableView.createCustomerContainerFromList(CustomerController.IdDesc()));
            IDsort.setText("ID Descending");
            state_id = "Ascend";
            CustomerController.IdAsc();
        }
    }
    @FXML private void onBack(ActionEvent e) throws IOException {

        Router.toAdminUI(e);
    }
}
