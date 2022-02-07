package com.example.androidservices
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService  : Service()
{
    //we use this when we have multiple client connected at same time but for npw we dont wanna use that
    override fun onBind(p0: Intent?): IBinder? = null;
    //this thread will by-default start in main thread so we need to start it by ourselves to avoid so

//    this can print something with init when our thread starts
    val TAG = "MY SERVICE"
    init {
       Log.d(TAG,"Service is Running")
    }

    //IMPORTANT FUNCTION - It will be executed and it is use to deliver the intent we started this service with
    // so by over-running this onStartCommand function we can get the intent we started service with and also we
    //can attach Data to that intent to communicate from Activity to our service ie Sent command
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //we will get string from MainActivity from intent
        val DataString = intent?.getStringExtra("data")
        DataString?.let {
            //if DataString is not null then let statement will execute
            Log.d(TAG,"THIS DATA IS PRINTED BY SERVICE : "+DataString)
        }

        // START_NOT_STICKY - if android service need resources then it will kill service and again won't create it
        // START_STICKY - It means if android service kill this service then it will recreate it again
        //

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG,"Service is Stopping..........")
        super.onDestroy()
    }

}