package com.example.applocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.dbHiro";
    public static final String HIRO_TABLE_NAME = "herois";
    public static final String HIRO_COLUMN_ID = "idHiro";
    public static final String HIRO_COLUMN_NAME = "name";
    public static final String HIRO_COLUMN_FULLNAME = "fullName";
    public static final String HIRO_COLUMN_BIRTH = "placeOfBirth";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbHiro) {
        dbHiro.execSQL(
                "create table herois " +
                        "(idHiro integer primary key, name text,fullName text,placeOfBirth text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbHiro, int oldVersion, int newVersion) {
        dbHiro.execSQL("DROP TABLE IF EXISTS herois");
        onCreate(dbHiro);
    }

    public boolean insertHiro (Herois h) {
        SQLiteDatabase dbHiro = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HIRO_COLUMN_NAME, h.getName());
        contentValues.put(HIRO_COLUMN_FULLNAME, h.getFullName());
        contentValues.put(HIRO_COLUMN_BIRTH, h.getPlaceOfBirth());
        dbHiro.insert(HIRO_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int idHiro) {
        SQLiteDatabase dbHiro = this.getReadableDatabase();
        Cursor res =  dbHiro.rawQuery( "select * from herois where idHiro="+idHiro+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase dbHiro = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(dbHiro, HIRO_TABLE_NAME);
        return numRows;
    }

    public boolean updateHiro (Herois h) {
        SQLiteDatabase dbHiro = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HIRO_COLUMN_NAME, h.getName());
        contentValues.put(HIRO_COLUMN_FULLNAME, h.getFullName());
        contentValues.put(HIRO_COLUMN_BIRTH, h.getPlaceOfBirth());
        dbHiro.update(HIRO_TABLE_NAME, contentValues, "idHiro = ? ", new String[] { Integer.toString(h.getIdHiro()) } );
        return true;
    }

    public Integer deleteHiro (Integer idHiro) {
        SQLiteDatabase dbHiro = this.getWritableDatabase();
        return dbHiro.delete(HIRO_TABLE_NAME,
                "idHiro = ?",
                new String[] { Integer.toString(idHiro) });
    }

    public Integer deleteAll () {
        SQLiteDatabase dbHiro = this.getWritableDatabase();
        return dbHiro.delete(HIRO_TABLE_NAME,
                null,
                null);
    }

    public ArrayList<String> getAllHiroes() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase dbHiro = this.getReadableDatabase();
        Cursor res =  dbHiro.rawQuery( "select * from herois", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(HIRO_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Herois> getHeroisList() {
        ArrayList<Herois> lista = new ArrayList<Herois>() ;

        SQLiteDatabase dbHiro = this.getReadableDatabase();
        Cursor res =  dbHiro.rawQuery( "select * from herois", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Herois h = new Herois();
            h.setName(res.getString(res.getColumnIndex(HIRO_COLUMN_NAME)));
            h.setIdHiro(Integer.parseInt(res.getString(res.getColumnIndex(HIRO_COLUMN_ID))));
            h.setFullName(res.getString(res.getColumnIndex(HIRO_COLUMN_FULLNAME)));
            h.setPlaceOfBirth(res.getString(res.getColumnIndex(HIRO_COLUMN_BIRTH)));

            lista.add(h);
            res.moveToNext();
        }
        return lista;
    }
}
