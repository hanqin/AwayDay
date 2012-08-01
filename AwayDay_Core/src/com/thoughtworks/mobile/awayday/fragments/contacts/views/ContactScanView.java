package com.thoughtworks.mobile.awayday.fragments.contacts.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.Camera;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.thoughtworks.mobile.awayday.R;

import java.util.Timer;
import java.util.TimerTask;

public class ContactScanView extends AbstractContactView {
    private View centerView;
    private SurfaceView sfvCamera;
    private SFHCamera sfhCamera;
    private TextView txtScanResult;
    private Timer mTimer;
    final static int width = 480;
    final static int height = 320;
    int dstLeft, dstTop, dstWidth, dstHeight;
    private MyTimerTask mTimerTask;

    public ContactScanView(Context context, ViewFlipper viewFlipper) {
        super(context, viewFlipper);
    }

    @Override
    protected void initViewComponents() {
        centerView = (View) view.findViewById(R.id.centerView);
        sfvCamera = (SurfaceView) view.findViewById(R.id.contact_sfv_camera);
        sfhCamera = new SFHCamera(sfvCamera.getHolder(), width, height,
                previewCallback);
        txtScanResult = (TextView) view.findViewById(R.id.txtScanResult);
        // 初始化定时器
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        mTimer.schedule(mTimerTask, 0, 80);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.contact_scan;
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (dstLeft == 0) {//只赋值一次
                Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int displayWidth = size.x;
                int displayHeight = size.y;
                dstLeft = centerView.getLeft() * width
                        / displayWidth;
                dstTop = centerView.getTop() * height
                        / displayHeight;
                dstWidth = (centerView.getRight() - centerView.getLeft()) * width
                        / displayWidth;
                dstHeight = (centerView.getBottom() - centerView.getTop()) * height
                        / displayHeight;
            }
            sfhCamera.AutoFocusAndPreviewCallback();
        }
    }

    /**
     * 自动对焦后输出图片
     */
    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera arg1) {
            //取得指定范围的帧的数据
            PlanarYUVLuminanceSource source = new PlanarYUVLuminanceSource(data, width, height, dstLeft, dstTop, dstWidth, dstHeight, false);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();
            try {
                Result result = reader.decode(bitmap);
                String strResult = "BarcodeFormat:"
                        + result.getBarcodeFormat().toString() + "  text:"
                        + result.getText();
                txtScanResult.setText(strResult);
                mTimer.cancel();
            } catch (Exception e) {
                txtScanResult.setText("Scanning...");
            }
        }
    };
}
