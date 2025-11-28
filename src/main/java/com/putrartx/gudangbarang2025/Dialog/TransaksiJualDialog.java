/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.putrartx.gudangbarang2025.Dialog;

import com.putrartx.gudangbarang2025.DBContext;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import java.util.Random;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Administrator
 */
public class TransaksiJualDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TransaksiJualDialog.class.getName());
Map<String, String[]> jenisMap = new HashMap<>();
Map<String, String[]> tipeMap = new HashMap<>();
DefaultTableModel model;
    /**
     * Creates new form DataBarangDialog
     */
public String OperationCode;

    public TransaksiJualDialog(JFrame parent) {
        super(parent, "Data Barang",true);
        initComponents();
        setLocationRelativeTo(parent);

        // Set default date
        tbx_date.setText(LocalDate.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        disableInputs();
        clearInputs();
        btn_delete.setEnabled(false);
        btn_cancel.setEnabled(false);
        btn_update.setEnabled(false);
        
        loadDataToTable(frame);
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
                enableInputs();
                
                // Fill Input
                tbx_code.setEnabled(false);
                btn_randomcode.setEnabled(false);
                tbx_date.setText((frame.getModel().getValueAt(row, 1)).toString());
                tbx_code.setText((frame.getModel().getValueAt(row, 2)).toString());
                tbx_name.setText((frame.getModel().getValueAt(row, 3)).toString());
                
                loadDataToSubTable(framecart, OperationCode);
                
        }
    }
});

    }
    public static DefaultTableModel fetchBarangFromDB() {
    String[] columns = {
        "Id", "Tanggal Input", "Kode Barang", "Nama Barang",
        "Jenis", "Tipe Jenis", "Merk",
        "Jumlah Barang", "Satuan", "Harga Satuan"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);

    String sql = "SELECT tanggal_input, kode_barang, nama_barang, jenis, tipe_jenis, " +
                 "merk, jumlah_barang, satuan, harga_satuan FROM barang";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int idCounter = 1;
        while (rs.next()) {
            Object[] row = {
                idCounter++,
                rs.getDate("tanggal_input"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getString("jenis"),
                rs.getString("tipe_jenis"),
                rs.getString("merk"),
                rs.getInt("jumlah_barang"),
                rs.getString("satuan"),
                rs.getBigDecimal("harga_satuan")
            };
            model.addRow(row);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return model;
}

public static void loadDataToTable(JTable table) {
    String[] columns = {
        "Id", "Tanggal Input" ,"Kode Transaksi", "Nama Toko", "Tanggal Transaksi"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);

    String sql = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (PARTITION BY kode_transaksi ORDER BY id) rn FROM transaksi WHERE tipe='Jual') t WHERE rn=1;";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int idCounter = 1;
        while (rs.next()) {
            Object[] row = {
                idCounter++,
                rs.getDate("tanggal_input"),
                rs.getString("kode_transaksi"),
                rs.getString("nama"),
                rs.getDate("tanggal_transaksi"),
            };
            model.addRow(row);
        }

        table.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void loadDataToSubTable(JTable table, String kode_trx) {
    String[] columns = {
        "Id", "Nama Barang" ,"Jumlah", "Harga Satuan", "Subtotal Harga"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);

    String sql = "SELECT * FROM transaksi WHERE tipe = 'Jual' AND kode_transaksi = '" + kode_trx +"';";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int idCounter = 1;
        while (rs.next()) {
            Object[] row = {
                idCounter++,
                rs.getString("nama_barang"),
                rs.getString("jumlah"),
                rs.getString("harga_satuan"),
                rs.getDate("subtotal_harga"),
            };
            model.addRow(row);
        }

        table.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private boolean validateInputs() {
    // validate date format
    try {
        LocalDate.parse(tbx_date.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Tanggal harus dalam format yyyy-MM-dd", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_date.requestFocus();
        return false;
    }

    // validate kode_barang (XX-YYYY)
    if (!tbx_code.getText().matches("^[A-Z]{2}-\\d{4}$")) {
        JOptionPane.showMessageDialog(this, "Kode Barang harus format XX-YYYY (contoh: AB-1234)", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_code.requestFocus();
        return false;
    }
    
    // Check if code exists in JTable
    String code = tbx_code.getText().trim();
    for (int i = 0; i < frame.getRowCount(); i++) {
        String existingCode = frame.getValueAt(i, 2).toString(); // column 0 assumed to be 'Kode Barang'
        if (code.equalsIgnoreCase(existingCode)) {
            JOptionPane.showMessageDialog(this, 
                "Kode Barang sudah ada di tabel!", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            tbx_code.requestFocus();
            return false;
        }
    }

    // validate nama_barang
    if (tbx_name.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nama Barang harus diisi", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_name.requestFocus();
        return false;
    }


    return true; // ✅ semua lolos
}
private boolean validateInputsEdit() {
    // validate date format
    try {
        LocalDate.parse(tbx_date.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Tanggal harus dalam format yyyy-MM-dd", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_date.requestFocus();
        return false;
    }

    // validate kode_barang (XX-YYYY)
    if (!tbx_code.getText().matches("^[A-Z]{2}-\\d{4}$")) {
        JOptionPane.showMessageDialog(this, "Kode Barang harus format XX-YYYY (contoh: AB-1234)", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_code.requestFocus();
        return false;
    }

    // validate nama_barang
    if (tbx_name.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nama Barang harus diisi", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_name.requestFocus();
        return false;
    }

    // validate harga per item (decimal allowed)
    try {
        Double.parseDouble(tbx_priceperitem.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Harga per item harus angka", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_priceperitem.requestFocus();
        return false;
    }

    return true; // ✅ semua lolos
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbx_barang = new javax.swing.JComboBox<>();
        btn_input = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tbx_qty = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tbx_priceperitem = new javax.swing.JTextField();
        btn_cancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btn_update = new javax.swing.JButton();
        tbx_subtotal = new javax.swing.JTextField();
        btn_delete = new javax.swing.JButton();
        tbx_search = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tbx_date = new javax.swing.JTextField();
        btn_randomcode = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        framecart = new javax.swing.JTable();
        btn_search = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        frame = new javax.swing.JTable();
        tbx_code = new javax.swing.JTextField();
        btn_exit = new javax.swing.JButton();
        tbx_name = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cbx_barang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_input.setText("INPUT");
        btn_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inputActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Tanggal Transaksi");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel1.setFont(new java.awt.Font("Cascadia Code", 0, 36)); // NOI18N
        jLabel1.setText("Jual Barang");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Jumlah :");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Barang :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Kode Transaksi :");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_cancel.setText("CANCEL");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Harga Satuan :");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        tbx_subtotal.setEditable(false);

        btn_delete.setText("HAPUS");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        tbx_search.setToolTipText("Nama Barang...");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("SubTotal :");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_randomcode.setText("RANDOM");
        btn_randomcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_randomcodeActionPerformed(evt);
            }
        });

        framecart.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(framecart);

        btn_search.setText("CARI");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(frame);

        btn_exit.setText("KELUAR");
        btn_exit.setActionCommand("");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Nama / Toko :");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(514, 514, 514)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_code, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_randomcode))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_date, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_name, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tbx_priceperitem))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tbx_qty, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbx_barang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tbx_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cancel)
                        .addGap(18, 18, 18)
                        .addComponent(btn_input)
                        .addGap(18, 18, 18)
                        .addComponent(btn_update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search)
                        .addGap(18, 18, 18)
                        .addComponent(btn_exit))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_input)
                        .addComponent(btn_cancel))
                    .addComponent(btn_update)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_delete)
                        .addComponent(tbx_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_search)
                        .addComponent(btn_exit)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2)
                .addGap(67, 67, 67))
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tbx_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tbx_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_randomcode)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(tbx_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tbx_priceperitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbx_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tbx_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tbx_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 159, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inputActionPerformed
        if(btn_input.getText().equals("INPUT")){
            btn_input.setText("SAVE");
            clearInputs(); // reset all fields
            enableInputs();
            OperationCode = "";
            btn_cancel.setEnabled(true);
            btn_delete.setEnabled(false);
            btn_search.setEnabled(false);
            btn_update.setEnabled(false);
        } else {
            if (!validateInputs()) {
                return; // stop kalau invalid
            }
            String sql = "INSERT INTO barang " +
            "(tanggal_input, kode_barang, nama_barang, jenis, tipe_jenis, merk, jumlah_barang, satuan, harga_satuan) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DBContext.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setDate(1, java.sql.Date.valueOf(tbx_date.getText())); // must be yyyy-MM-dd
                stmt.setString(2, tbx_code.getText());

                int rows = stmt.executeUpdate();

                if (rows > 0) {

                    System.out.println("Data inserted successfully!");

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            btn_input.setText("INPUT");
            disableInputs();
            OperationCode = "";
            btn_delete.setEnabled(false);
            btn_search.setEnabled(false);
            ResetState();
        }
        loadDataToTable(frame);

    }//GEN-LAST:event_btn_inputActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // THIS SHOULD PUT DIALOG INTO NORMAL STATE NO MATTER WHAT
        ResetState();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        if (!validateInputsEdit()) {
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
        String sql = "UPDATE barang SET " +
        "tanggal_input = ?, " +
        "nama_barang = ?, " +
        "jenis = ?, " +
        "tipe_jenis = ?, " +
        "merk = ?, " +
        "jumlah_barang = ?, " +
        "satuan = ?, " +
        "harga_satuan = ? " +
        "WHERE kode_barang = ?";   // condition!
        try (Connection conn = DBContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(tbx_date.getText())); // yyyy-MM-dd

            // kode_barang goes last (WHERE condition)
            stmt.setString(9, tbx_code.getText());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            } else {
                System.out.println("No record found to update.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        loadDataToTable(frame);
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
        String sql = "DELETE FROM barang WHERE kode_barang = ?";

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
        loadDataToTable(frame);
        ResetState();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_randomcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_randomcodeActionPerformed
        Random rand = new Random();
        String result;

        do {
            // Generate two random uppercase letters
            char first = (char) ('A' + rand.nextInt(26));
            char second = (char) ('A' + rand.nextInt(26));

            // Generate four random digits
            int number = rand.nextInt(10000); // 0–9999

            result = String.format("%c%c-%04d", first, second, number);

            // Check uniqueness in JTable
            boolean exists = false;
            for (int i = 0; i < frame.getRowCount(); i++) {
                String existingCode = frame.getValueAt(i, 2).toString();
                if (result.equalsIgnoreCase(existingCode)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) break;  // stop when code is unique
        } while (true);

        tbx_code.setText(result);
    }//GEN-LAST:event_btn_randomcodeActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        loadDataToTable(frame);
        btn_input.setEnabled(false);
        btn_cancel.setEnabled(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(frame.getModel());
        frame.setRowSorter(sorter);

        String keyword = tbx_search.getText(); // the string to search
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 3));
    }//GEN-LAST:event_btn_searchActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Anda yakin akan menutup " + this.getTitle()+ "?",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btn_exitActionPerformed
private void clearInputs() {
    tbx_date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    tbx_code.setText("");
    tbx_name.setText("");
    tbx_priceperitem.setText("");
}
private void disableInputs() {
    boolean enabled = false;
    tbx_date.setEnabled(enabled);
    tbx_code.setEnabled(enabled);
    tbx_name.setEnabled(enabled);
    btn_randomcode.setEnabled(enabled);
    cbx_barang.setEnabled(enabled);
    tbx_priceperitem.setEnabled(enabled);
    tbx_qty.setEnabled(enabled);
    tbx_subtotal.setEnabled(enabled);
}
private void enableInputs() {
    boolean enabled = true;
    tbx_date.setEnabled(enabled);
    tbx_code.setEnabled(enabled);
    tbx_name.setEnabled(enabled);
    btn_randomcode.setEnabled(enabled);
//    tbx_priceperitem.setEnabled(enabled);
//    cbx_barang.setEnabled(enabled);
//    tbx_qty.setEnabled(enabled);
}
private void ResetState(){
    clearInputs();
        disableInputs();
        btn_cancel.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_input.setEnabled(true);
        btn_input.setText("INPUT");
        btn_search.setEnabled(true);
        btn_update.setEnabled(false);
        tbx_search.setText("");
        OperationCode = "";
        loadDataToTable(frame);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(frame.getModel());
frame.setRowSorter(sorter);

String keyword = ""; // turn off filter
sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 3));
}
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_input;
    private javax.swing.JButton btn_randomcode;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbx_barang;
    private javax.swing.JTable frame;
    private javax.swing.JTable framecart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField tbx_code;
    private javax.swing.JTextField tbx_date;
    private javax.swing.JTextField tbx_name;
    private javax.swing.JTextField tbx_priceperitem;
    private javax.swing.JTextField tbx_qty;
    private javax.swing.JTextField tbx_search;
    private javax.swing.JTextField tbx_subtotal;
    // End of variables declaration//GEN-END:variables
}
