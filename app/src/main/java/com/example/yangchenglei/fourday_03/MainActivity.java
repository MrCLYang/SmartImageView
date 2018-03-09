package com.example.yangchenglei.fourday_03;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//自定义smartimageview的实现
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySmartImageView mySmartImageView= (MySmartImageView) findViewById(R.id.iv);
        mySmartImageView.setImageUrl("http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg",R.drawable.e);


    }
}
