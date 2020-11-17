package com.example.news;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopStoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopStoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProgressBar mLoadingProgress;
    private RecyclerView mRecyclerView;
    private TextView mError;

    public TopStoryFragment() {
        // Required empty public constructor
    }

    //    private static final String REQUEST = BASE_API + API_KEY;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopStoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopStoryFragment newInstance(String param1, String param2) {
        TopStoryFragment fragment = new TopStoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingProgress = rootView.findViewById(R.id.news_loading);
        mRecyclerView = rootView.findViewById(R.id.news_recycler);
        mError = rootView.findViewById(R.id.news_error);

        LinearLayoutManager musicLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager
                .VERTICAL, false);
        mRecyclerView.setLayoutManager(musicLayoutManager);

        URL url = null;
        final String KEY = "apikey";
        final String QUERY_PARAMETER = "country";
        final String COUNTRY = "ng";
        final String BASE_API = "https://newsapi.org/v2/top-headlines";
        final String API_KEY = "ed61cc66f42d4fc484e66103d605ad62";
        Uri uri = Uri.parse((BASE_API)).buildUpon().appendQueryParameter(QUERY_PARAMETER, COUNTRY)
                .appendQueryParameter(KEY, API_KEY).build();
        Log.e(TopStoryFragment.class.getSimpleName(), "uri = " + uri );
        try {
//            url = new URL(REQUEST);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new NewsAsyncTask().execute(url);

        return rootView;
    }

    private class NewsAsyncTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String jsonResponse = null;
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (TextUtils.isEmpty(result)){
                mRecyclerView.setVisibility(View.INVISIBLE);
                mError.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Error retrieving data from the internet", Toast.LENGTH_LONG).show();
            }
            else{
                mError.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                ArrayList<News> news = getNewsFromJson(result);



                String resultString = "";
                NewsAdapter adapter = new NewsAdapter(getContext(), news);
                mRecyclerView.setAdapter(adapter);
            }
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            if (url != null) {
                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;
                try {
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setConnectTimeout(15000);
                    urlConnection.connect();
                    if (urlConnection.getResponseCode() == 200) {
                        inputStream = urlConnection.getInputStream();
                        jsonResponse = readFromStream(inputStream);
                    }
                    else{
                        Log.e(TopStoryFragment.class.getSimpleName(), "Error response code " + urlConnection.getResponseCode());
                    }
                } catch (IOException e) {
                    assert urlConnection != null;
                    Log.e(TopStoryFragment.class.getSimpleName(), "Error response code " + urlConnection.getResponseCode());
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null){
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();

        }
    }

    private ArrayList<News> getNewsFromJson(String json) {
        final String TITLE = "title";
        final String ARTICLE = "articles";
        final String SOURCE = "source";
        final String PUBLISHED_DATE = "publishedAt";
        final String NAME = "name";
        final String IMAGE = "urlToImage";
        final String CONTENT = "content";

        ArrayList<News> news = new ArrayList<News>();
        try{
            JSONObject jsonNews = new JSONObject(json);
            JSONArray arrayNews = jsonNews.getJSONArray(ARTICLE);
            int numberOfNews = arrayNews.length();
            for(int i = 0; i < numberOfNews; i++){
                JSONObject newsJSON = arrayNews.getJSONObject(i);
                JSONObject sourceInfo = newsJSON.getJSONObject(SOURCE);
                String newsTitle = newsJSON.getString(TITLE);
                String image = null;
                if(newsJSON.has(IMAGE)){
                    image = newsJSON.getString(IMAGE);
                }
                String content = newsJSON.getString(CONTENT);
                String publishedDate = newsJSON.getString(PUBLISHED_DATE);
                News news1 = new News(
                        newsTitle,
                        image,
                        sourceInfo.getString(NAME),
                        content, publishedDate);
                news.add(news1);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return news;
    }
}