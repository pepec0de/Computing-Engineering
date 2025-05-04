package isdd.practice5.controller;

import isdd.practice5.model.*;
import isdd.practice5.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import javax.persistence.PersistenceException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pepe
 */
public class DBControl implements ActionListener, ItemListener {

    private LoginControl main;
    private String current, action;

    // View
    private DBView view;
    private DefaultTableModel model;
    private DefaultTableModel modelMembers;
    private MemberDialog memberDialog;
    private TrainerDialog trainDialog;
    private ActivityDialog activityDialog;
    private RegisterMemberDialog registerDialog;
    /// DEFENSE CODE
    private MembershipDialog membershipDialog;
    /// ---------------------------------------------

    // aux variable for comboboxes
    private ArrayList<String> activitiesId;
    private ArrayList<String> membersId;

    // Model
    private MemberDAO members;
    private TrainerDAO trainers;
    private ActivityDAO activities;
    private Verifier v;

    public DBControl(LoginControl c, DBView _view) {
        v = new Verifier();
        current = "";

        main = c;
        view = _view;
        view.pTable.setVisible(false);
        view.pBtn.setVisible(false);
        view.mMemManage.addActionListener(this);
        view.mTrainManage.addActionListener(this);
        view.mActivManage.addActionListener(this);
        view.mRegisterMem.addActionListener(this);
        view.btnNew.addActionListener(this);
        view.btnDel.addActionListener(this);
        view.btnUpdate.addActionListener(this);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.table.setModel(model);

        trainDialog = new TrainerDialog(view, true);
        trainDialog.ok.addActionListener(this);
        trainDialog.cancel.addActionListener(this);

        memberDialog = new MemberDialog(view, true);
        memberDialog.ok.addActionListener(this);
        memberDialog.cancel.addActionListener(this);

        activityDialog = new ActivityDialog(view, true);
        activityDialog.ok.addActionListener(this);
        activityDialog.cancel.addActionListener(this);

        activitiesId = new ArrayList<>();
        membersId = new ArrayList<>();
        modelMembers = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        registerDialog = new RegisterMemberDialog(view, true);
        registerDialog.btnAddMember.addActionListener(this);
        registerDialog.btnDelMember.addActionListener(this);
        registerDialog.btnDeleteAll.addActionListener(this);
        registerDialog.actCombo.addItemListener(this);
        registerDialog.tableMembersManage.setModel(modelMembers);

        /// DEFENSE CODE
        view.mMembership.addActionListener(this);
        membershipDialog = new MembershipDialog(view, true);
        membershipDialog.btnSelect.addActionListener(this);
        /// ---------------------------------------------
    }

    private void refreshMembers() {
        model.setDataVector(getTable(members.listAllMembers()), members.columnNames());
    }

    private void refreshTrainers() {
        model.setDataVector(getTable(trainers.listAllTrainers()), trainers.columnNames());
    }

    private void refreshActivities() {
        model.setDataVector(getTable(activities.listAllActivities()), activities.columnNames());
    }

    private void refreshActivityMembers(String a_id) {
        modelMembers.setDataVector(getTable(activities.listAllMembersFromActivity(a_id)), activities.columnNamesActivity());
    }

    private Object[][] getTable(ArrayList<Object[]> rows) {
        if (!rows.isEmpty()) {
            int rowLength = rows.get(0).length;
            Object[][] result = new Object[rows.size()][rowLength];
            int i = 0;
            for (Object[] row : rows) {
                System.arraycopy(row, 0, result[i], 0, rowLength);
                i++;
            }
            return result;
        }
        return null;
    }

    private void showPanels(boolean b) {
        view.pTable.setVisible(b);
        view.pBtn.setVisible(b);
    }

    private String getNextId() {
        String lastId = (String) view.table.getModel().getValueAt(view.table.getModel().getRowCount() - 1, 0);

        String letter = lastId.replaceAll("[0-9]", "");
        String code = lastId.replaceAll("[\\D]", "");

        // Cantidad de 0's que debemos incluir
        int c = 4 - letter.length();

        return String.format(letter + "%0" + c + "d", Integer.parseInt(code) + 1);
    }

    private void setTrainerId(String id) {
        trainDialog.code.setText(id);
        trainDialog.code.setEditable(false);
    }

    private void setActivityId(String id) {
        activityDialog.id.setText(id);
        activityDialog.id.setEditable(false);
    }

    private void setMemberId(String id) {
        memberDialog.num.setText(id);
        memberDialog.num.setEditable(false);
    }

