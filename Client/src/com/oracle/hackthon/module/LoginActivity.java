package com.oracle.hackthon.module;

import android.app.Activity;
import android.os.Bundle;
import com.oracle.hackthon.app.R;

/**
 * Created by bofan on 2015/1/8.
 */
public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}