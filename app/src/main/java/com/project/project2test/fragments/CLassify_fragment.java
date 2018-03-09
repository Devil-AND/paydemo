package com.project.project2test.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.project2test.R;
import com.project.project2test.customview.GramophoneView;

/**
 * Author:AND
 * Time:2018/2/26.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */


public class CLassify_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.classify_fragment, container, false);
        
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final GramophoneView gramophoneView = getActivity().findViewById(R.id.gramophone_view);
        gramophoneView.setDiskRotateSpeed(5);
        final Button button = (Button) getActivity().findViewById(R.id.btn_play_pause);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gramophoneView.getPlaying()) {
                    button.setText("点击播放");
                } else {
                    button.setText("点击暂停");
                }
                gramophoneView.setPlaying(!gramophoneView.getPlaying());
            }
        });
    }
}
