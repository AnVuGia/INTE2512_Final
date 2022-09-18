package com.example.inte2512_group4.Model;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Customer {
    private int numberOfRental;
    private String phone;
    private String customerType;
    private String ID;
    private String name;
    private String address;
    private ArrayList<Item> rentalList = new ArrayList<>();
    private ArrayList<String> rentalList_String = new ArrayList<>();
    private String userName;
    private String password;
    private int numberOfReturn = 0;

    public int getNumberOfReturn() {
        return numberOfReturn;
    }

    public Customer() {}

    public Customer(String ID, String name, String address,String phone, int numberOfRental,String customerType,
                    String userName,
                    String password) {
        this.customerType = customerType;
        this.numberOfRental = numberOfRental;
        this.phone = phone;
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }
    public void updateCustomerInfo(String ID, String name, String address,String phone, int numberOfRental,String customerType,
                                   String userName,
                                   String password){
        this.customerType = customerType;
        this.numberOfRental = numberOfRental;
        this.phone = phone;
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getCustomerID() {
        return ID;
    }



    public String getCustomerName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Item> getRentalList() {
        return rentalList;
    }
    public void showData(){
        System.out.println("ID: "+ ID);
        System.out.println("name: "+ name);
        System.out.println("userName: "+ userName);
        System.out.println("password: "+ password);
        System.out.println("Phone number: " + phone);
        System.out.println("Account Type: " + customerType);
        System.out.println("Number of Rentals: " + numberOfRental);
        for (Item rent : rentalList){
            System.out.println(rent.getItemID());
        }
    }

    public int getNumberOfRental() {
        return numberOfRental;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerType() {
        return customerType;
    }
    public boolean addRentItem(Item item){
        if(item.isRentable()){
            item.rent();
            rentalList.add(item);
            rentalList_String.add(item.getItemID());
            numberOfRental++;
            return true;
        }else {
            System.out.println("There is no more copy");
            return false;
        }
    }
    public boolean addItem(Item item){
        rentalList.add(item);
        return true;
    }
    public boolean returnItem(Item item){
        for (Item rental : rentalList){
            if(item.getItemID().equals(rental.getItemID())){
                rental.returnItem();
                rentalList.remove(rental);
                rentalList_String.remove(item.getItemID());
                numberOfRental--;
                numberOfReturn++;
                return true;
            }
        }
        return false;
    }
    public boolean removeItem(Item item){
        for (Item rental : rentalList){
            if(item.getItemID().equals(rental.getItemID())){
                rentalList.remove(rental);
                rentalList_String.remove(item.getItemID());
                numberOfRental--;
                return true;
            }
        }
        return false;
    };
    public static Comparator<Customer> sortName = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            String s1 = o1.getCustomerName().toUpperCase();
            String s2 = o2.getCustomerName().toUpperCase();
            return s1.compareTo(s2);
        }
    };
    public static Comparator<Customer> sortID = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            String s1 = o1.getCustomerID().toUpperCase();
            String s2 = o2.getCustomerID().toUpperCase();
            return s1.compareTo(s2);
        }
    };
    public void setRentalListStr(ArrayList<String> input) {
        rentalList_String = input;
    }
    public ArrayList<String> getRentalList_String(){
        return  rentalList_String;
    }
}
