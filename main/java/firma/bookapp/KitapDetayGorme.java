package firma.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KitapDetayGorme extends AppCompatActivity  {

    TextView ad, kategori,sayfasayisi,deger;
    DBAdapterSepet db;
    ImageView shop;
    int id;
    ArrayList<SepetKitap> kitaps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_detay_gorme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBAdapterSepet(this, "sepet", null, 1);

        Intent i = getIntent();

        final String adi = i.getExtras().getString("baslık");
        final String kategorsi = i.getExtras().getString("acıklama");
        final String sayfas = i.getExtras().getString("sayfa");
        final String degers = i.getExtras().getString("deger");
        id = i.getExtras().getInt("ID");

        ad = (TextView) findViewById(R.id.adi);
        kategori = (TextView) findViewById(R.id.adresad);
        sayfasayisi = (TextView) findViewById(R.id.sayfasayisi);
        deger = (TextView) findViewById(R.id.deger);
        shop = (ImageView) findViewById(R.id.shop);

        ad.setText(adi);
        kategori.setText(kategorsi);
        sayfasayisi.setText(sayfas);
        deger.setText(degers);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addSepet(ad.getText().toString(), kategori.getText().toString(), sayfasayisi.getText().toString(), deger.getText().toString());
                Toast.makeText(getApplicationContext(), "Added to shopping cart." + ad.getText().toString(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}

