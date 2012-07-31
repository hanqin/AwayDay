package com.thoughtworks.mobile.awayday.fragments.contacts.views;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ViewFlipper;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.fragments.contacts.Utils;

public class ContactSettingView extends AbstractContactView {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText skypeEditText;
    private EditText weiboEditText;

    public ContactSettingView(Context context, ViewFlipper viewFlipper) {
        super(context, viewFlipper);
    }

    @Override
    protected void initViewComponents() {
        initBackButton();
        initSaveButton();
        initContactInfos();

    }

    private void initContactInfos() {
        nameEditText = (EditText) view.findViewById(R.id.contact_setting_name_edit_text);
        phoneEditText = (EditText) view.findViewById(R.id.contact_setting_phone_edit_text);
        phoneEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        emailEditText = (EditText) view.findViewById(R.id.contact_setting_email_edit_text);
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        skypeEditText = (EditText) view.findViewById(R.id.contact_setting_skype_edit_text);
        weiboEditText = (EditText) view.findViewById(R.id.contact_setting_weibo_edit_text);
        weiboEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        if (!Utils.isFileNotExist(context)) {
            String contactInfos = Utils.readContactInfos(context);
            if ("" != contactInfos && contactInfos != null) {
                String[] infos = contactInfos.split(":");
                nameEditText.setText(infos[0]);
                phoneEditText.setText(infos[1]);
                emailEditText.setText(infos[2]);
                skypeEditText.setText(infos[3]);
                weiboEditText.setText(infos[4]);
            }
        }
    }

    private void initSaveButton() {
        View save = view.findViewById(R.id.contact_save);
        save.setVisibility(View.VISIBLE);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactInfos = String.format("%s:%s:%s:%s:%s",
                        nameEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        skypeEditText.getText().toString(),
                        weiboEditText.getText().toString());
                Utils.saveContactInfos(context,contactInfos);
                ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                initQrCode(contactInfos, viewFlipper.getChildAt(0));
                viewFlipper.setDisplayedChild(0);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.contact_setting;
    }
}
