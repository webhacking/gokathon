package gokathonit2.coalarm2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import gokathonit2.coalarm2.JSON.MyJSON;
import gokathonit2.coalarm2.ListView.ListViewBtnAdapter;
import gokathonit2.coalarm2.ListView.ListViewBtnItem;

/**
 * Created by shinhyungjune on 2016. 8. 6..
 */
public class SignInActivity extends Activity implements ListViewBtnAdapter.ListBtnClickListener {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private ListView listView;
    private ArrayList<ListViewBtnItem> items;
    private View mScrollFormView;
    private BackPressCloseHandler backPressCloseHandler;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        backPressCloseHandler = new BackPressCloseHandler(this);
        items = new ArrayList<ListViewBtnItem>();
        ListViewBtnAdapter adapter;

        loadItemFromDB(items);

        Intent intent = getIntent();
        int flag = intent.getExtras().getInt("flag");
        if(flag != 0) {
            int hour = intent.getExtras().getInt("hour");
            int min = intent.getExtras().getInt("min");
            addItemToList(hour, min);

            String savedMsg = items.get(0).getTimeText();
            for(int i = 1;i<items.size();i++){
                savedMsg = savedMsg + "@" + items.get(i).getTimeText();
            }
            MyJSON.saveData(savedMsg);
            //add보다는 해당 리스트를 파일에 쓰는게 좋을 듯?
        }



        /** plus icon setting **/
        ImageView plusIconView = (ImageView) findViewById(R.id.plusAlarm);
        Drawable plusIconImage = ContextCompat.getDrawable(this, R.drawable.plus_button);
        plusIconView.setImageDrawable(plusIconImage);

        ImageView deleteIconView = (ImageView) findViewById(R.id.deleteAlarm);
        Drawable deleteIconImage = ContextCompat.getDrawable(this, R.drawable.ic_watch_later_black_24dp);
        deleteIconView.setImageDrawable(deleteIconImage);
        // Sets the margins of the imageview, making it move to a specific location on the screen;

        plusIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("plus icon","clicked!!");

                attempAddAlarm();
            }
        });
        deleteIconView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.e("delete icon","clicked!!");
                attempDeleteAlarm();
            }
        });
        //item load


        //gen adapter
        if(items.size() != 0) {
            adapter = new ListViewBtnAdapter(this, R.layout.listview_btn_item, items, this);

            // 리스트뷰 참조 및 Adapter달기
            listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(adapter);

            //ListView에 클릭 이벤트 핸들러
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    //ToDo: item Click
                }
            });
        }
       // mScrollFormView = findViewById(R.id.scroll_view);
    }

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(this, Integer.toString(position + 1) + "번 아이템이 선택되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public boolean loadItemFromDB(ArrayList<ListViewBtnItem> list) {
        ListViewBtnItem item;

        if (list == null) {
            list = new ArrayList<ListViewBtnItem>();
        }

        //file read
        String readMsg = MyJSON.getData();
        if(readMsg != null) {
            String[] texts = readMsg.split("@");
            for(int i = 0;i<texts.length;i++){
                int hour = Integer.parseInt(texts[i].split(":")[0]);
                int min = Integer.parseInt(texts[i].split(":")[1]);
                item = new ListViewBtnItem();
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_watch_later_black_24dp));
                item.setHour(hour);
                item.setMin(min);
                list.add(item);
            }
        }

        //아이템 생성


        return true;
    }
    public boolean addItemToList(int hour, int min){
        ListViewBtnItem item = new ListViewBtnItem();
        item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_watch_later_black_24dp));
        item.setHour(hour);
        item.setMin(min);
        items.add(item);
        return true;
    }
    public void attempAddAlarm(){
        Intent intent = new Intent(SignInActivity.this, AlarmSettingActivity.class);
        startActivity(intent);
    }
    public void attempDeleteAlarm(){
        MyJSON.removeFile();
    }
/*
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mScrollFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mScrollFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mScrollFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mScrollFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }*/



    public void showGuide() {
        toast = Toast.makeText(this,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
