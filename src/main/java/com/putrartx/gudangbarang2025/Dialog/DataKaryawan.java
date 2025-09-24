/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.putrartx.gudangbarang2025.Dialog;

import com.putrartx.gudangbarang2025.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrator
 */
public class DataKaryawan extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DataKaryawan.class.getName());

    /**
     * Creates new form DataKaryawan
     */
    private String OperationCode;
    public DataKaryawan(JFrame parent) {
        super(parent, "Data Barang",true);
        initComponents();
        setLocationRelativeTo(parent);
        
        tbx_tglinput.setText(LocalDate.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        disableInput();
        String sql = "SELECT kota FROM kota ORDER BY kota ASC";

try (Connection conn = DBContext.getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql);
     ResultSet rs = stmt.executeQuery()) {

    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
    while (rs.next()) {
        model.addElement(rs.getString("kota"));
        model2.addElement(rs.getString("kota"));
    }

    cbx_borncity.setModel(model);
    cbx_city.setModel(model2);
    ResetState();

} catch (SQLException e) {
    e.printStackTrace();
}
        loadkaryawanToTable(frame);
        
        
//        new Thread(() -> {
//    while (true) {
//        try {
//            // Fetch data from DB
//            DefaultTableModel model = loadkaryawanToTableTh(frame);
//
//            // Update JTable safely on EDT
//            SwingUtilities.invokeLater(() -> {
//                frame.setModel(model);
//            });
//
//            // Refresh every 3 seconds
//            Thread.sleep(3000);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}).start();
frame.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if(btn_input.getText() == "SAVE"){ // Block Action when inputting
            btn_delete.setEnabled(false);
            btn_search.setEnabled(false);
            btn_update.setEnabled(false);
            return;
        }
        btn_cancel.setEnabled(true);
        btn_input.setEnabled(false);
        btn_update.setEnabled(true);
        btn_search.setEnabled(true);
        btn_delete.setEnabled(true);
        btn_search.setEnabled(false);
        int row = frame.rowAtPoint(evt.getPoint());
        int col = frame.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            // Get value directly from model
            Object value = frame.getModel().getValueAt(row, col);
            System.out.println("Clicked value: " + value);

            // If you want the whole row data
            int columnCount = frame.getColumnCount();
//            for (int i = 0; i < columnCount; i++) {
//                Object cell = frame.getModel().getValueAt(row, i);
//                System.out.println("Column " + i + ": " + cell);
//                if(i == 2)
//            }
                Object code = frame.getModel().getValueAt(row, 2);
                OperationCode = code.toString();
                
                
                
                // Enable Input
                enableInput();
                // Fill Input
                tbx_code.setEnabled(false);
                tbx_tglinput.setText((frame.getModel().getValueAt(row, 1)).toString());
                tbx_code.setText((frame.getModel().getValueAt(row, 2)).toString());
                tbx_name.setText((frame.getModel().getValueAt(row, 3)).toString());
                cbx_borncity.setSelectedItem((frame.getModel().getValueAt(row, 4)).toString());
                try {
    String dateStr = frame.getModel().getValueAt(row, 5).toString();
    jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
} catch (Exception e) {
    e.printStackTrace(); // or handle properly
}

                cbx_city.setSelectedItem((frame.getModel().getValueAt(row, 6)).toString());
                jTextArea1.setText((frame.getModel().getValueAt(row, 7)).toString());
        }
    }
});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tbx_code = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbx_name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbx_borncity = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        frame = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        tbx_tglinput = new javax.swing.JTextField();
        btn_cancel = new javax.swing.JButton();
        cbx_city = new javax.swing.JComboBox<>();
        btn_input = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        tbx_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Cascadia Code", 0, 36)); // NOI18N
        jLabel1.setText("Data Karyawan");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("No. Karyawan");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Nama Karyawan");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tbx_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbx_nameKeyTyped(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("TTL");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Alamat Kota");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Alamat Jalan");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        frame.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(frame);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Tanggal Input");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_cancel.setText("CANCEL");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_input.setText("INPUT");
        btn_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inputActionPerformed(evt);
            }
        });

        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        tbx_search.setToolTipText("Nama Barang...");

        btn_search.setText("CARI");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tbx_name, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tbx_code, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbx_borncity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbx_city, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(31, 31, 31)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tbx_tglinput, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btn_cancel)
                        .addGap(18, 18, 18)
                        .addComponent(btn_input)
                        .addGap(18, 18, 18)
                        .addComponent(btn_update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_search, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8))
                    .addComponent(tbx_tglinput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(tbx_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(tbx_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbx_borncity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbx_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cancel)
                            .addComponent(btn_input)
                            .addComponent(btn_update)
                            .addComponent(btn_delete)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbx_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_search))))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        ResetState();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void tbx_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbx_nameKeyTyped
         if (tbx_name.getText().length() > 20) {
        evt.consume(); // prevent typing more
        JOptionPane.showMessageDialog(null, "Nama maksimal 20 karakter");
    }
    }//GEN-LAST:event_tbx_nameKeyTyped

    private void btn_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inputActionPerformed
        if((btn_input.getText())=="INPUT"){
            enableInput();
            
            clearInput();
            tbx_tglinput.setText(LocalDate.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            btn_input.setText("SAVE");
            btn_cancel.setEnabled(true);
            btn_search.setEnabled(false);
        }else{
            if(!validateInputs()){
                return;
            }
            
            String sql = "INSERT INTO karyawan " +
    "(tanggal_input,no_karyawan,nama,kota_lahir,tanggal_lahir,alamat_kota,alamat_jalan) " +
    "VALUES (?, ?, ?, ?, ?, ?, ?)";

try (Connection conn = DBContext.getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
stmt.setDate(1, java.sql.Date.valueOf(tbx_tglinput.getText())); // must be yyyy-MM-dd 
stmt.setString(2, tbx_code.getText()); stmt.setString(3, tbx_name.getText()); stmt.setString(4, cbx_borncity.getSelectedItem().toString()); stmt.setDate(5, new java.sql.Date(jDateChooser1.getDate().getTime())); stmt.setString(6, cbx_city.getSelectedItem().toString()); stmt.setString(7, jTextArea1.getText().toString());

    int rows = stmt.executeUpdate();

    if (rows > 0) {
        
        System.out.println("Data inserted successfully!");
        
    }

} catch (Exception ex) {
    ex.printStackTrace();
}
            loadkaryawanToTable(frame);
            
            disableInput();
            btn_input.setText("INPUT");
            ResetState();
        }
    }//GEN-LAST:event_btn_inputActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if (!validateInputs()) {
        return; // stop kalau invalid
        }
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Apakah kamu yakin ingin mengubah data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );
            if(confirm == JOptionPane.NO_OPTION){
                return;
            }
        String sql = "UPDATE karyawan SET " +
             "tanggal_input = ?, " +
             "nama = ?, " +
             "kota_lahir = ?, " +
             "tanggal_lahir = ?, " +
             "alamat_kota = ?, " +
             "alamat_jalan = ? " +
             "WHERE no_karyawan = ?";

