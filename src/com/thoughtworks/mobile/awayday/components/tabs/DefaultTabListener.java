package com.thoughtworks.mobile.awayday.components.tabs;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.thoughtworks.mobile.awayday.R;

public class DefaultTabListener implements ActionBar.TabListener {

    private Activity activity;
    private String tag;
    private Fragment fragment;
    private String className;
    private Bundle args;

    public DefaultTabListener(Activity activity, String tag, String className) {
        this(activity, tag, className, null);
    }

    public DefaultTabListener(Activity activity, String tag, String className, Bundle args) {
        this.activity = activity;
        this.tag = tag;
        this.className = className;
        this.args = args;

        fragment = activity.getFragmentManager().findFragmentByTag(this.tag);
        if (fragment != null && !fragment.isDetached()) {
            final FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
            fragmentTransaction.detach(fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (fragment == null) {
            fragment = Fragment.instantiate(activity, className, args);
            fragmentTransaction.replace(R.id.details, fragment, tag);
        } else {
            fragmentTransaction.attach(fragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        if (fragment == null) return;
        fragmentTransaction.detach(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
}
