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

    /**
     * Method to control the flux of the application
     * @param view implementation of the desired view type
     * @param dao implementation of the desired dao type
     * @throws ConnectException if cannot connect to the DDBB
     * @throws CreateException if fails trying to write on the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    public void run(View view, DAO dao) throws ReadException, ConnectException, CreateException, UpdateException {
        int opc = 0;
        do {
            opc = view.menu();
            switch (opc) {
                case 1:
                    dao.createCustomer();
                    break;
                case 2:
                    Customer cus = dao.searchCustomer();
                    view.searchCustomer(cus);
                    break;
                case 3:
                    Collection<Account> accounts = dao.listAccount();
                    view.listAccount(accounts);
                    break;
                case 4:
                    dao.createAccount();
                    break;
                case 5:
                    dao.addCustomerToAccount();
                    break;
                case 6:
                    Account account = dao.readAccount();
                    view.readAccount(account);
                    break;
                case 7:
                    dao.createMovement();
                    break;
                case 8:
                    Collection<Movement> mov = dao.listMovement();
                    view.listMovement(mov);
                    break;
                case 9:
                    view.message("Fin del Programa");
                    break;
            }
        } while (opc != 9);
    }
}
