package com.oracle.hackthon.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.fxml.expression.Expression;

import java.util.List;
import java.util.Map;

/**
 * Created by bofan on 2015/1/2.
 */
public class GsonHelper {
    public static String createJsonString(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 解析单个对象的JSON对象
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString,Class<T> tClass)
    {
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(jsonString,tClass);
        return  t;
    }

    /**
     * 解析数组类型的JSON文件
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> parseObjectList(String jsonString,Class<T> tClass)
    {
        List<T> t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        catch(Exception e)
        {

        }

        return t;

    }
    public static List<Map<String,Object>> parseListKeyMaps(String jsonString)
    {
        List<Map<String,Object>> rntList = null;
        try
        {
            Gson gson = new Gson();
            rntList = gson.fromJson(jsonString,new TypeToken<List<Map<String,Object>>>(){}.getType());
        }
        catch (Exception e)
        {

        }
        return  rntList;
    }

}
