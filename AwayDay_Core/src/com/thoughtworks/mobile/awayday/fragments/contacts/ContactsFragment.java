package com.thoughtworks.mobile.awayday.fragments.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.fragments.contacts.views.ContactMainView;
import com.thoughtworks.mobile.awayday.fragments.contacts.views.ContactScanView;
import com.thoughtworks.mobile.awayday.fragments.contacts.views.ContactSettingView;

public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View pathView = inflater.inflate(R.layout.contacts, null);
        final ViewFlipper viewFilpper = (ViewFlipper) pathView.findViewById(R.id.viewFlipper);

        ContactMainView contactMainView = new ContactMainView(getActivity(), viewFilpper);
        viewFilpper.addView(contactMainView.getView());
        ContactSettingView contactSettingView = new ContactSettingView(getActivity(), viewFilpper);
        viewFilpper.addView(contactSettingView.getView());
        ContactScanView contactScanView = new ContactScanView(getActivity(), viewFilpper);
        viewFilpper.addView(contactScanView.getView());


        if(Utils.isFileNotExist(getActivity())){
            viewFilpper.setDisplayedChild(1);
        }
        return pathView;
    }
}
