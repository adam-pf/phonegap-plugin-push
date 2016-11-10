package com.adobe.phonegap.push;

import android.content.Context;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import me.pushy.sdk.Pushy;

import java.io.IOException;

public class RegistrationIntentService extends IntentService implements PushConstants {
    public static final String LOG_TAG = "PushPlugin_RegistrationIntentService";

    public RegistrationIntentService() {
        super(LOG_TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(COM_ADOBE_PHONEGAP_PUSH, Context.MODE_PRIVATE);

        try {
            String token = Pushy.register(getApplicationContext());
            PushPlugin.setRegistrationID(token);
            Log.i(LOG_TAG, "new GCM Registration Token: " + token);

        } catch (Exception e) {
            Log.d(LOG_TAG, "Failed to complete token refresh", e);
        }
    }
}
