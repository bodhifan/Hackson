package com.oracle.hackson.common;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bofan on 2014/12/29.
 */
public class HttpHelper {

    static String m_url;
    static HttpHelper m_Instance = new HttpHelper();

    // 默认的编码方式
    public  static String encodeMode = "gbk";

    public static HttpHelper setUrl(String url) {
        m_url = url;
        return m_Instance;
    }

    public static InputStream getInputStream() {
        try {
            URL murl = new URL(m_url);
            HttpURLConnection cnn = (HttpURLConnection) murl.openConnection();
            cnn.setConnectTimeout(3000);
            cnn.setDoInput(true);
            cnn.setRequestMethod("GET");

            if (cnn.getResponseCode() == 200) {
                InputStream inputStream = cnn.getInputStream();
                return inputStream;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static String sendPostMessage(Map<String, Object> params, String encode) {

        URL url = null;
        StringBuffer buffer = new StringBuffer();

        try {

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), encode));
                buffer.append("&");
            }
            buffer.deleteCharAt(buffer.length() - 1);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(m_url);
            HttpURLConnection cnn = (HttpURLConnection) url.openConnection();
            cnn.setDoOutput(true);
            cnn.setConnectTimeout(3000);
            cnn.setRequestMethod("POST");
            cnn.setDoInput(true);

            byte[] data = buffer.toString().getBytes();
            cnn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            cnn.setRequestProperty("Content-Lenght", String.valueOf(data.length));

            OutputStream outputStream = cnn.getOutputStream();
            outputStream.write(data, 0, data.length);

            if (cnn.getResponseCode() == 200) {
                System.out.println("hello!");
                return changeInputStream(cnn.getInputStream(), encode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String changeInputStream(InputStream inputStream, String encode) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        try {
            while ((len=inputStream.read(data)) != -1)
            {
                outputStream.write(data, 0, len);
            }
            String content = new String(outputStream.toByteArray(),encode);
            return  content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";
    }

    public static String get(String urlpath) {
        InputStream inputStream = HttpHelper.setUrl(urlpath).getInputStream();
        if (inputStream == null)
            return "";
        return changeInputStream(inputStream,DefineConfig.DEFAULT_ENCONDING);

    }

    public static void get(String urlPath, String imagePath) {
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imageFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        try {

            inputStream = HttpHelper.setUrl(urlPath).getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
