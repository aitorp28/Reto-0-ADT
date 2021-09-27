/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logical;

import java.util.Collection;
import model.*;

/**
 *
 * @author Ordenador
 */
public interface View {
    
    // Main APP Menu
    public int menu();
    // SQL Method Return
    public void searchCustomer(Customer cust);
    public void listAccount(Collection<Account> accounts);
    public void readAccount(Account acc);
    public void searchMovement(Collection<Movement> move);
    // Extra info Messages
    public void message(String message);
}
