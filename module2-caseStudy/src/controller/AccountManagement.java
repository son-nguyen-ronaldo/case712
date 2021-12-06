package controller;

import model.Account;

import java.util.List;
import java.util.Scanner;

import static model.Const.*;

public class AccountManagement implements IGeneralManagement<Account> {
    Scanner scanner = new Scanner(System.in);
    static IOFile<Account> accountIOFile = new IOFile<>();
    static List<Account> accounts = accountIOFile.readDataFromFile("data/accounts.txt");

    public AccountManagement() {
    }

    @Override
    public void display() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    @Override
    public Account inputInfoFromKeyboard() {
        String userName = inputUsername();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter balance: ");
        int balance = scanner.nextInt();
        scanner.nextLine();
        return new Account(userName, password, balance);
    }

    @Override
    public void add() {
        Account account = inputInfoFromKeyboard();
        accounts.add(account);
        accountIOFile.writeDataToFile(accounts, "data/accounts.txt");
    }

    @Override
    public void edit() {
        System.out.print("Enter userName of account you wanna edit: ");
        String userName = scanner.nextLine();
        int index = findIndex(userName);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            System.out.print("Enter new userName: ");
            String newUserName = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Enter new balance: ");
            int newBalance = scanner.nextInt();
            scanner.nextLine();
            Account account = new Account(newUserName, newPassword, newBalance);
            accounts.set(index, account);
            accountIOFile.writeDataToFile(accounts, "data/accounts.txt");
        } else {
            System.out.println("<<< Username not found! >>>");
        }
    }

    @Override
    public void remove() {
        System.out.print("Enter userName of account you wanna remove: ");
        String userName = scanner.nextLine();
        int index = findIndex(userName);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            accounts.remove(index);
            accountIOFile.writeDataToFile(accounts, "data/accounts.txt");
        } else {
            System.out.println("<<< Username not found! >>>");
        }
    }

    @Override
    public int findIndex(String userName) {
        int index = OUT_OF_INDEX_BOUNDARY;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(userName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean isExisted(String userName) {
        for (Account acc : accounts) {
            if (acc.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    private String inputUsername() {
        String userName = "";
        do {
            System.out.print("Enter userName: ");
            userName = scanner.nextLine();
            if (userName.equals("")) {
                System.out.println("<<< The input field is empty! Please try again! >>>");
            } else if (isExisted(userName)) {
                System.out.println("<<< Username already exist! Please try again! >>>");
            }
        } while (userName.equals("") || isExisted(userName));
        return userName;
    }

    public void recharge() {
        System.out.print("Enter userName of account you wanna recharge: ");
        String userName = scanner.nextLine();
        int index = findIndex(userName);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            System.out.print("Enter amount of money for recharging: ");
            int newBalance = scanner.nextInt();
            scanner.nextLine();
            int oldBalance = accounts.get(index).getBalance();
            accounts.get(index).setBalance(newBalance+oldBalance);
            accountIOFile.writeDataToFile(accounts, "data/accounts.txt");
        } else {
            System.out.println("<<< Username not found! >>>");
        }
    }
}
