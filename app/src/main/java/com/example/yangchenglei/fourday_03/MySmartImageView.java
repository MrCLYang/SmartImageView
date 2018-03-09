package com.example.yangchenglei.fourday_03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Switch;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：杨成雷
 * 时间：2018/3/7:13:52
 */
public class MySmartImageView extends ImageView {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    //显示数据
                    MySmartImageView.this.setImageBitmap(bitmap);
                    break;
                case 2:
                    int resourse= (int) msg.obj;
                    MySmartImageView.this.setImageResource(resourse);
                    break;
                case 3:
                    int resourse3= (int) msg.obj;
                    MySmartImageView.this.setImageResource(resourse3);
                    break;
                default:
                    break;
            }

        }
    };


    //在new的时候一个参数的时候的这个构造方法就会被使用
    public MySmartImageView(Context context) {
        super(context);
    }

    //在布局文件中使用的时候调用两个参数的时候调用
    public MySmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySmartImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySmartImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //显示图片的方法的path，是通过传过来的url地址
    public void setImageUrl(final String path, final int resource) {
        new Thread() {
            @Override
            public void run() {
                try {
                    //固定获取网络的代码
                    //创建URL对象指定我们要访问的网络对象
                    URL url = new URL(path);
                    //拿到HttpURLConnection对象用于发送或者接受数据
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //发送Get请求
                    conn.setRequestMethod("GET");
                    //设置请求超时时间
                    conn.setConnectTimeout(5000);
                    //获取服务器返回码状态
                    int code = conn.getResponseCode();
                    //如果code==200说明请求成功
                    if (code == 200) {
                        InputStream in = conn.getInputStream();
                        //解析xml数据方法
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        Message msg = Message.obtain();
                        msg.what = 1;
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = Message.obtain();
                        msg.what = 2;
                        msg.obj = resource;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Message msg = Message.obtain();
                    msg.what = 3;
                    msg.obj = resource;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();

    }


}
