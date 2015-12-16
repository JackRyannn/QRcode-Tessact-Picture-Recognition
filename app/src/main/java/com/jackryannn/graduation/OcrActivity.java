package com.jackryannn.graduation;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OcrActivity extends Activity {
    @OnClick(R.id.takepic)
    void takepic() {
//        Intent intent = new Intent(this,CameraActivity.class);
//        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.pickpic)
    void pickpic() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Bind(R.id.tessResults)
    TextView tessResults;
    @Bind(R.id.imageView)
    ImageView imageView;
    private static final String TESSBASE_PATH = Environment.getExternalStorageDirectory().toString();
    private static final String CHINESE_LANGUAGE = "chi_sim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orc);
        ButterKnife.bind(this);
        tessResults.setText("这是用了Tesseract OCR，需要下载中文语言包在sdcard里（附件中，需下载到android的getExternalStorageDirectory()/tessact这个的路径，不同手机返回的不一定相同，如果不行下次我来演示，大约40M，只能识别中文，或者英文，不能识别中英混排，目前是识别的中文，而且识别速度很慢，大约30s到两分钟不等（华为荣耀6）），虽然他没有对图片的大小有限制，但是拍照后的图片未经压缩仍然有很大几率识别不出来，建议拍照后截屏从相册上传");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = null;
        if (requestCode == 1) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 0) {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");

        }
        imageView.setImageBitmap(bitmap);
        tessResults.setText("识别中");
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(bitmap);
    }

    class MyAsyncTask extends AsyncTask<Bitmap, Integer, String> {

        @Override
        protected String doInBackground(Bitmap... params) {
            TessBaseAPI baseApi = new TessBaseAPI();
            baseApi.init(TESSBASE_PATH, CHINESE_LANGUAGE);
            baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_AUTO);
            baseApi.setImage(params[0]);
            String outputText = baseApi.getUTF8Text();
            if (outputText == null) {
                outputText = "输出值为空";
            }
            baseApi.end();

            return outputText;
        }

        @Override
        protected void onPostExecute(String s) {
            tessResults.setText(s);
            super.onPostExecute(s);
        }

    }
}