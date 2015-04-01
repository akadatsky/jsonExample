package com.example.jsonExample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.example.jsonExample.model.GiphyGifInfo;
import com.example.jsonExample.model.GiphyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends FragmentActivity {

    public static final int REQUEST_CODE_SEARCH = 1;

    private static final String URL = "http://api.giphy.com/v1/gifs/search";
    private static String requestString = "funny cat";
    private static final int count = 10;
    private int page = 1;

    private MyFragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SEARCH);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            requestString = data.getStringExtra("text");
            MyTask task = new MyTask();
            task.execute();
        }
    }

    private class MyTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
                requestParams.add(new BasicNameValuePair("q", requestString));
                requestParams.add(new BasicNameValuePair("api_key", "dc6zaTOxFJmzC"));
                requestParams.add(new BasicNameValuePair("limit", String.valueOf(count)));
                requestParams.add(new BasicNameValuePair("offset", String.valueOf(page)));
                return WebUtil.getRequest(URL, requestParams);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonString) {
            if (!TextUtils.isEmpty(jsonString)) {
                Log.i("MyActivityTag", jsonString);

                Gson gson = new GsonBuilder().create();

                // parse json:
                GiphyResponse response = gson.fromJson(jsonString, GiphyResponse.class);

                List<String> urls = new ArrayList<String>();
                for (GiphyGifInfo gitInfo : response.getData()) {
                    urls.add(gitInfo.getImages().getOriginal().getUrl());
                }
                Log.i("MyActivityTag", "urls count:" + urls.size());
                showViewPager(urls);
            }
        }

    }

    private void showViewPager(List<String> urls) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), urls);
        viewPager.setAdapter(pagerAdapter);
    }


}
