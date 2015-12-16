package com.jackryannn.graduation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler;

public class QRFragment extends Fragment
        implements ZXingScannerView.ResultHandler
{
    private Context context;
    private ZXingScannerView mScannerView;

    public void handleResult(Result paramResult)
    {
        Intent localIntent = new Intent(this.context, ShowActivity.class);
        Bundle localBundle = new Bundle();
        localBundle.putInt("flag", ShowActivity.FLAG_QR);
        localBundle.putString("data", paramResult.getText().toString());
        localIntent.putExtras(localBundle);
        startActivity(localIntent);
    }

    public void onAttach(Context paramContext)
    {
        super.onAttach(paramContext);
        this.context = paramContext;
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.mScannerView = new ZXingScannerView(this.context);
        return this.mScannerView;
    }

    public void onResume()
    {
        super.onResume();
        this.mScannerView.setResultHandler(this);
        this.mScannerView.startCamera();
    }

    public void onStop()
    {
        super.onStop();
        this.mScannerView.stopCamera();
    }
}