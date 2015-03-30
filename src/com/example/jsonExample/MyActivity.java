package com.example.jsonExample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.example.jsonExample.model.GiphyGifInfo;
import com.example.jsonExample.model.GiphyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends FragmentActivity {

    private static final String URL = "http://api.giphy.com/v1/gifs/search";
    private static final String REQUEST_STRING = "funny cat";
    private static final int count = 10;
    private int page = 1;

    private PagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.execute();
                page++;
            }
        });

    }

    private class MyTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
                requestParams.add(new BasicNameValuePair("q", REQUEST_STRING));
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
//                showListView(urls);
                showViewPager(urls);
            }
        }

    }

    private void showViewPager(List<String> urls) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

//    private void showListView(List<String> urls) {
//        ListView listView = (ListView) findViewById(R.id.list);
//        MyAdapter adapter = new MyAdapter(this, urls);
//        listView.setAdapter(adapter);
//    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private static final int PAGE_COUNT = 10;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

}
