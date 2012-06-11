package com.thoughtworks.mobile.awayday.fragments.miniawayday;

import com.thoughtworks.mobile.awayday.components.html.HtmlFragment;

public class MiniAwayDayFragment extends HtmlFragment {
    @Override
    protected String getUrl() {
        return "file:///android_asset/mini/index.html";
    }
}
