package com.thoughtworks.mobile.awayday.components.list;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.thoughtworks.mobile.awayday.util.FragmentConstants.WEIBO_QUERY_INTERVAL;

public class WeiboView extends ListView {

    private Timer timer;

    public WeiboView(Context context) {
        super(context);
        initUI();
    }

    public WeiboView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    private void initUI() {
        final ArrayList<String> words = new ArrayList<String>();
        words.add("@_QinHan: Still working on Away Day app");
        words.add("@AngryBird: Give my eggs back, you @pigs !!!!!!!!!!!!!!!!!!!");
        words.add("@OutMan said, it's a very difficult to do android development, I don't know Java.");
        words.add("@Plants shouted 'Come on Zombies!'");
        words.add("@Android said to @IOS, let's fight, On the highest roof of of the forbidden city.");
        words.add("@IOS, zzz, ZZZ.");
        words.add("@TWers Are your ready for the away day?");
        words.add("@WindowsPhone:, @Nokia, 886");

        setAdapter(new BaseAdapter() {
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
                textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                return textView;
            }
        });
    }

    public void startAutoRefresh() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                smoothScrollToPosition(i++ % getCount());
            }
        }, WEIBO_QUERY_INTERVAL, WEIBO_QUERY_INTERVAL);
    }

    public void cancelAutoRefresh() {
        this.timer.cancel();
        this.timer = null;
    }
}
