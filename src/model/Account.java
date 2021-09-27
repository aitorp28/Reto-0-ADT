/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import utilities.Utilities;

/**
 *
 * @author 2dam
 */
public class Account implements Serializable {

    private long id;
    private double balance;
    private double beginBalance;
    private Timestamp beginBalanceTimeStamp;
    private double creditLine;
    private String description;
    private AccountType type;

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
    public Timestamp getBeginBalanceTimeStamp() {
        return beginBalanceTimeStamp;
    }

    /**
     * @param beginBalanceTimeStamp the beginBalanceTimeStamp to set
     */
    public void setBeginBalanceTimeStamp(Timestamp beginBalanceTimeStamp) {
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
        return this.type.ordinal();
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        if (type == 0) {
            this.type = AccountType.STANDARD;
        } else {
            this.type = AccountType.CREDIT;
        }
    }

    public void setData() {
        System.out.println("Introduzca el importe inicial de la cuenta");
        this.setBeginBalance(Utilities.leerDouble());
        this.setBalance(this.getBeginBalance());
        System.out.println("Introduzca el crédito de la cuenta de la cuenta");
        this.setCreditLine(Utilities.leerDouble());
        System.out.println("Introduzca una descripción de la cuenta");
        this.setDescription(Utilities.leerString(10000000));
        System.out.println("Indique el tipo de la cuenta:\n"
                + "0 - Débito\n"
                + "1 - Crédito");
        this.setType(Utilities.leerInt(0, 1));
        this.setBeginBalanceTimeStamp(Timestamp.from(Instant.now()));
    }

    public static long askId() {
        long id = Utilities.leerInt("Introduzca la id del usuarios a buscar");
        return id;
    }

}
