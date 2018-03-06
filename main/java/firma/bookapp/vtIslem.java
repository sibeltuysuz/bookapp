package firma.bookapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class vtIslem extends SQLiteOpenHelper {

    public String TABLE_NAME = "uyeler";

    public vtIslem(Context context, String name, SQLiteDatabase.CursorFactory factory,
                   int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE IF NOT EXISTS uyeler (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , eposta VARCHAR,parola VARCHAR,soru VARCHAR,cevap VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST uyeler;");
    }

    public void üyekayit(String eposta, String parola, String soru, String cevap ) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO uyeler (eposta,parola,soru,cevap) VALUES ('" + eposta + "','" + parola + "','" + soru + "','" + cevap + "')");
    }

    public boolean uyeKontrol(String eposta) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE eposta=?", new String[]{eposta});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean Login(String eposta, String parola) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE eposta=? AND parola=?", new String[]{eposta, parola});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean sifreunuttum(String eposta,String soru,String cevap) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE eposta=? AND soru=? AND cevap=?", new String[]{eposta,soru,cevap});

        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public void sifre_update(String sifre,String eposta) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE uyeler SET parola='"+sifre+"' WHERE eposta= '"+eposta+"'");


    }

}
