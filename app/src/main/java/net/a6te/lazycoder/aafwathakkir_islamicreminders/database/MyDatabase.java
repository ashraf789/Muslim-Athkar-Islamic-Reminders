package net.a6te.lazycoder.aafwathakkir_islamicreminders.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;

public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "quran_atkhar.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ARABIC = "arabic";
    public static final String TABLE_ENGLISH = "english";

    public static final String COL_ID = "id";
    public static final String COL_ATKHAR = "atkhar";
    public static String DB_PATH;
    private Context context;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";
        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    public String getAtkhar(String table, int id) {

        deleteAssetsFolderDb();//already copied assets folder database now delete that old database

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

    public void deleteAssetsFolderDb(){
        File file = new File(DB_PATH+DATABASE_NAME);
        if(file.exists()) {
            file.delete();
            Log.d("TEST", "Database deleted.");
        }

        boolean b = context.deleteDatabase(DATABASE_NAME);
        Log.d("TEST", "deleteAssetsFolderDb: is database deleted = "+b);
    }

    public int getLastDataId(String table) {

        SQLiteDatabase db = getReadableDatabase();
        int totalData = (int) DatabaseUtils.queryNumEntries(db, table);
        db.close();

        //database index start from 1 but array index start from 0 so we are subtracting 1 from database index
        return (totalData-1);
    }

}
