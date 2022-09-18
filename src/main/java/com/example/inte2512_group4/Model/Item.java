package com.example.inte2512_group4.Model;

import java.util.Comparator;

public class Item {
    private String ID;
    private String title;
    private String rentType;
    private String loanType;
    private String genre;
    private int quantity;
    private double fee;
    private boolean status;

    public Item(String ID, String title, String rentType, String loanType, String genre, int quantity, double fee, boolean status) {
        this.ID = ID;
        this.title = title;
        this.rentType = rentType;
        this.loanType = loanType;
        this.genre = genre;
        this.quantity = quantity;
        this.fee = fee;
        this.status = status;
    }
    public static Item createNewItem(String ID, String title, String rentType, String loanType, String genre,
                                     int quantity, double fee, boolean status){
        return new Item(ID, title, rentType,loanType,genre,quantity,fee,status);
    }
    public String getItemID() {
        return ID;
    }

    public Item() {}

    public String getItemTitle() {
        return title;
    }

    public void setItemTitle(String title) {
        this.title = title;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public boolean getStatus() {
        return status;
    }
    public String getStatusValue(){
        if(this.status){
            return "Available";
        } else {
            return "Not Available";
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void updateItem (String ID, String title, String rentType, String loanType, String genre, int quantity,
                       double fee,
             boolean status) {
        this.ID = ID;
        this.title = title;
        this.rentType = rentType;
        this.loanType = loanType;
        this.genre = genre;
        this.quantity = quantity;
        this.fee = fee;
        this.status = status;
    }
    public void showData(){
        System.out.println("ID: " + ID);
        System.out.println("title: " + title);
        System.out.println("rentType: " + rentType);
        System.out.println("loanType: " + loanType);
        System.out.println("genre: " + genre);
        System.out.println("quantity: " + quantity);
        System.out.println("fee: " + fee);
        System.out.println("status: " + status);
    }
    public boolean rent(){
        if(this.quantity > 0) {
            this.quantity -=1;
            if(this.quantity == 0){
                this.status = false;
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean isRentable(){
        if(this.quantity > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static Comparator<Item> sortTitle = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {
            String s1 = i1.getItemTitle().toUpperCase();
            String s2 = i2.getItemTitle().toUpperCase();
            return s1.compareTo(s2);
        }
    };
    public static Comparator<Item> sortID = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {
            String[] s1 = i1.getItemID().toUpperCase().split("-");
            String[] s2 = i2.getItemID().toUpperCase().split("-");
            return s1[0].compareTo(s2[0]);
        }
    };
    public void returnItem(){
        this.quantity += 1;
    }
}
