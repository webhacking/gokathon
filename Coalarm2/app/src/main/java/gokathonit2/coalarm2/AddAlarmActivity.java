package gokathonit2.coalarm2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import gokathonit2.coalarm2.ListView.ListViewBtnItem;

/**
 * Created by shinhyungjune on 2016. 8. 6..
 */
public class AddAlarmActivity extends Activity {
    private ListViewBtnItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        ImageView addAlarmTest = (ImageView) findViewById(R.id.plusAlarm);
        Drawable addAlaramTestImage = ContextCompat.getDrawable(this, R.drawable.plus_button);
        addAlarmTest.setImageDrawable(addAlaramTestImage);

        addAlarmTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addAlarm();
            }
        });
    }
    public void addAlarm(){
        item = new ListViewBtnItem();
        item.setText("9:00 AM");

        Intent intent = new Intent(AddAlarmActivity.this, SignInActivity.class);
        intent.putExtra("flag",1);
        intent.putExtra("Time",item.getText());
        startActivity(intent);
    }
}
