package gokathonit2.coalarm2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
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
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ListViewBtnAdapter adapter;
        ArrayList<ListViewBtnItem> items = new ArrayList<ListViewBtnItem>();

        /** plus icon setting **/
        ImageView plusIconView = (ImageView) findViewById(R.id.plusAlarm);
        Drawable plusIconImage = ContextCompat.getDrawable(this, R.drawable.plus_button);
        plusIconView.setImageDrawable(plusIconImage);

        // Sets the margins of the imageview, making it move to a specific location on the screen;

        plusIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("plus icon","clicked!!");
                attempAddAlarm();
            }
        });
        //item load
        loadItemFromDB(items);

        //gen adapter
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

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(this, Integer.toString(position + 1) + "번 아이템이 선택되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public boolean loadItemFromDB(ArrayList<ListViewBtnItem> list) {
        ListViewBtnItem item;

        if (list == null) {
            list = new ArrayList<ListViewBtnItem>();
        }

        //아이템 생성
        for (int i = 1; i < 5; i++) {
            item = new ListViewBtnItem();
            item.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_watch_later_black_24dp));
            item.setText("7:00 AM");
            list.add(item);
        }

        return true;
    }

    public void attempAddAlarm(){
        Intent intent = new Intent(SignInActivity.this, AddAlarmActivity.class);
        startActivity(intent);
    }
}
