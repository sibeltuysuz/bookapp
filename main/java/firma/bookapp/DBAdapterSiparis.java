package firma.bookapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterSiparis extends SQLiteOpenHelper {

    Context c;
    SQLiteDatabase db;
    String TABLE_NAME = "siparisler";

    public DBAdapterSiparis(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBAdapterSiparis openDB() {
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
            db.execSQL("CREATE TABLE IF NOT EXISTS siparisler(id INTEGER PRIMARY KEY AUTOINCREMENT,kullaniciad,adresad TEXT NOT NULL,adres TEXT NOT NULL);");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS siparisler");
        onCreate(db);
    }

    public long addSiparis(String kullaniciadi,String adresadi,String adres)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO siparisler (kullaniciad,adresad,adres) VALUES ('" + kullaniciadi + "','" + adresadi + "','" + adres + "')");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public Cursor getAllNotSiparis() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM siparisler ", null);

    }

    public int delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            db.execSQL("delete from "+ TABLE_NAME);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

}
