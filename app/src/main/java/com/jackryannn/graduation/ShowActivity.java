package com.jackryannn.graduation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowActivity extends AppCompatActivity {
    public static final int FLAG_QR = 0;
    public static final int FLAG_NORMAL = 1;
    public static final int FLAG_OCR = 2;
    private Handler handler;

    @Bind({R.id.imageView})
    ImageView imageView;

    @Bind({R.id.textView})
    TextView textView;
    private String url = "http://apis.baidu.com/apistore/idlocr/ocr";

    private void BitmapProcess(Bitmap bitmap) {
        BitmapFactory.Options Options = new BitmapFactory.Options();
        Options.inSampleSize = 4;
        Options.inJustDecodeBounds = true;
        ByteArrayOutputStream ByteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, ByteArrayOutputStream);
        final String str = Base64.encodeToString(ByteArrayOutputStream.toByteArray(), 2);
        System.out.println(str);
        this.imageView.setImageBitmap(bitmap);
        new Thread(new Runnable() {
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();

                RequestBody builder = new FormEncodingBuilder()
                        .add("fromdevice", "android")
                        .add("clientip", "10.10.10.0")
                        .add("detecttype", "LocateRecognize")
                        .add("languagetype", "CHN_ENG")
                        .add("imagetype", "1")
                        .add("image", str)
                        .build();
                Request request = new Request.Builder()
                        .addHeader("apikey", "a8e0da3977de6d259e082a802035b93c")
                        .post(builder)
                        .url(url)
                        .build();
                okHttpClient.newCall(request)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {
                                String str = response.body().string();
                                System.out.println(str);
                                String retStr  = "";
                                try {
                                    JSONArray jsonArray = new JSONObject(str).getJSONArray("retData");
                                    for(int i =0 ;i< jsonArray.length();i++){
                                        System.out.println(i);
                                        retStr +=  ((JSONObject)jsonArray.get(i)).getString("word");
                                    }
                                } catch (Exception Exception) {
                                    Exception.printStackTrace();
                                }
                                    if(retStr.length()==0){
                                        retStr = "无返回结果";
                                    }
                                    Message message = Message.obtain();
                                    message.obj = retStr;
                                    handler.sendMessage(message);

                            }
                        });
            }
        }).start();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                textView.setText((String)msg.obj);
            }
        };
        Bundle bundle = getIntent().getExtras();
        switch (((Integer) bundle.get("flag"))) {
            case FLAG_QR:
                textView.setText((String) bundle.get("data"));
                break;
            case FLAG_NORMAL:
                imageView.setImageBitmap((Bitmap) bundle.get("data"));
                break;
            case FLAG_OCR:
                Bitmap bitmap = (Bitmap) bundle.get("data");
                BitmapProcess(bitmap);
                break;

        }
    }
}

//                    this.handler = new Handler(this)
//                    {
//                        public void handleMessage()
//                        {
//                            super.handleMessage(paramMessage);
//                            this.this$0.textView.setText((String)paramMessage.obj);
//                        }
//                    };
//                    return;
//                    this.textView.setText((String)localBundle.get("data"));
//                }
//                this.imageView.setImageBitmap((Bitmap)localBundle.get("data"));
//            }
//            Bitmap localBitmap = (Bitmap)localBundle.get("data");
//            this.imageView.setImageBitmap(localBitmap);
//            BitmapProcess(localBitmap);
