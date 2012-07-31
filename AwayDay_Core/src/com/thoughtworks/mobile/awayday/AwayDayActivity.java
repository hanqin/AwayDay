package com.thoughtworks.mobile.awayday;

import android.app.ActionBar;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.thoughtworks.mobile.awayday.fragments.agenda.AgendaFragment;
import com.thoughtworks.mobile.awayday.components.tabs.DefaultTabListener;
import com.thoughtworks.mobile.awayday.fragments.contacts.ContactsFragment;
import com.thoughtworks.mobile.awayday.fragments.timeline.PathFragment;

public class AwayDayActivity extends MapActivity {

    private MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mapView = new MapView(this, getString(R.string.google_map_api_key));
        setContentView(R.layout.main_screen);

        final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);


        final String fragmentName = AgendaFragment.class.getName();
        actionBar.addTab(actionBar.newTab().setText("Agenda").setTabListener(new DefaultTabListener(this, fragmentName, fragmentName)));

        final String pathFragmentClass = PathFragment.class.getName();
        actionBar.addTab(actionBar.newTab().setText("Path").setTabListener(new DefaultTabListener(this, pathFragmentClass, pathFragmentClass)));

        String contactsFragmentClass = ContactsFragment.class.getName();
        actionBar.addTab(actionBar.newTab().setText("Contact").setTabListener(new DefaultTabListener(this, contactsFragmentClass, contactsFragmentClass)));
    }

    public MapView getMapView() {
        return mapView;
    }

    protected boolean isRouteDisplayed() {
        return false;
    }

}
