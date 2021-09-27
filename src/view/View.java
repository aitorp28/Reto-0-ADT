/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Collection;
import model.*;

/**
 *
 * @author Jaime
 */
public interface View {
    
    // Main APP Menu

    /**
     *
     * @return
     */
    public int menu();
    // SQL Method Return

    /**
     *
     * @param cust
     */
    public void searchCustomer(Customer cust);

    /**
     *
     * @param accounts
     */
    public void listAccount(Collection<Account> accounts);

    /**
     *
     * @param acc
     */
    public void readAccount(Account acc);

    /**
     *
     * @param move
     */
    public void listMovement(Collection<Movement> move);
    // Extra info Messages

    /**
     *
     * @param message
     */
    public void message(String message);
}
