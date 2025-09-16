/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.putrartx.gudangbarang2025;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class DataBarangDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DataBarangDialog.class.getName());
Map<String, String[]> jenisMap = new HashMap<>();
Map<String, String[]> tipeMap = new HashMap<>();
DefaultTableModel model;
    /**
     * Creates new form DataBarangDialog
     */
    public DataBarangDialog(JFrame parent) {
        super(parent, "Data Barang",true);
        initComponents();
        setLocationRelativeTo(parent);

        // Set default date
        tbx_date.setText(LocalDate.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        initComboModels();
        disableInputs();
        clearInputs();
        loadBarangToTable(frame);
        new Thread(() -> {
    while (true) {
        try {
            // Fetch data from DB
            DefaultTableModel model = fetchBarangFromDB();

            // Update JTable safely on EDT
            SwingUtilities.invokeLater(() -> {
                frame.setModel(model);
            });

            // Refresh every 3 seconds
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}).start();

    }
    public static DefaultTableModel fetchBarangFromDB() {
        String[] columns = {
            "Id", "Tanggal Input", "Kode Barang", "Nama Barang",
            "Jenis", "Tipe Jenis", "Merk",
            "Jumlah Barang", "Satuan", "Harga Satuan"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id, tanggal_input, kode_barang, nama_barang, jenis, tipe_jenis, " +
                     "merk, jumlah_barang, satuan, harga_satuan FROM barang";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
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
    public static void loadBarangToTable(JTable table) {
        String[] columns = {
            "Id", "Tanggal Input", "Kode Barang", "Nama Barang",
            "Jenis", "Tipe Jenis", "Merk",
            "Jumlah Barang", "Satuan", "Harga Satuan"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id, tanggal_input, kode_barang, nama_barang, jenis, tipe_jenis, " +
                     "merk, jumlah_barang, satuan, harga_satuan FROM barang";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
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

    // validate nama_barang
    if (tbx_name.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nama Barang harus diisi", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_name.requestFocus();
        return false;
    }

    // validate combo boxes
    if (cbx_type.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Pilih Jenis Barang", "Validation Error", JOptionPane.WARNING_MESSAGE);
        cbx_type.requestFocus();
        return false;
    }
    if (cbx_typejenis1.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Pilih Tipe Jenis", "Validation Error", JOptionPane.WARNING_MESSAGE);
        cbx_typejenis1.requestFocus();
        return false;
    }
    if (cbx_merk.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Pilih Merk", "Validation Error", JOptionPane.WARNING_MESSAGE);
        cbx_merk.requestFocus();
        return false;
    }

    // validate jumlah_barang
    try {
        Integer.parseInt(tbx_total.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah Barang harus angka bulat", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_total.requestFocus();
        return false;
    }

    // validate satuan
    if (cbx_pieces.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this, "Pilih Satuan Barang", "Validation Error", JOptionPane.WARNING_MESSAGE);
        cbx_pieces.requestFocus();
        return false;
    }

    // validate harga per item (decimal allowed)
    try {
        Double.parseDouble(tbx_priceperitem.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Harga per item harus angka (bisa desimal)", "Validation Error", JOptionPane.WARNING_MESSAGE);
        tbx_priceperitem.requestFocus();
        return false;
    }

    return true; // ✅ semua lolos
}


    private void initComboModels() {
    // Jenis -> Tipe Jenis
    jenisMap.put("Makanan", new String[]{"Snack", "Minuman", "Mie Instan", "Bumbu Dapur"});
    jenisMap.put("Elektronik", new String[]{"Handphone", "Laptop", "Televisi", "Kipas Angin"});
    jenisMap.put("Pakaian", new String[]{"Kaos", "Celana", "Jaket", "Sepatu"});
    jenisMap.put("Perawatan", new String[]{"Sabun", "Shampoo", "Pasta Gigi", "Kosmetik"});
    jenisMap.put("ATK", new String[]{"Pulpen", "Buku Tulis", "Penghapus", "Spidol"});
    jenisMap.put("Otomotif", new String[]{"Motor", "Mobil", "Ban", "Aki"});

    // Tipe Jenis -> Merk
    // Makanan
    tipeMap.put("Snack", new String[]{"Chitato", "Qtela", "Taro", "Tango"});
    tipeMap.put("Minuman", new String[]{"Teh Botol Sosro", "Aqua", "Le Minerale", "Good Day"});
    tipeMap.put("Mie Instan", new String[]{"Indomie", "Mie Sedaap", "Supermi", "Sarimi"});
    tipeMap.put("Bumbu Dapur", new String[]{"ABC", "Sajiku", "Royco", "Masako"});

    // Elektronik
    tipeMap.put("Handphone", new String[]{"Advan", "Polytron", "Evercoss"});
    tipeMap.put("Laptop", new String[]{"Axioo", "Zyrex"});
    tipeMap.put("Televisi", new String[]{"Polytron", "Akari", "Toshiba Indonesia"});
    tipeMap.put("Kipas Angin", new String[]{"Maspion", "Cosmos", "Miyako"});

    // Pakaian
    tipeMap.put("Kaos", new String[]{"3Second", "Eiger", "Erigo"});
    tipeMap.put("Celana", new String[]{"Lea", "Cardinal", "Hammer"});
    tipeMap.put("Jaket", new String[]{"Consina", "Eiger"});
    tipeMap.put("Sepatu", new String[]{"Compass", "League", "Specs"});

    // Perawatan
    tipeMap.put("Sabun", new String[]{"Lifebuoy", "Nuvo", "Giv"});
    tipeMap.put("Shampoo", new String[]{"Clear", "Pantene", "Emeron"});
    tipeMap.put("Pasta Gigi", new String[]{"Pepsodent", "Formula", "Ciptadent"});
    tipeMap.put("Kosmetik", new String[]{"Wardah", "Emina", "Make Over"});

    // ATK
    tipeMap.put("Pulpen", new String[]{"Standard", "Snowman", "Kenko"});
    tipeMap.put("Buku Tulis", new String[]{"Sidu", "Big Boss", "Kiky"});
    tipeMap.put("Penghapus", new String[]{"Joyko", "Kenko"});
    tipeMap.put("Spidol", new String[]{"Snowman", "Faster", "Kenko"});

    // Otomotif
    tipeMap.put("Motor", new String[]{"Honda", "Yamaha", "Suzuki"});
    tipeMap.put("Mobil", new String[]{"Toyota", "Daihatsu", "Mitsubishi"});
    tipeMap.put("Ban", new String[]{"GT Radial", "Achilles", "Swallow"});
    tipeMap.put("Aki", new String[]{"GS Astra", "Incoe", "Yuasa"});


    // Set Jenis combobox model
    cbx_type.setModel(new DefaultComboBoxModel<>(jenisMap.keySet().toArray(new String[0])));

    // Clear dependent combos
    cbx_typejenis1.setModel(new DefaultComboBoxModel<>());
    cbx_merk.setModel(new DefaultComboBoxModel<>());
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_merk = new javax.swing.JLabel();
        tbx_total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbx_pieces = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        tbx_priceperitem = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btn_delete = new javax.swing.JButton();
        tbx_date = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        tbx_code = new javax.swing.JTextField();
        btn_exit = new javax.swing.JButton();
        tbx_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        frame = new javax.swing.JTable();
        cbx_type = new javax.swing.JComboBox<>();
        btn_input = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbx_merk = new javax.swing.JComboBox<>();
        tbx_typejenis = new javax.swing.JLabel();
        cbx_typejenis1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label_merk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_merk.setText("Merk :");
        label_merk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Jumlah Barang :");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cbx_pieces.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pcs", "lusin", "pack", "dus", "kg", "gram" }));
        cbx_pieces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_piecesActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Satuan :");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Harga Satuan :");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_delete.setText("HAPUS");

        btn_search.setText("CARI");

        btn_exit.setText("KELUAR");
        btn_exit.setActionCommand("");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
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
        jScrollPane1.setViewportView(frame);

        cbx_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman", "Barang Rumah Tangga" }));
        cbx_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_typeActionPerformed(evt);
            }
        });

        btn_input.setText("INPUT");
        btn_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inputActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Cascadia Code", 0, 36)); // NOI18N
        jLabel1.setText("Data Barang");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tanggal Input :");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Kode Barang :");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Nama Barang :");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Jenis :");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tbx_typejenis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tbx_typejenis.setText("Tipe Jenis :");
        tbx_typejenis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cbx_typejenis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_typejenis1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_date, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(681, 681, 681)
                        .addComponent(btn_exit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_code, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_name, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_type, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(tbx_typejenis, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_typejenis1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(label_merk, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_merk, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_total, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_pieces, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbx_priceperitem, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btn_input)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete)
                        .addGap(18, 18, 18)
                        .addComponent(btn_search))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(tbx_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_exit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(tbx_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(tbx_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(cbx_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(tbx_typejenis))
                    .addComponent(cbx_typejenis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(label_merk))
                    .addComponent(cbx_merk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel10))
                    .addComponent(tbx_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11))
                    .addComponent(cbx_pieces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12))
                    .addComponent(tbx_priceperitem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_input)
                    .addComponent(btn_delete)
                    .addComponent(btn_search))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbx_piecesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_piecesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_piecesActionPerformed

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

    private void cbx_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_typeActionPerformed
        String jenis = (String) cbx_type.getSelectedItem();
        if (jenis != null) {
            String[] tipeList = jenisMap.get(jenis);
            cbx_typejenis1.setModel(new DefaultComboBoxModel<>(tipeList));
            cbx_typejenis1.setSelectedIndex(-1);
            cbx_merk.setModel(new DefaultComboBoxModel<>()); // clear merk
        }
    }//GEN-LAST:event_cbx_typeActionPerformed

    private void btn_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inputActionPerformed
        if(btn_input.getText().equals("INPUT")){
            btn_input.setText("SAVE");
            clearInputs(); // reset all fields
            enableInputs();
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
    stmt.setString(3, tbx_name.getText());
    stmt.setString(4, cbx_type.getSelectedItem().toString());
    stmt.setString(5, cbx_typejenis1.getSelectedItem().toString());
    stmt.setString(6, cbx_merk.getSelectedItem().toString());
    stmt.setInt(7, Integer.parseInt(tbx_total.getText()));
    stmt.setString(8, cbx_pieces.getSelectedItem().toString());
    stmt.setBigDecimal(9, new java.math.BigDecimal(tbx_priceperitem.getText()));

    int rows = stmt.executeUpdate();

    if (rows > 0) {
        
        System.out.println("Data inserted successfully!");
        
    }

} catch (Exception ex) {
    ex.printStackTrace();
}
            btn_input.setText("INPUT");
            disableInputs();
        }
        loadBarangToTable(frame);
    }//GEN-LAST:event_btn_inputActionPerformed

    private void cbx_typejenis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_typejenis1ActionPerformed
        String tipe = (String) cbx_typejenis1.getSelectedItem();
        if (tipe != null) {
            String[] merkList = tipeMap.get(tipe);
            cbx_merk.setModel(new DefaultComboBoxModel<>(merkList));
            cbx_merk.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_cbx_typejenis1ActionPerformed
private void clearInputs() {
    tbx_date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    tbx_code.setText("");
    tbx_name.setText("");
    cbx_type.setSelectedIndex(-1);
    cbx_typejenis1.setSelectedIndex(-1); // reset selection
    cbx_merk.setSelectedIndex(-1);       // reset selection
    tbx_total.setText("");
    cbx_pieces.setSelectedIndex(-1);
    tbx_priceperitem.setText("");
}
private void disableInputs() {
    boolean enabled = false;
    tbx_date.setEnabled(enabled);
    tbx_code.setEnabled(enabled);
    tbx_name.setEnabled(enabled);
    cbx_type.setEnabled(enabled);
    cbx_typejenis1.setEnabled(enabled);
    cbx_merk.setEnabled(enabled);
    tbx_total.setEnabled(enabled);
    cbx_pieces.setEnabled(enabled);
    tbx_priceperitem.setEnabled(enabled);
}
private void enableInputs() {
    boolean enabled = true;
    tbx_date.setEnabled(enabled);
    tbx_code.setEnabled(enabled);
    tbx_name.setEnabled(enabled);
    cbx_type.setEnabled(enabled);
    cbx_typejenis1.setEnabled(enabled);
    cbx_merk.setEnabled(enabled);
    tbx_total.setEnabled(enabled);
    cbx_pieces.setEnabled(enabled);
    tbx_priceperitem.setEnabled(enabled);
}
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_input;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cbx_merk;
    private javax.swing.JComboBox<String> cbx_pieces;
    private javax.swing.JComboBox<String> cbx_type;
    private javax.swing.JComboBox<String> cbx_typejenis1;
    private javax.swing.JTable frame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_merk;
    private javax.swing.JTextField tbx_code;
    private javax.swing.JTextField tbx_date;
    private javax.swing.JTextField tbx_name;
    private javax.swing.JTextField tbx_priceperitem;
    private javax.swing.JTextField tbx_total;
    private javax.swing.JLabel tbx_typejenis;
    // End of variables declaration//GEN-END:variables
}
