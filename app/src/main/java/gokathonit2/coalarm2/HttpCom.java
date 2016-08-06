package gokathonit2.coalarm2;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by shinhyungjune on 2016. 8. 6..
 */

public class HttpCom {
    private String URL;
    public HttpCom(String URL){
        this.URL = URL;
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
}
