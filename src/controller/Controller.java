/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.*;
import java.util.Collection;
import model.DAO;
import view.View;
import model.Account;
import model.Customer;
import model.Movement;

/**
 * Control and link of the different levels of the application
 * @author Jaime San Sebastián
 */
public class Controller {

    private DAO dao;
    private View view;
    
    /**
     * Method to control the flux of the application
     * @param view implementation of the desired view type
     * @param dao implementation of the desired dao type
     * @throws ConnectException if cannot connect to the DDBB
     * @throws CreateException if fails trying to write on the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    public void run(View view, DAO dao) throws ReadException, ConnectException, CreateException, UpdateException {
        this.dao = dao;
        this.view = view;
        int opc = 0;
        do {
            opc = view.menu();
            switch (opc) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    Customer cus = searchCustomer();
                    break;
                case 3:
                    Collection<Account> accounts = listAccount();
                    view.listAccount(accounts);
                    break;
                case 4:
                    createAccount();
                    break;
                case 5:
                    addCustomerToAccount();
                    break;
                case 6:
                    Account account = readAccount();
                    view.readAccount(account);
                    break;
                case 7:
                    createMovement();
                    break;
                case 8:
                    Collection<Movement> mov = listMovement();
                    view.listMovement(mov);
                    break;
                case 9:
                    view.message("Fin del Programa");
                    break;
            }
        } while (opc != 9);
    }

    private void createCustomer() {
        dao.createCustomer();
    }

    private Customer searchCustomer() {
    
                    view.searchCustomer(cus);
    }

    private Collection<Account> listAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addCustomerToAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Account readAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createMovement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Collection<Movement> listMovement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
