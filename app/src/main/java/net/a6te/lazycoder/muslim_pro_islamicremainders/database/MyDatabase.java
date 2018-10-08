package net.a6te.lazycoder.muslim_pro_islamicremainders.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;


import net.a6te.lazycoder.muslim_pro_islamicremainders.R;
import net.a6te.lazycoder.muslim_pro_islamicremainders.SavedData;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.Athkar;

public class MyDatabase {
    private Context context;

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private SavedData savedData;

    public MyDatabase(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
        savedData = new SavedData(context);
    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }

    public boolean insertAthkar(Athkar.Data data){
        this.Open();
        long dataInserted = 0;

        for (Athkar.Amharic athkar: data.getAmharic()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_AMHARIC,null,contentValues);
        }
        for (Athkar.Arabic athkar: data.getArabic()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_ARABIC,null,contentValues);
        }
        for (Athkar.Azerbaijani athkar: data.getAzerbaijani()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_AZERBAIJANI,null,contentValues);
        }
        for (Athkar.Azerus athkar: data.getAzeri()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_AZERI,null,contentValues);
        }
        for (Athkar.BehasaMelayu athkar: data.getBehasaMelayu()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_BEHASA_MALAYU,null,contentValues);
        }
        for (Athkar.Bengali athkar: data.getBengali()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_BENGALI,null,contentValues);
        }
        for (Athkar.ChineseSimplified athkar: data.getChineseSimplified()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_CHINESE_SIMPLIFIED,null,contentValues);
        }
        for (Athkar.ChineseTraditional athkar: data.getChineseTraditional()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_CHINESE_TRADITIONAL,null,contentValues);
        }
        for (Athkar.English athkar: data.getEnglish()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            Log.d("data", "insertAthkar: Tag = "+athkar.getTag());

            database.insert(dbHelper.TABLE_ENGLISH,null,contentValues);
        }
        for (Athkar.French athkar: data.getFrench()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_FRENCH,null,contentValues);
        }
        for (Athkar.German athkar: data.getGerman()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_GERMAN,null,contentValues);
        }
        for (Athkar.Hindi athkar: data.getHindi()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_HINDI,null,contentValues);
        }
        for (Athkar.Indonesian athkar: data.getIndonesian()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_INDONESIAN,null,contentValues);
        }
        for (Athkar.Malay athkar: data.getMalay()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_MALAY,null,contentValues);
        }
        for (Athkar.Pashto athkar: data.getPashto()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_PASHTO,null,contentValues);
        }
        for (Athkar.Persian athkar: data.getPersian()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_PERSIAN,null,contentValues);
        }
        for (Athkar.Russian athkar: data.getRussian()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_RUSSIAN,null,contentValues);
        }
        for (Athkar.Spanish athkar: data.getSpanish()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_SPANISH,null,contentValues);
        }
        for (Athkar.Turkish athkar: data.getTurkish()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            database.insert(dbHelper.TABLE_TURKISH,null,contentValues);
        }
        for (Athkar.Urdu athkar: data.getUrdu()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());
            contentValues.put(dbHelper.COL_TAG,athkar.getTag());

            dataInserted = database.insert(dbHelper.TABLE_URDU,null,contentValues);


        }

        savedData.saveDataSynchronizedStatusTrue();

        this.Close();
        if(dataInserted > 0 ){
            Log.d("TEST", "data: ------------ new data inserted");
            return true;
        }else {
            Log.d("TEST", "data: ------------ new data inserted failed");
            return false;
        }

    }


    public String getAtkhar(String table, int id) {

        //if no data found then this will be the message
        String getData=context.getString(R.string.required_data_connection);
//        id++;//array start from 0 but datase start from 1 so we are adding +1 to start it from first row
        /*todo need to work in here later i have commented the id++; line maybe it is connected with other */


        Open();

        try {
            Cursor cursor = database.query(table,new String[] {dbHelper.COL_ATHKAR}, dbHelper.COL_ID+"=?", new String[] {String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();

            getData = cursor.getString(cursor.getColumnIndex(dbHelper.COL_ATHKAR));

        }catch (Exception e){
            e.printStackTrace();
        }
        Close();

        return getData;
    }
    public Bundle getAtkhar(String table, String  tag, int id) {

        //if no data found then this will be the message
        String getData=context.getString(R.string.required_data_connection);
        Bundle bundle = new Bundle();


        Open();

        try {
            Cursor cursor = database.query(table,new String[] {dbHelper.COL_ID,dbHelper.COL_ATHKAR}, dbHelper.COL_TAG+"=?", new String[] {tag}, null, null, null);

            if (cursor.moveToFirst()){
                getData = cursor.getString(cursor.getColumnIndex(dbHelper.COL_ATHKAR));
                id = cursor.getInt(cursor.getColumnIndex(dbHelper.COL_ID));
            }
            else {
                Cursor cursor2 = database.query(table,new String[] {dbHelper.COL_ATHKAR}, dbHelper.COL_ID+"=?", new String[] {String.valueOf(id)}, null, null, null);
                cursor2.moveToFirst();

                getData = cursor2.getString(cursor.getColumnIndex(dbHelper.COL_ATHKAR));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        Close();

        bundle.putString("atkhar",getData);
        bundle.putInt("id",id);

        return bundle;
    }


    public int getLastDataId(String table) {

        int totalData = 0;
        Open();
        try {
            Cursor cursor = database.query(table,new String[] {dbHelper.COL_ID}, null, null, null, null, dbHelper.COL_ID+" DESC");
            cursor.moveToFirst();
            totalData = cursor.getInt(cursor.getColumnIndex(dbHelper.COL_ID));

        }catch (Exception e){
            e.printStackTrace();
        }

        Close();

        //database index start from 1 but array index start from 0 so we are subtracting 1 from database index
        return (totalData-1);
    }

}
