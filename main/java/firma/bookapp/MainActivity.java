package firma.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    FloatingActionButton floatingActionButton,floatingActionButtonn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButtonn = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.anasayfa:
                                selectedFragment = Anasayfa.newInstance();
                                break;
                            case R.id.alisveris:
                                selectedFragment = Sepet.newInstance();
                                break;
                            case R.id.exit:
                                String oturum_kapat = "oturum_kapatildi";
                                Uye_Giris.now = oturum_kapat;
                                Toast.makeText(MainActivity.this, "You logged out", Toast.LENGTH_SHORT).show();
                                selectedFragment = Anasayfa.newInstance();
                                floatingActionButton.setVisibility(View.INVISIBLE);
                                floatingActionButtonn.setVisibility(View.INVISIBLE);
                                break;
                            case R.id.adresad:
                                selectedFragment = Kategori.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, Anasayfa.newInstance());
        transaction.commit();

        if (Uye_Giris.epostam.equals("sibel") && Uye_Giris.sifree.equals("123abc") )
        {
            floatingActionButton.setVisibility(View.VISIBLE);
            floatingActionButtonn.setVisibility(View.VISIBLE);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent yonlen = new Intent(getApplicationContext(),SiparisListe.class);
                    startActivity(yonlen);
                }
            });
            floatingActionButtonn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent yonlen = new Intent(getApplicationContext(),Kitap_Ekle.class);
                    startActivity(yonlen);
                }
            });
        }

        else
        {
            floatingActionButton.setVisibility(View.INVISIBLE);
            floatingActionButtonn.setVisibility(View.INVISIBLE);
        }

    }

}
