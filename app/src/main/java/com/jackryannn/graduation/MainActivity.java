package com.jackryannn.graduation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.Image;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @OnClick(R.id.button)
    void ToScannerActivity() {
        startActivity(new Intent(this, Main2Activity.class));
    }

    @OnClick(R.id.button3)
    void ToOrcActivity() {
        startActivity(new Intent(this, OcrActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toast.makeText(MainActivity.this, Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_LONG).show();
    }

}
