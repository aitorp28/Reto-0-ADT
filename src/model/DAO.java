/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.*;
import java.util.Collection;

/**
 * Class designed to controll the interactions with the data
 *
 * @author Jaime San Sebastián
 */
public interface DAO {

    /**
     * Method for creating a new Customer on the DDBB
     *
     * @param cust Customer ready to be recorded
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     */
    public void createCustomer(Customer cust) throws ConnectException, CreateException;

    /**
     * Method for reading the data of a Customer from the DDBB
     *
     * @param cus id of the Customer
     * @return the wanted customer, if it exists
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Customer searchCustomer(long cus) throws ConnectException, ReadException;

    /**
     * Method for searchig a customer's accounts
     *
     * @param id of the Customer
     * @return the accounts of the customer, if any
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Collection<Account> listAccount(long id) throws ConnectException, ReadException; //

    /**
     * Method for creating a new Account on the DDBB
     *
     * @param cus id of the Customer
     * @param acc Account ready to be recorded
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public void createAccount(long cus, Account acc) throws ConnectException, CreateException, ReadException;

    /**
     * Method for linking a Customer with an Account on the DDBB
     *
     * @param cus id of the Customer
     * @param acc id of the Account
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     */
    public void addCustomerToAccount(long cus, long acc) throws ConnectException, CreateException;

    /**
     *
     * Method for reading the data of a Customer from the DDBB
     *
     * @param id id of the Account
     * @return the wanted account, if it exists
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Account readAccount(long id) throws ConnectException, ReadException; //
    // SQL Related to Customer

    //
    // SQL Related to Movement
    /**
     * Method for creating a new Movement for an Account on the DDBB
     *
     * @param acc Account to be updated
     * @param mov Movement to be recorded
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     * @throws ReadException if failing to read from the DDBB
     * @throws UpdateException if failing to update values on the DDBB
     */
    public void createMovement(Account acc, Movement mov) throws ConnectException, CreateException, ReadException, UpdateException;

    /**
     * Method for searchig an Account's Movements
     *
     * @param acc id of the Account
     * @return the movements of the account, if any
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Collection<Movement> listMovement(long acc) throws ConnectException, ReadException;

    /**
     * Method to check the existence of a Account based on an ID
     *
     * @param acc the id given by the user
     * @return the existence ot the Account on a boolean
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public boolean existingAccount(long acc) throws ConnectException, ReadException;

    /**
     * Method to check the existence of a Customer based on an ID
     *
     * @param clie the id given by the user
     * @return the existence ot the Customer on a boolean
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public boolean existingCustomer(long clie) throws ConnectException, ReadException;

}
