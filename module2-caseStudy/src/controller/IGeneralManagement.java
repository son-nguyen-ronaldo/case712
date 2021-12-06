package controller;

public interface IGeneralManagement<E> {
    void display();

    E inputInfoFromKeyboard();

    void add();

    void edit();

    void remove();

    int findIndex(String s);

    boolean isExisted(String s);
}
