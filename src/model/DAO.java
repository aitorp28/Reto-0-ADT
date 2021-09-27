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
 * @author Jaime 
 */
public interface DAO {

    /**
     *
     * @return
     * @throws ConnectException
     * @throws ReadException
     */
    public Collection <Account> listAccount() throws ConnectException, ReadException ; //

    /**
     *
     * @throws ConnectException
     * @throws CreateException
     * @throws ReadException
     */
    public void createAccount() throws ConnectException, CreateException, ReadException ;

    /**
     *
     * @throws ConnectException
     * @throws CreateException
     */
    public void addCustomerToAccount() throws ConnectException, CreateException ;

    /**
     *
     * @return
     * @throws ConnectException
     * @throws ReadException
     */
    public Account readAccount() throws ConnectException, ReadException ; //
    // SQL Related to Customer

    /**
     *
     * @throws ConnectException
     * @throws CreateException
     */
    public void createCustomer() throws ConnectException, CreateException ;

    /**
     *
     * @return
     * @throws ConnectException
     * @throws ReadException
     */
    public Customer searchCustomer() throws ConnectException, ReadException ; //
    // SQL Related to Movement

    /**
     *
     * @throws ConnectException
     * @throws CreateException
     * @throws exception.ReadException
     */
    public void createMovement() throws ConnectException, CreateException, ReadException, UpdateException ;

    /**
     *
     * @return
     * @throws ConnectException
     * @throws ReadException
     */
    public Collection <Movement> listMovement() throws ConnectException, ReadException ;
}
