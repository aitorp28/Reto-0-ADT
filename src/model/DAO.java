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
     * Method for searchig a customer's accounts
     *
     * @return the accounts of the customer, if any
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Collection<Account> listAccount() throws ConnectException, ReadException; //

    /**
     * Method for creating a new Account on the DDBB
     *
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public void createAccount() throws ConnectException, CreateException, ReadException;

    /**
     * Method for linking a Customer with an Account on the DDBB
     *
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     */
    public void addCustomerToAccount() throws ConnectException, CreateException;

    /**
     *
     * Method for reading the data of a Customer from the DDBB
     *
     * @return the wanted account, if it exists
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Account readAccount() throws ConnectException, ReadException; //
    // SQL Related to Customer

    /**
     * Method for creating a new Customer on the DDBB
     *
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     */
    public void createCustomer() throws ConnectException, CreateException;

    /**
     * Method for reading the data of a Customer from the DDBB
     *
     * @return the wanted customer, if it exists
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Customer searchCustomer() throws ConnectException, ReadException; //
    // SQL Related to Movement

    /**
     * Method for creating a new Movement on the DDBB
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     * @throws ReadException if failing to read from the DDBB
     * @throws UpdateException if failing to update values on the DDBB
     */
    public void createMovement() throws ConnectException, CreateException, ReadException, UpdateException;

    /**
     * Method for searchig an  account's moivements
     * @return the movements of the account, if any
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    public Collection<Movement> listMovement() throws ConnectException, ReadException;
}
