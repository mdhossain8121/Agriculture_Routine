/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agricultureproject;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Dead
 */
public class FormPlantSetup extends javax.swing.JFrame {

    /**
     * Creates new form FormPlantSetup
     */
    String id = "0";
    HashMap hm = new HashMap();
    DatabaseConnection dc = new DatabaseConnection();
    Border empty = BorderFactory.createLineBorder(Color.red, 1);
    Border notEmpty = BorderFactory.createLineBorder(Color.gray, 1);
    UserPanel up = new UserPanel();
    

    public FormPlantSetup() {
        initComponents();
    }

    public FormPlantSetup(String a) {
        initComponents();
        this.id = a;
        btnSave.setText("Update");
        viewDiseaseCureForm();
    }
    
    public void tableWidth(JTable table)
    {
        TableColumnModel m = table.getColumnModel();
        TableModel m1 = table.getModel();
        int totalColumn = m.getColumnCount();
        for (int i=0;i<totalColumn;i++)
        {
            int length = 0;
            int totalRow = m1.getRowCount();
            for (int j=0;j<totalRow;j++)
            {
                if(m1.getValueAt(j, i)!=null)
                {
                    int length2 = m1.getValueAt(j, i).toString().length()*8;
                    if(length2>length)
                    {
                        length = length2;
                    }
                }
                m.getColumn(i).setMinWidth(length);
            }           
        }
    }

    public void viewDiseaseCureForm() {
        //System.out.println("Update : " + id);
        HashMap<Integer, HashMap> plant_mst_setup = new HashMap<>();

        plant_mst_setup = dc.getAllInformation("plant_setup_mst_tb", " where id = " + id);

        for (Object key : plant_mst_setup.keySet()) {
            txtPlantName.setText(plant_mst_setup.get(key).get("plant_name").toString());
            txtPlantDescription.setText(plant_mst_setup.get(key).get("description").toString());
            //model.addRow(new Object[]{plant_setup.get(key).get("id"),plant_setup.get(key).get("plant_name"),plant_setup.get(key).get("description")});
        }

        DefaultTableModel model = (DefaultTableModel) tablePlantSetup.getModel();
        model.setRowCount(0);
        HashMap<Integer, HashMap> plant_chd_setup = new HashMap<>();

        plant_chd_setup = dc.getAllInformation("plant_setup_chd_tb", " where plant_id = " + id);

        for (Object key : plant_chd_setup.keySet()) {
            //comboPlantName.addItem(plant_setup.get(key).get("plant_name").toString());
            model.addRow(new Object[]{plant_chd_setup.get(key).get("id"), plant_chd_setup.get(key).get("days"), plant_chd_setup.get(key).get("steps_desc")});
        }
        tableWidth(tablePlantSetup);
    }

