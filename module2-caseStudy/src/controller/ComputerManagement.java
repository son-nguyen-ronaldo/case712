package controller;

import model.Account;
import model.Computer;
import model.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static model.Const.*;

public class ComputerManagement implements IGeneralManagement<Computer> {
    Scanner scanner = new Scanner(System.in);
    BillManagement billManagement = new BillManagement();
    static IOFile<Computer> computerIOFile = new IOFile<>();
    static List<Computer> computers = computerIOFile.readDataFromFile("data/computers.txt");
    static IOFile<Account> accountIOFile = new IOFile<>();
    static List<Account> accounts = accountIOFile.readDataFromFile("data/accounts.txt");

    public ComputerManagement() {
    }

    @Override
    public void display() {
        for (Computer pc : computers) {
            System.out.println(pc);
        }
    }

    @Override
    public Computer inputInfoFromKeyboard() {
        String id = "";
        do {
            System.out.print("Enter new ID: ");
            id = scanner.nextLine();
            if (id.equals("")) {
                System.out.println("<<< The input field is empty! Please try again! >>>");
            } else if (isExisted(id)) {
                System.out.println("<<< ID already exist! Please try again! >>>");
            }
        } while (id.equals("") || isExisted(id));
        return new Computer(id);
    }

    @Override
    public void add() {
        Computer computer = inputInfoFromKeyboard();
        computers.add(computer);
        computerIOFile.writeDataToFile(computers, "data/computers.txt");
    }

    @Override
    public void edit() {
        System.out.print("Enter ID of computer you wanna edit: ");
        String id = scanner.nextLine();
        int index = findIndex(id);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            Computer computer = inputInfoFromKeyboard();
            computers.set(index, computer);
            computerIOFile.writeDataToFile(computers, "data/accounts.txt");
        } else {
            System.out.println("<<< ID not found! >>>");
        }
    }

    @Override
    public void remove() {
        System.out.print("Enter ID of computer you wanna remove: ");
        String id = scanner.nextLine();
        int index = findIndex(id);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            computers.remove(index);
            computerIOFile.writeDataToFile(computers, "data/accounts.txt");
        } else {
            System.out.println("<<< ID not found! >>>");
        }
    }

    @Override
    public int findIndex(String id) {
        int index = OUT_OF_INDEX_BOUNDARY;
        for (int i = 0; i < computers.size(); i++) {
            if (computers.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean isExisted(String id) {
        for (Computer pc : computers) {
            if (pc.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAvailableComputer(int index) {
        return computers.get(index).getStatus().equals(ONLINE);
    }

    public void startPlaying() {
        System.out.print("Enter ID of computer which start playing: ");
        String id = scanner.nextLine();
        int index = findIndex(id);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            if (!isAvailableComputer(index)) {
                computers.get(index).setStatus(ONLINE);
                computers.get(index).setAccount(accounts.get(new Random().nextInt(accounts.size())));
                computerIOFile.writeDataToFile(computers, "data/computers.txt");
            } else {
                System.out.println("<<< This computer is already available! Please try another! >>>");
            }
        } else {
            System.out.println("<<< ID not found! >>>");
        }
    }

    public void addService() {
        System.out.print("Enter ID of computer you wanna add service: ");
        String id = scanner.nextLine();
        int index = findIndex(id);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            if (isAvailableComputer(index)) {
                System.out.print("Enter service's name: ");
                String serviceName = scanner.nextLine();
                System.out.print("Enter service's price: ");
                int price = scanner.nextInt();
                scanner.nextLine();
                Service service = new Service(serviceName, price);
                computers.get(index).getServices().add(service);
                computerIOFile.writeDataToFile(computers, "data/computers.txt");
            } else {
                System.out.println("<<< Cannot add due to computer is disable! >>>");
            }
        } else {
            System.out.println("<<< ID not found! >>>");
        }
    }

    public void endPlaying() {
        System.out.print("Enter ID of computer which end playing: ");
        String id = scanner.nextLine();
        int index = findIndex(id);
        if (index != OUT_OF_INDEX_BOUNDARY) {
            if (isAvailableComputer(index)) {
                billManagement.checkOutBill(index);
                checkOutComputer(index);
            } else {
                System.out.println("<<< This computer is already disable! Please try another! >>>");
            }
        } else {
            System.out.println("<<< ID not found! >>>");
        }
    }

    public void checkOutComputer(int index) {
        computers.get(index).setStatus(OFFLINE);
        computers.get(index).setAccount(null);
        computers.get(index).setServices(new ArrayList<>());
        computerIOFile.writeDataToFile(computers, "data/computers.txt");
    }
}
