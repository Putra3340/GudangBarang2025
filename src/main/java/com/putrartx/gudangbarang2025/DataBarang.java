package com.putrartx.gudangbarang2025;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DataBarang extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DataBarang.class.getName());
    DefaultTableModel model;
    public DataBarang(JFrame parent) {
        super(parent, "Data Barang", true); // modal
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
    // initialize columns
    String[] columns = {
        "Tanggal Input",
        "Kode Barang",
        "Nama Barang",
        "Jenis",
        "Tipe Jenis",
        "Merk",
        "Jumlah Barang",
        "Satuan",
        "Harga Satuan"
    };

    // Create empty table model with columns
    this.model = new DefaultTableModel(columns, 0);

    // Apply model to JTable1
    frame.setModel(this.model);

    // Set default date
    tbx_date.setText(LocalDate.now().format(
        DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    initComboModels();
    disableInputs();
    clearInputs();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tbx_typejenis = new javax.swing.JLabel();
        label_merk = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tbx_date = new javax.swing.JTextField();
        tbx_code = new javax.swing.JTextField();
        tbx_name = new javax.swing.JTextField();
        cbx_type = new javax.swing.JComboBox<>();
        btn_input = new javax.swing.JButton();
        cbx_merk = new javax.swing.JComboBox<>();
        cbx_typejenis1 = new javax.swing.JComboBox<>();
        tbx_total = new javax.swing.JTextField();
        cbx_pieces = new javax.swing.JComboBox<>();
        tbx_priceperitem = new javax.swing.JTextField();
        btn_delete = new javax.swing.JButton();
        btn_search = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        frame = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        label_merk.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_merk.setText("Merk :");
        label_merk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Jumlah Barang :");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Satuan :");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Harga Satuan :");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

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

        cbx_typejenis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_typejenis1ActionPerformed(evt);
            }
        });

        cbx_pieces.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pcs", "lusin", "pack", "dus", "kg", "gram" }));
        cbx_pieces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_piecesActionPerformed(evt);
            }
        });

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
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("label_judul");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbx_piecesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_piecesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_piecesActionPerformed

    private void btn_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inputActionPerformed
        if(btn_input.getText().equals("INPUT")){
        btn_input.setText("SAVE");
        clearInputs(); // reset all fields
        enableInputs();
    } else {
        model.addRow(new Object[]{
            tbx_date.getText(),
            tbx_code.getText(),
            tbx_name.getText(),
            cbx_type.getSelectedItem(),
            cbx_typejenis1.getSelectedItem(),
            cbx_merk.getSelectedItem(),
            tbx_total.getText(),
            cbx_pieces.getSelectedItem(),
            tbx_priceperitem.getText()
        });
        btn_input.setText("INPUT");
        disableInputs();
    }
    }//GEN-LAST:event_btn_inputActionPerformed
// Data model (you can expand it)
Map<String, String[]> jenisMap = new HashMap<>();
Map<String, String[]> tipeMap = new HashMap<>();

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
    private void cbx_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_typeActionPerformed
        String jenis = (String) cbx_type.getSelectedItem();
    if (jenis != null) {
        String[] tipeList = jenisMap.get(jenis);
        cbx_typejenis1.setModel(new DefaultComboBoxModel<>(tipeList));
        cbx_merk.setModel(new DefaultComboBoxModel<>()); // clear merk
    }
    }//GEN-LAST:event_cbx_typeActionPerformed

    private void cbx_typejenis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_typejenis1ActionPerformed
        String tipe = (String) cbx_typejenis1.getSelectedItem();
    if (tipe != null) {
        String[] merkList = tipeMap.get(tipe);
        cbx_merk.setModel(new DefaultComboBoxModel<>(merkList));
    }
    }//GEN-LAST:event_cbx_typejenis1ActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_exitActionPerformed
private void clearInputs() {
    tbx_date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    tbx_code.setText("");
    tbx_name.setText("");
    cbx_type.setSelectedIndex(0);
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
