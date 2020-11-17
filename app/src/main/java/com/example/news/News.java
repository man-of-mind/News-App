package com.example.news;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class News implements Parcelable {
    private String mTitle;
    private String mImageUrl;
    private String mContent;
    private String mSource;
    private String mPublishedDate;

    public News(String title, String imageUrl, String source, String content, String publishedDate){
        this.mTitle = title;
        this.mImageUrl = imageUrl;
        this.mSource = source;
        this.mContent= content;
        this.mPublishedDate = publishedDate;
    }

    protected News(Parcel in) {
        mTitle = in.readString();
        mImageUrl = in.readString();
        mSource = in.readString();
        mContent = in.readString();
        mPublishedDate = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel parcel) {
            return new News(parcel);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public String getContent() {
        return mContent;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public String getSource() {
        return mSource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mImageUrl);
        parcel.writeString(mSource);
        parcel.writeString(mContent);
        parcel.writeString(mPublishedDate);

    }

    @BindingAdapter({"android:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl){
        if(!imageUrl.isEmpty()) {
            Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.ic_image).into(view);
        }
        else{
            view.setBackgroundResource(R.drawable.ic_image);
        }
    }
}
