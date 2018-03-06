package firma.bookapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {

    Context c;
    SQLiteDatabase db;

    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBAdapter openDB() {
        try
        {
            db = this.getWritableDatabase();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {
            db.execSQL("CREATE TABLE IF NOT EXISTS kitaplar(id INTEGER PRIMARY KEY AUTOINCREMENT,ad TEXT NOT NULL,kategori TEXT NOT NULL, sayfasayisi VARCHAR,deger TEXT);");
        }
        catch(android.database.SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kitaplar");
        onCreate(db);
    }

    public long add(String ad,String kategori,String sayfasayisi,String deger)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO kitaplar (ad,kategori,sayfasayisi,deger) VALUES ('" + ad + "','" + kategori + "','" + sayfasayisi + "','" + deger + "')");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public Cursor getAllSiparis() {
        SQLiteDatabase db = this.getReadableDatabase();

            return db.rawQuery("SELECT * FROM siparisler ", null);

        }

    public Cursor getAllNot2() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM kitaplar ORDER BY RANDOM() LIMIT 3", null);

    }

    public Cursor getAllNot3() {

        String kategori = Kategori.kateg;

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM kitaplar  WHERE kategori = '" + kategori + "'", null);

    }

}
