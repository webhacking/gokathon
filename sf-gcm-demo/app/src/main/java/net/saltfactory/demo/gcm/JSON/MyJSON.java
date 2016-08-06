package net.saltfactory.demo.gcm.JSON;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by shinhyungjune on 2016. 8. 6..
 */
public class MyJSON {
    private static String fileName = Environment.getExternalStorageDirectory() + "/alarm.txt";

    public static void saveData(String mJsonResponse) {
        try{
            //FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            FileWriter file = new FileWriter(fileName);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        }catch(IOException e){
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public static String getData(){
        try{
            File f = new File(fileName);
            //check whether file is exist
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        }catch(IOException e){
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void removeFile(){

        File f = new File(fileName);
        f.delete();

    }
}
