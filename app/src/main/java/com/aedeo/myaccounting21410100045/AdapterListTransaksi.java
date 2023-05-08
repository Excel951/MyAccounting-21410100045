package com.aedeo.myaccounting21410100045;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterListTransaksi extends BaseAdapter {
    Context context;

    ArrayList<String> idTransaksi = new ArrayList<>();
    ArrayList<String> uraian = new ArrayList<>();
    ArrayList<Date> tanggalTransaksi = new ArrayList<>();
    ArrayList<Integer> debit = new ArrayList<>();
    ArrayList<Integer> kredit = new ArrayList<>();
    ArrayList<Integer> logo = new ArrayList<>();

    LayoutInflater layoutInflater;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public AdapterListTransaksi() {
    }

    public AdapterListTransaksi(Context context, ArrayList<Transaksi> listTransaksi) {
        for (int i = 0; i < listTransaksi.size(); i++) {
            idTransaksi.add(listTransaksi.get(i).getIdTransaksi());
            uraian.add(listTransaksi.get(i).getUraian());
            tanggalTransaksi.add(listTransaksi.get(i).getTanggalTransaksi());
            debit.add(listTransaksi.get(i).getDebit());
            kredit.add(listTransaksi.get(i).getKredit());
            logo.add(listTransaksi.get(i).getLogo());
        }
        layoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return idTransaksi.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.layout_item_transaksi, null);
        TextView tglTransaksi = (TextView) view.findViewById(R.id.listItem_tanggal);
        TextView ketTransaksi = (TextView) view.findViewById(R.id.listItem_keterangan);
        TextView hrgTransaksi = (TextView) view.findViewById(R.id.listItem_harga);
        ImageView logoTransaksi = (ImageView) view.findViewById(R.id.listImg_Transaksi);
//                String curDate=sdf.format(tanggalTransaksi.get(i).toString());
//        tglTransaksi.setText();
        ketTransaksi.setText(uraian.get(i).toString());
        logoTransaksi.setImageResource(logo.get(i));
//        if (debit.get(i) != 0) {
//            hrgTransaksi.setText(kredit.get(i));
//        } else {
//            hrgTransaksi.setText(debit.get(i));
//        }
        return view;
    }
}
