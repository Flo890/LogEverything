package org.hcilab.projects.logeverything.sensor.implementation;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import org.hcilab.projects.logeverything.sensor.AbstractSensor;
import org.hcilab.projects.logeverything.service.AccessibilityLogService;
import org.hcilab.projects.logeverything.service.GlobalTouchService;
import org.hcilab.projects.logeverything.service.Window;

public class GlobalTouchSensor extends AbstractSensor {

    private Context m_Context = null;
    private Intent m_Intent;

    public GlobalTouchSensor() {
        m_IsRunning = false;
        TAG = getClass().getName();
        SENSOR_NAME = "GlobalTouch";
        FILE_NAME = "global_touch.csv";
        m_FileHeader = "TimeUnix,Ssid";
    }

    @Override
    public View getSettingsView(Context context) {
        return null;
    }

    @Override
    public boolean isAvailable(Context context) {
        return false;
    }

    @Override
    public void start(Context context) {
        Log.i(TAG, "starting");
        super.start(context);
        if (!m_isSensorAvailable)
            return;

        m_Context = context;



//        Window window=new Window(m_Context);
//        window.open();

        m_Intent = new Intent(m_Context, GlobalTouchService.class);
        context.startService(m_Intent);

//        if (m_Receiver == null)
//            m_Receiver = new AccessibilitySensor.DataUpdateReceiver();
//
//        IntentFilter intentFilter = new IntentFilter(AccessibilityLogService.TAG);
//        intentFilter.addAction(AccessibilityLogService.TAG);
//        m_Context.registerReceiver(m_Receiver, intentFilter);

        m_IsRunning = true;
    }

    @Override
    public void stop() {
        Log.i(TAG,"stopping");
        m_IsRunning = false;
        if (m_Context == null)
            return;
        m_Context.stopService(m_Intent);
    }
}
