package net.saltfactory.demo.gcm;

import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MyHttpPost extends AsyncTask<Void, Void, String> {

    protected String doInBackground(Void... unused) {
        String content = executeClient();
        return content;
    }

    protected void onPostExecute(String result) {
        // 모두 작업을 마치고 실행할 일 (메소드 등등)
    }

    public String executeClient() {
        ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("device_token", ""));

        // 연결 HttpClient 객체 생성
        HttpClient client = new DefaultHttpClient();

        // 객체 연결 설정 부분, 연결 최대시간 등등
        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 5000);

        // Post객체 생성
        HttpPost httpPost = new HttpPost("http://api.gokathon.hax0r.info/auth/send_push");

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");
            httpPost.setEntity(entity);
            client.execute(httpPost);
            return EntityUtils.getContentCharSet(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
