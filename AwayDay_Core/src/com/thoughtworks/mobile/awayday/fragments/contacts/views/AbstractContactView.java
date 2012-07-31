package com.thoughtworks.mobile.awayday.fragments.contacts.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import com.google.zxing.WriterException;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.fragments.contacts.Utils;

public abstract class AbstractContactView {

    protected Context context;
    protected View view;
    protected ViewFlipper viewFlipper;

    public AbstractContactView(Context context, ViewFlipper viewFlipper){
        this.context = context;
        this.viewFlipper = viewFlipper;
        addView(getLayoutId());
        initViewComponents();
    }
    public View getView(){
        return view;
    }

    protected abstract void initViewComponents();

    protected abstract int getLayoutId();

    private void addView(int layout) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = layoutInflater.inflate(layout, null);
    }
    protected void initBackButton() {
        LinearLayout backButton = (LinearLayout) view.findViewById(R.id.contact_back);
        backButton.setClickable(true);

        View.OnClickListener detailBackOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setDisplayedChild(0);
            }
        };
        backButton.setOnClickListener(detailBackOnClickListener);
        View back = view.findViewById(R.id.location_detail_btn_back);
        back.setOnClickListener(detailBackOnClickListener);
    }

    protected void initQrCode(String contactInfo, View contactMainView) {
        ImageView codeImageView = (ImageView) contactMainView.findViewById(R.id.qr_code_image_view);
        ViewGroup.LayoutParams layoutParams = codeImageView.getLayoutParams();
        layoutParams.height = 300;
        layoutParams.width = 300;
        try {
            codeImageView.setImageBitmap(Utils.create2DCode(contactInfo));
        } catch (WriterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
