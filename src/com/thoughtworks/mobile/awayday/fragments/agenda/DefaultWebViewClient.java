package com.thoughtworks.mobile.awayday.fragments.agenda;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DefaultWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