    public boolean checkInput() {
        //System.out.println(jTable2.getColumnCount()-1);
        if (txtPlantName.getText().trim().equals("")) {
            txtPlantName.setBorder(empty);
            return false;
        } else {
            txtPlantName.setBorder(notEmpty);
        }
        int row = 0, column = 0;
        try {
            for (row = 0; row < tablePlantSetup.getRowCount(); row++) {
                for (column = 0; column < tablePlantSetup.getColumnCount(); column++) {
                    if (tablePlantSetup.getValueAt(row, column).toString().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Found Empty cell at row = " + row + " column =  " + column);
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Found Empty cell at row = " + row + " column =  " + column);
            tablePlantSetup.editCellAt(row, column);
            return false;
        }

        //.out.println("All true");
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtPlantName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePlantSetup = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPlantDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtPlantName.setToolTipText("Enter Plant Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Plant Description");

        tablePlantSetup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablePlantSetup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null}
            },
            new String [] {
                "id", "Day", "Work Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablePlantSetup.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablePlantSetup.setColumnSelectionAllowed(true);
        tablePlantSetup.setGridColor(new java.awt.Color(204, 204, 204));
        tablePlantSetup.getTableHeader().setReorderingAllowed(false);
        tablePlantSetup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tablePlantSetupMouseExited(evt);
            }
        });
        jScrollPane4.setViewportView(tablePlantSetup);
        tablePlantSetup.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tablePlantSetup.getColumnModel().getColumnCount() > 0) {
            tablePlantSetup.getColumnModel().getColumn(0).setMinWidth(0);
            tablePlantSetup.getColumnModel().getColumn(0).setMaxWidth(0);
            tablePlantSetup.getColumnModel().getColumn(1).setMinWidth(40);
            tablePlantSetup.getColumnModel().getColumn(1).setMaxWidth(50);
            tablePlantSetup.getColumnModel().getColumn(2).setMinWidth(2000);
        }

        txtPlantDescription.setColumns(20);
        txtPlantDescription.setRows(5);
        jScrollPane1.setViewportView(txtPlantDescription);

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("*");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Plant Name : ");

        jButton2.setText("add row");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("remove row");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(70, 147, 21));
        jPanel8.setPreferredSize(new java.awt.Dimension(131, 43));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rake.png"))); // NOI18N
        jLabel11.setText("Plant Setup");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 131, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPlantName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPlantName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(btnSave))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void insertPlantInformation()
    {
        DefaultTableModel model = (DefaultTableModel) tablePlantSetup.getModel();
        boolean check = checkInput();
        //System.out.println(check);
        boolean mstSuccess = false;
        if (check == true) {
            // Create a hash map
            hm.clear();
            // Put elements to the map
            hm.put("plant_name", txtPlantName.getText());
            hm.put("description", txtPlantDescription.getText());

            if (this.id != "0") {
                mstSuccess = dc.updateData(" plant_setup_mst_tb ", hm, " where id = " + this.id);
            } else {
                mstSuccess = dc.insertData("plant_setup_mst_tb", hm);
            }

            String chdId = "";
            boolean chdSuccess = false;
            for (int row = 0; row < tablePlantSetup.getRowCount(); row++) {
                hm.clear();
                hm.put("days", tablePlantSetup.getValueAt(row, 1).toString().trim());
                hm.put("steps_desc", tablePlantSetup.getValueAt(row, 2).toString().trim());
                if (this.id != "0") {
                    chdId = tablePlantSetup.getValueAt(row, 0).toString().trim();
                    chdSuccess = dc.updateData(" plant_setup_chd_tb ", hm, " where id = " + chdId);
                } else {
                    hm.put("plant_id", dc.maxId("plant_setup_mst_tb", "id"));
                    chdSuccess = dc.insertData("plant_setup_chd_tb", hm);
                }
                //chdSuccess = dc.insertData("plant_setup_chd_tb",hm);
            }
            //System.out.println(mstSuccess);
            //System.out.println(chdSuccess);
            //System.out.println(this.id);
            if (mstSuccess == true && chdSuccess == true && this.id == "0") 
            {
                JOptionPane.showMessageDialog(null, "Data Saved");
                txtPlantName.setText("");
                txtPlantDescription.setText("");
                model.setRowCount(0);
            } 
            else if (mstSuccess == true && chdSuccess == true && this.id != "0") 
            {
                JOptionPane.showMessageDialog(null, "Data Updated");
 AdminPanel ap = new AdminPanel(); 
        ap.ReloadPlantSetp();
                this.dispose();
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: Inserting Plant Information");
        }
        
       
    }
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        //System.out.println(dc.maxId("plant_setup_mst_tb", "id"));
        insertPlantInformation();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) tablePlantSetup.getModel();
            int i = tablePlantSetup.getSelectedRow();
            if (i >= 0) {
                model.removeRow(i);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row from the table");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in removing row");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tablePlantSetup.getModel();
        model.addRow(new Object[]{1, null, null});
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tablePlantSetupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePlantSetupMouseExited
        // TODO add your handling code here:
        tableWidth(tablePlantSetup);
    }//GEN-LAST:event_tablePlantSetupMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tablePlantSetup;
    private javax.swing.JTextArea txtPlantDescription;
    private javax.swing.JTextField txtPlantName;
    // End of variables declaration//GEN-END:variables
}
