package com.oracle.test;

import com.oracle.hackson.common.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bofan on 2015/1/2.
 */
public class Test {

    public static void main(String[] args) {

        String content = HttpHelper.get("http://192.168.10.1:8080/WebTest/servlet/json?flag=1");
        System.out.println(content);
        System.out.println(DefineConfig.DEFAULT_ENCONDING);
    }
}
