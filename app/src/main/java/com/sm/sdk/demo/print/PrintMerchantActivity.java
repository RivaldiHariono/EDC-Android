package com.sm.sdk.demo.print;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sm.sdk.demo.BaseAppCompatActivity;
import com.sm.sdk.demo.MyApplication;
import com.sm.sdk.demo.R;
import com.sm.sdk.demo.utils.LogUtil;
import com.sunmi.peripheral.printer.InnerResultCallbcak;

public class PrintMerchantActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_merchant);
        //initToolbarBringBack(R.string.print);
        initView();

    }

    private void initView() {
        TextView MID, TID, MERCHANT_NAME;
        MID = findViewById(R.id.textView);
        TID = findViewById(R.id.textView2);
        MERCHANT_NAME = findViewById(R.id.textView3);

        SQLiteDatabase mydatabase = openOrCreateDatabase("GID_10",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS GID_10(MID VARCHAR,TID VARCHAR, MERCHANT_NAME VARCHAR);");
        mydatabase.execSQL("INSERT INTO GID_10 VALUES('12345678','7300011','ARSENAL_STORE');");
        Cursor resultSet = mydatabase.rawQuery("Select * from GID_10",null);
        resultSet.moveToFirst();
        String getMID = resultSet.getString(0);
        String getTID = resultSet.getString(1);
        String getMERCHANT_NAME = resultSet.getString(2);

        int getColumn = resultSet.getColumnCount();


        findViewById(R.id.btn_print_merchant).setOnClickListener(this);
        resultSet.moveToFirst();
        if (resultSet.getCount() >0) {
            resultSet.moveToPosition(0);
            MID.setText(getMID);
            TID.setText(getTID);
            MERCHANT_NAME.setText(getMERCHANT_NAME);

        }
        System.out.println(getColumn);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_print_merchant:
                printBitmap();
                break;
        }
    }


    private void printBitmap() {
        try {
            if (MyApplication.app.sunmiPrinterService == null) {
                showToast("Print not supported");
                return;
            }
            View content = findViewById(R.id.print_content);
            Bitmap bitmap = createViewBitmap(content);
//            bitmap = getBinaryzationBitmap(bitmap);
            MyApplication.app.sunmiPrinterService.enterPrinterBuffer(true);
            MyApplication.app.sunmiPrinterService.printBitmap(bitmap, new InnerResultCallbcak() {
                @Override
                public void onRunResult(boolean isSuccess) throws RemoteException {
                    LogUtil.e(TAG, "onRunResult-->isSuccess:" + isSuccess);
                }

                @Override
                public void onReturnString(String result) throws RemoteException {
                    LogUtil.e(TAG, "onReturnString-->result:" + result);
                }

                @Override
                public void onRaiseException(int code, String msg) throws RemoteException {
                    LogUtil.e(TAG, "onRaiseException-->code:" + code + ",msg:" + msg);
                }

                @Override
                public void onPrintResult(int code, String msg) throws RemoteException {
                    LogUtil.e(TAG, "onPrintResult-->code:" + code + ",msg:" + msg);
                }
            });
            MyApplication.app.sunmiPrinterService.lineWrap(4, null);
            MyApplication.app.sunmiPrinterService.exitPrinterBuffer(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Create Bitmap by View */
    private Bitmap createViewBitmap(View v) {
        long start = System.currentTimeMillis();
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888); //???????????????View???????????????Bitmap
        Canvas canvas = new Canvas(bitmap);  //???????????????Bitmap??????canvas
        v.draw(canvas);  //???View??????Bitmap???
        LogUtil.e(TAG, "createViewBitmap time:" + (System.currentTimeMillis() - start));
        return bitmap;
    }

    /** Create Bitmap by View */
    public Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = null;
        //??????view??????bitmap
        view.setDrawingCacheEnabled(true);
        //??????view??????Bitmap??????
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        //???????????????bitmap
        Bitmap cache = view.getDrawingCache();
        if (cache != null && !cache.isRecycled()) {
            bitmap = Bitmap.createBitmap(cache);
        }
        //??????view??????bitmap
        view.destroyDrawingCache();
        //??????view??????bitmap
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    /** Bitmap Binaryzation */
    private Bitmap getBinaryzationBitmap(Bitmap bm) {
        long start = System.currentTimeMillis();
        Bitmap bitmap = null;
        // ????????????????????????
        int width = bm.getWidth();
        int height = bm.getHeight();
        // ?????????????????????
        bitmap = bm.copy(Bitmap.Config.ARGB_8888, true);
        // ????????????????????????,????????????????????????
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // ????????????????????????
                int pixel = bitmap.getPixel(i, j);
                // ??????Alpha????????????
                int alpha = pixel & 0xFF000000;
                // ??????Red??????
                int red = (pixel & 0x00FF0000) >> 16;
                // ??????Green??????
                int green = (pixel & 0x0000FF00) >> 8;
                // ??????Blue??????
                int blue = pixel & 0x000000FF;
                // ????????????????????????,????????????????????????
                int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                // ????????????????????????
                if (gray <= 95) {
                    gray = 0;
                } else {
                    gray = 255;
                }
                // ?????????????????????
                int newPiexl = alpha | (gray << 16) | (gray << 8) | gray;
                // ????????????????????????
                bitmap.setPixel(i, j, newPiexl);
            }
        }
        LogUtil.e(TAG, "getBinaryzationBitmap time:" + (System.currentTimeMillis() - start));
        return bitmap;
    }



}
