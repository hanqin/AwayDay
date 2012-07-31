package com.thoughtworks.mobile.awayday.fragments.contacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final String TAG = "Utils";
    private Context context;
    private static final String FILE_NAME = "contact.txt";


    public static Bitmap create2DCode(String str) throws WriterException {
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300, hints);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static void saveContactInfos(Context context, String content) {

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
    }

    public static boolean isFileNotExist(Context context) {
        try {
            context.openFileInput(FILE_NAME);
        } catch (FileNotFoundException e) {
            return true;
        }
        return false;
    }

    public static String readContactInfos(Context context) {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(FILE_NAME);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int len = 0;

            while ((len = fis.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }

            fis.close();
            baos.close();

            return baos.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
        return "";
    }
}
