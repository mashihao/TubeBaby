package com.shsy.tubebaby.activity;

import android.os.Bundle;

import com.shsy.tubebaby.fragment.HomeFragment;
import com.shsy.tubebaby.R;
import com.shsy.tubebaby.model.MessageItem;
import com.shsy.tubebaby.model.MessageModel;
import com.shsy.tubebaby.network.RetrofitActivity;
import com.shsy.tubebaby.widget.BottomBar;
import com.shsy.tubebaby.fragment.MineFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends RetrofitActivity {

    @BindView(R.id.bottom_main)
    BottomBar bottomMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomMain.setContainer(R.id.fl_main).setTitleBeforeAndAfterColor("#333333", "#333333")
                .addItem(HomeFragment.class,
                        "首页",
                        R.mipmap.icon_home_normal,
                        R.mipmap.icon_home_press)
                .addItem(MineFragment.class,
                        "我的",
                        R.mipmap.icon_mine_normal,
                        R.mipmap.icon_mine_press)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void OnCustomEvent(MessageModel messageModel) {
        messageModel= EventBus.getDefault().getStickyEvent(MessageModel.class);
        if(messageModel!=null) {
            MessageItem messageItem = messageModel.messageItem;
            if (messageItem.getType()==1){
                MainActivity.this.finish();
            }
            EventBus.getDefault().removeStickyEvent(messageModel);
        }
    }
}
