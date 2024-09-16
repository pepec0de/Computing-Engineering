package isdd.practice4.application;

import isdd.practice4.controller.LoginControl;

/**
 *
 * @author Pepe
 */
public class Practice4 {

    private LoginControl login;
    
    public Practice4() {
        login = new LoginControl();
        login.open();
    }
    
    public static void main(String[] args) {
        new Practice4();
    }
}
