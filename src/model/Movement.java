/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author 2dam
 */
public class Movement {
    private int id;
    private double amount;
    private double balance;
    private String description;
    private LocalDate timeStamp;
    private int account_id;
}
