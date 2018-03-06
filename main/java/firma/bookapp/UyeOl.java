package firma.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

public class UyeOl extends AppCompatActivity {

    EditText mail, sifre, cevap;
    Spinner soru;
    Button kaydet;
    private vtIslem vtIslem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vtIslem = new vtIslem(this, "uyeler", null, 1);
        tanımlar();

    }

    private void tanımlar() {
        mail = (EditText) findViewById(R.id.eposta);
        sifre = (EditText) findViewById(R.id.sifre);
        soru = (Spinner) findViewById(R.id.soru);
        cevap = (EditText) findViewById(R.id.cevap);
        kaydet = (Button) findViewById(R.id.kaydet);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (vtIslem.uyeKontrol(mail.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "This e-mail address is available.", Toast.LENGTH_SHORT).show();
                        Intent gidis = new Intent(getApplicationContext(), Uye_Giris.class);
                        startActivity(gidis);
                    } else {
                        vtIslem.üyekayit(mail.getText().toString(), sifre.getText().toString(), soru.getSelectedItem().toString(), cevap.getText().toString());
                        Toast.makeText(getApplicationContext(), "Successful.", Toast.LENGTH_SHORT).show();
                        Intent gidis = new Intent(getApplicationContext(), Uye_Giris.class);
                        startActivity(gidis);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}