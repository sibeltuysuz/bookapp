package firma.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Kitap_Ekle extends AppCompatActivity {

    EditText ad,sayfasayisi,deger;
    Button kaydet;
    Spinner kategori;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap__ekle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBAdapter(this, "kitaplar", null, 1);

        tanımlar();

    }

    private void tanımlar()
    {
        ad = (EditText) findViewById(R.id.adi);
        sayfasayisi = (EditText) findViewById(R.id.sayfa);
        deger = (EditText) findViewById(R.id.deger);
        kaydet = (Button) findViewById(R.id.kaydet);
        kategori = (Spinner) findViewById(R.id.adresad);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.add(ad.getText().toString(), kategori.getSelectedItem().toString(), sayfasayisi.getText().toString(), deger.getText().toString());
                Toast.makeText(getApplicationContext(), "Not eklendi.", Toast.LENGTH_LONG).show();
                Intent yönlen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(yönlen);
            }
        });
    }

}
