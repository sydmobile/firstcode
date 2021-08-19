package com.example.firstcode.fourth_chapter.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstCode.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：$
 * <p>
 * date: 2019-11-27 20:58
 *
 * @author syd
 * @version 1.0
 */
public class NewsTitleFrament extends Fragment {
    private boolean isTwoPane;
    private List<News> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.news_title_frag,container,false);
        list = new ArrayList<>();
        for (int i=0;i<20;i++){
            News news = new News();
            news.setContent("Content"+i);
            news.setTitle("title"+i);
            list.add(news);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView recyclerView = view.findViewById(R.id.rlv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NesAdapter());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.news_content_framelayout) != null;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.news_title);
        }
    }

    class NesAdapter extends RecyclerView.Adapter<ViewHolder>{
        public NesAdapter(){

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = list.get(viewHolder.getAdapterPosition());
                    if (isTwoPane){
                        NesContentFragment contentFragment = (NesContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        contentFragment.refresh(news.getTitle(),news.getContent());

                    }else {
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }

                }
            });

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tvTitle.setText(list.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
