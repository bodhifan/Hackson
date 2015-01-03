package com.demo.actions;

import com.oracle.hackson.common.DefineConfig;
import com.oracle.hackson.common.GsonHelper;
import com.oracle.hackson.common.JsonHelper;
import com.oracle.hackson.common.JsonServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.oracle.hackson.common.DefineConfig.*;

/**
 * Created by bofan on 2015/1/2.
 */
@WebServlet(name = "JsonAction")
public class JsonAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("test/html;charset=gbk");
        response.setCharacterEncoding(DEFAULT_ENCONDING);
       // request.setCharacterEncoding(DefineConfig.DEFAULT_ENCONDING);
        int flag = Integer.parseInt(request.getParameter("flag"));
        PrintWriter writer = response.getWriter();
        System.out.println("now requst flag is " + flag);
        switch (flag)
        {
            case 1:
                //writer.println(JsonHelper.createJsonString("person",JsonServer.getPerson()));
                writer.println(GsonHelper.createJsonString(JsonServer.getPerson()));
                break;
            case 2:
                //writer.println("hell1");
               // writer.println(JsonHelper.createJsonString("pesons", JsonServer.getPersons()));
                writer.println(GsonHelper.createJsonString(JsonServer.getPersons()));
                break;
            case 3:
               //writer.println(JsonHelper.createJsonString("mapList", JsonServer.getMapList()));
                writer.println(GsonHelper.createJsonString(JsonServer.getMapList()));
                break;
            case 4:
               // writer.println(JsonHelper.createJsonString("address", JsonServer.getList()));
                writer.println(GsonHelper.createJsonString(JsonServer.getList()));
                break;
        }
        writer.flush();
        writer.close();


    }
}
