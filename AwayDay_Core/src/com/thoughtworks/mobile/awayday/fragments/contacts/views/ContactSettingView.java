package com.thoughtworks.mobile.awayday.fragments.contacts.views;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ViewFlipper;
import com.thoughtworks.mobile.awayday.R;

public class ContactSettingView extends AbstractContactView {

    public ContactSettingView(Context context, ViewFlipper viewFlipper) {
        super(context, viewFlipper);
    }

    @Override
    protected void initViewComponents() {
        initBackButton();
        EditText nameEditText = (EditText) view.findViewById(R.id.contact_setting_name_edit_text);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.contact_setting;
    }
}
