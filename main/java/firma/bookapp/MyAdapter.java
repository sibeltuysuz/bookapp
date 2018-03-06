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

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Kitap> kitaps;
    CardView cardView;
    TextView baslik, aciklama, sayfa_sayisi;
    public static int pos;

    public MyAdapter(Context ctx, ArrayList<Kitap> kitaps) {
        this.c = ctx;
        this.kitaps = kitaps;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);
        v.setMinimumWidth(parent.getMeasuredWidth());

        cardView = (CardView) v.findViewById(R.id.card_view1);
        baslik = (TextView) v.findViewById(R.id.nameTxt);
        aciklama = (TextView) v.findViewById(R.id.adresad);
        sayfa_sayisi = (TextView) v.findViewById(R.id.tarih);

        MyHolder holder = new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder,final int position) {

        holder.posTxt.setText(kitaps.get(position).getKategori());
        holder.nameTxt.setText(kitaps.get(position).getAd());
        holder.tarih.setText(kitaps.get(position).getDeger());

        holder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onItemClick(View v,int pos) {
                Intent i = new Intent(c, KitapDetayGorme.class);
                i.putExtra("baslık", kitaps.get(pos).getAd());
                i.putExtra("acıklama", kitaps.get(pos).getKategori());
                i.putExtra("sayfa",kitaps.get(pos).getSayfa_sayisi());
                i.putExtra("deger",kitaps.get(pos).getDeger());
                i.putExtra("ID", kitaps.get(pos).getId());
                c.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kitaps.size();
    }
}
