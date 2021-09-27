/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Collection;
import logical.View;
import model.*;

/**
 *
 * @author Ordenador
 */
public class ConsoleView implements View {

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

    @Override
    public void searchCustomer(Customer cust) {
        System.out.println(cust.toString());
    }

    @Override
    public void listAccount(Collection<Account> accounts) {
        for (Account account : accounts) {
             System.out.println(account.toString());
        }
    }

    @Override
    public void readAccount(Account acc) {
         System.out.println(acc.toString());
    }

    @Override
    public void searchMovement(Collection<Movement> move) {
        for (Movement movement : move) {
             System.out.println(movement.toString());
        }
    }

    @Override
    public void message(String message) {
         System.out.println(message);
    }
}
