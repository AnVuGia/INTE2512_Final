package com.example.inte2512_group4.Controller;

import javafx.scene.control.Alert;

public class ErrorHandlerController {

    static public void errorPopup(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setContentText(str);
        alert.showAndWait();
    }

    static public boolean checkItemQuantity(String quantity) {
        try {
            int a = Integer.parseInt(quantity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    static public boolean checkItemFee( String fee) {
        try {
            double d = Double.parseDouble(fee);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    static public boolean checkItemYear(String year ) {
        if (!year.matches("[0-9][0-9][0-9][0-9]")) {
            return false;
        }else {
            return true;
        }
    }

    static public boolean checkCustomerName(String name) {
        if (!name.matches("^[ A-Za-z]+$")) {
            return false;
        }else {
            return true;
        }
    }
    static public boolean checkCustomerPhone(String phone) {
        if (!phone.matches("[0-9]{9}") && !phone.matches("[0-9]{10}")
                && !phone.matches("[0-9]{11}") && !phone.matches("[0-9]{12}")) {
            return false;
        }else {
            return true;
        }
    }
}