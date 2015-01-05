package com.oracle.hackthon.module;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.oracle.hackson.app.R;
import com.oracle.hackthon.common.BaseActivity;
import com.oracle.hackthon.common.CommonUtils;
import com.oracle.hackthon.config.Config;

public class MainActivity extends BaseActivity {

    @ViewInject(R.id.callPhoto)
    Button btnCallPhoto;
    @ViewInject(R.id.loadImageFromNet)
    Button btnLoadImage;

    @ViewInject(R.id.imageView)
    ImageView imageContainer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
    }

    // 注册点击事件
    @OnClick(R.id.callPhoto)
    public void onCallPhoto(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.loadImageFromNet)
    public void onLoadImage(View view)
    {

        BitmapUtils bitmapUtils = new BitmapUtils(this);
        // 测试URL
        String url = "http://a.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3dc05e5623b8a1cd11728b2961.jpg";
    //    bitmapUtils.display(imageContainer,url);
        bitmapUtils.display(imageContainer,url
                ,
                new BitmapLoadCallBack<ImageView>() {
                    @Override
                    public void onLoadCompleted(ImageView view, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {

                        String name = CommonUtils.generateName()+".jpg";
                        String imagePath = Config.workDir+"/" + name;
                        Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                        view.setImageBitmap(bitmap);
                        CommonUtils.saveBitmap(bitmap,imagePath);
                    }

                    @Override
                    public void onLoadFailed(ImageView view, String s, Drawable drawable) {

                        Toast.makeText(MainActivity.this,"图片加载失败",Toast.LENGTH_LONG).show();

                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            String name = CommonUtils.generateName() + ".jpg";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式


            String filepath = Config.workDir + "/" + name;

            CommonUtils.saveBitmap(bitmap, filepath);

            ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// 将图片显示在ImageView里
        }
    }
}
