package com.thoughtworks.mobile.awayday.fragments.contacts.views;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.fragments.contacts.Utils;

public class ContactMainView extends AbstractContactView {
    public ContactMainView(Context context, ViewFlipper viewFlipper) {
        super(context, viewFlipper);

    }

    @Override
    protected void initViewComponents() {
        initButtons();
        if(!Utils.isFileNotExist(context)){
            initQrCode(Utils.readContactInfos(context), view);
        }
    }

    private void initButtons() {
        Button contactSetting = (Button) view.findViewById(R.id.contacts_setting);
        contactSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setDisplayedChild(1);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.contact_main;
    }
}
