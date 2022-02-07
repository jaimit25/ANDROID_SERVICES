package com.example.androidservices
import io.flutter.embedding.android.FlutterActivity

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import java.util.concurrent.Executors
import android.os.Handler
import android.os.Looper
import java.util.Timer
import android.widget.Toast
import kotlin.concurrent.schedule
import io.flutter.embedding.engine.plugins.FlutterPlugin
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.AsyncTask
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class MainActivity: FlutterActivity() {

 private val CHANNEL = "com.example.androidServices/service"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Toast.makeText(this, "OUTSIDE  METHOD CHANNEL" , Toast.LENGTH_LONG).show()
        
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,CHANNEL).setMethodCallHandler{
            call,result->
            Toast.makeText(this, "INSIDE  METHOD CHANNEL " + call.argument<String>("text") , Toast.LENGTH_LONG).show()



            // *********THREAD SECTION(THIS WHOLE THREAD WILL RUN IN MAIN THREAD SECTION - NOT A GOOD PRACTISE)***********
            // ..we will start our service
            Intent(this,MyService::class.java).also {
                startService(it)
                Toast.makeText(this, "Service Running" , Toast.LENGTH_LONG).show()
            }//Intent

            // To send Data to Service 
             Intent(this,MyService::class.java).also {
                 val dataString = call.argument<String>("text")
                 it.putExtra("data",dataString)
                startService(it)
             }//Intent

            // we create a time so that after 5 seconds we will stop our service
            Timer().schedule(10000){
                print("******DELAY OVER*****")
            }//TIMER

            //this will stop the service
            Intent(this,MyService::class.java).also {
                    stopService(it)
                    Toast.makeText(this, "Service Stopped" , Toast.LENGTH_LONG).show()
            }//Intent
             // *********THREAD SECTION OVER***********

             //**********TO START SERVICE  IN DIFFERENT THREAD RATHER THAN MAIN THREAD WE DO**********/
                Thread{
                         // your..code
                }.start()
            Thread.interrupted()

             // *********MULTI THREAD SECTION OVER***********
            
        }//MethodChannel
    } 
    //configureFlutterEngine
} 
//MainActivity
