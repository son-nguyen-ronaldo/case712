package model;

import java.io.Serializable;

public class Bill implements Serializable {
    private Computer computer;
    private Account account;
    private int playTime;
    private int totalPrice;

    public Bill() {
    }

    public Bill(Computer computer, Account account, int playTime, int totalPrice) {
        this.computer = computer;
        this.account = account;
        this.playTime = playTime;
        this.totalPrice = totalPrice;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "computerID='" + computer.getId() + '\'' +
                ", " + account +
                ", " + computer.getServices() +
                ", playTime=" + playTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
