package gokathonit2.coalarm2;

import android.telephony.TelephonyManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
}
