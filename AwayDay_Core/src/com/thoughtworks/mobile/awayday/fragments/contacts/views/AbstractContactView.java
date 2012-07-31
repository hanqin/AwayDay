package com.thoughtworks.mobile.awayday.fragments.contacts.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import com.thoughtworks.mobile.awayday.R;

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
        LinearLayout backButton = (LinearLayout) view.findViewById(R.id.location_detail_back);
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
}
