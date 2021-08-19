package com.example.firstcode.fourth_chapter.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstCode.R;

/**
 * 说明：$
 * <p>
 * date: 2019-11-27 20:42
 *
 * @author syd
 * @version 1.0
 */
public class NesContentFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout = view.findViewById(R.id.visible_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView  tvContent = view.findViewById(R.id.tv_content);
        tvTitle.setText(newsTitle);
        tvContent.setText(newsContent);
    }
}
