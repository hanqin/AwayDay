package com.thoughtworks.mobile.awayday.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.thoughtworks.mobile.awayday.components.html.EventSelectedHandler;

public class EventSelectedHandlerFactory implements EventSelectedHandler {

    private Context applicationContext;

    public EventSelectedHandlerFactory(Context applicationContext) {

        this.applicationContext = applicationContext;
    }

    @Override
    public void eventSelected(String eventName) {
        Log.d("EventHandler", eventName + " selected");
        if ("Hotel Check in".equalsIgnoreCase(eventName)) {
            new HotelEventHandler(applicationContext).eventSelected(eventName);
            return;
        }
        Toast.makeText(applicationContext, "You have selected " + eventName + ", coming soon, try the Hotel Check In event ", 9000).show();
    }
}
