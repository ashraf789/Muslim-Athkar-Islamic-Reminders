package net.a6te.lazycoder.aafwathakkir_islamicreminders.Remainder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.LocaleManager;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.MainActivity;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.SavedData;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.database.MyDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";
    private Context context;
    private SavedData savedData;
    private MyDatabase myDatabase;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        context = LocaleManager.setLocale(context);
        this.context = context;


        savedData = new SavedData(context);
        myDatabase = new MyDatabase(context);

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                SavedData savedData = new SavedData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, savedData.getAppStartHour(), savedData.getAppStartMin(),savedData.getNewRemainderInterval());
                return;
            }
        }

        int lastAthkarId = savedData.getLastAthkarId()+1;
        savedData.setLastAthkarId(lastAthkarId);

        NotificationScheduler.showNotification(context, MainActivity.class,
                context.getString(R.string.app_sub_name), getAtkhar(lastAthkarId));

    }

    public String getAtkhar(int id){

          String  tableName = getTableName();

        int lastDataId = myDatabase.getLastDataId(tableName);
        String atkhar;

        if (lastDataId >= id){
            //still available new data
            atkhar = myDatabase.getAtkhar(tableName, id);
        }else {
            //no new data available we already seen last atkhar lets start again from first
            id = 0;
            atkhar = myDatabase.getAtkhar(tableName, id);
            savedData.setLastAthkarId(id);
        }
        return atkhar;
    }

    public String getTableName(){
        List<String > tableLanguages = Arrays.asList(context.getResources().getStringArray(R.array.remainder_language_table_name));

        int size = tableLanguages.size();
        boolean[] remainderLanguages = savedData.getRemainderLanguages(size);

        ArrayList<Integer> indexNoOfSelectedLanguage = new ArrayList<>();

        for (int i = 0; i < size; i++){
            if (remainderLanguages[i]){
                indexNoOfSelectedLanguage.add(i);
            }
        }
        size = indexNoOfSelectedLanguage.size();
        Random random= new Random();
        int randomSelectedLanguageIndex = random.nextInt(size);

        String tableName = tableLanguages.get(indexNoOfSelectedLanguage.get(randomSelectedLanguageIndex));
        savedData.saveAthkarTableName(tableName);//new image generated table name
        return tableName;
    }

}


