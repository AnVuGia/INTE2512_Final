package com.example.inte2512_group4.Controller;

import com.example.inte2512_group4.Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn_Controller {
    private String admin1 = "admin";
    private String admin1_pwd = "123";
    public static boolean isAdmin;
    @FXML
    private TextField username,adminUser;
    @FXML
    private PasswordField adminPw, customerPw;
    @FXML
    private Label loginFail, adminLog;

    @FXML
    public void initialize(){
        //onload fxml file

    }
    @FXML
    private void toCustomer(ActionEvent e) throws IOException {
        for (Customer cus: CustomerController.customers){
            if (username.getText().trim().equals(cus.getUserName().trim())) {
                if (customerPw.getText().trim().equals(cus.getPassword().trim())) {
                    CustomerController.selectedCustomer = cus;
                    isAdmin = false;
                    Router.toCustomerUI(e);
                }
            } else {
                loginFail.setVisible(true);
            }
        }
    }
    @FXML
    private void toAdmin(ActionEvent e) throws IOException {
        if (adminUser.getText().trim().equals(admin1.trim())) {
            if (adminPw.getText().trim().equals(admin1_pwd.trim())){
                isAdmin = true;
                System.out.println(isAdmin);
                Router.toAdminUI(e);
            }
        }else {
            adminLog.setVisible(true);
        }
    }
    @FXML
    private void toAdminLogin(ActionEvent e) throws IOException{
        Router.toAdminLogin(e);
    }
    @FXML private void toCustomerLogin(ActionEvent e) throws IOException{
        Router.toCustomerLogin(e);
    }
    @FXML private void toAdminItem(ActionEvent e) throws IOException{
        Router.toAdminItem(e);
    }
    @FXML private void toAdminCustomer(ActionEvent e) throws IOException{
        Router.toAdminCustomer(e);
    }
    @FXML private void toCustomerShop(ActionEvent e) throws IOException{
        Router.toCustomerShop(e);
    }
    @FXML private void toCustomerProfile(ActionEvent e) throws IOException{
        Router.toCustomerProfile(e);
    }
    @FXML private void toRegister(ActionEvent e) throws IOException{
        Router.toRegister(e);
    }
}
