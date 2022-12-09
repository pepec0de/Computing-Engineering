package isdd.practice3.controller;

import isdd.practice3.model.*;
import isdd.practice3.view.ActivityDialog;
import isdd.practice3.view.DBView;
import isdd.practice3.view.MemberDialog;
import isdd.practice3.view.TrainerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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

    private MemberDialog memberDialog;
    private TrainerDialog trainDialog;
    private ActivityDialog activityDialog;

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

        v = new Verifier();
    }

    private void refreshMembers() throws SQLException {
        model.setDataVector(getMembersData(), members.columnNames());
    }

    private void refreshTrainers() throws SQLException {
        model.setDataVector(getTrainersData(), trainers.columnNames());
    }

    private void refreshActivities() throws SQLException {
        model.setDataVector(getActivitiesData(), activities.columnNames());
    }

    private Object[][] getMembersData() throws SQLException {
        ArrayList<Member> arr = members.listAllMembers();
        if (!arr.isEmpty()) {
            Object[][] result = new Object[arr.size()][members.columnNames().length];
            int i = 0;
            for (Member m : arr) {
                result[i][0] = m.getM_num();
                result[i][1] = m.getM_name();
                result[i][2] = m.getM_id();
                result[i][3] = m.getM_birthdate();
                result[i][4] = m.getM_phone();
                result[i][5] = m.getM_emailMember();
                result[i][6] = m.getM_startingDateMember();
                result[i][7] = m.getM_cathegoryMember();
                i++;
            }
            return result;
        }
        return null;
    }

    private Object[][] getTrainersData() throws SQLException {
        ArrayList<Trainer> arr = trainers.listAllTrainers();
        if (!arr.isEmpty()) {
            Object[][] result = new Object[arr.size()][trainers.columnNames().length];
            int i = 0;
            for (Trainer m : arr) {
                result[i][0] = m.getT_cod();
                result[i][1] = m.getT_name();
                result[i][2] = m.getT_idNumber();
                result[i][3] = m.getT_phoneNumber();
                result[i][4] = m.getT_email();
                result[i][5] = m.getT_date();
                result[i][6] = m.getT_nick();
                i++;
            }
            return result;
        }
        return null;
    }

    private Object[][] getActivitiesData() throws SQLException {
        ArrayList<Activity> arr = activities.listAllActivities();
        if (!arr.isEmpty()) {
            Object[][] result = new Object[arr.size()][activities.columnNames().length];
            int i = 0;
            for (Activity m : arr) {
                result[i][0] = m.getA_id();
                result[i][1] = m.getA_name();
                result[i][2] = m.getA_description();
                result[i][3] = m.getA_price();
                result[i][4] = m.getA_trainerInCharge();
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
                memberDialog.cathegory.setText((String) view.table.getModel().getValueAt(row, 7));
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

    private void execUpdate() throws SQLException {
        String ver;
        switch (current) {
            case "Member":
                Member m = new Member(memberDialog.num.getText(),
                        memberDialog.name.getText(),
                        memberDialog.id.getText(),
                        v.getDateString(memberDialog.birthDate.getDate()),
                        memberDialog.phone.getText(),
                        memberDialog.email.getText(),
                        v.getDateString(memberDialog.startingDate.getDate()),
                        memberDialog.cathegory.getText());
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
                Trainer t = new Trainer(trainDialog.code.getText(),
                        trainDialog.name.getText(),
                        trainDialog.id.getText(),
                        trainDialog.phone.getText(),
                        trainDialog.email.getText(),
                        v.getDateString(trainDialog.date.getDate()),
                        trainDialog.nick.getText());
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
                Activity a = new Activity(activityDialog.id.getText(),
                        activityDialog.name.getText(),
                        activityDialog.description.getText(),
                        activityDialog.price.getText(),
                        activityDialog.trainer.getText());
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
            }
        } catch (SQLException ex) {
            main.getDialog().show(-1, "SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            main.getDialog().show(-1, "Unexpected error: " + ex.getMessage());
        }
        openPanels();
    }

    public void show() {
        trainers = new TrainerDAO(main.getConn());
        members = new MemberDAO(main.getConn());
        activities = new ActivityDAO(main.getConn());
        view.setVisible(true);
    }
}
