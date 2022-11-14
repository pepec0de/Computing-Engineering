package isdd.practice3.controller;

import java.awt.event.*;
import java.sql.SQLException;
import isdd.practice3.model.*;
import isdd.practice3.view.*;

/**
 *
 * @author Pepe
 */
public class LoginControl implements ActionListener {

    private LoginView view;
    private ConnectionDB conn;
    private DialogView dialog;
    private DBView dbView;
    
    public LoginControl() {
        view = new LoginView();
        view.btnConnect.addActionListener(this);
        view.setVisible(true);
        
        dbView = new DBView();
        dbView.mExit.addActionListener(this);
        dbView.mMemManage.addActionListener(this);
        dbView.mTrainManage.addActionListener(this);

        dialog = new DialogView();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Connect":
                boolean isConn = connect(view.comboServer.getItemAt(view.comboServer.getSelectedIndex()).toLowerCase(),
                        view.txtIP.getText(), 
                        view.txtPort.getText(), 
                        view.txtName.getText(), 
                        view.txtUser.getText(),
                        String.valueOf(view.passwd.getPassword()));
                if (isConn) {
                    dialog.show(0, "Successfully connected!");
                    openDBView();
                    view.dispose();
                } else {
                    dialog.show(-1, "Connection failed :(");
                }
                
                break;
                
            case "Exit":
                disconnect();
                dbView.dispose();
                System.exit(0);
                break;
                
        }
    }

    private void openDBView() {
        
        dbView.setVisible(true);
    }
    
    private boolean connect(String dbms, String ip, String port, String db, String user, String password) {
        try {
            //conn = new ConnectionDB("oracle", "172.17.20.39:1521", "ETSI", "ISDD_003", "holaBuenas");
            //conn = new ConnectionDB("mariadb", "172.18.1.241:3306", "ETSI", "ISDD_003", "ISDD_003");
            conn = new ConnectionDB(dbms, ip + ":" + port, db, user, password);
            dbView.setTitle(db + " database (" + ip + ":" + port + ")");
            return true;
        } catch (SQLException ex) {
            dialog.show(-1, ex.getMessage());
        }
        return false;
    }

    private void disconnect() {
        try {
            conn.disconnect();
            
        } catch (SQLException ex) {
            dialog.show(-1, ex.getMessage());
        }
    }

    private void retrieveMetadata() {
    }

}
