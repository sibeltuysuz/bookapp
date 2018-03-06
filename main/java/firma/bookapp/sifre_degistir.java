package firma.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sifre_degistir extends AppCompatActivity {

    EditText sifre1, sifre2;
    Button sifredegis;
    private vtIslem vtIslem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_degistir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vtIslem = new vtIslem(this, "uyeler", null, 1);

        sifre1 = (EditText) findViewById(R.id.editText1);
        sifre2 = (EditText) findViewById(R.id.editText2);
        sifredegis = (Button) findViewById(R.id.degis);

        sifredegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String mail = sifre_kontrol.mail_tasi;
                    String ilksifre = sifre1.getText().toString();
                    if (TextUtils.equals(sifre1.getText(), sifre2.getText())) {
                        vtIslem.sifre_update(ilksifre, mail);
                        Toast.makeText(sifre_degistir.this, "New password :" + ilksifre, Toast.LENGTH_SHORT).show();
                        Intent yönlen = new Intent(getApplicationContext(), Uye_Giris.class);
                        startActivity(yönlen);
                    } else {
                        Toast.makeText(sifre_degistir.this, "The password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}