package net.a6te.lazycoder.muslim_pro_islamicremainders.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DeleteData {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DeleteData(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }


    public void deleteAllData(){
        this.Open();
        try {
            database.delete(dbHelper.TABLE_AMHARIC,null,null);
            database.delete(dbHelper.TABLE_ARABIC,null,null);
            database.delete(dbHelper.TABLE_AZERBAIJANI,null,null);
            database.delete(dbHelper.TABLE_AZERI,null,null);
            database.delete(dbHelper.TABLE_BEHASA_MALAYU,null,null);
            database.delete(dbHelper.TABLE_BENGALI,null,null);
            database.delete(dbHelper.TABLE_CHINESE_SIMPLIFIED,null,null);
            database.delete(dbHelper.TABLE_CHINESE_TRADITIONAL,null,null);
            database.delete(dbHelper.TABLE_ENGLISH,null,null);
            database.delete(dbHelper.TABLE_FRENCH,null,null);
            database.delete(dbHelper.TABLE_GERMAN,null,null);
            database.delete(dbHelper.TABLE_HINDI,null,null);
            database.delete(dbHelper.TABLE_INDONESIAN,null,null);
            database.delete(dbHelper.TABLE_MALAY,null,null);
            database.delete(dbHelper.TABLE_PASHTO,null,null);
            database.delete(dbHelper.TABLE_PERSIAN,null,null);
            database.delete(dbHelper.TABLE_RUSSIAN,null,null);
            database.delete(dbHelper.TABLE_SPANISH,null,null);
            database.delete(dbHelper.TABLE_TURKISH,null,null);
            database.delete(dbHelper.TABLE_URDU,null,null);

        }catch (Exception e){
            Log.d("AAFWathakkir", "deleteAllData: "+e);
        }
        this.Close();
    }

}
