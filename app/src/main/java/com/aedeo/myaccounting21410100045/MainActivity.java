package com.aedeo.myaccounting21410100045;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    //    =====================================
//    object transaksi
    Transaksi transaksi = new Transaksi();

    //    arraylist dari object transaksi
    ArrayList<Transaksi> listTransaksi = new ArrayList<Transaksi>();
    //    =======================================
    String valueIDTransaksi, valueKeterangan;
    Integer valueDebit, valueKredit, valueLogo;
    Date valueTanggal;
    private Button pilihTanggal, btnLaporanMain, btnTambah;
    private EditText tanggal, inputId_transaksi, inputViewTanggal_transaksi, inputKet_transaksi, inputDebit_transaksi, inputKredit_transaksi;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private View myCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Deklarasi Element
//        View
        myCoordinatorLayout = (View) findViewById(R.id.myCoordinatorLayout);
//        EditText
        tanggal = (EditText) findViewById(R.id.inputViewTanggal_transaksi);
        inputId_transaksi = (EditText) findViewById(R.id.inputId_transaksi);
        inputViewTanggal_transaksi = (EditText) findViewById(R.id.inputViewTanggal_transaksi);
        inputKet_transaksi = (EditText) findViewById(R.id.inputKet_transaksi);
        inputDebit_transaksi = (EditText) findViewById(R.id.inputDebit_transaksi);
        inputKredit_transaksi = (EditText) findViewById(R.id.inputKredit_transaksi);
//        Button
        pilihTanggal = (Button) findViewById(R.id.btnInputTanggal);
        btnLaporanMain = (Button) findViewById(R.id.btnLaporan);
        btnTambah = (Button) findViewById(R.id.btnTambah);

//        ketika klik button pilih tanggal
        showDateDialog();

//        ketika klik button laporan
        pindahKeLaporan();

//        ketika klik button tambah
        tambahTransaksi();
    }

    private void tambahTransaksi() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TES!!!", "onClick: TES");
                getValueTransaksi();
                if (valueDebit == valueKredit) {
//                    ketika debit dan kredit sama besar
                    String text = "Debit dan Kredit tidak dapat sama besar";
                    Snackbar warning = Snackbar.make(myCoordinatorLayout, text, Snackbar.LENGTH_SHORT);
                    warning.show();
                } else {
//                ===============================
//                masukkan transaksi ke dalam object
                    transaksi = new Transaksi(valueIDTransaksi, valueKeterangan, valueTanggal, valueDebit, valueKredit, valueLogo);

//                tambahkan object kedalam arraylist
                    listTransaksi.add(transaksi);
//                for (int i = 0; i < listTransaksi.size(); i++) {
//                    Log.d("TES!!!!!!", "onClick: " + listTransaksi.get(i).getIdTransaksi());
//                    Log.d("TES!!!!!!!", "onClick: " + listTransaksi.get(i).getTanggalTransaksi());
//                    Log.d("TES!!!!!!!", "onClick: " + listTransaksi.get(i).getUraian());
//                    Log.d("TES!!!!!!!", "onClick: " + listTransaksi.get(i).getDebit());
//                    Log.d("TES!!!!!!!", "onClick: " + listTransaksi.get(i).getKredit());
//                }
//                ================================
                }
            }
        });
    }

    private void getValueTransaksi() {
//        fungsi untuk mengambil value dari inputan user
        valueIDTransaksi = inputId_transaksi.getText().toString();
        try {
            valueTanggal = sdf.parse(inputViewTanggal_transaksi.getText().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        valueKeterangan = String.valueOf(inputKet_transaksi.getText());
        valueDebit = Integer.valueOf(String.valueOf(inputDebit_transaksi.getText()));
        valueKredit = Integer.valueOf(String.valueOf(inputKredit_transaksi.getText()));
        if (valueDebit > valueKredit) {
            valueLogo = R.drawable.dLogo;
            valueKredit = 0;
        } else if (valueKredit > valueDebit) {
            valueLogo = R.drawable.kLogo;
            valueDebit = 0;
        }
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