package com.thoughtworks.mobile.awayday;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import com.thoughtworks.mobile.awayday.fragments.agenda.HtmlAgendaFragment;
import com.thoughtworks.mobile.awayday.fragments.map.HotelFragment;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_screen);

        final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);

        final String fragmentName = HtmlAgendaFragment.class.getName();
        actionBar.addTab(actionBar.newTab().setText("Agenda").setTabListener(new DefaultTabListener(this, fragmentName, fragmentName)));

        final String hotelTabClass = HotelFragment.class.getName();
        actionBar.addTab(actionBar.newTab().setText("Hotel").setTabListener(new DefaultTabListener(this, hotelTabClass, hotelTabClass)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public static class DefaultTabListener implements ActionBar.TabListener {

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
}
