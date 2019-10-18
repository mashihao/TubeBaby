package com.shsy.tubebaby.view.bannerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shsy.tubebaby.R;
import com.shsy.tubebaby.model.Banner;


/**
 * @author 小米Xylitol
 * @email xiaomi987@hotmail.com
 * @desc Banner子项帮助类
 * @date 2018-06-06 14:09
 */
public class BannerViewHolder implements BaseBannerViewHolder<Banner>{

    private Context context;
    private TextView tvBanner;
    private ImageView ivBanner;

    public BannerViewHolder(Context context) {
        this.context = context;
    }

    public View createView() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner,null,false);
        ivBanner = view.findViewById(R.id.iv_banner);
        return view;
    }

    public void bind(int position, Banner s) {

    }
}
