package com.example.inte2512_group4.Model;

public class Regular extends Customer{

    private int returnTimes;


    public Regular() {}

    public Regular(String ID, String name, String address,String phone,int numOfRental,
                   String customerType, String userName, String password, int returnTimes) {
        super(ID, name, address,phone,numOfRental,customerType, userName, password);
        this.returnTimes = 0;
    }

    public static Regular createnewRegular(String ID, String name, String address,String phone,int numOfRental,
                                           String customerType, String userName, String password
                                          ){
        return new Regular(ID, name, address,phone,numOfRental,customerType, userName, password, 0);
    }
    public int getReturnTimes() {
        return returnTimes;
    }

    public void setReturnTimes(int returnTimes) {
        this.returnTimes = returnTimes;
    }

}
