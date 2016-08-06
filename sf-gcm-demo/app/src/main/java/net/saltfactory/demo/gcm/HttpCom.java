package net.saltfactory.demo.gcm;

import android.net.http.HttpResponseCache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinhyungjune on 2016. 8. 6..
 */

public class HttpCom {
    private String URL;
    public HttpCom(String URL){
        this.URL = URL;
    }

    public String sendSignupInfoByHttp(String name, String email, String password){

        DefaultHttpClient client = new DefaultHttpClient();
        try{
            //msg 전
            // HttpPost post = new HttpPost(URL+"?msg="+msg);
            //HttpParams params = client.getParams();
            //HttpConnectionParams.setConnectionTimeout(params,3000);
            //HttpConnectionParams.setSoTimeout(params,3000);
            HttpPost post = new HttpPost(URL);
            List<NameValuePair> pairs =new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("name", name));
            pairs.add(new BasicNameValuePair("email", email));
            pairs.add(new BasicNameValuePair("password", password));
            post.setEntity(new UrlEncodedFormEntity(pairs));
            /*데이터 전송 후 서버로 부터 데이터를 받음 */
            HttpResponse response = client.execute(post);

            return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
            client.getConnectionManager().shutdown(); //연결 지연 종료
            return "";
        }
    }

    public String sendLogInfoByHttp(String email, String password){
        DefaultHttpClient client = new DefaultHttpClient();
        try{
            HttpPost post = new HttpPost(URL);
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("email",email));
            pairs.add(new BasicNameValuePair("password",password));
            post.setEntity(new UrlEncodedFormEntity(pairs));

            HttpResponse response = client.execute(post);
            return EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
            client.getConnectionManager().shutdown();
            return "";
        }
    }

    public String sendByHttp(String msg){
        if(msg == null){
            return "msg is null";
        }
        DefaultHttpClient client = new DefaultHttpClient();
        try{
            //msg 전
            HttpPost post = new HttpPost(URL+"?msg="+msg);
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params,3000);
            HttpConnectionParams.setSoTimeout(params,3000);

            /*데이터 전송 후 서버로 부터 데이터를 받음 */
            HttpResponse response = client.execute(post);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(),"utf-8"));

            String line = null;
            String result = "";

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            client.getConnectionManager().shutdown(); //연결 지연 종료
            return "";
        }
    }

    /*
    HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://api.gokathon.hax0r.info/auth/send_push");

        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        //nameValuePair.add(new BasicNameValuePair("username", "test_user"));
        //nameValuePair.add(new BasicNameValuePair("id", "123456789"));
        //nameValuePair.add(new BasicNameValuePair("password", "123456789"));
        nameValuePair.add(new BasicNameValuePair("device_token", token));


     */

    public String testGet(String arg1) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(URL);

        try {
            HttpResponse response = client.execute(get);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity);
        } catch (Exception e) { }

        return null;
    }

    public String testPut(String arg1) {
        DefaultHttpClient client = new DefaultHttpClient();
        try{
            //msg 전
            HttpPut put = new HttpPut(URL);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("arg1", arg1));
            try {
                put.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (UnsupportedEncodingException e) {
                // log exception
                e.printStackTrace();
            }

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params,3000);
            HttpConnectionParams.setSoTimeout(params,3000);

            /*데이터 전송 후 서버로 부터 데이터를 받음 */
            HttpResponse response = client.execute(put);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(),"utf-8"));

            String line = null;
            String result = "";

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            client.getConnectionManager().shutdown(); //연결 지연 종료
            return "";
        }
    }


    public String sendLoginInfo(String token){
        if(token == null){
            return "token is null";
        }
        DefaultHttpClient client = new DefaultHttpClient();
        try{
            //msg 전
            HttpPost post = new HttpPost(URL);
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("device_token", token));
            try {
                post.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (UnsupportedEncodingException e) {
                // log exception
                e.printStackTrace();
            }

            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params,3000);
            HttpConnectionParams.setSoTimeout(params,3000);

            /*데이터 전송 후 서버로 부터 데이터를 받음 */
            HttpResponse response = client.execute(post);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(),"utf-8"));

            String line = null;
            String result = "";

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
            client.getConnectionManager().shutdown(); //연결 지연 종료
            return "";
        }
    }


}
