package isdd.practice5.controller;

import com.formdev.flatlaf.FlatLightLaf;
import isdd.practice5.view.DBView;
import isdd.practice5.view.DialogView;
import isdd.practice5.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import org.hibernate.Session;

/**
 *
 * @author Pepe
 */
public class LoginControl implements ActionListener, ItemListener {

    // TODO : ADD ERROR MANAGEMENT IN STORED PROCEDURE
    private LoginView view;
    private DialogView dialog;
    private DBView dbView;
    private DBControl dbCtl;
    private HibernateUtil hibernate;
    private Session session;

    public LoginControl() {
        view = new LoginView();
        view.btnConnect.addActionListener(this);
        view.comboServer.addItemListener(this);

        dbView = new DBView();
        dbCtl = new DBControl(this, dbView);
        dbView.mExit.addActionListener(this);

        dialog = new DialogView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Connect":
                if (view.comboServer.getSelectedIndex() == 0) {
                    dialog.show(-1, "Please, select a server");
                } else {
                    boolean isConn = connect();
                    if (isConn) {
                        openDBView();
                        view.dispose();
                    } else {
                        dialog.show(-1, "Connection failed :(");
                    }
                }
                break;

            case "Exit":
                disconnect();
                dbView.dispose();
                System.exit(0);
                break;

        }
    }

    public boolean connect() {
        String configFile = view.comboServer.getSelectedItem().toString().toLowerCase() + ".cfg.xml";
        hibernate = new HibernateUtil(configFile);
        session = hibernate.getSessionFactory().openSession();

        return session != null;
    }

    public void disconnect() {
        hibernate.close();
    }

    public void openDBView() {
        dbCtl.show();
        dbView.setTitle(view.txtName.getText() + " database in (" + view.txtIP.getText() + ":" + view.txtPort.getText() + ")");
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        switch ((String) view.comboServer.getSelectedItem()) {
            case "Oracle":
                view.txtIP.setText("172.17.20.39");
                view.txtPort.setText("1521");
                view.txtName.setText("ETSI");
                view.txtUser.setText("ISDD_003");
                view.passwd.setText("holaBuenas");
                break;

            case "MariaDB":
                view.txtIP.setText("172.18.1.241");
                view.txtPort.setText("3306");
                view.txtName.setText("ISDD_003");
                view.txtUser.setText("ISDD_003");
                view.passwd.setText("ISDD_003");
                break;
        }
    }

    public DialogView getDialog() {
        return dialog;
    }

    public void open() {
        view.setVisible(true);
    }

    public Session getSession() {
        return session;
    }

}