    private void emptyDialog() {
        switch (current) {
            case "Member":
                memberDialog.birthDate.setDate(null);
                memberDialog.cathegory.setText(null);
                memberDialog.email.setText(null);
                memberDialog.id.setText(null);
                memberDialog.name.setText(null);
                memberDialog.phone.setText(null);
                memberDialog.startingDate.setDate(null);
                break;

            case "Trainer":
                trainDialog.date.setDate(null);
                trainDialog.email.setText(null);
                trainDialog.name.setText(null);
                trainDialog.phone.setText(null);
                trainDialog.nick.setText(null);
                trainDialog.id.setText(null);
                break;

            case "Activity":
                activityDialog.name.setText(null);
                activityDialog.description.setText(null);
                activityDialog.price.setText(null);
                activityDialog.trainer.setText(null);
                break;
        }
    }

    private void fillDialog(int row) {
        switch (current) {
            case "Member":
                memberDialog.name.setText((String) view.table.getModel().getValueAt(row, 1));
                memberDialog.id.setText((String) view.table.getModel().getValueAt(row, 2));
                try {
                    memberDialog.birthDate.setDate(v.getStringDate((String) view.table.getModel().getValueAt(row, 3)));
                } catch (ParseException ex) {
                }
                memberDialog.phone.setText((String) view.table.getModel().getValueAt(row, 4));
                memberDialog.email.setText((String) view.table.getModel().getValueAt(row, 5));
                try {
                    memberDialog.startingDate.setDate(v.getStringDate((String) view.table.getModel().getValueAt(row, 6)));
                } catch (ParseException ex) {
                }
                memberDialog.cathegory.setText(((Character) view.table.getModel().getValueAt(row, 7)).toString());
                break;

            case "Trainer":
                trainDialog.name.setText((String) view.table.getModel().getValueAt(row, 1));
                trainDialog.id.setText((String) view.table.getModel().getValueAt(row, 2));
                trainDialog.phone.setText((String) view.table.getModel().getValueAt(row, 3));
                trainDialog.email.setText((String) view.table.getModel().getValueAt(row, 4));
                try {
                    trainDialog.date.setDate(v.getStringDate((String) view.table.getModel().getValueAt(row, 5)));
                } catch (ParseException ex) {
                }
                trainDialog.nick.setText((String) view.table.getModel().getValueAt(row, 6));
                break;

            case "Activity":
                activityDialog.name.setText((String) view.table.getModel().getValueAt(row, 1));
                activityDialog.description.setText((String) view.table.getModel().getValueAt(row, 2));
                activityDialog.price.setText((String) view.table.getModel().getValueAt(row, 3));
                activityDialog.trainer.setText((String) view.table.getModel().getValueAt(row, 4));
                break;
        }
    }

    private void openDialog() {
        // Get ID to block the code textfield
        String id = "";
        switch (action) {
            case "New":
                id = getNextId();
                emptyDialog();
                break;

            case "Update":
                int row = view.table.getSelectedRow();
                id = (String) view.table.getModel().getValueAt(row, 0);
                fillDialog(row);
                break;
        }

        switch (current) {
            case "Trainer":
                setTrainerId(id);
                trainDialog.setTitle(action + " Trainer");
                trainDialog.setVisible(true);
                break;

            case "Member":
                setMemberId(id);
                memberDialog.setTitle(action + " Member");
                memberDialog.setVisible(true);
                break;

            case "Activity":
                setActivityId(id);
                activityDialog.setTitle(action + " Activity");
                activityDialog.setVisible(true);
                break;
        }
    }

    private DefaultComboBoxModel<String> getActivityCombo() {
        activitiesId.clear();
        DefaultComboBoxModel<String> cModel = new DefaultComboBoxModel<>();
        cModel.addElement("Select an activity...");
        for (Object[] vec : getTable(activities.listAllActivities())) {
            activitiesId.add((String) vec[0]);
            cModel.addElement((String) vec[1]);
        }
        return cModel;
    }

    private DefaultComboBoxModel<String> getMembersCombo() {
        membersId.clear();
        DefaultComboBoxModel<String> cModel = new DefaultComboBoxModel<>();
        cModel.addElement("Select a member...");
        for (Object[] vec : getTable(members.listAllMembers())) {
            membersId.add((String) vec[0]);
            cModel.addElement((String) vec[1]);
        }
        return cModel;
    }

    private void openRegisterDialog() {
        modelMembers.setRowCount(0);
        registerDialog.actCombo.setModel(getActivityCombo());
        registerDialog.membersCombo.setModel(getMembersCombo());
        registerDialog.setVisible(true);
    }

