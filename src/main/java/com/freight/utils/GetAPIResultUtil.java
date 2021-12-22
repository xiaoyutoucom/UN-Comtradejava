package com.freight.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
@Component
public class GetAPIResultUtil {
    /**
     *
     *
     * @param url
     * @param param
     * @return
     */
    public  String getAPIResult(String url, String param, String plainCredentials) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            URLConnection conn = realUrl.openConnection();
            //conn.setConnectTimeout(5000);

            // String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
//            conn.setRequestProperty("Authorization", "Bearer " + plainCredentials);
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("Content-type", "application/json");
//            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            out = new PrintWriter(conn.getOutputStream());
//            out.print("accountId:30002549\n" +
//                    "fromSuburbName:Sockburn\n" +
//                    "toSuburbName:Balfour\n" +
//                    "numberPieces:1\n" +
//                    "weight:30\n" +
//                    "volume:0.579\n" +
//                    "fromPostCode:1061\n" +
//                    "toPostcode:9779");
//            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            in.readLine();
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
