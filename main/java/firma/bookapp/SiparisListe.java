package firma.bookapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SiparisListe extends AppCompatActivity {

    RecyclerView rv;
    SiparisMyAdapter adapter;
    ArrayList<Siparis> siparises = new ArrayList<>();
    DBAdapterSiparis db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_liste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBAdapterSiparis(getApplicationContext(), "siparisler", null, 1);
        rv = (RecyclerView) findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter= new SiparisMyAdapter(getApplicationContext(), siparises);

        retrieve();

    }

    private void retrieve() {
        db = new DBAdapterSiparis(getApplicationContext(), "siparisler", null, 1);
        db.openDB();
        siparises.clear();
        Cursor c = db.getAllNotSiparis();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String kullaniciad = c.getString(1);
            String adresad = c.getString(2);
            String adres = c.getString(3);

            Siparis siparis = new Siparis(kullaniciad,adresad,id,adres);

            siparises.add(siparis);

        }

        if (!(siparises.size() < 1)) {
            rv.setAdapter(adapter);
        }
    }

}
