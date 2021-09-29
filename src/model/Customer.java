/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import utilities.Utilities;

/**
 * The Class that represents the values from the Customer table on the DDBB
 *
 * @author Jaime San Sebastián
 */
public class Customer implements Serializable {

    private long id;
    private String city;
    private String email;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private long phone;
    private String state;
    private String street;
    private int zip;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleInitial
     */
    public String getMiddleInitial() {
        return middleInitial;
    }

    /**
     * @param middleInitial the middleInitial to set
     */
    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    /**
     * @return the phone
     */
    public long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Method designet to create a new Customer in the local storage previous to
     * save it on the DDBB
     */
    public void setData() {
        this.city = Utilities.leerString("Introduzca la ciudad del Customer");
        this.email = Utilities.leerString("Introduzca el email del Customer");
        this.firstName = Utilities.leerString("Introduzca el nombre del Customer");
        this.lastName = Utilities.leerString("Introduzca el Apellido del Customer");
        this.middleInitial = Utilities.leerString("Introduzca posibles iniciales del Customer");
        this.phone = Utilities.leerLong("Introduzca el teléfono del Customer");
        this.state = Utilities.leerString("Introduzca el estado/provincia donde vive del Customer");
        this.street = Utilities.leerString("Introduzca el país del Customer");
        this.zip = Utilities.leerInt("Introduzca el Código Postal del Customer");
    }

    /**
     * Method designed to ask a Customer ID
     *
     * @return the id
     */
    public static long askId() {
        long id = Utilities.leerLong("Introduzca el id del Customer a buscar");
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", city=" + city + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", middleInitial=" + middleInitial + ", phone=" + phone + ", state=" + state + ", street=" + street + ", zip=" + zip + '}';
    }
}
