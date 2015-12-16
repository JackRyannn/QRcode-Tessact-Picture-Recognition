package com.jackryannn.graduation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NormalFragment extends Fragment {
    private Camera camera;
    private Context context;
    private SurfaceHolder holder;
    private Camera.PictureCallback myPictureCallback;

    @Bind(R.id.relativelayout)
    RelativeLayout relativeLayout;

    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;

    private void closeCamera() {
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    private void getCamera() {
        try {
            camera = Camera.open();
        } catch (Exception localException) {
            camera = null;
            localException.printStackTrace();
        }
    }

    private void startCamera() {
        try {
            System.out.println("this");
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
    }

    @OnClick(R.id.surfaceView)
    void AutoFocus() {
        this.camera.autoFocus(null);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        ((Main2Activity) getActivity()).getShutter().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.Parameters localParameters = camera.getParameters();
                localParameters.setPictureSize(720, 1280);
                localParameters.setPictureFormat(256);
                localParameters.setFocusMode("auto");
                camera.autoFocus(new AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            camera.takePicture(null, null, myPictureCallback);
                        }
                    }
                });
            }
        });

        View view = paramLayoutInflater.inflate(R.layout.fragment_normal, paramViewGroup, false);
        ButterKnife.bind(this, view);
        holder = surfaceView.getHolder();
        holder.addCallback(new Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                startCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
        FrameView frameView = new FrameView(context);
        relativeLayout.addView(frameView);
        myPictureCallback = new PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                File file = new File( "/storage/emulated/0"+ "/temp.jpg");

                if (data != null)
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(data);
                    fileOutputStream.close();
                    Bitmap Bitmap1 = Bitmap.createScaledBitmap(ImageUtil.getRotateBitmap(BitmapFactory.decodeByteArray(data, 0, data.length), 90.0F), FrameView.width, FrameView.height, true);
                    Bitmap Bitmap2 = Bitmap.createBitmap(Bitmap1, -300 + Bitmap1.getWidth() / 2, -250 + Bitmap1.getHeight() / 2, 600, 200);
                    Intent Intent = new Intent(context, ShowActivity.class);
                    Bundle Bundle = new Bundle();
                    Bundle.putInt("flag", ShowActivity.FLAG_NORMAL);
                    Bundle.putParcelable("data", Bitmap2);
                    Intent.putExtras(Bundle);
                    camera.stopPreview();
                    startActivity(Intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        return view;
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeCamera();
    }

    public void onPause() {
        super.onPause();
        camera.stopPreview();
    }

    public void onResume() {
        super.onResume();
        if (camera == null) {
            getCamera();
        }else{
            camera.startPreview();
        }
    }
}