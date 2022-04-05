package com.e2ess.starprinter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.starmicronics.stario.StarResultCode;
import com.starmicronics.starioextension.ConnectionCallback;
import com.starmicronics.starioextension.StarIoExtManager;
import com.starmicronics.starioextension.StarIoExtManagerListener;

public class ToasterMessage {

    public ToasterMessage(MyListner m){
        this.listner = m;
    }
    private StarIoExtManager mStarIoExtManager;
    public  Context context;

    MyListner listner;

    public void search(Context _context){

//        mStarIoExtManager.

    }
    public void s(Context c, String message){
        this.context = c;

        Toast.makeText(c,message,Toast.LENGTH_SHORT).show(); // STAR mPOP-K0168
        mStarIoExtManager = new StarIoExtManager(StarIoExtManager.Type.OnlyBarcodeReader, "BT:00:12:F3:39:BA:5C", "", 10000, c);     // 10000mS!!!
        mStarIoExtManager.setCashDrawerOpenActiveHigh(true);
    }

    public  void connect(){
        mStarIoExtManager.setListener(mStarIoExtManagerListener);
        mStarIoExtManager.connect(mConnectionCallback);
    }

     StarIoExtManagerListener mStarIoExtManagerListener = new StarIoExtManagerListener() {
        @Override
        public void onBarcodeDataReceive(byte[] data) {
            String[] barcodeDataArray = new String(data).split("\n");

            for(String barcodeData:barcodeDataArray) {

                System.out.println(barcodeData);
                listner.callback(barcodeData);
            }
        }

        @Override
        public void onBarcodeReaderImpossible() {
             System.out.println("Barcode Reader Impossible.");
//
//            mComment.setTextColor(Color.RED);
        }

        @Override
        public void onBarcodeReaderConnect() {
            Toast.makeText(context,"Barcode Reader Connect.",Toast.LENGTH_SHORT);
//            mComment.setText("Barcode Reader Connect.");
//
//            mComment.setTextColor(Color.BLUE);
        }

        @Override
        public void onBarcodeReaderDisconnect() {
             System.out.println("Barcode Reader Disconnect.");
//
//            mComment.setTextColor(Color.RED);
        }

        @Override
        public void onAccessoryConnectSuccess() {
             System.out.println("Accessory Connect Success.");
//
//            mComment.setTextColor(Color.BLUE);
        }

        @Override
        public void onAccessoryConnectFailure() {
             System.out.println("Accessory Connect Failure.");
//
//            mComment.setTextColor(Color.RED);
        }

        @Override
        public void onAccessoryDisconnect() {
             System.out.println("Accessory Disconnect.");
//
//            mComment.setTextColor(Color.RED);
        }
    };

    private final ConnectionCallback mConnectionCallback = new ConnectionCallback() {
        @Override
        public void onConnected(boolean result, int resultCode) {


            if (!result) {
                String message;

                if (resultCode == StarResultCode.FAILURE_IN_USE) {
                    message = "Check the device. (In use)\nThen touch up the Refresh button.";
                }
                else {
                    message = "Check the device. (Power and Bluetooth pairing)\nThen touch up the Refresh button.";
                }

                System.out.println(message);
            } else
            System.out.println("coneected to barcode");
        }

        @Override
        public void onDisconnected() {
            // do nothing
            System.out.println("disconeected to barcode");
        }
    };
}
