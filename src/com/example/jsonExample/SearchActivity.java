package com.example.jsonExample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.example.jsonExample.model.GiphyGifInfo;
import com.example.jsonExample.model.GiphyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {


    private EditText text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        text = (EditText) findViewById(R.id.text);
        text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(text.getText().toString().trim())) {
                        performSearch(text.getText().toString().trim());
                    } else {
                        text.requestFocus();
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void performSearch(String trim) {
        Intent intent = new Intent();
        intent.putExtra("text", text.getText().toString());
        setResult(RESULT_OK, intent);
        hideKeyboard(this, text.getWindowToken());
        finish();
        showActivityAnimation(SearchActivity.this);
    }

    public static void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    public static void showActivityAnimation(Activity activity) {
        activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }



}
