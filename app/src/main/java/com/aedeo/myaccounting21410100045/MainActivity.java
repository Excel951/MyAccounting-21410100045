package com.aedeo.myaccounting21410100045;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    private Button pilihTanggal, btnLaporanMain;
    private EditText tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Deklarasi Element
        pilihTanggal = (Button) findViewById(R.id.btnInputTanggal);
        tanggal = (EditText) findViewById(R.id.inputViewTanggal_transaksi);
        btnLaporanMain = (Button) findViewById(R.id.btnLaporan);

//        ketika klik button pilih tanggal
        showDateDialog();

//        ketika klik button laporan
        pindahKeLaporan();
    }

    private void pindahKeLaporan() {
        btnLaporanMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//        declare pindah rekap
                Intent pindahRekap = new Intent(MainActivity.this, RekapTransaksi.class);

//        kirim data
//pindahRekap.putExtra()

//        mulai activity
                startActivity(pindahRekap);
                finish();
            }
        });
    }

    private void showDateDialog() {
        pilihTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deklarasi kalender dan ambil tahun, bulan, dan tanggal dari dialog
                final Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(Calendar.YEAR);
                int currentMonth = cal.get(Calendar.MONTH);
                int currentDate = cal.get(Calendar.DAY_OF_MONTH);

//                date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tanggal.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, currentYear, currentMonth, currentDate);
                datePickerDialog.show();
            }
        });
    }
}