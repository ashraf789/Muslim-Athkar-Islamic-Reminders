package net.a6te.lazycoder.aafwathakkir_islamicreminders.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "quran_atkhar.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ARABIC = "arabic";
    public static final String TABLE_ENGLISH = "english";

    public static final String COL_ID = "id";
    public static final String COL_ATKHAR = "atkhar";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    public String getAtkhar(String table, int id) {

        //if no data found then this will be the message
        String getData="no data found on '"+table+"' table";
        id++;//array start from 0 but datase start from 1 so we are adding +1 to start it from first row
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        String [] sqlSelect = {"id","atkhar"};
        String sqlTables = table;

        qb.setTables(sqlTables);

        try {
            Cursor cursor = qb.query(db, sqlSelect, COL_ID+" = ?",new String []{String.valueOf(id)},
                    null, null, null);

            cursor.moveToFirst();

            getData = cursor.getString(cursor.getColumnIndex("atkhar"));

        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();

        return getData;

    }

    public int getLastDataId(String table) {
//
//        int lastIndex = 0;
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

//        String [] sqlSelect = {"id","atkhar"};
//        String sqlTables = table;
//
//        qb.setTables(sqlTables);
//
//        try {
//            Cursor cursor = qb.query(db, sqlSelect, null, null,
//                    null, null, COL_ID+" DESC");
//
//            cursor.moveToFirst();
//            lastIndex = cursor.getInt(cursor.getColumnIndex(COL_ID)+0);
//
//
//            Cursor cursor= db.rawQuery("SELECT COUNT (*) FROM " + table, null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        DatabaseUtils.queryNumEntries(
//
        int totalData = (int) DatabaseUtils.queryNumEntries(db, table);
        db.close();
//

        //database index start from 1 but array index start from 0 so we are subtracting 1 from database index
        return (totalData-1);
    }

}
