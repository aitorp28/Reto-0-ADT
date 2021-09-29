/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import model.*;
import utilities.Utilities;

/**
 * Class designed to control the traffic of information between program and user
 *
 * @author Aitor Pérez
 */
public class ConsoleView implements View {

    /**
     * Method to show the options to the user
     *
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
     * Method to ask to the user the Customer Data
     *
     * @return the Customer ready to record in in the DDBB
     */
    @Override
    public Customer createCustomer() {
        Customer cust = setCustomerData();
        return cust;
    }

    /**
     * Method to show the data of a Customer
     *
     * @param cust received from the model, is the complete personal information
     * of the Customer (no Accounts data)
     */
    @Override
    public void searchCustomer(Customer cust) {
        if (cust != null) {
            System.out.println(cust.toString());
        } else {
            System.out.println("No se ha encontrado ningún Customer con esa ID");
        }
    }

    /**
     * Method to show the Accounts linked to a Customer
     *
     * @param accounts received from the model, is the set of Accounts linked to
     * the desired Customer
     */
    @Override
    public void listAccount(Collection<Account> accounts) {
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                System.out.println(account.toString());
            }
        } else {
            System.out.println("No se ha encontrado ninguna Account para ese Customer ID");
        }
    }

    /**
     * Method to ask to the user the Customer Data
     *
     * @return the Customer ready to record in in the DDBB
     */
    @Override
    public Account createAccount() {
        Account acc = setAccountData();
        return acc;
    }

    /**
     * Method to show the data of an Account
     *
     * @param acc received from the model, is the complete information of the
     * Account
     */
    @Override
    public void readAccount(Account acc) {
        if (acc != null) {
            System.out.println(acc.toString());
        } else {
            System.out.println("No se ha encontrado ninguna Account con esa ID");
        }
    }

    /**
     * Method to show the Movements linked to an Account
     *
     * @param move received from the model, is the set of Movements linked to
     * the desired Account
     */
    @Override
    public void listMovement(Collection<Movement> move) {
        if (!move.isEmpty()) {
            for (Movement movement : move) {
                movement.toString();
            }
        } else {
            System.out.println("No se ha encontrado ningún Movement para esa Account ID");
        }
    }

     /**
     * Method to ask the user for the Movement Data
     * @param acc the account the Movement will be linked to
     * @return a Movement ready to record it on the DDB
     */
    @Override
    public Movement createMovement(Account acc) {
        long id = acc.getId();
        double bal = acc.getBalance();
        Movement mov = setMovementData(id, bal);
        return mov;
    }

    // METODOS DE INTERACCIÓN CON OBJETOS DEL MODELO
    /**
     * Method designet to create a new Customer in the local storage previous to
     * save it on the DDBB
     *
     * @return the Customer ready to be recorded
     */
    public Customer setCustomerData() {
        Customer cust = new Customer();
        cust.setCity(Utilities.leerString("Introduzca la ciudad del Customer"));
        cust.setEmail(Utilities.leerString("Introduzca el email del Customer"));
        cust.setFirstName(Utilities.leerString("Introduzca el nombre del Customer"));
        cust.setLastName(Utilities.leerString("Introduzca el Apellido del Customer"));
        cust.setMiddleInitial(Utilities.leerString("Introduzca posibles iniciales del Customer"));
        cust.setPhone(Utilities.leerLong("Introduzca el teléfono del Customer"));
        cust.setState(Utilities.leerString("Introduzca el estado/provincia donde vive del Customer"));
        cust.setStreet(Utilities.leerString("Introduzca el país del Customer"));
        cust.setZip(Utilities.leerInt("Introduzca el Código Postal del Customer"));
        return cust;
    }

    /**
     * Method designet to create a new Account in the local storage previous to
     * save it on the DDBB
     * @return the Account ready to be recorded
     */
    public Account setAccountData() {
        Account acc = new Account();
        acc.setBeginBalance(Utilities.leerDouble("Introduzca el importe inicial de la cuenta"));
        acc.setBalance(acc.getBeginBalance());
        acc.setCreditLine(Utilities.leerDouble("Introduzca el crédito de la cuenta de la cuenta"));
        acc.setDescription(Utilities.leerString("Introduzca una descripción de la cuenta"));
        acc.setType(Utilities.leerInt(0, 1, "Indique el tipo de la cuenta:\n"
                + "0 - STANDARD\n"
                + "1 - CREDIT"));
        acc.setBeginBalanceTimeStamp(Timestamp.from(Instant.now()));
        return acc;
    }
    
    /**
     * Method for recording the data of a new Movement
     *
     * @param acc previously checked existing Account to link the Movement to
     * @param bal actual balance of the Account the Movement will be linked to
     * @return the Movement ready to be recorded
     */
    public Movement setMovementData(long acc, double bal) {
        Movement mov = new Movement();
        mov.setAccount_id(acc);
        mov.setAmount(Utilities.leerDouble("Introduzca el importe a mover (positivo para ingresos, negativo para retirar)"));
        mov.setBalance(bal + mov.getAmount());
        mov.setDescription(Utilities.leerString("Introduzca una breve descripción de la cuenta"));
        return mov;
    }

    /**
     * Auxiliar Method to ask the  user an ID
     * @param mensaje displayed to the user to know the type of ID asked
     * @return the ID given by the user
     */
    @Override
    public long askId(String mensaje) {
        long str = Utilities.leerLong(mensaje);
        return str;
    }

    /**
     * Auxiliar method to display information messages and ask for inputs
     *
     * @param message
     */
    @Override
    public void message(String message) {
        System.out.println(message);
    }
    
}
