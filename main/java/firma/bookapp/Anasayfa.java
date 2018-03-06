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
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Anasayfa extends Fragment {

    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Kitap> kitaplar = new ArrayList<>();
    DBAdapter db;
    ViewFlipper viewFlipper;

    public static Anasayfa newInstance() {
        Anasayfa fragment = new Anasayfa();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.anasayfa,container,false);

        viewFlipper = (ViewFlipper) v.findViewById(R.id.viewflipper);
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(2000);
        db = new DBAdapter(getContext(), "kitaplar", null, 1);
        rv = (RecyclerView) v.findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter= new MyAdapter(getContext(), kitaplar);

        retrieve();

        return v;
    }

    private void retrieve()
    {
        db = new DBAdapter(getContext(), "kitaplar", null, 1);
        db.openDB();
        kitaplar.clear();
        Cursor c = db.getAllNot2();
        while(c.moveToNext())
        {
            int id = c.getInt(0);
            String ad = c.getString(1);
            String kategori = c.getString(2);
            String sayfasayisi = c.getString(3);
            String deger = c.getString(4);

            Kitap kitaps = new Kitap(ad,kategori,id,sayfasayisi,deger);

            kitaplar.add(kitaps);

        }

        if(!(kitaplar.size()<1))
        {
            rv.setAdapter(adapter);
        }
    }
}
