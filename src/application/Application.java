/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.Controller;
import exception.*;
import model.DAO;
import model.DAOImplement;
import view.View;
import view.ConsoleView;

/**
 * Initial launch of the application and generator of the required Objects
 * @author Andoni Alday
 */
public class Application {

    /**
     * @param args the command line arguments
     * @throws exception.ReadException
     * @throws exception.ConnectException
     * @throws exception.CreateException
     */
    public static void main(String[] args) throws ReadException, ConnectException, CreateException {
        // TODO code application logic here
        DAO dao = new DAOImplement();
        View view = new ConsoleView();
        Controller control = new Controller();
        control.run(view, dao);
    }

}
