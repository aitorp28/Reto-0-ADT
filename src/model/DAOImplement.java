/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.TreeSet;

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
    private final String searchCustomer = "SELECT * FROM customer WHERE id = ?";
    private final String searchAccount = "SELECT * FROM account WHERE id = ?";
    private final String listAccount = "SELECT account.* FROM account,customer_account WHERE Account.id = customer_account.accounts_id AND customer_account.customers_id = ?";
    private final String listMovement = "SELECT * FROM movement WHERE account_id = ?";
    private final String createCustomer = "INSERT INTO Customer (city, email, firstName, lastName, middleInitial, phone, state, street, zip) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String createAccount = "INSERT INTO Account (balance, beginBalance, beginBalanceTimestamp,creditLine, description, `type`) VALUES (?,?,CURRENT_TIMESTAMP,?,?,?)";
    private final String linkAccountCustomer = "INSERT INTO customer_account (customers_id, accounts_id) VALUES (?,?)";
    private final String createMovement = "INSERT INTO Customer (timestamp, amount, balance, description, account_id) VALUES (?,?,?,?)";
    private final String updateAccount = "UPDATE account SET balance = ? WHERE id = ?";

    /**
     * Method for creating a new Customer on the DDBB
     *
     * @param cust Customer ready to be recorded
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     */
    @Override
    public void createCustomer(Customer cust) throws ConnectException, CreateException {
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
            stmt.setLong(6, cust.getPhone());
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

    /**
     * Method for reading the data of a Customer from the DDBB
     *
     * @param id id of the Customer
     * @return the wanted customer, if it exists
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    @Override
    public Customer searchCustomer(long id) throws ConnectException, ReadException {
        Customer cus = null;
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
                cus.setMiddleInitial(rs.getString("middleInitial"));
                cus.setPhone(rs.getLong("phone"));
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

    /**
     * Method for searchig a customer's accounts
     *
     * @param id of the Customer
     * @return the accounts of the customer, if any
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    @Override
    public Collection<Account> listAccount(long id) throws ConnectException, ReadException {
        Collection<Account> accounts = new TreeSet<>();
        Account acc = null;
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

    /**
     * Method for creating a new Account on the DDBB
     *
     * @param clie id of the Customer
     * @param acc Account ready to be recorded
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    @Override
    public void createAccount(long clie, Account acc) throws ConnectException, CreateException, ReadException {
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            throw new ConnectException(ex.getMessage());
        }
        try {
            stmt = con.prepareStatement(createAccount);
            stmt.setDouble(1, acc.getBalance());
            stmt.setDouble(2, acc.getBeginBalance());
            stmt.setDouble(3, acc.getCreditLine());
            stmt.setString(4, acc.getDescription());
            stmt.setInt(5, acc.getType());
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

    /**
     * Method for linking a Customer with an Account on the DDBB
     *
     * @param cus id of the Customer
     * @param acc id of the Account
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     */
    @Override
    public void addCustomerToAccount(long cus, long acc) throws ConnectException, CreateException {
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            throw new ConnectException(ex.getMessage());
        }

        try {
            stmt = con.prepareStatement(linkAccountCustomer);
            stmt.setLong(1, cus);
            stmt.setLong(2, acc);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new CreateException("Error al Crear la Cuenta");
        }
        try {
            conection.closeConnection(stmt, con);
        } catch (ConnectException e) {
            throw new ConnectException(e.getMessage());
        }
    }

    /**
     *
     * Method for reading the data of a Customer from the DDBB
     *
     * @param id id of the Account
     * @return the wanted account, if it exists
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    @Override
    public Account readAccount(long id) throws ConnectException, ReadException {
        Account acc = null;
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            new ConnectException("Error al Leer");
        }
        try {
            stmt = con.prepareStatement(searchAccount);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                acc = new Account();
                acc.setId(rs.getLong("account.id"));
                acc.setBalance(rs.getDouble("account.balance"));
                acc.setBeginBalance(rs.getDouble("account.beginBalance"));
                acc.setBeginBalanceTimeStamp(rs.getTimestamp("account.beginBalanceTimestamp"));
                acc.setCreditLine(rs.getDouble("account.creditLine"));
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

    /**
     * Method for creating a new Movement for an Account on the DDBB
     *
     * @param acc Account to be updated
     * @param mov Movement to be recorded
     * @throws ConnectException if failing to connect to the DDBB
     * @throws CreateException if failing to write on the DDBB
     * @throws ReadException if failing to read from the DDBB
     * @throws UpdateException if failing to update values on the DDBB
     */
    @Override
    public void createMovement(Account acc, Movement mov) throws ConnectException, CreateException, ReadException, UpdateException {
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            throw new ConnectException(ex.getMessage());
        }
        try {
            stmt = con.prepareStatement(createMovement);
            stmt.setDouble(1, mov.getAmount());
            stmt.setDouble(2, mov.getBalance());
            stmt.setString(3, mov.getDescription());
            stmt.setDouble(4, acc.getBalance());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new CreateException("Error al realizar Movimiento");
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
            stmt.setDouble(2, acc.getId());
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

    /**
     * Method for searchig an Account's Movements
     *
     * @param acc id of the Account
     * @return the movements of the account, if any
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    @Override
    public Collection<Movement> listMovement(long acc) throws ReadException, ConnectException {
        Collection<Movement> movs = new TreeSet<>();
        Movement mov = null;
        ResultSet rs = null;
        try {
            con = conection.openConnection();
        } catch (ConnectException ex) {
            new ConnectException("Error al Leer");
        }
        try {
            stmt = con.prepareStatement(listMovement);
            stmt.setLong(1, acc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                mov = new Movement();
                mov.setId(rs.getLong("id"));
                mov.setAmount(rs.getDouble("amount"));
                mov.setBalance(rs.getDouble("balance"));
                mov.setTimeStamp(rs.getTimestamp("timestamp"));
                mov.setDescription(rs.getString("description"));
                mov.setAccount_id(acc);
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

        return movs;
    }

    /**
     * Method to check the existence of an Account based on an ID
     *
     * @param num the id given by the user
     * @return the existence ot the Customer on a boolean
     * @throws ConnectException if failing to connect to the DDBB
     * @throws ReadException if failing to read from the DDBB
     */
    @Override
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
    @Override
    public boolean existingCustomer(long num) throws ConnectException, ReadException {
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

}
