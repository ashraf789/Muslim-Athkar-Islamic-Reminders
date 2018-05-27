package net.a6te.lazycoder.aafwathakkir_islamicreminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private Context context;
    @Override
    public void onReceive(final Context context, final Intent intent) {

        this.context = context;
        if (!checkConnect()) {
            Log.d("TEST", "onReceive: internet connected");
            // TODO

            sendMessage(context.getString(R.string.required_data_connection),Utils.NO_CONNECTION_CODE, false);

        }else if (!checkLocation()) {
            Log.d("TEST", "onReceive: internet disconnected");
            sendMessage(context.getString(R.string.required_data_connection),Utils.NO_CONNECTION_CODE, true);
        }else{
            sendMessage("All are connected",Utils.ALL_CONNECTED, true);
        }

    }
    public void sendMessage(String status,int statusCode){
        Intent intent = new Intent(Utils.BROADCAST_CONNECTION_STATUS);
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.STATUS_CODE,statusCode);
        bundle.putString(Utils.CONNECTION_STATUS,status);
        intent.putExtras(bundle);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    public void sendMessage(String status,int statusCode, boolean dataConnectionEnable){
        Intent intent = new Intent(Utils.BROADCAST_CONNECTION_STATUS);
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.STATUS_CODE,statusCode);
        bundle.putString(Utils.CONNECTION_STATUS,status);
        bundle.putBoolean(Utils.DATA_CONNECTION_ENABLE,dataConnectionEnable);
        intent.putExtras(bundle);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private boolean checkConnect() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        }
        return false;
    }
    private boolean checkLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
