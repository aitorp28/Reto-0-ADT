/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logical;

import exception.*;
import java.util.Collection;
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author 2dam
 */
public interface DAO {
    // SQL Related to Account
    public Collection <Account> listAccount(); //
    public void createAccount() throws ConnectException, CreateException ;
    public void addCustomerToAccount();
    public Account readAccount(); //
    // SQL Related to Customer
    public void createCustomer();
    public Customer searchCustomer(); //
    // SQL Related to Movement
    public void newMovement();
    public Collection <Movement> searchMovement();
}