    private void closeDialog() {
        switch (current) {
            case "Member":
                memberDialog.dispose();
                break;

            case "Trainer":
                trainDialog.dispose();
                break;

            case "Activity":
                activityDialog.dispose();
                break;
        }
    }

    private void execUpdate() {
        String ver;
        switch (current) {
            case "Member":
                Member1 m = new Member1();
                switch (action) {
                    case "Update":
                        m = main.getSession().get(Member1.class, memberDialog.num.getText());
                        break;
                }

                m.setMNum(memberDialog.num.getText());
                m.setMName(memberDialog.name.getText());
                m.setMId(memberDialog.id.getText());
                m.setMBirhtdate(v.getDateString(memberDialog.birthDate.getDate()));
                m.setMPhone(memberDialog.phone.getText());
                m.setMEmailmember(memberDialog.email.getText());
                m.setMStartingdatemember(v.getDateString(memberDialog.startingDate.getDate()));
                m.setMCathegorymember(memberDialog.cathegory.getText().charAt(0));

                ver = v.verify(m);

                if (ver != null) {
                    main.getDialog().show(-1, ver);
                } else {
                    switch (action) {
                        case "New" ->
                            members.insertMember(m);

                        case "Update" ->
                            members.updateMember(m);
                    }
                    memberDialog.dispose();
                    refreshMembers();
                }
                break;

            case "Trainer":
                Trainer t = new Trainer();
                switch (action) {
                    case "Update":
                        t = main.getSession().get(Trainer.class, trainDialog.code.getText());
                        break;
                }

                t.setTCod(trainDialog.code.getText());
                t.setTName(trainDialog.name.getText());
                t.setTIdnumber(trainDialog.id.getText());
                t.setTPhonenumber(trainDialog.phone.getText());
                t.setTEmail(trainDialog.email.getText());
                t.setTDate(v.getDateString(trainDialog.date.getDate()));
                t.setTNick(trainDialog.nick.getText());

                ver = v.verify(t);

                if (ver != null) {
                    main.getDialog().show(-1, ver);
                } else {
                    switch (action) {
                        case "New" ->
                            trainers.insertTrainer(t);

                        case "Update" ->
                            trainers.updateTrainer(t);
                    }
                    trainDialog.dispose();
                    refreshTrainers();
                }
                break;

            case "Activity":
                Activity a = new Activity();
                ;
                switch (action) {
                    case "Update":
                        a = main.getSession().get(Activity.class, activityDialog.id.getText());
                        break;
                }

                a.setAId(activityDialog.id.getText());
                a.setAName(activityDialog.name.getText());
                a.setADescription(activityDialog.description.getText());
                a.setAPrice(BigInteger.valueOf(Integer.parseInt(activityDialog.price.getText())));
                a.setATrainerincharge(main.getSession().get(Trainer.class, activityDialog.trainer.getText()));

                ver = v.verify(a);
                if (ver != null) {
                    main.getDialog().show(-1, ver);
                } else {
                    switch (action) {
                        case "New" ->
                            activities.insertActivity(a);

                        case "Update" ->
                            activities.updateActivity(a);
                    }
                    activityDialog.dispose();
                    refreshActivities();
                }
                break;
        }
    }

