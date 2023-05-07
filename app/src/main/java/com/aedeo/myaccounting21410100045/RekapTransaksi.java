package com.aedeo.myaccounting21410100045;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RekapTransaksi extends AppCompatActivity {

    private Button btnLaporanRekap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_transaksi);

//        deklarasi element
        btnLaporanRekap = (Button) findViewById(R.id.btnLaporanRekap);

//        pindah ke main
        pindahKeMain();
    }

    private void pindahKeMain() {
        btnLaporanRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deklarasi intent
                Intent pindahMain = new Intent(RekapTransaksi.this, MainActivity.class);

//                jalankan activity main
                startActivity(pindahMain);
                finish();
            }
        });
    }
}