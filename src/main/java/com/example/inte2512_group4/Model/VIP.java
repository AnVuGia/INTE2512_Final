package com.example.inte2512_group4.Model;

import com.example.inte2512_group4.Model.Customer;

public class VIP extends Customer {

    private int rewardPoints;


    public VIP() {}

    public VIP(String ID, String name, String address,String phone,int numOfRental,
               String customerType, String userName, String password, int rewardPoints) {
        super(ID, name, address,phone,numOfRental,customerType, userName, password);
        this.rewardPoints = 0;
    }
    public static VIP createNewVIP(String ID, String name, String address,String phone,int numOfRental,
                                   String customerType, String userName, String password){
        return new VIP(ID, name, address,phone,numOfRental,customerType, userName, password, 0);
    }

    @Override
    public boolean addRentItem(Item item) {
        if(super.addRentItem(item)){
            this.rewardPoints += 10;
            if(this.rewardPoints == 100){
                System.out.println("You have accumulate enough points!" +
                        "This item will be rent for free!");
                this.rewardPoints = 0;
            }
        }
        return true;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    //Insert method "Exchange Reward" here later
}
