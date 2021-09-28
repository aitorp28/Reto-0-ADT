/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Collection;
import model.*;

/**
 * Class designed to control the traffic of information between program and user
 * @author Jaime San Sebastián
 */
public interface View {
    
    // Main APP Menu

    /**
     * Method to show the options to the user
     * @return
     */
    public int menu();
    // SQL Method Return

    /**
     * Method to show the data of a Customer
     *
     * @param cust received from the model, is the complete personal information
     * of the Customer (no Accounts data)
     */
    public void searchCustomer(Customer cust);

    /**
     * Method to show the Accounts linked to a Customer
     *
     * @param accounts received from the model, is the set of Accounts linked to
     * the desired Customer
     */
    public void listAccount(Collection<Account> accounts);

    /**
     * Method to show the data of an Account
     *
     * @param acc received from the model, is the complete information of the
     * Account
     */
    public void readAccount(Account acc);

    /**
     * Method to show the Movements linked to an Account
     *
     * @param move received from the model, is the set of Movements linked to
     * the desired Account
     */
    public void listMovement(Collection<Movement> move);
    // Extra info Messages

    /**
     * Auxiliar method to display information messages and ask for inputs
     *
     * @param message
     */
    public void message(String message);
}
