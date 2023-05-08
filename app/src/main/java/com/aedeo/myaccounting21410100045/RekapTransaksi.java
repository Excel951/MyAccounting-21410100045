package com.aedeo.myaccounting21410100045;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RekapTransaksi extends AppCompatActivity {


    //    Bundle bundle = getIntent().getExtras();
    ArrayList<Transaksi> listTransaksiClone = new ArrayList<>();
    Integer totalKredit = 0, totalDebit = 0, totalSeluruh = 0;
    private Button btnLaporanRekap;
    private TextView rekapPemasukan, rekapPengeluaran, rekapSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_transaksi);

//        deklarasi element
//        intent
        Intent intent = getIntent();
//        button
        btnLaporanRekap = (Button) findViewById(R.id.btnLaporanRekap);
//        textview
        rekapPemasukan = (TextView) findViewById(R.id.rekapPemasukan);
        rekapPengeluaran = (TextView) findViewById(R.id.rekapPengeluaran);
        rekapSaldo = (TextView) findViewById(R.id.rekapSaldo);

        ArrayList<Transaksi> listTransaksi = (ArrayList<Transaksi>) intent.getSerializableExtra("list");
        listTransaksiClone = (ArrayList<Transaksi>) listTransaksi.clone();
//        pindah ke main
        pindahKeMain();

//        hitung total dari rekap
        hitungTotalTrx();

//        set value rekap
        setRekap();
    }

    private void setRekap() {
        rekapPemasukan.setText(totalDebit.toString());
        rekapPengeluaran.setText(totalKredit.toString());
        rekapSaldo.setText(totalSeluruh.toString());
    }

    private void hitungTotalTrx() {
        for (int i = 0; i < listTransaksiClone.size(); i++) {
            totalKredit += listTransaksiClone.get(i).getKredit();
            Log.d("TAG", "hitungTotalTrx: " + totalKredit);
            totalDebit += listTransaksiClone.get(i).getDebit();
            Log.d("TAG", "hitungTotalTrx: " + totalDebit);
            totalSeluruh = totalDebit - totalKredit;
            Log.d("TAG", "hitungTotalTrx: " + totalKredit);
        }
    }

    private void pindahKeMain() {
        btnLaporanRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deklarasi intent
                Intent pindahMain = new Intent(RekapTransaksi.this, MainActivity.class);

                pindahMain.putExtra("list", listTransaksiClone);

//                jalankan activity main
                startActivity(pindahMain);
            }
        });
    }
}