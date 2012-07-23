package com.thoughtworks.mobile.awayday.fragments.agenda;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.components.list.WeiboView;
import com.thoughtworks.mobile.awayday.components.web.AgendaWebView;

public class AgendaFragment extends Fragment {

    private AgendaWebView webView;
    private WeiboView weiboView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View resultView = inflater.inflate(R.layout.html_fragment, container, false);
        webView = (AgendaWebView) resultView.findViewById(R.id.web_view);
        weiboView = (WeiboView) resultView.findViewById(R.id.weibo_list);

        return resultView;
    }

    @Override
    public void onResume() {
        weiboView.startAutoRefresh();
        super.onResume();
    }

    @Override
    public void onPause() {
        weiboView.cancelAutoRefresh();
        super.onPause();
    }

}
