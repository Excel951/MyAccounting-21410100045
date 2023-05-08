package com.aedeo.myaccounting21410100045;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    String valueIDTransaksi = "", valueKeterangan = "";
    Integer valueDebit = 0, valueKredit = 0, valueLogo = 0;
    Date valueTanggal = null;

    ListView listViewTransaksi;
    private Button pilihTanggal, btnLaporanMain, btnTambah;
    private EditText tanggal, inputId_transaksi, inputViewTanggal_transaksi, inputKet_transaksi, inputDebit_transaksi, inputKredit_transaksi;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    private View myCoordinatorLayout;

    private Context notif;

    private AdapterListTransaksi adapterListTransaksi = new AdapterListTransaksi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Deklarasi Element
//        List View
        listViewTransaksi = (ListView) findViewById(R.id.listView_Transaksi);
//        Context
        notif = getApplicationContext();
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

//        jika kembali dari rekap
//        if (getIntent() != null) {
//            Intent newIntent = getIntent();
//            ArrayList<Transaksi> listTransaksiClone = (ArrayList<Transaksi>) newIntent.getSerializableExtra("list");
//            listTransaksi = (ArrayList<Transaksi>) listTransaksiClone.clone();
//
////          masukkan ke dalam adapter
//            adapterListTransaksi = new AdapterListTransaksi(getApplicationContext(), listTransaksi);
////          implementasi list view
//            listViewTransaksi.setAdapter(adapterListTransaksi);
//        }
    }

    private void clearForm() {
        inputId_transaksi.setText("");
        inputViewTanggal_transaksi.setText("");
        inputKet_transaksi.setText("");
        inputDebit_transaksi.setText("");
        inputKredit_transaksi.setText("");

        valueIDTransaksi = "";
        valueKeterangan = "";
        valueDebit = 0;
        valueKredit = 0;
        valueLogo = 0;
        valueTanggal = null;
    }

    private void tambahTransaksi() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TES!!!", "onClick: TES");
                getValueTransaksi();
                if (valueIDTransaksi == "" || valueKeterangan == "" || valueTanggal == null) {
//                    ketika ada kolom yang masih kosong
                    String text = "Masih ada kolom yang kosong";
                    Toast warning = Toast.makeText(notif, text, Toast.LENGTH_SHORT);
                    warning.show();
                } else if (valueDebit == valueKredit) {
//                    ketika debit dan kredit sama besar
                    String text = "Debit dan Kredit tidak dapat sama besar";
                    Toast warning = Toast.makeText(notif, text, Toast.LENGTH_SHORT);
                    warning.show();
                } else {
//                ===============================
//                masukkan transaksi ke dalam object
                    transaksi = new Transaksi(valueIDTransaksi, valueKeterangan, valueTanggal, valueDebit, valueKredit, valueLogo);

//                tambahkan object kedalam arraylist
                    listTransaksi.add(transaksi);

//                    masukkan ke dalam adapter
                    adapterListTransaksi = new AdapterListTransaksi(getApplicationContext(), listTransaksi);
//                    implementasi list view
                    listViewTransaksi.setAdapter(adapterListTransaksi);

                    for (int i = 0; i < listTransaksi.size(); i++) {
                        Log.d("TAG", "onClick: " + listTransaksi.get(i).getIdTransaksi());
                    }

//                    bersihkan form
                    clearForm();
//                ================================
                }
            }
        });
    }

    private void getValueTransaksi() {
//        fungsi untuk mengambil value dari inputan user
//        ambil id transaksi
        if (!inputId_transaksi.getText().toString().isEmpty()) {
            valueIDTransaksi = inputId_transaksi.getText().toString();
        }
//        ambil tanggal transaksi
        try {
            if (!inputViewTanggal_transaksi.getText().toString().isEmpty()) {
                valueTanggal = sdf.parse(inputViewTanggal_transaksi.getText().toString());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        ambil keterangan
        if (!inputKet_transaksi.getText().toString().isEmpty()) {
            valueKeterangan = String.valueOf(inputKet_transaksi.getText());
        }
//        ambil debit dan kredit
        if (!inputKredit_transaksi.getText().toString().isEmpty()) {
            valueKredit = Integer.valueOf(inputKredit_transaksi.getText().toString());
        }
        if (!inputDebit_transaksi.getText().toString().isEmpty()) {
            valueDebit = Integer.valueOf(inputDebit_transaksi.getText().toString());
        }
//        menentukan logo
        if (valueDebit > valueKredit) {
            valueLogo = R.drawable.dlogo;
            valueKredit = 0;
        } else if (valueKredit > valueDebit) {
            valueLogo = R.drawable.klogo;
            valueDebit = 0;
        }
    }

    private void pindahKeLaporan() {
        btnLaporanMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//        declare pindah rekap
                Intent pindahRekap = new Intent(MainActivity.this, RekapTransaksi.class);
//                Intent pindahRekap = new Intent(, RekapTransaksi.class);

//        kirim data
//                pindahRekap.putParcelableArrayListExtra("ListTransaksi", );
                pindahRekap.putExtra("list", listTransaksi);

//        mulai activity
                startActivity(pindahRekap);
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