package com.example.admin.pdd.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.pdd.R;
import com.example.admin.pdd.pattern.Singleton;

import java.io.ByteArrayOutputStream;

public class BaseActivity extends FragmentActivity {
    protected Singleton singleton = Singleton.getInstance();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.back_to_main:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setViewVisibility(Bundle savedInstanceState, String parameter, View view) {
        switch (savedInstanceState.getInt(parameter)) {

            case View.VISIBLE:
                view.setVisibility(View.VISIBLE);
                break;

            case View.INVISIBLE:
                view.setVisibility(View.INVISIBLE);
                break;

            case View.GONE:
                view.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }

    public byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public Bitmap getBitmapFromByteArray(byte[] bitmap) {
        return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
    }
}
