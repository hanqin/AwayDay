package com.thoughtworks.mobile.awayday.fragments.hotel;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.thoughtworks.mobile.awayday.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelFragment extends Fragment {

    private GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View resultView = inflater.inflate(R.layout.hotel_details, null);
        final Gallery gallery = (Gallery) resultView.findViewById(R.id.event_gallery);
        gallery.setAdapter(new ImageAdapter());

        final View reminderBar = resultView.findViewById(R.id.hotel_details_reminder_bar);
        final ListView calendarDetailsView = (ListView) resultView.findViewById(R.id.calendar_details);

        final TextView headerView = new TextView(getActivity());
        headerView.setTextSize(12);
        headerView.setText("You have 3 registered events");
        headerView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        calendarDetailsView.addHeaderView(headerView);
        calendarDetailsView.setAdapter(new TestDataAdapter());

        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                final int MIN_DISTANCE = 15;
                if (e2.getY() - e1.getY() > MIN_DISTANCE) {
                    calendarDetailsView.setVisibility(View.GONE);
                } else if (e1.getY() - e2.getY() > MIN_DISTANCE) {
                    calendarDetailsView.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });

        reminderBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        reminderBar.setOnClickListener(new View.OnClickListener() {
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

    private static class TestDataAdapter extends BaseAdapter {

        Map<String, String> eventItems = new LinkedHashMap<String, String>();
        List<String> events = new ArrayList<String>();

        public TestDataAdapter() {
            eventItems.put("Keynote by Xiao", "11:30 AM - 12:00 PM");
            eventItems.put("Mobile Summit", "12:00 PM - 13:00 PM");
            eventItems.put("Lunch", "12:30 PM - 14:00 PM");
            events.add("Keynote by Xiao");
            events.add("Mobile Summit");
            events.add("Lunch");
        }

        @Override
        public int getCount() {
            return events.size();
        }

        @Override
        public Object getItem(int position) {
            return events.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View detailItem;
            if (convertView == null) {
                final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                detailItem = inflater.inflate(R.layout.calendar_detail_item, null);

            } else {
                detailItem = convertView;
            }

            final TextView title = (TextView) detailItem.findViewById(R.id.title);
            title.setText(events.get(position));
            final TextView time = (TextView) detailItem.findViewById(R.id.time);
            time.setText(eventItems.get(events.get(position)));
            return detailItem;
        }
    }
}
