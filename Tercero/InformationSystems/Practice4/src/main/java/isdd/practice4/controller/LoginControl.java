package isdd.practice4.controller;

import java.awt.event.*;
import java.sql.SQLException;
import isdd.practice4.model.*;
import isdd.practice4.view.*;

/**
 *
 * @author Pepe
 */
public class LoginControl implements ActionListener {

    private LoginView view;
    private ConnectionDB conn;
    private DialogView dialog;
    private DBView dbView;
    private DBControl dbCtl;

    public LoginControl() {
        view = new LoginView();
        view.btnConnect.addActionListener(this);

        dbView = new DBView();
        dbCtl = new DBControl(this, dbView);
        dbView.mExit.addActionListener(this);

        dialog = new DialogView();
    }

    private void openDBView() {
        dbCtl.show();
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

    public ConnectionDB getConn() {
        return conn;
    }

    public DialogView getDialog() {
        return dialog;
    }

    public void open() {
        view.setVisible(true);
    }

}
