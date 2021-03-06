package gokathonit2.coalarm2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AlarmAlertActivity extends AppCompatActivity {

    Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);

        setContentView(R.layout.activity_alarm_alert);

        DoSoundOn();

        Button soundOffBtn = (Button) findViewById(R.id.btn_sound_off);
        soundOffBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DoSoundOff();
            }
        });
    }

    private void DoSoundOn() {
        ringtone.play();
    }

    private void DoSoundOff() {
        ringtone.stop();
        this.finish();
    }

}
