/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.Controller;
import exception.*;
import logical.DAO;
import model.DAOImplement;
import logical.View;
import view.ConsoleView;

/**
 *
 * @author Andoni Alday
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ReadException, ConnectException, CreateException {
        // TODO code application logic here
        DAO dao = new DAOImplement();
        View view = new ConsoleView();
        Controller control = new Controller();
        control.run(view, dao);
    }

}
