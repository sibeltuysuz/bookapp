package firma.bookapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SiparisMyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Siparis> siparises;
    CardView cardView;
    TextView baslik, aciklama, sayfa_sayisi;

    public SiparisMyAdapter(Context ctx, ArrayList<Siparis> siparises) {
        this.c = ctx;
        this.siparises = siparises;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model2, null);
        v.setMinimumWidth(parent.getMeasuredWidth());

        cardView = (CardView) v.findViewById(R.id.card_view1);
        baslik = (TextView) v.findViewById(R.id.kullaniciad);
        aciklama = (TextView) v.findViewById(R.id.adresad);
        sayfa_sayisi = (TextView) v.findViewById(R.id.adres);

        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder,final int position) {

        holder.posTxt.setText(siparises.get(position).getAdresad());
        holder.nameTxt.setText(siparises.get(position).getKullaniciad());
        holder.tarih.setText(siparises.get(position).getAdres());

    }

    @Override
    public int getItemCount() {
        return siparises.size();
    }
}

