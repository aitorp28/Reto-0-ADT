/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.*;
import java.util.Collection;
import java.util.TreeSet;
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

    private final String IDCustomer = "Introduzca la ID del Customer";
    private final String IDAccount = "Introduzca la ID de la Account";
    private DAO dao;
    private View view;
    
    /**
     * Method to control the flux of the application
     * @param view implementation of the desired view type
     * @param dao implementation of the desired dao type
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws CreateException if the model fails trying to write on the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB
     * @throws UpdateException if the model fails updating data on the DDBB
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
                    searchCustomer();
                    break;
                case 3:
                    listAccount();
                    break;
                case 4:
                    createAccount();
                    break;
                case 5:
                    addCustomerToAccount();
                    break;
                case 6:
                    readAccount();
                    break;
                case 7:
                    createMovement();
                    break;
                case 8:
                    listMovement();
                    break;
                case 9:
                    view.message("Fin del Programa");
                    break;
            }
        } while (opc != 9);
    }

    /**
     * Method to control the creation of a new Customer
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws CreateException  if the model fails trying to write on the DDBB
     */
    private void createCustomer() throws ConnectException, CreateException {
        Customer cus = view.createCustomer();
        dao.createCustomer(cus);
    }

    /**
     * Method to control the reading of data of a Customer
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB
     */
    private void searchCustomer() throws ConnectException, ReadException {
        long cust = view.askId(IDCustomer);
        Customer cus = dao.searchCustomer(cust);
        view.searchCustomer(cus);
    }

    /**
     * Method to control the listing of the Accounts of a Customer
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB 
     */
    private void listAccount() throws ConnectException, ReadException {
        long id = view.askId(IDAccount);
        Collection<Account> accounts = dao.listAccount(id);
        view.listAccount(accounts);
    }

    /**
     * Method to control the creation of a new Account
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws CreateException if the model fails trying to write on the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB 
     */
    private void createAccount() throws ConnectException, CreateException, ReadException {
        long clie = view.askId(IDCustomer);
        if (dao.existingCustomer(clie)) {
        Account acc = view.createAccount();
        dao.createAccount(clie, acc);
        } else {
            view.message("No existe ningún Customer con esa ID");
        }
    }

    /**
     * Method to control the linking of a Customer with an Account
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws CreateException if the model fails trying to write on the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB 
     */
    private void addCustomerToAccount() throws ConnectException, CreateException, ReadException {
        long cus = view.askId(IDCustomer);
        long acc = view.askId(IDAccount);
        if (dao.existingCustomer(cus) && dao.existingAccount(acc))
        dao.addCustomerToAccount(cus, acc);
    }

    /**
     * Method to control the reading of data od an Account
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB 
     */
    private void readAccount() throws ConnectException, ReadException {
        long id = view.askId(IDAccount);
        Account account = dao.readAccount(id);
        view.readAccount(account);
    }

    /**
     * Method to control the creation of a new Movement
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws CreateException if the model fails trying to write on the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB
     * @throws UpdateException if the model fails updating data on the DDBB
     */
    private void createMovement() throws ConnectException, CreateException, ReadException, UpdateException {
        long id = view.askId(IDAccount);
        Account acc = dao.readAccount(id);
        if (acc != null) {
        Movement mov = view.createMovement(acc);
        dao.createMovement(acc, mov);
        } else {
            view.message("No existe ninguna Account con esa ID");
        }
    }

    /**
     * Method to control the listing of Movements of an Account
     * @throws ConnectException if the model cannot connect to the DDBB
     * @throws ReadException if the model fails trying to read from the DDBB 
     */
    private void listMovement() throws ConnectException, ReadException {
        Collection<Movement> movements = new TreeSet<>();
        long id = view.askId(IDAccount);
        movements = dao.listMovement(id);
        view.listMovement(movements);
    }
}
