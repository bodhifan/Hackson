package com.demo.test;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bofan on 2014/12/29.
 */
public class Test1 extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setContentType("text/html;charset=gbk");

        //request.setCharacterEncoding("gbk");
        // response.setCharacterEncoding("gbk");
        PrintWriter writer = response.getWriter();
        String userName = request.getParameter("user");
        String passwd = request.getParameter("pwd");

        if (userName.equals("hao123"))
        {
            writer.append("login success!");
        }
        else
        {
            writer.append("login failed!");
        }
        writer.append("这是测试");

    }
}
