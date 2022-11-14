/*
 * 
 * 
 */
package isdd.practice3.view;

import javax.swing.*;

/**
 *
 * @author Pepe
 */
public class DBView extends JFrame {

    /**
     * Creates new form DBView
     */
    public DBView() {
        setResizable(false);
        setLocationRelativeTo(null);
        
        initComponents();
        initJMenu();
    }
    
    private void initJMenu() {
        menuBar = new JMenuBar();
        mMain = new JMenu("Database");
        mExit = new JMenuItem("Exit");
        mExit.setActionCommand("Exit");
        
        mMembers = new JMenu("Members");
        mMemManage = new JMenuItem("Members Management");
        mMemManage.setActionCommand("MembersManage");
        
        mTrainers = new JMenu("Trainers");
        mTrainManage = new JMenuItem("Trainers Management");
        mTrainManage.setActionCommand("TrainManage");
        
        mActivities = new JMenu("Activities");
        
        menuBar.add(mMain);
        mMain.add(mExit);
        
        menuBar.add(mMembers);
        mMembers.add(mMemManage);
        
        menuBar.add(mTrainers);
        mTrainers.add(mTrainManage);
        
        menuBar.add(mActivities);
        
        setJMenuBar(menuBar);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        pBtn = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout pTableLayout = new javax.swing.GroupLayout(pTable);
        pTable.setLayout(pTableLayout);
        pTableLayout.setHorizontalGroup(
            pTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
        );
        pTableLayout.setVerticalGroup(
            pTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );

        btnNew.setText("New");

        btnDel.setText("Delete");

        btnUpdate.setText("Update");

        javax.swing.GroupLayout pBtnLayout = new javax.swing.GroupLayout(pBtn);
        pBtn.setLayout(pBtnLayout);
        pBtnLayout.setHorizontalGroup(
            pBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBtnLayout.createSequentialGroup()
                .addComponent(btnNew)
                .addGap(18, 18, 18)
                .addComponent(btnDel)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pBtnLayout.setVerticalGroup(
            pBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnDel)
                    .addComponent(btnUpdate))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnDel;
    public javax.swing.JButton btnNew;
    public javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel pBtn;
    public javax.swing.JPanel pTable;
    public javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

private JMenuBar menuBar;
private JMenu mMain;
public JMenuItem mExit;

private JMenu mMembers;
public JMenuItem mMemManage;

private JMenu mTrainers;
public JMenuItem mTrainManage;

private JMenu mActivities;

}
