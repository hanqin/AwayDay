package com.thoughtworks.mobile.awayday.fragments.hotel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.thoughtworks.mobile.awayday.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HotelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View resultView = inflater.inflate(R.layout.hotel_details, null);
        final Gallery gallery = (Gallery) resultView.findViewById(R.id.event_gallery);
        gallery.setAdapter(new ImageAdapter());

        final View reminder = resultView.findViewById(R.id.hotel_details_reminder_bar);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addEventToCalendar();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return resultView;
    }

    protected void addEventToCalendar() throws ParseException {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        final String dateTimePattern = "MMMM d, hh:mm a";
        intent.putExtra("beginTime", new SimpleDateFormat(dateTimePattern).parse("September 14, 12:00 PM").getTime());
        intent.putExtra("endTime", new SimpleDateFormat(dateTimePattern).parse("September 14, 14:00 PM").getTime());
        intent.putExtra("title", "Hotel Check in");
        intent.putExtra("eventLocation", "");
        intent.putExtra("description", "Check in Conference Resort Chengdu:  www.hojoqc.com, then take a small break");
        getActivity().startActivity(intent);
    }

}
