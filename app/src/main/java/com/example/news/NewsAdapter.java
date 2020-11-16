package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<News> mNews;
    Context mContext;

    public NewsAdapter(Context context, ArrayList<News> news) {
        this.mContext = context;
        this.mNews = news;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsView = inflater.inflate(R.layout.news_list, parent, false);
//        musicView.setOnClickListener(mOnClickListener);

        return new NewsViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {

        News news = mNews.get(position);

        TextView textView = holder.title;
        textView.setText(news.getTitle());
        TextView textView1 = holder.source;
        textView1.setText(news.getSource());
        TextView textView2 = holder.publishedDate;
        textView2.setText(news.getPublishedDate());
        ImageView imageView = holder.image;
        Picasso.with(imageView.getContext()).load(news.getImageUrl()).error(R.drawable.ic_image).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView source;
        public TextView publishedDate;
        public ImageView image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            source = (TextView) itemView.findViewById(R.id.source);
            publishedDate = (TextView) itemView.findViewById(R.id.publishedDate);
            image = (ImageView) itemView.findViewById(R.id.imageUrl);
        }
    }
}
