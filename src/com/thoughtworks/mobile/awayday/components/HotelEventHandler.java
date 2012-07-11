package com.thoughtworks.mobile.awayday.components;

import android.content.Context;
import android.content.Intent;
import com.thoughtworks.mobile.awayday.EventDetailsActivity;
import com.thoughtworks.mobile.awayday.components.html.EventSelectedHandler;
import com.thoughtworks.mobile.awayday.fragments.hotel.HotelFragment;
import com.thoughtworks.mobile.awayday.util.FragmentConstants;

public class HotelEventHandler implements EventSelectedHandler {
    private Context applicationContext;

    public HotelEventHandler(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void eventSelected(String eventName) {
        final Intent intent = new Intent();
        intent.setClass(applicationContext, EventDetailsActivity.class);
        intent.putExtra(FragmentConstants.FRAGMENT_CLASS, HotelFragment.class.getName());
        applicationContext.startActivity(intent);
    }
}
