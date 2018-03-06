package firma.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class Uye_Giris extends AppCompatActivity {

    EditText mail, parola;
    TextView sifre_unuttum, uye_ol;
    Button giris, uye_olma;
    public vtIslem islem;
    public String email, sifre;
    public static String epostam;
    public static String sifree;
    public static String now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye__giris);
        tanımlar();
    }

    public String durum(String pozisyon) {
        now = pozisyon;
        return (now);
    }

    private void tanımlar() {
        mail = (EditText) findViewById(R.id.mail);
        sifre_unuttum = (TextView) findViewById(R.id.sifre_unut);
        uye_ol = (TextView) findViewById(R.id.üye_ol);
        parola = (EditText) findViewById(R.id.sifre);
        giris = (Button) findViewById(R.id.giris);
        islem = new vtIslem(this, "uyeler", null, 1);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mail.getText().toString();
                sifre = parola.getText().toString();
                try {
                    String aktar;
                    aktar = "";
                    aktar = epostam;
                    if (aktar != null) {
                        aktar = "giris_var";
                    }

                    if (Uye_Giris.now == "giris_var") {
                        Toast.makeText(Uye_Giris.this, "You are already logged in!", Toast.LENGTH_SHORT).show();

                    } else {
                        if (email.length() > 0 && sifre.length() > 0) {

                            if (islem.Login(email, sifre)) {
                                Toast.makeText(Uye_Giris.this, "Your login is confirmed, dear " + email, Toast.LENGTH_SHORT).show();

                                epostam = email;
                                sifree = sifre;
                                Intent yonlen = new Intent(getApplicationContext(), MainActivity.class);
                                String giris = "giris_var";
                                durum(giris);
                                startActivity(yonlen);

                            } else
                                Toast.makeText(Uye_Giris.this, "You have logged in incorrectly. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        sifre_unuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent niyet = new Intent(getApplicationContext(), sifre_kontrol.class);
                startActivity(niyet);
            }
        });

        uye_ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent niyet = new Intent(getApplicationContext(), UyeOl.class);
                startActivity(niyet);
            }
        });
    }
}