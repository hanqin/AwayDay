package com.thoughtworks.mobile.awayday.fragments.agenda;

import com.thoughtworks.mobile.awayday.components.html.HtmlFragment;

public class HtmlAgendaFragment extends HtmlFragment {
    @Override
    protected String getUrl() {
        return "file:///android_asset/agenda/index.html";
    }
}
