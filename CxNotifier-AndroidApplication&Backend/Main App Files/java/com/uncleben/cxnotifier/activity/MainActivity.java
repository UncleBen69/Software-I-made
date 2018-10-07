package com.uncleben.cxnotifier.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.Toast;


import com.google.firebase.messaging.FirebaseMessaging;

import com.uncleben.cxnotifier.R;
import com.uncleben.cxnotifier.app.Config;
import com.uncleben.cxnotifier.util.NotificationUtils;
import com.uncleben.cxnotifier.util.randomutils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private Switch sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9,sw10,sw11,sw12,sw13,sw14,sw15,sw16,sw17,sw18,sw19,sw20,sw21,sw22,sw23,sw24,sw25;
    private  Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    //Subscribe to everyone
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    FirebaseMessaging.getInstance().subscribeToTopic("events");
                    FirebaseMessaging.getInstance().subscribeToTopic("posts");
                    FirebaseMessaging.getInstance().subscribeToTopic("community");
                    FirebaseMessaging.getInstance().subscribeToTopic("uploads");
                    FirebaseMessaging.getInstance().subscribeToTopic("polls");
                    FirebaseMessaging.getInstance().subscribeToTopic("burger_planet");
                    FirebaseMessaging.getInstance().subscribeToTopic("sam_pepper");
                    FirebaseMessaging.getInstance().subscribeToTopic("ice_poseidon");
                    FirebaseMessaging.getInstance().subscribeToTopic("kiedom");
                    FirebaseMessaging.getInstance().subscribeToTopic("sweeterin");
                    FirebaseMessaging.getInstance().subscribeToTopic("hyphonix");
                    FirebaseMessaging.getInstance().subscribeToTopic("brokemalone");
                    FirebaseMessaging.getInstance().subscribeToTopic("ebz");
                    FirebaseMessaging.getInstance().subscribeToTopic("aria");
                    FirebaseMessaging.getInstance().subscribeToTopic("yuber");
                    FirebaseMessaging.getInstance().subscribeToTopic("bjorn");
                    FirebaseMessaging.getInstance().subscribeToTopic("gary");
                    FirebaseMessaging.getInstance().subscribeToTopic("demon_andy");
                    FirebaseMessaging.getInstance().subscribeToTopic("anything4views");
                    FirebaseMessaging.getInstance().subscribeToTopic("cxnews");
                    FirebaseMessaging.getInstance().subscribeToTopic("mexicanandy");
                    FirebaseMessaging.getInstance().subscribeToTopic("kpopandy");
                    FirebaseMessaging.getInstance().subscribeToTopic("hypeman_vince");
                    FirebaseMessaging.getInstance().subscribeToTopic("nojumper");
                    FirebaseMessaging.getInstance().subscribeToTopic("ness");
                    FirebaseMessaging.getInstance().subscribeToTopic("tracksuit_andy");
                    FirebaseMessaging.getInstance().subscribeToTopic("d3");
                    FirebaseMessaging.getInstance().subscribeToTopic("onlyusemeblade");
                    FirebaseMessaging.getInstance().subscribeToTopic("asian_andy");


                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");
                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    //txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();

        //This is switch shit

        setContentView(R.layout.activity_main);
        sw1 = (Switch)findViewById(R.id.switch1);
        sw2 = (Switch)findViewById(R.id.switch2);
        sw3 = (Switch)findViewById(R.id.switch3);
        sw4 = (Switch)findViewById(R.id.switch4);
        sw5 = (Switch)findViewById(R.id.switch5);
        sw6 = (Switch)findViewById(R.id.switch6);
        sw7 = (Switch)findViewById(R.id.switch7);
        sw8 = (Switch)findViewById(R.id.switch8);
        sw9 = (Switch)findViewById(R.id.switch9);
        sw10 = (Switch)findViewById(R.id.switch10);
        sw11 = (Switch)findViewById(R.id.switch11);
        sw12 = (Switch)findViewById(R.id.switch12);
        sw13 = (Switch)findViewById(R.id.switch13);
        sw14 = (Switch)findViewById(R.id.switch14);
        sw15 = (Switch)findViewById(R.id.switch15);
        sw16 = (Switch)findViewById(R.id.switch16);
        sw17 = (Switch)findViewById(R.id.switch17);
        sw18 = (Switch)findViewById(R.id.switch18);
        sw19 = (Switch)findViewById(R.id.switch19);
        sw20 = (Switch)findViewById(R.id.switch20);
        sw21 = (Switch)findViewById(R.id.switch21);
        sw22 = (Switch)findViewById(R.id.switch22);
        sw23 = (Switch)findViewById(R.id.switch23);
        sw24 = (Switch)findViewById(R.id.switch24);
        sw25 = (Switch)findViewById(R.id.switch25);

        btnGet = (Button)findViewById(R.id.getBtn);
        //This loads the saved values
        SharedPreferences sharedPrefs = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE);
        sw1.setChecked(sharedPrefs.getBoolean("burger_planet_enabled", true));
        sw2.setChecked(sharedPrefs.getBoolean("sam_pepper_enabled", true));
        sw3.setChecked(sharedPrefs.getBoolean("ice_poseidon_enabled", true));
        sw4.setChecked(sharedPrefs.getBoolean("kiedom_enabled", true));
        sw5.setChecked(sharedPrefs.getBoolean("sweeterin_enabled", true));
        sw6.setChecked(sharedPrefs.getBoolean("hyphonix_enabled", true));
        sw7.setChecked(sharedPrefs.getBoolean("brokemalone_enabled", true));
        sw8.setChecked(sharedPrefs.getBoolean("ebz_enabled", true));
        sw9.setChecked(sharedPrefs.getBoolean("aria_enabled", true));
        sw10.setChecked(sharedPrefs.getBoolean("yuber_enabled", true));
        sw11.setChecked(sharedPrefs.getBoolean("bjorn_enabled", true));
        sw12.setChecked(sharedPrefs.getBoolean("gary_enabled", true));
        sw13.setChecked(sharedPrefs.getBoolean("demon_andy_enabled", true));
        sw14.setChecked(sharedPrefs.getBoolean("anything4views_enabled", true));
        sw15.setChecked(sharedPrefs.getBoolean("cxnews_enabled", true));
        sw16.setChecked(sharedPrefs.getBoolean("mexicanandy_enabled", true));
        sw17.setChecked(sharedPrefs.getBoolean("kpopandy_enabled", true));
        sw18.setChecked(sharedPrefs.getBoolean("hypeman_vince_enabled", true));
        sw19.setChecked(sharedPrefs.getBoolean("nojumper_enabled", true));
        sw20.setChecked(sharedPrefs.getBoolean("ness_enabled", true));
        sw21.setChecked(sharedPrefs.getBoolean("tracksuit_andy_enabled", true));
        sw22.setChecked(sharedPrefs.getBoolean("d3_enabled", true));
        sw23.setChecked(sharedPrefs.getBoolean("onlyusemeblade_enabled", true));
        sw24.setChecked(sharedPrefs.getBoolean("asian_andy_enabled", true));
        sw25.setChecked(sharedPrefs.getBoolean("events_enabled", true));

        btnGet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (sw1.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("burger_planet");
                    //This saves the state
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("burger_planet_enabled", true);
                    editor.commit();
                }
                else{
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("burger_planet");
                    //Saves state
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("burger_planet_enabled", false);
                    editor.commit();
                }
                if (sw2.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("sam_pepper");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("sam_pepper_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("sam_pepper");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("sam_pepper_enabled", false);
                    editor.commit();
                }
                if (sw3.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("ice_poseidon");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("ice_poseidon_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ice_poseidon");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("ice_poseidon_enabled", false);
                    editor.commit();
                }
                if (sw4.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("kiedom");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("kiedom_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("kiedom");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("kiedom_enabled", false);
                    editor.commit();
                }
                if (sw5.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("sweeterin");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("sweeterin_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("sweeterin");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("sweeterin_enabled", false);
                    editor.commit();
                }
                if (sw6.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("hyphonix");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("hyphonix_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("hyphonix");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("hyphonix_enabled", false);
                    editor.commit();
                }
                if (sw7.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("brokemalone");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("brokemalone_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("brokemalone");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("brokemalone_enabled", false);
                    editor.commit();
                }
                if (sw8.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("ebz");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("ebz_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ebz");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("ebz_enabled", false);
                    editor.commit();
                }
                if (sw9.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("aria");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("aria_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("aria");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("aria_enabled", false);
                    editor.commit();
                }
                if (sw10.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("yuber");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("yuber_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("yuber");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("yuber_enabled", false);
                    editor.commit();
                }
                if (sw11.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("bjorn");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("bjorn_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("bjorn");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("bjorn_enabled", false);
                    editor.commit();
                }
                if (sw12.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("gary");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("gary_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("gary");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("gary_enabled", false);
                    editor.commit();
                }
                if (sw13.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("demon_andy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("demon_andy_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("demon_andy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("demon_andy_enabled", false);
                    editor.commit();
                }
                if (sw14.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("anything4views");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("anything4views_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("anything4views");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("anything4views_enabled", false);
                    editor.commit();
                }
                if (sw15.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("cxnews");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("cxnews_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("cxnews");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("cxnews_enabled", false);
                    editor.commit();
                }
                if (sw16.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("mexicanandy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("mexicanandy_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("mexicanandy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("mexicanandy_enabled", false);
                    editor.commit();
                }
                if (sw17.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("kpopandy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("kpopandy_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("kpopandy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("kpopandy_enabled", false);
                    editor.commit();
                }
                if (sw18.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("hypeman_vince");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("hypeman_vince_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("hypeman_vince");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("hypeman_vince_enabled", false);
                    editor.commit();
                }
                if (sw19.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("nojumper");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("nojumper_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("nojumper");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("nojumper_enabled", false);
                    editor.commit();
                }
                if (sw20.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("ness");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("ness_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ness");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("ness_enabled", false);
                    editor.commit();
                }
                if (sw21.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("tracksuit_andy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("tracksuit_andy_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("tracksuit_andy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("tracksuit_andy_enabled", false);
                    editor.commit();
                }
                if (sw22.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("d3");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("d3_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("d3");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("d3_enabled", false);
                    editor.commit();
                }
                if (sw23.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("onlyusemeblade");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("onlyusemeblade_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("onlyusemeblade");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("onlyusemeblade_enabled", false);
                    editor.commit();
                }
                if (sw24.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("asian_andy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("asian_andy_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("asian_andy");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("asian_andy_enabled", false);
                    editor.commit();
                }
                if (sw25.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("events");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("events_enabled", true);
                    editor.commit();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("events");
                    SharedPreferences.Editor editor = getSharedPreferences("com.uncleben.cxnotifier", MODE_PRIVATE).edit();
                    editor.putBoolean("events_enabled", false);
                    editor.commit();
                }
            }
        });

    }


    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);
        /*
        if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");
        */

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

}
