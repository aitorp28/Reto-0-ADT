/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.*;
import utilities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implemented class to controll the interactions with the database
 *
 * @author Andoni Alday
 */
public class DAOImplement implements DAO {

    // Atributos
    private Connection con;
    private PreparedStatement stmt;
    private ConnectionOpenClose conection = new ConnectionOpenClose();
    private boolean exists;

    // SQL Querys
    private final String searchAccount = "SELECT * FROM account WHERE id = ?";
    private final String searchCustomer = "SELECT * FROM customer WHERE id = ?";
    private final String readAccount = "SELECT * FROM account WHERE id = ?";
    private final String readBalance = "SELECT balance FROM account WHERE account_id = ?";
    private final String updateAccount = "UPDATE account SET balance = ? WHERE id = ?";
    private final String createAccount = "INSERT INTO Account (balance, beginBalance, beginBalanceTimestamp,creditLine, description, `type`) VALUES (?,?,CURRENT_TIMESTAMP,?,?,?)";
    private final String createCustomer = "INSERT INTO Customer (city, email, firstName, lastName, middleInitial, phone, state, street, zip) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String createMovement = "INSERT INTO Customer (timestamp, amount, balance, description, account_id) VALUES (?,?,?,?)";
    private final String linkAccountCustomer = "INSERT INTO customer_account (?,?)";
    private final String listAccount = "SELECT account.* FROM account,customer_account WHERE Account.id = customer_account.accounts_id AND customer_account.customers_id = ?";
    private final String listMovement = "SELECT * FROM movement WHERE account_id = ?";
    
