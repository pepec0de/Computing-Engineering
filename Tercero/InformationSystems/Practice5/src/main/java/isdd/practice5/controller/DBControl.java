package isdd.practice5.controller;

import isdd.practice5.model.*;
import isdd.practice5.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pepe
 */
public class DBControl implements ActionListener {

    private LoginControl main;
    private String current, action;

    // View
    private DBView view;
    private DefaultTableModel model;
    
    // ActivityMembers dialog
    private DefaultTableModel modelMembers;

    private MemberDialog memberDialog;
    private TrainerDialog trainDialog;
    private ActivityDialog activityDialog;
    private ActivityMembersDialog actMemberDialog;
    private ArrayList<String> activitiesId;

    // Model
    private MemberDAO members;
    private TrainerDAO trainers;
    private ActivityDAO activities;
    private Verifier v;

    public DBControl(LoginControl c, DBView _view) {
        current = "";

        main = c;
        view = _view;
        view.pTable.setVisible(false);
        view.pBtn.setVisible(false);
        view.mMemManage.addActionListener(this);
        view.mTrainManage.addActionListener(this);
        view.mActivManage.addActionListener(this);
        view.mMemInActiv.addActionListener(this);
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

        actMemberDialog = new ActivityMembersDialog(view, true);
        actMemberDialog.ok.addActionListener(this);
        actMemberDialog.cancel.addActionListener(this);
        modelMembers = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        actMemberDialog.table.setModel(modelMembers);
        v = new Verifier();
    }

    private void refreshMembers() {
        model.setDataVector(getMembersData(), members.columnNames());
    }

    private void refreshTrainers() {
        model.setDataVector(getTrainersData(), trainers.columnNames());
    }

    private void refreshActivities() {
        model.setDataVector(getActivitiesData(), activities.columnNames());
    }
    
    private void refreshActivityMembers(String a_id) {
        //modelMembers.setDataVector(getActivityMembersData(a_id), activityMembers.columnNames());
    }

    private Object[][] getMembersData() {
        ArrayList<Object[]> arr = members.listAllMembers();
        if (!arr.isEmpty()) {
            int rowLength = arr.get(0).length;
            Object[][] result = new Object[arr.size()][rowLength];
            int i = 0;
            for (Object[] row : arr) {
                System.arraycopy(row, 0, result[i], 0, rowLength);
                i++;
            }
            return result;
        }
        return null;
    }

    private Object[][] getTrainersData() {
        ArrayList<Object[]> arr = trainers.listAllTrainers();
        if (!arr.isEmpty()) {
            int rowLength = arr.get(0).length;
            Object[][] result = new Object[arr.size()][rowLength];
            int i = 0;
            for (Object[] row : arr) {
                System.arraycopy(row, 0, result[i], 0, rowLength);
                i++;
            }
            return result;
        }
        return null;
    }

    private Object[][] getActivitiesData() {
        ArrayList<Object[]> arr = activities.listAllActivities();
        if (!arr.isEmpty()) {
            int rowLength = arr.get(0).length;
            Object[][] result = new Object[arr.size()][rowLength];
            int i = 0;
            for (Object[] row : arr) {
                System.arraycopy(row, 0, result[i], 0, rowLength);
                i++;
            }
            return result;
        }
        return null;
    }

    private void openPanels() {
        view.pTable.setVisible(true);
        view.pBtn.setVisible(true);
    }

    private String getNextId() {
        String lastId = (String) view.table.getModel().getValueAt(view.table.getModel().getRowCount() - 1, 0);

        String letter = lastId.replaceAll("[0-9]", "");
        String code = lastId.replaceAll("[\\D]", "");

        return String.format(letter + "%03d", Integer.parseInt(code) + 1);
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

    private void openActivityMembersDialog() throws SQLException {
        DefaultComboBoxModel<String> cModel = new DefaultComboBoxModel<>();
        activitiesId = new ArrayList<>();
        cModel.addElement("Select an activity...");
        Object[][] result = getActivitiesData();
        for (Object[] vec : result) {
            activitiesId.add((String) vec[0]);
            cModel.addElement((String) vec[1]);
        }
        actMemberDialog.actCombo.setModel(cModel);
        actMemberDialog.setVisible(true);
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
            
            case "ActivityMembers":
                actMemberDialog.dispose();
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
                Activity a = new Activity();;
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

    private void execDelete(String ver) throws SQLException {
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
        try {
            switch (e.getActionCommand()) {
                case "MembersManage":
                    current = "Member";
                    refreshMembers();
                    break;

                case "TrainManage":
                    current = "Trainer";
                    refreshTrainers();
                    break;

                case "ActivManage":
                    current = "Activity";
                    refreshActivities();
                    break;
                    
                case "MemInActiv":
                    current = "ActivityMembers";
                    openActivityMembersDialog();
                    break;

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
                    break;

                // Dialog buttons
                case "OK":
                    execUpdate();
                    break;

                case "Cancel":
                    closeDialog();
                    break;
                    
                case "Members":
                    row = actMemberDialog.actCombo.getSelectedIndex();
                    if (row != 0) {
                        refreshActivityMembers(activitiesId.get(row-1));
                    } else {
                        main.getDialog().show(-1, "Select an activity");
                    }
                    break;
            }
        } catch (SQLException ex) {
            main.getDialog().show(-1, "SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            main.getDialog().show(-1, "Unexpected error: " + ex.getMessage());
        }
        openPanels();
    }

    public void show() {
        members = new MemberDAO(main.getSession());
        trainers = new TrainerDAO(main.getSession());
        activities = new ActivityDAO(main.getSession());
        view.setVisible(true);
    }
}
