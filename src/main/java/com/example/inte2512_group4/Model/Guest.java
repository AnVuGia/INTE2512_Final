package com.example.inte2512_group4.Model;



public class Guest extends Customer {

    private int returnTimes;


    public Guest() {}

    public Guest(String ID, String name, String address,String phone,int numOfRental,
                 String customerType, String userName, String password,
                 int returnTimes) {
        super(ID, name, address,phone,numOfRental,customerType, userName, password);
        this.returnTimes = 0;
    }
    public static Guest createNewGuest(String ID, String name, String address,String phone,int numOfRental,
                                       String customerType, String userName, String password){
        return new Guest(ID, name, address,phone,numOfRental,customerType, userName, password,0);
    }
    public int getReturnTimes() {
        return returnTimes;
    }

    @Override
    public boolean addRentItem(Item item) {
        if(this.getNumberOfRental() >=2){
            System.out.println("Up rank to borrow more!");
            return false;
        }
        if(item.getLoanType().equals("2-day")){
            System.out.println("Up rank to borrow this type of item!");
            return false;
        }
        System.out.println(this.getNumberOfRental());
        return super.addRentItem(item);
    }

    public void setReturnTimes(int returnTimes) {
        this.returnTimes = returnTimes;
    }
}
