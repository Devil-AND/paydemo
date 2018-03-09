package com.project.zhengpengfei20180306;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.project.zhengpengfei20180306.customview.GramophoneView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private GramophoneView mGramophoneview;
    private Button mClickmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();


    }

    private void initView() {
        mGramophoneview = (GramophoneView) findViewById(R.id.gramophoneview);
        mClickmusic = (Button) findViewById(R.id.clickmusic);
        mClickmusic.setOnClickListener(this);
        mGramophoneview.setDiskRotateSpeed(3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickmusic:
                if (mGramophoneview.getPlaying()) {
                    mClickmusic.setText("点击播放");
                } else {
                    mClickmusic.setText("点击暂停");
                }
                mGramophoneview.setPlaying(!mGramophoneview.getPlaying());
                break;
            default:
                break;
        }
    }
}
