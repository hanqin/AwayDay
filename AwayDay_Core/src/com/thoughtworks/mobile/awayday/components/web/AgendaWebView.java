package com.thoughtworks.mobile.awayday.components.web;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.thoughtworks.mobile.awayday.fragments.agenda.EventSelectedHandlerFactory;

public class AgendaWebView extends WebView {

    public AgendaWebView(Context context) {
        super(context);
        initUi();
    }

    public AgendaWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi();
    }

    private void initUi() {
        setWebViewClient(new DefaultWebViewClient());
        getSettings().setJavaScriptEnabled(true);
        addJavascriptInterface(new EventSelectedHandlerFactory(getContext()), "eventHandler");

        loadUrl("file:///android_asset/agenda/index.html");
    }
}
