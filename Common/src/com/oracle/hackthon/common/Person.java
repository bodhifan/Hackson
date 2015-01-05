package com.oracle.hackthon.common;

import java.io.Serializable;

/**
 * Created by bofan on 2015/1/2.
 */
public class Person implements Serializable {
    private  String name;
    private  int id;
    private  String address;

    public Person(int id, String name, String addr) {
        this.name = name;
        this.id = id;
        this.address = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setAge(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
