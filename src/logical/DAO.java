/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logical;

import java.util.List;
import model.Account;
import model.Customer;
import model.Movement;

/**
 *
 * @author 2dam
 */
public interface DAO {
    // SQL Related to Account
    public List <Account> searchAccount();
    public void createAccount();
    public void addCustomerToAccount();
    public Account readAccount();
    // SQL Related to Customer
    public void createCustomer();
    public Customer searchCustomer();
    // SQL Related to Movement
    public void newMovement();
    public List <Movement> searchMovement();
}
