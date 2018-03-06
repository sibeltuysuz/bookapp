package firma.bookapp;

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
import android.widget.ImageView;

import java.util.ArrayList;

public class Kategori extends Fragment implements View.OnClickListener {

    ImageView arts,science,tech,psychlogy,history;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Kitap> kitaplar = new ArrayList<>();
    DBAdapter db;
    public static String kateg;

    public static Kategori newInstance() {
        Kategori fragment = new Kategori();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kategori,container,false);

        db = new DBAdapter(getContext(), "kitaplar", null, 1);
        rv = (RecyclerView) v.findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter= new MyAdapter(getContext(), kitaplar);

        arts = (ImageView) v.findViewById(R.id.Arts);
        science = (ImageView) v.findViewById(R.id.Science);
        tech = (ImageView) v.findViewById(R.id.Technology);
        psychlogy = (ImageView) v.findViewById(R.id.Psychlogy);
        history = (ImageView) v.findViewById(R.id.History);
        arts.setOnClickListener(this);
        science.setOnClickListener(this);
        tech.setOnClickListener(this);
        psychlogy.setOnClickListener(this);
        history.setOnClickListener(this);

        return v;
    }

    private void retrieve() {
        db = new DBAdapter(getContext(), "kitaplar", null, 1);
        db.openDB();
        kitaplar.clear();
        Cursor c = db.getAllNot3();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String ad = c.getString(1);
            String kategori = c.getString(2);
            String sayfasayisi = c.getString(3);
            String deger = c.getString(4);

            Kitap kitaps = new Kitap(ad, kategori, id, sayfasayisi, deger);

            kitaplar.add(kitaps);

        }

        if (!(kitaplar.size() < 1)) {
            rv.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Arts:
                kateg = "Arts";
                retrieve();
                break;
            case R.id.Science:
                kateg = "Science";
                retrieve();
                break;
            case R.id.Technology:
                kateg = "Technology";
                retrieve();
                break;
            case R.id.Psychlogy:
                kateg = "Psychlogy";
                retrieve();
                break;
            case R.id.History:
                kateg = "History";
                retrieve();
                break;

        }
    }
}
