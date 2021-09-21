/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author 2dam
 */
public class Account {
    private int id;
    private double balance;
    private double beginBalance;
    private LocalDate beginBalanceTimeStamp;
    private double creditLine;
    private String description;
    private int type;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the beginBalance
     */
    public double getBeginBalance() {
        return beginBalance;
    }

    /**
     * @param beginBalance the beginBalance to set
     */
    public void setBeginBalance(double beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * @return the beginBalanceTimeStamp
     */
    public LocalDate getBeginBalanceTimeStamp() {
        return beginBalanceTimeStamp;
    }

    /**
     * @param beginBalanceTimeStamp the beginBalanceTimeStamp to set
     */
    public void setBeginBalanceTimeStamp(LocalDate beginBalanceTimeStamp) {
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }

    /**
     * @return the creditLine
     */
    public double getCreditLine() {
        return creditLine;
    }

    /**
     * @param creditLine the creditLine to set
     */
    public void setCreditLine(double creditLine) {
        this.creditLine = creditLine;
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
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    
    
}
