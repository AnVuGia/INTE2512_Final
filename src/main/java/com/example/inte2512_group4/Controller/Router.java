package com.example.inte2512_group4.Controller;

import com.example.inte2512_group4.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Router {
    private static ScreenController screenController = new ScreenController();
    public static void toAdminLogin(ActionEvent e) throws IOException {
        screenController.switchScene("Admin_LogIn.fxml",e);
    }
    @FXML
    public static void toCustomerLogin(ActionEvent e) throws IOException {
        screenController.switchScene("loginPage.fxml",e);
    }
    @FXML
    public static void toCustomerProfile(ActionEvent e) throws IOException {
        screenController.switchScene("Customer_Profile.fxml",e);
    }
    @FXML
    public static void toCustomerShop(ActionEvent e) throws IOException {
        screenController.switchBiggerScene("Customer_Shop.fxml",e);
    }
    @FXML public static void toAdminItem(ActionEvent e) throws IOException {
        screenController.switchBiggerScene("Admin_Item.fxml",e);
    }
    @FXML public static void toAdminCustomer(ActionEvent e) throws IOException {
        screenController.switchScene("Admin_Customer.fxml",e);
    }
    @FXML public static void toAdminUI(ActionEvent e) throws IOException{
        screenController.switchScene("Admin_UI.fxml",e);
    }
    @FXML public static void toCustomerUI(ActionEvent e)throws IOException{
        screenController.switchScene("Customer_UI.fxml",e);
    }
    @FXML public static void toUpdateCustomer(ActionEvent e)throws IOException{
        screenController.switchScene("updateCustomer.fxml",e);
    }
    @FXML public static void toUpdateItem(ActionEvent e)throws IOException{
        screenController.switchScene("updateItem.fxml",e);
    }
    @FXML public static void toRegister(ActionEvent e)throws IOException{
        screenController.switchScene("add-customer.fxml",e);
    }
    @FXML public static void toAddItem(ActionEvent e)throws IOException{
        screenController.switchScene("add-item.fxml",e);
    }
}
