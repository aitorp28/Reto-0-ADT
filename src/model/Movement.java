/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import utilities.Utilities;

/**
 *
 * @author 2dam
 */
public class Movement implements Serializable{
    private long id;
    private double amount;
    private double balance;
    private String description;
    private LocalDate timeStamp;
    private long account_id;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the timeStamp
     */
    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the account_id
     */
    public long getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
    
    public void setData(long acc) {
        this.setAccount_id(acc);
        System.out.println("Introduzca el importe inicial de la cuenta");
        this.setAmount(Utilities.leerDouble());
        this.setBalance(Utilities.leerDouble());
        System.out.println("Introduzca una descripción de la cuenta");
        this.setDescription(Utilities.leerString(10000000));
    }
}
