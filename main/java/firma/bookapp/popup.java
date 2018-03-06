package firma.bookapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class popup extends AppCompatActivity {

    EditText adresadi,siparisadres;
    Button tamam;
    DBAdapterSiparis db;

    public static String adresadideger;
    public static String siparisadresdeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        adresadi = (EditText) findViewById(R.id.siparisad);
        siparisadres = (EditText) findViewById(R.id.siparisadres);
        tamam = (Button) findViewById(R.id.tamam);

        db = new DBAdapterSiparis(this, "siparisler", null, 1);

        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .3));

        tamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adresadideger = adresadi.getText().toString();
                siparisadresdeger = siparisadres.getText().toString();
                String kullaniciad = Uye_Giris.epostam;
                db.addSiparis(kullaniciad,adresadideger,siparisadresdeger);
                Toast.makeText(popup.this, "Order complete!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
