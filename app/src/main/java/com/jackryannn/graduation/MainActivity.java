package com.jackryannn.graduation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.img_upload)
    ImageView img_upload;

    @OnClick(R.id.button2)
    void ToUploadImage() {
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 1);
        startActivity(new Intent(this, CameraActivity.class));
    }

    @OnClick(R.id.button)
    void ToScannerActivity() {
        startActivity(new Intent(this, ScannerActivity.class));
    }

    @OnClick(R.id.button3)
    void ToOrcActivity() {
        startActivity(new Intent(this, OcrActivity.class));
    }

    @OnClick(R.id.button4)
    void ToOrc2Activity() {
        startActivity(new Intent(this, Ocr2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toast.makeText(MainActivity.this, Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode);
        if (requestCode == 1) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            img_upload.setImageBitmap(bitmap);
        }

    }
}
