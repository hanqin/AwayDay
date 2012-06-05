package com.thoughtworks.mobile.awayday.fragments.map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HotelLocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final LinearLayout linearLayout = new LinearLayout(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setText("Hello Hotel");

        linearLayout.addView(textView);
        return linearLayout;
    }
}
