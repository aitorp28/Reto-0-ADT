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
 * @author Aitor Pérez
 */
public class ConsoleView implements View {

    /**
     * Method to show the options to the user
     * @return the input from the client to the controller
     */
    @Override
    public int menu() {
        int opc = utilities.Utilities.leerInt("MENÚ"
                + "\n1.- Crear cliente (customer)\n"
                + "2.- Consultar datos de un cliente.\n"
                + "3.- Consultar cuentas (account) de un cliente.\n"
                + "4.- Crear cuenta para cliente.\n"
                + "5.- Agregar cliente a cuenta.\n"
                + "6.- Consultar datos de una cuenta.\n"
                + "7.- Realizar movimiento (movement) sobre una cuenta.\n"
                + "8.- Consultar movimientos de una cuenta.\n"
                + "9.- SALIR");
        return opc;
    }

    /**
     * Method to show the data of a Customer
     * @param cust received from the model, is the complete personal information of the Customer (no Accounts data)
     */
    @Override
    public void searchCustomer(Customer cust) {
        System.out.println(cust.toString());
    }

    /**
     * Method to show the Accounts linked to a Customer
     * @param accounts received from the model, is the set of Accounts linked to the desired Customer
     */
    @Override
    public void listAccount(Collection<Account> accounts) {
        for (Account account : accounts) {
             System.out.println(account.toString());
        }
    }

    /**
     * Method to show the data of an Account
     * @param acc received from the model, is the complete information of the Account
     */
    @Override
    public void readAccount(Account acc) {
         System.out.println(acc.toString());
    }

    /**
     * Method to show the Movements linked to an Account
     * @param move received from the model, is the set of Movements linked to the desired Account
     */
    @Override
    public void listMovement(Collection<Movement> move) {
        for (Movement movement : move) {
             System.out.println(movement.toString());
        }
    }

    /**
     * Auxiliar method to display information messages and ask for inputs
     * @param message
     */
    @Override
    public void message(String message) {
         System.out.println(message);
    }
}
