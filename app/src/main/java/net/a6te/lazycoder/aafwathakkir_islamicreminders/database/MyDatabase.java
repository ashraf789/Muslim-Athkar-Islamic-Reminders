package net.a6te.lazycoder.aafwathakkir_islamicreminders.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.nfc.Tag;
import android.util.Log;


import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.model.Athkar;

import java.util.Arrays;
import java.util.List;

public class MyDatabase {
    private Context context;

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public MyDatabase(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
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

            database.insert(dbHelper.TABLE_AMHARIC,null,contentValues);
        }
        for (Athkar.Arabic athkar: data.getArabic()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_ARABIC,null,contentValues);
        }
        for (Athkar.Azerbaijani athkar: data.getAzerbaijani()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_AZERBAIJANI,null,contentValues);
        }
        for (Athkar.Azerus athkar: data.getAzeri()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_AZERI,null,contentValues);
        }
        for (Athkar.BehasaMelayu athkar: data.getBehasaMelayu()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_BEHASA_MALAYU,null,contentValues);
        }
        for (Athkar.Bengali athkar: data.getBengali()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_BENGALI,null,contentValues);
        }
        for (Athkar.ChineseSimplified athkar: data.getChineseSimplified()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_CHINESE_SIMPLIFIED,null,contentValues);
        }
        for (Athkar.ChineseTraditional athkar: data.getChineseTraditional()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.CHINESE_TRADITIONAL_QUERY,null,contentValues);
        }
        for (Athkar.English athkar: data.getEnglish()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_ENGLISH,null,contentValues);
        }
        for (Athkar.French athkar: data.getFrench()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_FRENCH,null,contentValues);
        }
        for (Athkar.German athkar: data.getGerman()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_GERMAN,null,contentValues);
        }
        for (Athkar.Hindi athkar: data.getHindi()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_HINDI,null,contentValues);
        }
        for (Athkar.Indonesian athkar: data.getIndonesian()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_INDONESIAN,null,contentValues);
        }
        for (Athkar.Malay athkar: data.getMalay()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_MALAY,null,contentValues);
        }
        for (Athkar.Pashto athkar: data.getPashto()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_PASHTO,null,contentValues);
        }
        for (Athkar.Persian athkar: data.getPersian()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_PERSIAN,null,contentValues);
        }
        for (Athkar.Russian athkar: data.getRussian()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_RUSSIAN,null,contentValues);
        }
        for (Athkar.Spanish athkar: data.getSpanish()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_SPANISH,null,contentValues);
        }
        for (Athkar.Turkish athkar: data.getTurkish()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            database.insert(dbHelper.TABLE_TURKISH,null,contentValues);
        }
        for (Athkar.Urdu athkar: data.getUrdu()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ATHKAR_ID,athkar.getId());
            contentValues.put(dbHelper.COL_ATHKAR,athkar.getAtkhar());

            dataInserted = database.insert(dbHelper.TABLE_URDU,null,contentValues);


        }

        this.Close();
        if(dataInserted > 0 ){
            Log.d("TEST", "data: ------------ new data inserted");
            return true;
        }else {
            Log.d("TEST", "data: ------------ new data inserted failed");
            return false;
        }

    }


//    public MyDatabase(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
//        DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";
//
//    }

//    /*
//    *
//    * when we use string_array then this method will be use as our database query
//    * */
//    public String getAtkhar(String table, int id) {
//
//        List<String > data = Arrays.asList(getData(table));
//        //        if no data found then this will be the message
//        String athkar ="no data found on '"+table+"' table";
//        try {
//            athkar = data.get(id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return athkar;
//    }
//
//
//    /*
//    *
//    * get last id data id
//    * */
//    public int getLastDataId(String table) {
//
//        List<String > data = Arrays.asList(getData(table));
//        try {
//            return data.size();// last data id will be the size of string_array
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //if table not found then it will return 0
//        return 0;
//    }

    private String[] getData(String table) {
        switch (table){
            case "english":
                return context.getResources().getStringArray(R.array.english);
            case "arabic":
                return context.getResources().getStringArray(R.array.arabic);
            case "indonesian":
                return context.getResources().getStringArray(R.array.indonesian);
            case "hindi":
                return context.getResources().getStringArray(R.array.hindi);
            case "urdu":
                return context.getResources().getStringArray(R.array.urdu);
            case "bengali":
                return context.getResources().getStringArray(R.array.bengali);
            case "turkish":
                return context.getResources().getStringArray(R.array.turkish);
            case "persian":
                return context.getResources().getStringArray(R.array.persian);
            case "amharic":
                return context.getResources().getStringArray(R.array.amharic);
            case "pashto":
                return context.getResources().getStringArray(R.array.pashto);
            case "russian":
                return context.getResources().getStringArray(R.array.russian);
            case "chinese_simplified":
                return context.getResources().getStringArray(R.array.chinese_simplified);
            case "chinese_traditional":
                return context.getResources().getStringArray(R.array.chinese_traditional);
            case "malay":
                return context.getResources().getStringArray(R.array.malay);
            case "azerbaijani":
                return context.getResources().getStringArray(R.array.azerbaijani);
            case "spanish":
                return context.getResources().getStringArray(R.array.spanish);
            case "french":
                return context.getResources().getStringArray(R.array.french);
            case "german":
                return context.getResources().getStringArray(R.array.german);
        }
        return new String[0];
    }


    /*
     * bellow method for external database which is located at databases/database.db
     * */

//    public String getAtkhar(String table, int id) {
//
//        //if no data found then this will be the message
//        String getData="no data found on '"+table+"' table";
//        id++;//array start from 0 but datase start from 1 so we are adding +1 to start it from first row
//        SQLiteDatabase db = getReadableDatabase();
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//
//
//        String [] sqlSelect = {"id","atkhar"};
//        String sqlTables = table;
//
//        qb.setTables(sqlTables);
//
//        try {
//            Cursor cursor = qb.query(db, sqlSelect, COL_ID+" = ?",new String []{String.valueOf(id)},
//                    null, null, null);
//
//            cursor.moveToFirst();
//
//            getData = cursor.getString(cursor.getColumnIndex("atkhar"));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        db.close();
//
//        return getData;
//
//    }


    public String getAtkhar(String table, int id) {

        //if no data found then this will be the message
        String getData="no data found on '"+table+"' table";
        id++;//array start from 0 but datase start from 1 so we are adding +1 to start it from first row


        Open();

        try {
//            Cursor cursor = database.query(db, sqlSelect, COL_ID+" = ?",new String []{String.valueOf(id)},
//                    null, null, null);

            Cursor cursor = database.query(table,new String[] {dbHelper.COL_ATHKAR}, dbHelper.COL_ID+"=?", new String[] {String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();

            getData = cursor.getString(cursor.getColumnIndex(dbHelper.COL_ATHKAR));

        }catch (Exception e){
            e.printStackTrace();
        }
        Close();

        return getData;
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
