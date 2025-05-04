package isdd.practice5.application;

import isdd.practice5.controller.LoginControl;

/**
 *
 * @author Pepe
 */
public class Practice5 {

    public Practice5() {
        LoginControl login = new LoginControl();
        login.open();
    }
    
    public static void main(String[] args) {
        new Practice5();
    }
}
