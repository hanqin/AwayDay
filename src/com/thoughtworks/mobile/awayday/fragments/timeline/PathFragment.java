package com.thoughtworks.mobile.awayday.fragments.timeline;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.thoughtworks.mobile.awayday.EventDetailsActivity;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.fragments.hotel.HotelFragment;
import com.thoughtworks.mobile.awayday.fragments.hotel.ImageAdapter;
import com.thoughtworks.mobile.awayday.util.FragmentConstants;

import java.util.ArrayList;
import java.util.List;

public class PathFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View pathView = inflater.inflate(R.layout.path_screen, null);
        ListView activityHistory = (ListView) pathView.findViewById(R.id.activity_history);
        activityHistory.setAdapter(new ActivityHistoryAdapter());

        return pathView;
    }

    private static class ActivityHistoryAdapter extends BaseAdapter {
        private List<String> activities = new ArrayList<String>();

        public ActivityHistoryAdapter() {
            activities.add("Han exchanged contact with Robert Carlos");
            activities.add("Han took a nice photo");
            activities.add("Oh, beautiful weather!");
            activities.add("Han checked in to the Hotel");
            activities.add("Han joined away day.");
        }

        @Override
        public int getCount() {
            return activities.size();
        }

        @Override
        public Object getItem(int position) {
            return activities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View itemView;
            if (convertView == null) {
                final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = inflater.inflate(R.layout.activity_history_item_with_gallery, null);
            } else {
                itemView = convertView;
            }

            final TextView title = (TextView) itemView.findViewById(R.id.activity_title);
            title.setText(activities.get(position));
            final Gallery gallery = (Gallery) itemView.findViewById(R.id.activity_images);
            gallery.setVisibility(View.GONE);
            itemView.setOnClickListener(null);

            if (position == 1) {
                gallery.setVisibility(View.VISIBLE);
                gallery.setAdapter(new TimeLineImageAdapter());
                gallery.setSelection(activities.size() - 1);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Intent intent = new Intent();
                        intent.setClass(v.getContext(), EventDetailsActivity.class);
                        intent.putExtra(FragmentConstants.FRAGMENT_CLASS, HotelFragment.class.getName());
                        v.getContext().startActivity(intent);
                    }
                });
            }
            return itemView;
        }
    }
}