    private void execDelete(String ver) {
        switch (current) {
            case "Member":
                members.deleteMember(ver);
                refreshMembers();
                break;

            case "Trainer":
                trainers.deleteTrainer(ver);
                refreshTrainers();
                break;

            case "Activity":
                activities.deleteActivity(ver);
                refreshActivities();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row;
        String ver;
        showPanels(false);
        try {
            switch (e.getActionCommand()) {
                // Menu items
                case "MembersManage":
                    current = "Member";
                    refreshMembers();
                    showPanels(true);
                    break;

                case "TrainManage":
                    current = "Trainer";
                    refreshTrainers();
                    showPanels(true);
                    break;

                case "ActivManage":
                    current = "Activity";
                    refreshActivities();
                    showPanels(true);
                    break;

                case "RegisterMem":
                    current = "RegisterMembers";
                    openRegisterDialog();
                    break;

                // Panel buttons
                case "Update":
                    // Check if the user has selected any cell
                    row = view.table.getSelectedRow();
                    if (row == -1) {
                        main.getDialog().show(-1, "For updating data you must select a row");
                    } else {
                        action = "Update";
                        openDialog();
                    }
                    break;

                case "New":
                    action = "New";
                    openDialog();
                    break;

                case "Delete":
                    row = view.table.getSelectedRow();
                    if (row == -1) {
                        main.getDialog().show(-1, "For deleting data you must select a row");
                    } else {
                        ver = (String) view.table.getModel().getValueAt(row, 0);
                        if (main.getDialog().confirmWarning("Confirm delete of " + ver + "?") == 0) {
                            execDelete(ver);
                        }
                    }
                    showPanels(true);
                    break;

                // Dialog buttons
                case "OK":
                    execUpdate();
                    showPanels(true);
                    break;

                case "Cancel":
                    closeDialog();
                    showPanels(true);
                    break;

                // Register Dialog buttons
                case "Add member":
                    // Get members combo idx
                    row = registerDialog.membersCombo.getSelectedIndex();
                    int actIdx = registerDialog.actCombo.getSelectedIndex();
                    if (actIdx == 0) {
                        main.getDialog().show(-1, "You must select an activity");
                    } else if (row == 0) {
                        main.getDialog().show(-1, "You must select a member");
                    } else {
                        String act_id = activitiesId.get(actIdx - 1);
                        String mem_id = membersId.get(row - 1);

                        System.out.println(act_id + " " + mem_id);
                        activities.addMemberToActivity(act_id, mem_id);
                        refreshActivityMembers(act_id);
                    }
                    break;

                case "Delete member":
                    // Delete member from selected activity
                    row = registerDialog.tableMembersManage.getSelectedRow();
                    actIdx = registerDialog.actCombo.getSelectedIndex();
                    if (actIdx == 0) {
                        main.getDialog().show(-1, "You must select an activity");
                    } else if (row == -1) {
                        main.getDialog().show(-1, "You must select a member in the table");
                    } else {
                        String act_id = activitiesId.get(actIdx - 1);
                        String mem_id = (String) modelMembers.getValueAt(row, 0);

                        activities.deleteMemberFromActivity(act_id, mem_id);
                        refreshActivityMembers(act_id);
                    }
                    break;

                case "Delete all":
                    // Delete all members from selected activity
                    actIdx = registerDialog.actCombo.getSelectedIndex();
                    if (actIdx == 0) {
                        main.getDialog().show(-1, "You must select an activity");
                    } else {
                        String act_id = activitiesId.get(actIdx - 1);

                        activities.deleteAllFromActivity(act_id);
                        refreshActivityMembers(act_id);
                    }
                    break;

                /// DEFENSE CODE
                case "Membership":
                    membershipDialog.lblAct.setText("0");
                    membershipDialog.lblFee.setText("0");
                    membershipDialog.txtMemCode.setText("");
                    membershipDialog.setVisible(true);
                    break;

                case "Select":
                    String code = membershipDialog.txtMemCode.getText();
                    // Check if the code is right
                    boolean ok = false;
                    if (!code.equals("")) {
                        // looking for the code in the member table
                        for (Object[] orow : members.listAllMembers()) {
                            if (code.equals(orow[0])) {
                                ok = true;
                                break;
                            }
                        }
                    }
                    if (!ok) {
                        main.getDialog().show(-1, "Member code doesn't exists");
                    } else {
                        /*
                            In MariaDB database the data type of the price it's an Integer while the 
                            data type in the Oracle database it's a BigDecimal
                         */
                        ArrayList<Object> activitiesFee = members.activitiesFeeFromMember(code);
                        int result = 0;
                        
                        switch ((String)main.getLoginView().comboServer.getSelectedItem()) {
                            case "Oracle":
                                for (Object o : activitiesFee) {
                                    BigDecimal price = (BigDecimal) o;
                                    result += price.intValue();
                                }
                                break;
                                
                            case "MariaDB":
                                for (Object o : activitiesFee) {
                                    Integer price = (Integer) o;
                                    result += price;
                                }
                                break;
                        }
                        membershipDialog.lblAct.setText(String.valueOf(activitiesFee.size()));
                        membershipDialog.lblFee.setText(String.valueOf(result));
                    }
                    break;
                /// ---------------------------------------------
            }
        } catch (PersistenceException ex) {
            main.getDialog().show(-1, "SQL error: " + ex.getMessage());
        } catch (NullPointerException ex) {
            main.getDialog().show(-1, "Null value error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            main.getDialog().show(-1, "Unexpected error: " + ex.getMessage());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        switch (current) {
            case "RegisterMembers":
                int idx = registerDialog.actCombo.getSelectedIndex();
                if (idx != 0) {
                    refreshActivityMembers(activitiesId.get(idx - 1));
                } else {
                    modelMembers.setRowCount(0);
                }
                break;
        }
    }

    public void show() {
        members = new MemberDAO(main.getSession());
        trainers = new TrainerDAO(main.getSession());
        activities = new ActivityDAO(main.getSession());
        view.setVisible(true);
    }

}
