package net.a6te.lazycoder.muslim_pro_islamicremainders;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import net.a6te.lazycoder.muslim_pro_islamicremainders.database.DeleteData;
import net.a6te.lazycoder.muslim_pro_islamicremainders.database.MyDatabase;
import net.a6te.lazycoder.muslim_pro_islamicremainders.interfaces.RetrofitApiCaller;
import net.a6te.lazycoder.muslim_pro_islamicremainders.model.Athkar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownloadData extends IntentService {

    private String baseUrl = Utils.BASE_URL;
    private String athkarAPI = Utils.GET_ATHKAR_API;

    private RetrofitApiCaller apiClient;
    public static final String TAG = "TEST";
    private SavedData sharedPreference;
    private DeleteData deleteData;
    private MyDatabase myDb;
    private CheckInternetConnection internetConnection;
    private Context context;
    public static boolean alreadyDownloading = false;
    private Athkar.Data data;
    private String updateCode;


    public DownloadData() {
        super("DownloadIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        initialize();

//        final long period = 3000;
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Log.d(TAG, "onHandleIntent: running");
                download();
//
//            }
//        }, 0, period);
    }

    private void initialize() {

        context = getApplicationContext();
        sharedPreference = new SavedData(context);
        internetConnection = new CheckInternetConnection();
        //API
        //https://lazycoderbd.000webhostapp.com/api/tutorial/read/1
        //GET_TUTORIAL_API / updateCode

        athkarAPI = Utils.GET_ATHKAR_API +"/"+sharedPreference.getLastUpdateCode();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient = retrofit.create(RetrofitApiCaller.class);
        deleteData = new DeleteData(context);//delete old data
        myDb = new MyDatabase(context);
    }

    public void download(){

        //internet connection checking
        if (!internetConnection.netCheck(context) || alreadyDownloading){
            return;
        }

        alreadyDownloading = true;
        Call<Athkar> athkar = apiClient.getData(athkarAPI);
        athkar.enqueue(new Callback<Athkar>() {
            @Override
            public void onResponse(Call<Athkar> call, Response<Athkar> response) {

                try {
                    if (response.body().getResult().equals(true)){
                        Log.d(TAG, "onResponse: new data came");

                        data = response.body().getData();
                        updateCode = response.body().getUpdateCode();

                        InsertDataIntoDB download = new InsertDataIntoDB();
                        download.execute();

                    }else{
                        Log.d(TAG, "onResponse: database is already up to date");
                        broadcastMessage(getString(R.string.data_all_up_to_date),false);
                        alreadyDownloading = false;
                    }
                }catch (Exception e){
                    alreadyDownloading = false;
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Athkar> call, Throwable t) {
                Log.d(TAG, "onFailure: faield to store data");
                t.printStackTrace();
                alreadyDownloading = false;

            }
        });

    }


    public void broadcastMessage(String message, boolean isUpdatedData){
        /*
         * Creates a new Intent containing a Uri object
         * BROADCAST_ACTION is a custom Intent action
         */
        Intent localIntent =
                new Intent(Utils.BROADCAST_ACTION);
        localIntent.putExtra(Utils.EXTENDED_DATA_STATUS_MESSAGE, message);
        localIntent.putExtra(Utils.EXTENDED_IS_UPDATE_DATA, isUpdatedData);
        // Broadcasts the Intent to receivers in this app.
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }

    class InsertDataIntoDB extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {

            //first delete old data from offline database then we will insert new downloaded data into off DB
            deleteData.deleteAllData();

            //now we will insert our new data
            myDb.insertAthkar(data);

            //now our updated data is saved on our offline database so we need to save last data updateCode
            sharedPreference.saveLastUpdateCode(Integer.parseInt(updateCode));

            return true;

        }

        /*
        * after complete insert data this method will be call
        * */
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            broadcastMessage("New data updated",true);//broadcast message
            alreadyDownloading = false;
        }
    }

}
