package com.oracle.hackson.common;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bofan on 2015/1/2.
 */
public class JsonServer {

    public static Person getPerson()
    {
        Person person = new Person(1002,"replaced","北京");
        return  person;
    }
    public static List<Person> getPersons()
    {
        List<Person> persons = new ArrayList<Person>();
        Person p1 = new Person(1002,"anti","北京");
        persons.add(p1);

        Person p2 = new Person(1001,"b","上海");
        persons.add(p2);

        Person p3 = new Person(1003,"jack","深圳");
        persons.add(p3);

        return  persons;
    }
    public static List<String> getTest2()
    {
        List<String> strings = new ArrayList<String>();
        strings.add("test1");
        strings.add("你好！");
        strings.add("test3");

        return  strings;
    }
    public  static List<String> getList()
    {
        List<String> strings = new ArrayList<String>();
        strings.add("上海");
        strings.add("北京");
        strings.add("南宁");

        return  strings;
    }

    public  static  List<Map<String,Object>> getMapList()
    {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        Map<String,Object> map1  = new HashMap<String, Object>();
        map1.put("id",1002);
        map1.put("name","jack");
        map1.put("address","北京");

        mapList.add(map1);

        Map<String,Object> map2  = new HashMap<String, Object>();
        map2.put("id",1003);
        map2.put("name","ice");
        map2.put("address","上海");
        mapList.add(map2);

        return mapList;
    }

}