    // Crear Cliente 
    /**
     * Method to create a new Customer on the DDBB
     *
     * @throws ConnectException if cannot connect to the DDBB
     * @throws CreateException if fails trying to write on the DDBB
     */
    @Override
    public void createCustomer() throws ConnectException, CreateException {
        Customer cust = new Customer();
        cust.setData();
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            throw new ConnectException(ex.getMessage());
        }
        try {
            stmt = con.prepareStatement(createCustomer);
            stmt.setString(1, cust.getCity());
            stmt.setString(2, cust.getEmail());
            stmt.setString(3, cust.getFirstName());
            stmt.setString(4, cust.getLastName());
            stmt.setString(5, cust.getMiddleInitial());
            stmt.setInt(6, cust.getPhone());
            stmt.setString(7, cust.getState());
            stmt.setString(8, cust.getStreet());
            stmt.setInt(9, cust.getZip());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new CreateException("Error al Crear");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }
    }

    // Consultar Datos Cliente
    /**
     * Method to search for a Customer on the DDBB
     *
     * @return the desired customer
     */
    @Override
    public Customer searchCustomer() {
        Customer cus = null;
        long id = cus.askId();
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            new ConnectException("Error al Leer");
        }
        try {
            stmt = con.prepareStatement(searchCustomer);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                cus = new Customer();
                cus.setId(rs.getLong("id"));
                cus.setCity(rs.getString("city"));
                cus.setEmail(rs.getString("email"));
                cus.setFirstName(rs.getString("firstname"));
                cus.setLastName(rs.getString("lastname"));
                cus.setMiddleInitial(rs.getString("middelinitial"));
                cus.setPhone(rs.getInt("phone"));
                cus.setState(rs.getString("state"));
                cus.setStreet(rs.getString("street"));
                cus.setZip(rs.getInt("zip"));
            }
        } catch (Exception e) {
            new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                new ReadException("Error al Leer");
            }
        }
        return cus;
    }

    // Listar cuentas de Cliente
    /**
     * Method to list the Accounts from a Customer from the DDBB
     *
     * @return Collection of desired accounts
     */
    @Override
    public Collection<Account> listAccount() {
        Collection<Account> accounts = new TreeSet<>();
        Account acc = null;
        long id = acc.askId();
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            new ConnectException("Error al Leer");
        }
        try {
            stmt = con.prepareStatement(listAccount);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getLong("account.id"));
                acc.setBalance(rs.getDouble("account.balance"));
                acc.setBeginBalance(rs.getDouble("account.beginBalance"));
                acc.setBeginBalanceTimeStamp(rs.getTimestamp("account.beginBalanceTimestamp"));
                acc.setCreditLine(rs.getDouble("account.creditLibne"));
                acc.setDescription(rs.getString("account.description"));
                acc.setType(rs.getInt("account.type"));
                accounts.add(acc);
            }
        } catch (Exception e) {
            new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                new ReadException("Error al Leer");
            }
        }
        return accounts;

    }

    // Crear Cuenta para Cliente
    /**
     * Method to create a new Account on the DDBB
     *
     * @throws ConnectException if cannot connect to the DDBB
     * @throws CreateException if fails trying to write on the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    @Override
    public void createAccount() throws ConnectException, CreateException, ReadException {
        boolean exis = true;
        long clie;
        clie = Customer.askId();
        ResultSet rs = null;
        try {
            exis = existingCustomer(clie);
        } catch (ConnectException | ReadException ex) {
            throw new ReadException(ex.getMessage());
        }

        if (exis) {
            Account acc = new Account();
            acc.setData();
            try {
                con = conection.openConnection();
            } catch (ConnectException ex) {
                throw new ConnectException(ex.getMessage());
            }

            try {
                stmt = con.prepareStatement(createAccount);
                stmt.setDouble(1, acc.getBalance());
                stmt.setDouble(2, acc.getBeginBalance());
                stmt.setTimestamp(3, acc.getBeginBalanceTimeStamp());
                stmt.setDouble(4, acc.getCreditLine());
                stmt.setString(5, acc.getDescription());
                stmt.setInt(6, acc.getType());
                stmt.executeUpdate();
                if (stmt.executeUpdate() == 0) {
                    throw new CreateException("Posible error en el alta de la cuenta");
                } else {
                    rs = stmt.getGeneratedKeys();
                    while (rs.next()) {
                        acc.setId(rs.getLong(1));
                        conection.closeConnection(stmt, con);
                        conection.openConnection();
                        stmt = con.prepareStatement(linkAccountCustomer);
                        stmt.setLong(1, clie);
                        stmt.setLong(2, acc.getId());
                        stmt.executeUpdate();
                    }
                }
            } catch (Exception e) {
                throw new CreateException("Error al Crear la Cuenta");
            }
            try {
                conection.closeConnection(stmt, con);
            } catch (ConnectException e) {
                throw new ConnectException(e.getMessage());
            }

        }

    }

    // PENDIENTE
    // Añadir Clientes a Cuentas
    /**
     * Method to add a Customer to an existing account
     *
     * @throws ConnectException if cannot connect to the DDBB
     * @throws CreateException if fails trying to write on the DDBB
     */
    @Override
    public void addCustomerToAccount() throws ConnectException, CreateException {
        
    }

    // Consultar Datos de una Cuenta
    /**
     * Method to search for an Account on the DDBB
     *
     * @return the desired account
     */
    @Override
    public Account readAccount() {
        Account acc = null;
        long id = acc.askId();
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            new ConnectException("Error al Leer");
        }
        try {
            stmt = con.prepareStatement(readAccount);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getLong("account.id"));
                acc.setBalance(rs.getDouble("account.balance"));
                acc.setBeginBalance(rs.getDouble("account.beginBalance"));
                acc.setBeginBalanceTimeStamp(rs.getTimestamp("account.beginBalanceTimestamp"));
                acc.setCreditLine(rs.getDouble("account.creditLibne"));
                acc.setDescription(rs.getString("account.description"));
                acc.setType(rs.getInt("account.type"));
            }
        } catch (Exception e) {
            new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                new ReadException("Error al Leer");
            }
        }
        return acc;
    }

    // Añadir Movimiento
    /**
     * Method to create a new Movement on the DDBB
     *
     * @throws ConnectException if cannot connect to the DDBB
     * @throws CreateException if fails trying to write on the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     * @throws UpdateException if fails trying to update data in the DDBB
     */
    @Override
    public void createMovement() throws ConnectException, CreateException, ReadException, UpdateException {
        long acc;
        double bal = 0;
        Movement mov = null;
        System.out.println("Introduzca el importe inicial de la cuenta");
        acc = Utilities.leerLong();
        boolean exis = false;
        try {
            exis = existingAccount(acc);
        } catch (ConnectException ex) {
            throw new ConnectException(ex.getMessage());
        } catch (ReadException ex) {
            throw new ReadException(ex.getMessage());
        }
        if (exis) {
            try {
                bal = readBalance(acc);
            } catch (ConnectException ex) {
                throw new ConnectException(ex.getMessage());
            } catch (ReadException ex) {
                throw new ReadException(ex.getMessage());
            }
            try {
                mov = new Movement();
                mov.setData(acc, bal);
                con = conection.openConnection();
            } catch (ConnectException ex) {
                throw new ConnectException(ex.getMessage());
            }
            try {
                stmt = con.prepareStatement(createMovement);
                stmt.setDouble(1, mov.getAmount());
                stmt.setDouble(2, mov.getBalance());
                stmt.setString(3, mov.getDescription());
                stmt.setDouble(4, acc);
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new CreateException("Error al Crear la Cuenta");
            }
            try {
                conection.closeConnection(stmt, con);
            } catch (ConnectException ex) {
                throw new ConnectException(ex.getMessage());
            }
            try {
                con = conection.openConnection();
            } catch (ConnectException ex) {
                throw new ConnectException(ex.getMessage());
            }
            try {
                stmt = con.prepareStatement(updateAccount);
                stmt.setDouble(1, mov.getBalance());
                stmt.setDouble(2, acc);
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new UpdateException("Error al Actualizar la Cuenta");
            }
            try {
                conection.closeConnection(stmt, con);
            } catch (ConnectException ex) {
                throw new ConnectException(ex.getMessage());
            }
        }
    }

    // PENDIENTE
    // Consultar Datos Movimiento
    /**
     * Method to list the Movements of a expecified account
     *
     * @return collection of movements
     * @throws ConnectException if cannot connect to the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    @Override
    public Collection<Movement> listMovement() throws ReadException, ConnectException {
        Collection<Movement> movs = new TreeSet<>();
        long num = Account.askId();
        Movement mov = null;
        if (existingAccount(num)) {
            ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            new ConnectException("Error al Leer");
        }
        try {
            stmt = con.prepareStatement(listMovement);
            stmt.setLong(1, num);
            rs = stmt.executeQuery();
            while (rs.next()) {
                mov = new Movement();
                mov.setId(rs.getLong("id"));
                mov.setAmount(rs.getDouble("amount"));
                mov.setBalance(rs.getDouble("balance"));
                mov.setTimeStamp(rs.getTimestamp("timestamp"));
                mov.setDescription(rs.getString("description"));
                mov.setAccount_id(num);
                movs.add(mov);
            }
        } catch (Exception e) {
            new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                new ReadException("Error al Leer");
            }
        }
        }
        return movs;
    }

    // Métodos Adicionales de comprobaciones
    /**
     * Method to check the existence of an Account via its id
     *
     * @param num Generated randomlly number for new Account ID
     * @return Returns a boolean indicating the existence of an account with the
     * provided number to avoid duplicates
     * @throws ConnectException if cannot connect to the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    public boolean existingAccount(long num) throws ConnectException, ReadException {
        ResultSet rs = null;
        exists = false;
        String id = String.valueOf(num);
        try {
            con = conection.openConnection();
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }

        try {
            stmt = con.prepareStatement(searchAccount);
            stmt.setString(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            throw new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                throw new ReadException("Error al Leer");
            }
        }
        return exists;
    }

    /**
     * Method to check the existence of a Customer via its id
     *
     * @param num asked number for Customer ID
     * @return Returns a boolean indicating the existence of a customer with the
     * provided number to enable Account Creation
     * @throws ConnectException if cannot connect to the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    private boolean existingCustomer(long num) throws ConnectException, ReadException {
        ResultSet rs = null;
        exists = false;
        try {
            con = conection.openConnection();
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }

        try {
            stmt = con.prepareStatement(searchCustomer);
            stmt.setLong(1, num);

            rs = stmt.executeQuery();
            while (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            throw new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                throw new ReadException("Error al Leer");
            }
        }
        return exists;
    }

    /**
     * Auxiliar method to obtain the balance on an exact moment from an account
     *
     * @param acc the id of the account the balance is wanted from
     * @return the balance of the account
     * @throws ConnectException if cannot connect to the DDBB
     * @throws ReadException if fails trying to read from the DDBB
     */
    private double readBalance(long acc) throws ConnectException, ReadException {
        long bal = 0;
        ResultSet rs = null;
        exists = false;
        try {
            con = conection.openConnection();
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }

        try {
            stmt = con.prepareStatement(readBalance);
            stmt.setLong(1, acc);

            rs = stmt.executeQuery();
            while (rs.next()) {
                bal = rs.getLong("balance");
            }
        } catch (Exception e) {
            throw new ReadException("Error al Leer");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                throw new ReadException("Error al Leer");
            }
        }
        return bal;
    }
}
