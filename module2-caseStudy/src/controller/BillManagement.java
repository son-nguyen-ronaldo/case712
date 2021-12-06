package controller;

import model.Account;
import model.Bill;
import model.Computer;
import model.Service;

import java.util.List;
import java.util.Scanner;

import static model.Const.*;

public class BillManagement {
    Scanner scanner = new Scanner(System.in);
    static IOFile<Bill> billIOFile = new IOFile<>();
    static List<Bill> bills = billIOFile.readDataFromFile("data/bills.txt");

    public int checkOutServicePrice(int index) {
        int totalServicePrice = 0;
        for (Service service : ComputerManagement.computers.get(index).getServices()) {
            totalServicePrice += service.getPrice();
        }
        return totalServicePrice;
    }

    public void checkOutBill(int index) {
        System.out.print("Enter total playing time: ");
        int playTime = scanner.nextInt();
        scanner.nextLine();
        Computer computer = ComputerManagement.computers.get(index);
        int totalPrice = (playTime * PLAYTIME_UNIT_PRICE) + checkOutServicePrice(index);
        if (getBalance(index) >= totalPrice) {
            int newBalance = getBalance(index) - totalPrice;
            setNewBalance(index, newBalance);
        } else {
            System.out.println();
            System.out.println("<<< Your account's balance is not enough money! >>>");
            System.out.println("<<<         Please pay cash directory!          >>>");
        }
        Account account = ComputerManagement.computers.get(index).getAccount();
        Bill bill = new Bill(computer, account, playTime, totalPrice);
        bills.add(bill);
        billIOFile.writeDataToFile(bills, "data/bills.txt");
        System.out.println(bill);
    }

    private void setNewBalance(int index, int newBalance) {
        ComputerManagement.computers.get(index).getAccount().setBalance(newBalance);
    }

    private int getBalance(int index) {
        return ComputerManagement.computers.get(index).getAccount().getBalance();
    }

    public void checkRevenue() {
        int total = 0;
        for (Bill bill : bills) {
            System.out.println(bill);
            total += bill.getTotalPrice();
        }
        System.out.println("--------------------------------------------");
        System.out.println("     The total revenue today is: " + total);
        System.out.println("--------------------------------------------");
    }
}
