package mydiary.example.clock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import java.util.Timer;
import java.util.TimerTask;

import mydiary.example.administrator.mydiary.R;


public class AlarmAlert extends Activity
{
    /* KC start */
    private SensorManager mManager;
    private Sensor mSensor = null;
    private SensorEventListener mListener = null;
    /* KC end */
    
    int recLen = 0;
    Timer timer = new Timer(true);
    TimerTask task = new TimerTask() {
                        public void run() {
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                     };
    private boolean followSettingsUri;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        /* KC start */
        mManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        /* KC end */
    
    
        recLen = 0;
        timer.schedule(task, 1000, 1000);

        
        /* Audio start */
        MediaPlayer mMediaPlayer = new MediaPlayer();         
        mMediaPlayer.reset();
        mMediaPlayer.setLooping(true);  
       
        
        try{
            
            mMediaPlayer.setDataSource(this, 
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        }
        catch(Exception e){
            Log.e("Timedown", e.toString());
        }
        /* Audio end */

        LayoutInflater factory = LayoutInflater.from(this); // @0.20
        final View setView = factory.inflate(R.layout.alert,null); // @0.20
        new AlertDialog.Builder(AlarmAlert.this)
            .setIcon(R.drawable.clock)
            .setTitle("")
            .setView(setView)
            .setMessage("")
            .setPositiveButton("",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        AlarmAlert.this.finish();
                        timer.cancel();
                    }
                })
            .show();
        
        /* KC start */
        mListener = new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int accuracy) 
            {
            }
            
            public void onSensorChanged(SensorEvent event) {
                int count = 0;
                float last_z = 0;
                while (count < 10) {
                    float z = event.values[SensorManager.DATA_Z];
                    if ((z > 0 && last_z < 0) || (z < 0 && last_z > 0)) {
                        count++;
                    } else {
                        if (last_z == 0) {
                            last_z = z;
                        }
                    }
                }
                AlarmAlert.this.finish();
                timer.cancel();
                

                int runtime = recLen;
            }
        };
        /* KC end */
  
    }
  
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recLen++;
                    Log.e("Timer", ""+recLen);
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
