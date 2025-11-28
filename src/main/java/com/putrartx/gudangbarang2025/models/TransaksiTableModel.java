/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.putrartx.gudangbarang2025.models;

/**
 *
 * @author Administrator
 */
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TransaksiTableModel extends AbstractTableModel {
   private final String[] columnNames = {
        "Kode Transaksi", "Tanggal", "Tipe", 
        "Nama Barang", "Jumlah", "Harga Satuan", "Subtotal"
    };

    private final List<TransaksiModel> data = new ArrayList<>();

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        TransaksiModel t = data.get(row);
        return switch (col) {
            case 0 -> t.kode_transaksi;
            case 1 -> t.tanggal_transaksi;
            case 2 -> t.tipe;
            case 3 -> t.nama_barang;
            case 4 -> t.jumlah;
            case 5 -> t.harga_satuan;
            case 6 -> t.subtotal_harga;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Add a new row
    public void addTransaksi(TransaksiModel t) {
        data.add(t);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public TransaksiModel getTransaksi(int row) {
        return data.get(row);
    }
}
