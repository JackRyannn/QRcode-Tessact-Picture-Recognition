//package com.jackryannn.graduation;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.hardware.Camera;
//import android.hardware.Camera.AutoFocusCallback;
//import android.hardware.Camera.Parameters;
//import android.hardware.Camera.PictureCallback;
//import android.hardware.Camera.ShutterCallback;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.v7.app.AppCompatActivity;
//import android.view.SurfaceHolder;
//import android.view.SurfaceHolder.Callback;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class CameraActivity extends AppCompatActivity
//{
//
//    @Bind(R.id.surfaceView)
//    SurfaceView surfaceView;
//
//    @Bind({2131492944})
//    RelativeLayout relativeLayout;
//
//    @Bind({2131492946})
//    Button button_takepic;
//    private SurfaceHolder holder;
//    private Camera camera;
//    private Camera.PictureCallback myPictureCallback;
//    private Camera.ShutterCallback myShutterCallback;
//
//    @OnClick({2131492945})
//    void AutoFocus()
//    {
//        this.surfaceView.setOnClickListener(new View.OnClickListener(this)
//        {
//            public void onClick() {
//                CameraActivity.access$000(this.this$0).autoFocus(null);
//            }
//        });
//    }
//
//    @OnClick({2131492946})
//    void TakePic() {
//        Camera.Parameters parameters = this.camera.getParameters();
//        parameters.setPictureSize(720, 1280);
//        parameters.setPictureFormat(256);
//        parameters.setFocusMode("auto");
//        this.camera.autoFocus(new Camera.AutoFocusCallback(this)
//        {
//            public void onAutoFocus(, Camera camera) {
//                if (success)
//                    camera.takePicture(null, null, CameraActivity.access$100(this.this$0));
//            }
//        });
//    }
//
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(2130968601);
//        ButterKnife.bind(this);
//        this.holder = this.surfaceView.getHolder();
//        this.holder.addCallback(new SurfaceHolder.Callback(this)
//        {
//            public void surfaceCreated() {
//                CameraActivity.access$200(this.this$0);
//            }
//
//            public void surfaceChanged(, int format, int width, int height)
//            {
//                CameraActivity.access$000(this.this$0).stopPreview();
//                CameraActivity.access$000(this.this$0).startPreview();
//            }
//
//            public void surfaceDestroyed()
//            {
//                if (CameraActivity.access$000(this.this$0) != null)
//                    CameraActivity.access$300(this.this$0);
//            }
//
//        });
//        FrameView frameView = new FrameView(this);
//        this.relativeLayout.addView(frameView);
//        this.myShutterCallback = new Camera.ShutterCallback(this)
//        {
//            public void onShutter()
//            {
//            }
//        };
//        this.myPictureCallback = new Camera.PictureCallback(this)
//        {
//            public void onPictureTaken(, Camera camera) {
//                Bitmap b = null;
//                if (null != data) {
//                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/temp.jpg");
//                    try {
//                        FileOutputStream fileOutputStream = new FileOutputStream(file);
//                        fileOutputStream.write(data);
//                        fileOutputStream.close();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    b = BitmapFactory.decodeByteArray(data, 0, data.length);
//                    camera.stopPreview();
//
//                    b = ImageUtil.getRotateBitmap(b, 90.0F);
//
//                    b = Bitmap.createScaledBitmap(b, FrameView.width, FrameView.height, true);
//
//                    b = Bitmap.createBitmap(b, b.getWidth() / 2 - 300, b.getHeight() / 2 - 250, 600, 200);
//                    Intent intent = new Intent();
//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("data", b);
//                    intent.putExtras(bundle);
//                    this.this$0.setResult(0, intent);
//                    CameraActivity.access$300(this.this$0);
//                    this.this$0.finish();
//                }
//            }
//        };
//    }
//
//    protected void onPause()
//    {
//        super.onPause();
//        if (this.camera != null)
//            closeCamera();
//    }
//
//    protected void onResume()
//    {
//        super.onResume();
//        if (this.camera == null)
//            getCamera();
//    }
//
//    private void getCamera() {
//        try {
//            this.camera = Camera.open();
//        } catch (Exception e) {
//            this.camera = null;
//            e.printStackTrace(); }
//    }
//
//    private void startCamera() {
//        try {
//            this.camera.setPreviewDisplay(this.holder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.camera.setDisplayOrientation(90);
//        this.camera.startPreview();
//    }
//
//    private void closeCamera() {
//        this.camera.setPreviewCallback(null);
//        this.camera.stopPreview();
//        this.camera.release();
//        this.camera = null;
//    }
//}