try (Connection conn = DBContext.getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {

    // Example of setting parameters
    stmt.setDate(1, java.sql.Date.valueOf(tbx_tglinput.getText())); // must be yyyy-MM-dd 
stmt.setString(2, tbx_name.getText());
stmt.setString(3, cbx_borncity.getSelectedItem().toString());
stmt.setDate(4, new java.sql.Date(jDateChooser1.getDate().getTime()));
stmt.setString(5, cbx_city.getSelectedItem().toString());
stmt.setString(6, jTextArea1.getText().toString());
stmt.setString(7, OperationCode);

    int rowsAffected = stmt.executeUpdate();
    System.out.println(rowsAffected + " row(s) updated.");
}
 catch (Exception ex) {
    ex.printStackTrace();
}
        loadkaryawanToTable(frame);
ResetState();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Apakah kamu yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );
            if(confirm == JOptionPane.NO_OPTION){
                return;
            }
        String sql = "DELETE FROM karyawan WHERE no_karyawan = ?";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, OperationCode); // assuming OperationCode is your selected kode_barang
        int affected = stmt.executeUpdate();

        if (affected > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            // Optionally refresh your JTable here
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus data: " + e.getMessage());
    }
        loadkaryawanToTable(frame);
        ResetState();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        loadkaryawanToTable(frame);
        btn_input.setEnabled(false);
        btn_cancel.setEnabled(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(frame.getModel());
        frame.setRowSorter(sorter);

        String keyword = tbx_search.getText(); // the string to search
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 3));
    }//GEN-LAST:event_btn_searchActionPerformed

    private boolean validateInputs() {
    if (tbx_tglinput.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Tanggal Input harus diisi (yyyy-MM-dd)");
        return false;
    }
    try {
        java.sql.Date.valueOf(tbx_tglinput.getText()); // check format
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Format Tanggal Input tidak valid. Gunakan yyyy-MM-dd");
        return false;
    }

    if (tbx_code.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "No Karyawan harus diisi");
        return false;
    }
    if (!tbx_code.getText().matches("^[A-Z]{2}-\\d{3}$")) {
    JOptionPane.showMessageDialog(null, "No Karyawan harus berformat AB-123");
    return false;
}
    if (tbx_name.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nama harus di isi");
        return false;
    }
     if (tbx_name.getText().length() > 20) {
        JOptionPane.showMessageDialog(null, "Nama maksimal 20 karakter");
        return false;
    }
    if (cbx_borncity.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(null, "Kota Lahir harus di pilih");
        return false;
    }
    if (jDateChooser1.getDate() == null) {
        JOptionPane.showMessageDialog(null, "Tanggal lahir harus di isi");
        return false;
    }
    if (cbx_city.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(null, "Alamat Kota harus di pilih");
        return false;
    }
    if (jTextArea1.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Alamat harus di isi");
        return false;
    }

    return true;
}

    
    private void clearInput(){
        cbx_borncity.setSelectedIndex(-1);
cbx_city.setSelectedIndex(-1);
jDateChooser1.setDate(null);
jTextArea1.setText("");
tbx_code.setText("");
tbx_name.setText("");
tbx_tglinput.setText("");

    }
    private void disableInput(){
        boolean into = false;
        cbx_borncity.setEnabled(into);
        cbx_city.setEnabled(into);
        jDateChooser1.setEnabled(into);
        jTextArea1.setEnabled(into);
        tbx_code.setEnabled(into);
        tbx_name.setEnabled(into);
        tbx_tglinput.setEnabled(into);
    }
        private void enableInput(){
        boolean into = true;
        cbx_borncity.setEnabled(into);
        cbx_city.setEnabled(into);
        jDateChooser1.setEnabled(into);
        jTextArea1.setEnabled(into);
        tbx_code.setEnabled(into);
        tbx_name.setEnabled(into);
        tbx_tglinput.setEnabled(into);
    }
        private void ResetState(){
    clearInput();
        disableInput();
        btn_cancel.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_input.setEnabled(true);
        btn_input.setText("INPUT");
        btn_search.setEnabled(true);
        btn_update.setEnabled(false);
        tbx_search.setText("");
        OperationCode = "";
        loadkaryawanToTable(frame);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(frame.getModel());
frame.setRowSorter(sorter);

String keyword = ""; // turn off filter
sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 3));
}
    public static void loadkaryawanToTable(JTable table) {
    String[] columns = {
        "Id", "Tanggal Input", "No Karyawan", "Nama Karyawan",
        "Kota Lahir", "Tanggal Lahir", "Kota",
        "Alamat"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);

    String sql = "SELECT tanggal_input, no_karyawan, nama, kota_lahir, tanggal_lahir, " +
                 "alamat_kota, alamat_jalan FROM karyawan";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int idCounter = 1;
        while (rs.next()) {
            Object[] row = {
                idCounter++,
                rs.getDate("tanggal_input"),
                rs.getString("no_karyawan"),
                rs.getString("nama"),
                rs.getString("kota_lahir"),
                rs.getDate("tanggal_lahir"),
                rs.getString("alamat_kota"),
                rs.getString("alamat_jalan")
                
            };
            model.addRow(row);
        }

        table.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
}
    public static DefaultTableModel loadkaryawanToTableTh(JTable table) {
    String[] columns = {
        "Id", "Tanggal Input", "No Karyawan", "Nama Karyawan",
        "Kota Lahir", "Tanggal Lahir", "Kota",
        "Alamat"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);

    String sql = "SELECT tanggal_input, no_karyawan, nama, kota_lahir, tanggal_lahir, " +
                 "alamat_kota, alamat_jalan FROM karyawan";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int idCounter = 1;
        while (rs.next()) {
            Object[] row = {
                idCounter++,
                rs.getDate("tanggal_input"),
                rs.getString("no_karyawan"),
                rs.getString("nama"),
                rs.getString("kota_lahir"),
                rs.getDate("tanggal_lahir"),
                rs.getString("alamat_kota"),
                rs.getString("alamat_jalan")
                
            };
            model.addRow(row);
        }

        table.setModel(model);
        return model;

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_input;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbx_borncity;
    private javax.swing.JComboBox<String> cbx_city;
    private javax.swing.JTable frame;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField tbx_code;
    private javax.swing.JTextField tbx_name;
    private javax.swing.JTextField tbx_search;
    private javax.swing.JTextField tbx_tglinput;
    // End of variables declaration//GEN-END:variables
}
