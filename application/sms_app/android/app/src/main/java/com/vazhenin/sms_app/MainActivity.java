package com.vazhenin.sms_app;

import android.telephony.SmsManager;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

    private static final String CHANNEL = "sendSms";


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            if(call.method.equals("send")){
                                String num = call.argument("phone");
                                String msg = call.argument("msg");
                                sendSMS(num,msg,result);
                            }else{
                                result.notImplemented();
                            }
                        }
                );
    }


    private void sendSMS(String phoneNo, String msg, MethodChannel.Result result) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            result.success("SMS Sent");
        } catch (Exception ex) {
            ex.printStackTrace();
            result.error("Err", "Sms Not Sent", "");
        }
    }


}
