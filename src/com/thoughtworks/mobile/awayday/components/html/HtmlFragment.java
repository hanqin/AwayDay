package com.thoughtworks.mobile.awayday.components.html;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.thoughtworks.mobile.awayday.R;
import com.thoughtworks.mobile.awayday.components.EventSelectedHandlerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class HtmlFragment extends Fragment {

    private ListView weiboListView;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View resultView = inflater.inflate(R.layout.html_fragment, container, false);
        final WebView webView = (WebView) resultView.findViewById(R.id.web_view);
        initWebView(webView);
        weiboListView = (ListView) resultView.findViewById(R.id.weibo_list);
        initWeiboView(weiboListView);

        return resultView;
    }

    @Override
    public void onResume() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                final int count = weiboListView.getCount();
                weiboListView.smoothScrollToPosition(i++ % count);
            }
        }, 3000, 3000);
        super.onResume();
    }

    @Override
    public void onPause() {
        timer.cancel();
        timer = null;
        super.onPause();
    }

    private void initWeiboView(ListView weiboListView) {
        final ArrayList<String> words = new ArrayList<String>();
        words.add("@_QinHan: Still working on Away Day app");
        words.add("@AngryBird: Give my eggs back, you @pigs !!!!!!!!!!!!!!!!!!!");
        words.add("@OutMan said, it's a very difficult to do android development, I don't know Java.");
        words.add("@Plants shouted 'Come on Zombies!'");
        words.add("@Android said to @IOS, let's fight, On the highest roof of of the forbidden city.");
        words.add("@IOS, zzz, ZZZ.");
        words.add("@TWers Are your ready for the away day?");
        words.add("@WindowsPhone:, @Nokia, 886");

        weiboListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return words.size();
            }

            @Override
            public Object getItem(int position) {
                return words.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final TextView textView = new TextView(parent.getContext());
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setText(words.get(position));
                textView.setLineSpacing(2f, 1f);
                textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                return textView;
            }
        });
    }

    private void initWebView(WebView webView) {
        webView.setWebViewClient(new DefaultWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new EventSelectedHandlerFactory(getActivity()), "eventHandler");

        webView.loadUrl(getUrl());
    }

    protected abstract String getUrl();

}
