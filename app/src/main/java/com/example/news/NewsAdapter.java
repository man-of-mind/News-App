package com.example.news;

import android.content.Context;
import android.content.Intent;
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
    private RecyclerView mRecyclerView;

    public NewsAdapter(Context context, ArrayList<News> news, RecyclerView recyclerView) {
        this.mContext = context;
        this.mNews = news;
        this.mRecyclerView = recyclerView;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = mRecyclerView.getChildAdapterPosition(view);
            News selectedNews = mNews.get(position);
            Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
            intent.putExtra("News", selectedNews);
            view.getContext().startActivity(intent);
        }
    };

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsView = inflater.inflate(R.layout.news_list, parent, false);
        newsView.setOnClickListener(mOnClickListener);

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
        String image = news.getImageUrl();
        if(image != null){
            Picasso.with(imageView.getContext()).load(image).error(R.drawable.ic_image).into(imageView);
        }
        else{
            imageView.setImageResource(R.drawable.ic_image);
        }

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
