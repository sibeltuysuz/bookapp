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

import java.sql.SQLException;

public class sifre_kontrol extends AppCompatActivity {

    Button sifregonder;
    private vtIslem vtIslem;
    Spinner soru;
    EditText cevabım, mail;
    public static String mail_tasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_kontrol);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sifregonder = (Button) findViewById(R.id.button);

        soru = (Spinner) findViewById(R.id.spinner);
        cevabım = (EditText) findViewById(R.id.editText4);
        mail = (EditText) findViewById(R.id.editText9);

        vtIslem = new vtIslem(this, "uyeler", null, 1);

        sifregonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String eposta = mail.getText().toString();
                    String spinner = soru.getSelectedItem().toString();
                    String cevap = cevabım.getText().toString();


                    if (vtIslem.sifreunuttum(eposta, spinner, cevap)) {
                        mail_tasi = eposta;
                        Intent sifre_degis = new Intent(getApplicationContext(), sifre_degistir.class);
                        startActivity(sifre_degis);
                        Toast.makeText(sifre_kontrol.this, "Your email is verified.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(sifre_kontrol.this, "Error!", Toast.LENGTH_SHORT).show();

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}