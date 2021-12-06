package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static model.Const.*;

public class Computer implements Serializable {
    private String id;
    private String status;
    private int playTime;
    private Account account;
    private List<Service> services = new ArrayList<>();

    public Computer() {
    }

    public Computer(String id) {
        this.id = id;
        this.status = OFFLINE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", user='" + account + '\'' +
                ", services=" + services +
                '}';
    }
}
