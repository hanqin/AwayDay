package com.thoughtworks.mobile.awayday.components.html;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.components.EventSelectedHandlerFactory;

public abstract class HtmlFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View resultView = inflater.inflate(R.layout.html_fragment, container, false);
        final WebView webView = (WebView) resultView.findViewById(R.id.web_view);

        webView.setWebViewClient(new DefaultWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new EventSelectedHandlerFactory(getActivity()), "eventHandler");

        webView.loadUrl(getUrl());

        return resultView;
    }

    protected abstract String getUrl();

}
