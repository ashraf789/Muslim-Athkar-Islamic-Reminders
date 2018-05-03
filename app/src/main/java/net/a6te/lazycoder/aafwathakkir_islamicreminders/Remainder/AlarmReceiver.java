package net.a6te.lazycoder.aafwathakkir_islamicreminders.Remainder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import net.a6te.lazycoder.aafwathakkir_islamicreminders.MainActivity;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.R;
import net.a6te.lazycoder.aafwathakkir_islamicreminders.SavedData;


public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";
    private Context context;
    private SavedData savedData;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        this.context = context;
        savedData = new SavedData(context);
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                SavedData savedData = new SavedData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, savedData.getAppStartHour(), savedData.getAppStartMin(),savedData.getNewRemainderInterval());
                return;
            }
        }

        //Trigger the notification
        NotificationScheduler.showNotification(context, MainActivity.class,
                context.getString(R.string.notification_title), context.getString(R.string.notification_subtitle));


        savedData.setLastAthkarId((savedData.getLastAthkarId()+1));
    }
}


