package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.oracle.hackson.common.DefineConfig;
import com.oracle.hackson.common.GsonHelper;
import com.oracle.hackson.common.HttpHelper;
import com.oracle.hackson.common.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    public final static String TAG = "TEST";
    Button btnGetJsonTest,persons,maplist,listbtn;
    public static String curJsonContent;

    @ViewInject(R.id.button5)
    public Button showImage;

    @OnClick(R.id.button5)
    public void showImage(View v)
    {
        Log.i(TAG,"button is click");
        Intent intent = new Intent(this,ShowImage.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "onCreate");
        btnGetJsonTest = (Button)this.findViewById(R.id.button);
        persons = (Button)this.findViewById(R.id.button2);
        maplist = (Button)this.findViewById(R.id.button3);
        listbtn = (Button)this.findViewById(R.id.button4);

        btnGetJsonTest.setOnClickListener(this);
        persons.setOnClickListener(this);
        maplist.setOnClickListener(this);
        listbtn.setOnClickListener(this);
        ViewUtils.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    public void onClick(View v) {

        Thread thread = null;
        int viewId = v.getId();
        switch (viewId)
        {
            case R.id.button:
                thread = new Thread(new TestGet(1));
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Person person = GsonHelper.parseObject(curJsonContent,Person.class);
                Log.i(TAG,"new json parse");
                Log.i(TAG,person.toString());
                break;
            case R.id.button2:
                thread = new Thread(new TestGet(2));
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Person> persons = GsonHelper.parseObjectList(curJsonContent,Person.class);
                Log.i(TAG,"new json persons");
                Log.i(TAG,persons.toString());
                break;
            case R.id.button3:
                thread = new Thread(new TestGet(3));
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Map<String,Object>> personMapList = GsonHelper.parseListKeyMaps(curJsonContent);
                Log.i(TAG,"new json personMapList");
                Log.i(TAG,personMapList.toString());
                thread.start();
                break;
            case R.id.button4:
                thread = new Thread(new TestGet(4));
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> personList = GsonHelper.parseObjectList(curJsonContent, String.class);
                Log.i(TAG,"new json personList");
                Log.i(TAG,personList.toString());
                thread.start();
                break;
        }
    }
}

class TestGet implements Runnable {

    private int flag = 0;

    public TestGet(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        Log.i(MyActivity.TAG,"begin");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag",flag);
        String content = HttpHelper.setUrl("http://192.168.10.1:8080/WebTest/servlet/json")
                .sendPostMessage(map, DefineConfig.DEFAULT_ENCONDING);
        MyActivity.curJsonContent = content;
    }
}