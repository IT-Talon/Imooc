package com.hzcwtech.imooc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hzcwtech.imooc.R;
import com.hzcwtech.imooc.utils.CommonUtil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Nathen
 * On 2016/04/22 00:54
 */
public class JCVideoPlayerStandardShowShareButtonAfterFullscreen extends JCVideoPlayerStandard {

    public ImageView shareButton;
    public ImageView clearButton;
    private ButtonClickListener clickListener;

    public JCVideoPlayerStandardShowShareButtonAfterFullscreen(Context context) {
        super(context);
    }

    public JCVideoPlayerStandardShowShareButtonAfterFullscreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        shareButton = (ImageView) findViewById(R.id.share);
        clearButton = (ImageView) findViewById(R.id.clear);
        shareButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_standard_with_share_button;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (clickListener != null) {
            clickListener.OnClick(v);
        }
        /*switch (v.getId()) {
            case R.id.clear:
                Toast.makeText(getContext(), "Whatever the icon means", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(getContext(), "Whatever the icon means", Toast.LENGTH_SHORT).show();
                break;
        }*/
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            shareButton.setVisibility(View.INVISIBLE);
            clearButton.setVisibility(View.INVISIBLE);
        } else {
            shareButton.setVisibility(View.VISIBLE);
            clearButton.setVisibility(View.VISIBLE);
        }
    }

    public void setClickListener(ButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ButtonClickListener {
        void OnClick(View view);
    }
}
