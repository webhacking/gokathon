package net.saltfactory.demo.gcm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

public class AlarmSettingActivity extends AppCompatActivity {

    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        setContentView(R.layout.activity_alarm_setting);

        final TimePicker timePicker = (TimePicker) findViewById(R.id.watch);

        Button okBtn = (Button)findViewById(R.id.btn_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            //@TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v) {
                SetAlarm(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
            }
        });
    }

    private void SetAlarm(int hour, int min)
    {
      //  manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, MakePendingIntent());
       // Toast.makeText(getApplicationContext(), "SET ALARM", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // Set the alarm's trigger time to 8:30 a.m.
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), MakePendingIntent());

        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtra("hour",hour);
        intent.putExtra("min", min);
        intent.putExtra("flag",1);
        startActivity(intent);
    }

    private PendingIntent MakePendingIntent() {
        Intent intent = new Intent(getApplicationContext(), AlarmAlertActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        return pendingIntent;
    }
}
