/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isdd.practice3.controller;

import isdd.practice3.model.Query;
import isdd.practice3.view.DBView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pepe
 */
public class DBControl implements ActionListener {
    
    private DBView view;
    private DefaultTableModel model;
    private Object[][] data;
    private Query query;
    
    public DBControl(DBView v) {
        view = v;
        view.pTable.setVisible(false);
        view.pBtn.setVisible(false);
        //query = new Query();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MembersManage":
                model = new DefaultTableModel(data, 
                        new String[]{"Code", "Name", "ID", "Birthday", "Phone", "E-Mail", "Starting Date", "Category"});
                view.table.setModel(model);
                break;
            case "TrainManage":
                
                break;
        }
    }
}
