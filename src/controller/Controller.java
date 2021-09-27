/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exception.*;
import logical.DAO;
import logical.View;

/**
 *
 * @author Ordenador
 */
public class Controller {

    public void run(View view, DAO dao) throws ReadException, ConnectException, CreateException {
        int opc = 0;
        do {
            opc = view.menu();
            switch (opc) {
                case 1:
                    dao.createCustomer();
                    break;
                case 2:
                    dao.searchCustomer();
                    break;
                case 3:
                    dao.listAccount();
                    break;
                case 4:
                    dao.createAccount();
                    break;
                case 5:
                    dao.addCustomerToAccount();
                    break;
                case 6:
                    dao.readAccount();
                    break;
                case 7:
                    dao.createMovement();
                    break;
                case 8:
                    dao.listMovement();
                    break;
                case 9:
                    view.message("Fin del Programa");
                    break;
            }
        } while (opc != 9);
    }
}
