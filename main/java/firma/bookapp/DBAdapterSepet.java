package firma.bookapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterSepet extends SQLiteOpenHelper {

    Context c;
    SQLiteDatabase db;
    String TABLE_NAME = "sepet";

    public DBAdapterSepet(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBAdapterSepet openDB() {
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
            db.execSQL("CREATE TABLE IF NOT EXISTS sepet(id INTEGER PRIMARY KEY AUTOINCREMENT,ad TEXT NOT NULL,kategori TEXT NOT NULL, sayfasayisi VARCHAR,deger TEXT);");
        }
        catch(android.database.SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sepet");
        onCreate(db);
    }

    public long addSepet(String ad,String kategori,String sayfasayisi,String deger)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("INSERT INTO sepet (ad,kategori,sayfasayisi,deger) VALUES ('" + ad + "','" + kategori + "','" + sayfasayisi + "','" + deger + "')");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public Cursor getAllNotSepet() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM sepet ", null);

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
