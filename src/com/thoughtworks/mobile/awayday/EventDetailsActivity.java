package com.thoughtworks.mobile.awayday;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import com.thoughtworks.mobile.awayday.util.FragmentConstants;

public class EventDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        final String fragmentClass = getIntent().getStringExtra(FragmentConstants.FRAGMENT_CLASS);

        if (fragmentClass == null || "".equalsIgnoreCase(fragmentClass)) throw new IllegalArgumentException(FragmentConstants.FRAGMENT_CLASS + " not set");

        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();

        Log.d("EventDetails", "Display fragment " + fragmentClass);
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentClass);
        if (fragment != null && !fragment.isDetached()) return;
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fragmentClass, null);
            transaction.replace(R.id.details, fragment, fragmentClass);
        } else {
            transaction.attach(fragment);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
