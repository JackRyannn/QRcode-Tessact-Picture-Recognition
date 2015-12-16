package com.jackryannn.graduation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private QRFragment qrFragment;
    private OCRFragment ocrFragment;
    private NormalFragment normalFragment;
    private MyShutter myShutter;

    @Bind({R.id.relativelayout})
    RelativeLayout relativeLayout;

    @OnClick({R.id.button_ocr})
    void toOCRFragment() {
        OCRFragment ocrFragment = new OCRFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, ocrFragment);
        transaction.commit();
    }

    @OnClick({R.id.button_normal})
    void toNormalFragment() {
        NormalFragment normalFragment = new NormalFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, normalFragment);
        transaction.commit();
    }

    @OnClick({R.id.button_qr})
    void toQRFragment() {
        qrFragment = new QRFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, qrFragment);
        transaction.commit();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        normalFragment = new NormalFragment();
        transaction.add(R.id.fragment, normalFragment);
        transaction.commit();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(140, 140);
        layoutParams.addRule(13, -1);
        myShutter = new MyShutter(this);
        myShutter.setBackgroundResource(0);
        relativeLayout.addView(myShutter, layoutParams);
    }

    public MyShutter getShutter() {
        return this.myShutter;
    }
}