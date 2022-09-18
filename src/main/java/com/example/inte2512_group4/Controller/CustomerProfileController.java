package com.example.inte2512_group4.Controller;


import com.example.inte2512_group4.Model.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class CustomerProfileController {
    @FXML private Label customer_name_label;
    @FXML private Label customer_type_label;
    @FXML private Label customer_phone_label;
    @FXML private Label customer_address_label;
    @FXML private ScrollPane rent_item_container;
    @FXML private Label number_of_return_label;
    private TableView tableView = new TableView();

    @FXML public void initialize(){
       showCustomerProfile();
       showRentItem();
    }

    private void showCustomerProfile(){
        customer_name_label.setText(CustomerController.selectedCustomer.getCustomerName());
        customer_type_label.setText(CustomerController.selectedCustomer.getCustomerType());
        customer_address_label.setText(CustomerController.selectedCustomer.getAddress());
        customer_phone_label.setText(CustomerController.selectedCustomer.getPhone());
        number_of_return_label.setText(Integer.toString(CustomerController.selectedCustomer.getNumberOfReturn()));
    }
    private void showRentItem(){
        rent_item_container.setContent(tableView.createItemFromListContainer(CustomerController.selectedCustomer.getRentalList(), "return"));
    }

    @FXML private void onBack(ActionEvent e) throws IOException {
        Router.toCustomerUI(e);
    }
    @FXML private void toEditProfile(ActionEvent e) throws IOException{
        Router.toUpdateCustomer(e);
    }
}
