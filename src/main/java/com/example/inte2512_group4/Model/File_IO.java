package com.example.inte2512_group4.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static com.example.inte2512_group4.Model.Guest.createNewGuest;
import static com.example.inte2512_group4.Model.Regular.createnewRegular;
import static com.example.inte2512_group4.Model.VIP.createNewVIP;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class File_IO {
    static int count = 0;
    private final String customer_Filename = "customers.txt";
    private final String items_Filename = "items.txt";
    public ArrayList<Customer> readFileCustomerTxtM(String filename) throws FileNotFoundException {
        String ex;
        String ID;
        String name;
        String address;
        String phone;
        int numberOfRental;
        String customerType;
        String userName;
        String password;
        File text = new File(filename);
        Scanner fileSc = new Scanner(text);
        ArrayList<String> str = new ArrayList<>(readItem(filename));
        ArrayList<Integer> vrf = new ArrayList<>(countItem(filename));
        int index = 0;

        ArrayList<Customer> result = new ArrayList<>();
        try {
            while (fileSc.hasNextLine()) {
                ex = fileSc.nextLine();
                StringTokenizer reader = new StringTokenizer(ex, ",");
                if (!reader.hasMoreElements()) continue;
                ID = reader.nextToken();
                if (!reader.hasMoreElements()) continue;
                name = reader.nextToken();

                if (!reader.hasMoreElements()) break;
                address = reader.nextToken();

                if (!reader.hasMoreElements()) break;
                phone = reader.nextToken();

                if (!reader.hasMoreElements()) break;
                numberOfRental = parseInt(reader.nextToken());
                if (!reader.hasMoreElements()) break;
                customerType = reader.nextToken();
                if (!reader.hasMoreElements()) break;
                userName = reader.nextToken();
                if (!reader.hasMoreElements()) continue;
                password = reader.nextToken();


                if (customerType.compareTo("Guest") == 0) {
                    Guest temp = createNewGuest(ID, name, address, phone, numberOfRental, customerType, userName, password);
                    boolean valid = true;
                    if (vrf.get(index) != numberOfRental) {
                        System.out.println(ID + " has problem with rental list. RentTotal doesn't match number of item in the list");
                        valid = false;
                    }

                    ArrayList<String> rent = new ArrayList<>();
                    for (int i = 0; i < numberOfRental; i++) {
                        rent.add(str.get(count));
                        count++;
                    }

                    if (valid) {
                        temp.setRentalListStr(rent);
                        result.add(temp);
                    } else {
                        System.out.println("Please check " + ID + "'s validity before adding in; User can't be add in");
                    }

                } else if (customerType.compareTo("Regular") == 0) {
                    Regular temp = createnewRegular(ID, name, address, phone, numberOfRental, customerType, userName, password);
                    boolean valid = true;
                    if (vrf.get(index) != numberOfRental) {
                        System.out.println(ID + " has problem with rental list. RentTotal doesn't match number of item in the list");
                        valid = false;
                    }

                    ArrayList<String> rent = new ArrayList<>();
                    for (int i = 0; i < numberOfRental; i++) {
                        rent.add(str.get(count));
                        count++;
                    }

                    if (valid) {
                        temp.setRentalListStr(rent);
                        result.add(temp);
                    } else {
                        System.out.println("Please check " + ID + "'s validity before adding in; User can't be add in");
                    }
                } else if (customerType.compareTo("VIP") == 0) {
                    VIP temp = createNewVIP(ID, name, address, phone, numberOfRental, customerType, userName, password);
                    boolean valid = true;
                    if (vrf.get(index) != numberOfRental) {
                        System.out.println(ID + " has problem with rental list. RentTotal doesn't match number of item in the list");
                        valid = false;
                    }

                    ArrayList<String> rent = new ArrayList<>();
                    for (int i = 0; i < numberOfRental; i++) {
                        rent.add(str.get(count));
                        count++;
                    }

                    if (valid) {
                        temp.setRentalListStr(rent);
                        result.add(temp);
                    } else {
                        System.out.println("Please check " + ID + "'s validity before adding in; User can't be add in");
                    }
                }
                index++;
            }


        } catch (Exception error) {
            error.printStackTrace();
        }


        return result;
    }

    public ArrayList<String> readItem(String filename) {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == 'I') {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ArrayList<Integer> countItem(String filename) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int count = 0;
        int add = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == 'I') {
                    count++;
                }

                result.add(count);
                count = 0;

            }
            result.add(0);

            for (int value : result) {
                if (value == 1) {
                    add += value;
                } else if (value == 0) {
                    temp.add(add);
                    add = 0;
                }
            }

            temp.remove(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    public ArrayList<Item> readFileItemTxtM(String filename) throws FileNotFoundException {
        String ex;
        String ID;
        String title;
        String rentType;
        String loanType;
        int numberOfCopies;
        double rentalFee;
        String genre;
        File text = new File(filename);
        Scanner fileSc = new Scanner(text);
        ArrayList<Item> itemList = new ArrayList<>();

        try {
            while (fileSc.hasNextLine()) {
                ex = fileSc.nextLine();
                StringTokenizer reader = new StringTokenizer(ex, ",");
                if (!reader.hasMoreElements()) continue;
                ID = reader.nextToken();
                if (!reader.hasMoreElements()) continue;
                title = reader.nextToken();

                if (!reader.hasMoreElements()) break;
                rentType = reader.nextToken();

                if (!reader.hasMoreElements()) break;
                loanType = reader.nextToken();

                if (!reader.hasMoreElements()) break;
                numberOfCopies = parseInt(reader.nextToken());
                if (!reader.hasMoreElements()) break;
                rentalFee = parseDouble(reader.nextToken());
                boolean status = true;
                if(numberOfCopies == 0) status = false;
                if (rentType.compareTo("DVD") == 0 || rentType.compareTo("Record") == 0) {
                    if (!reader.hasMoreElements()) break;
                    genre = reader.nextToken();

                    itemList.add(Item.createNewItem(ID, title, rentType, loanType, genre, numberOfCopies, rentalFee,
                            status));
                } else {
                    itemList.add(Item.createNewItem(ID, title, rentType, loanType, "", numberOfCopies, rentalFee, status));
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return itemList;
    }

    //WRITE ALL CUSTOMERS INTO FILE NAME customers.txt AND CONVERT ALL THE OBJECTS INTO THEIR APPROPRIATE CONTENTS
    public void writeFileCustomerTxtM(ArrayList<Customer> customersList) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(customer_Filename));

        for (Customer customer : customersList) {
            pw.write(customer.getCustomerID() + "," + customer.getCustomerName() + ","
                    + customer.getAddress() + "," + customer.getPhone() + ","
                    + customer.getNumberOfRental() + "," + customer.getCustomerType() + ","
                    + customer.getUserName() + "," + customer.getPassword() + "\n");

            for (int z = 0; z < customer.getRentalList_String().size(); z++) {
                pw.write(customer.getRentalList_String().get(z) + "\n");
            }
        }


        pw.close();
    }

    //WRITE ALL ITEMS INTO FILE NAME items.txt AND CONVERT ALL THE OBJECTS INTO THEIR APPROPIATE CONTENTS
    public void writeFileItemTxtM(ArrayList<Item> itemList) throws IOException {
        PrintWriter pw2 = new PrintWriter(new FileWriter(items_Filename));
        for (Item item : itemList) {
            if (item.getRentType().compareTo("Game") != 0) {
                pw2.write(item.getItemID() + "," + item.getItemTitle() + "," +
                        item.getRentType() + "," + item.getLoanType() + ","
                        + item.getQuantity() + "," + item.getFee() + ","
                        + item.getGenre() + "\n");
            } else {
                pw2.write(item.getItemID() + "," + item.getItemTitle() + "," +
                        item.getRentType() + "," + item.getLoanType() + ","
                        + item.getQuantity() + "," + item.getFee() + "\n");
            }

        }
        pw2.close();

    }
}
