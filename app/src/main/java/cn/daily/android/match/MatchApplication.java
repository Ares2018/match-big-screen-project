package cn.daily.android.match;

import android.app.Application;

import cn.daily.android.match.util.ChannelUtil;

public class MatchApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ChannelUtil.setChannel("big");
    }
}
