package com.aedeo.myaccounting21410100045;

import java.util.ArrayList;
import java.util.Date;

public class Transaksi {
    String idTransaksi, uraian;
    Date tanggalTransaksi;
    Integer debit, kredit;

    public Transaksi() {
    }

    public Transaksi(String idTransaksi, String uraian, Date tanggalTransaksi, Integer debit, Integer kredit) {
        this.idTransaksi = idTransaksi;
        this.uraian = uraian;
        this.tanggalTransaksi = tanggalTransaksi;
        this.debit = debit;
        this.kredit = kredit;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getUraian() {
        return uraian;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public Integer getDebit() {
        return debit;
    }

    public Integer getKredit() {
        return kredit;
    }
}
