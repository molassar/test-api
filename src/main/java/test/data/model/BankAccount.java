package test.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {

    @Id
    private int number;
    private int balance;

    protected BankAccount() {}

    public BankAccount(int number, int balance) {
        this.number = number;
        this.balance = balance;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("BankAccount[number=%d, balance=%d]", number, balance);
    }

}
