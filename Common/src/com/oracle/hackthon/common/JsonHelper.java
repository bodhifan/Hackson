package com.oracle.hackthon.common;

import net.sf.json.JSONObject;

/**
 * Created by bofan on 2015/1/2.
 */
public class JsonHelper {

    public  static String createJsonString(String key, Object object)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key,object);
        return  jsonObject.toString();
    }
}
