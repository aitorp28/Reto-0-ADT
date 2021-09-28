/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Enumeration for the AccountType field on the Account Class
 * @author Aitor Perez
 */
public enum AccountType implements Serializable{

    /**
     * Enumeration option 0
     */
    STANDARD,

    /**
     * Enumeration option 1
     */
    CREDIT;
}
