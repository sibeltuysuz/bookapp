package firma.bookapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class Sepet extends Fragment {

    RecyclerView rv;
    SepetMyAdapter adapter;
    ArrayList<SepetKitap> kitaplar = new ArrayList<>();
    DBAdapterSepet db;
    Button sil,ok;
    String ad,kategori,sayfasayisi,deger,adres;

    public static Sepet newInstance() {
        Sepet fragment = new Sepet();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sepet,container,false);

        db = new DBAdapterSepet(getContext(), "sepet", null, 1);
        rv = (RecyclerView) v.findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());
        sil = (Button) v.findViewById(R.id.sil);
        ok = (Button) v.findViewById(R.id.ok);

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete();
                Toast.makeText(getContext(), "Deletion is complete!", Toast.LENGTH_SHORT).show();
                Intent yonlen = new Intent(getContext(),MainActivity.class);
                startActivity(yonlen);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ad = "deneme";
                kategori = "deneme";
                sayfasayisi = "deneme";
                deger = "deneme";
                adres = "deneme";

                try {
                    Intent popupp = new Intent(getContext(),popup.class);
                    startActivity(popupp);
                    //Toast.makeText(getContext(), "Shopping complete!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "Hata!", Toast.LENGTH_SHORT).show();
                }

        }
        });

        adapter= new SepetMyAdapter(getContext(), kitaplar);

        retrieve();

        return v;
    }

    private void retrieve() {
        db = new DBAdapterSepet(getContext(), "sepet", null, 1);
        db.openDB();
        kitaplar.clear();
        Cursor c = db.getAllNotSepet();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String ad = c.getString(1);
            String kategori = c.getString(2);
            String sayfasayisi = c.getString(3);
            String deger = c.getString(4);

            SepetKitap kitaps = new SepetKitap(ad, kategori, id, sayfasayisi, deger);

            kitaplar.add(kitaps);

        }

        if (!(kitaplar.size() < 1)) {
            rv.setAdapter(adapter);
        }
    }

}
