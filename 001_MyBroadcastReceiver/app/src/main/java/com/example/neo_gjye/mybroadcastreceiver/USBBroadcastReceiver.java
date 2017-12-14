package com.example.neo_gjye.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class USBBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = USBBroadcastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        Log.i(TAG,"action : " + action);
        UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        String deviceName = device.getDeviceName();
        switch(action) {
            case UsbManager.ACTION_USB_DEVICE_ATTACHED:
                // usb in
                Log.i(TAG,"USB device is Attached: " + deviceName);
                break;
            case UsbManager.ACTION_USB_DEVICE_DETACHED:
                //usb out
                Log.i(TAG,"USB device is detached: " + deviceName);
                break;
            default:
                break;
        }
    }
}
