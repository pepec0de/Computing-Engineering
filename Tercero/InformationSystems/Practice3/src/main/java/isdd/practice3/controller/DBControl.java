/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdd.practice3.controller;

import isdd.practice3.model.Member;
import isdd.practice3.model.MemberDAO;
import isdd.practice3.model.TrainerDAO;
import isdd.practice3.view.DBView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pepe
 */
public class DBControl implements ActionListener {
    
    private DBView view;
    private DefaultTableModel model;
    private MemberDAO members;
    private TrainerDAO trainers;
    private LoginControl main;
    
    public DBControl(LoginControl c, DBView v) {
        main = c;
        view = v;
        view.pTable.setVisible(false);
        view.pBtn.setVisible(false);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MembersManage":
            {
                members = new MemberDAO(main.getConn());
                try {
                    Object[][] data = getMembersData();
                    model = new DefaultTableModel(data, members.columnNames());
                    view.table.setModel(model);
                    
                    view.pTable.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }break;

                
            case "TrainManage":
                
                break;
        }
    }
}
