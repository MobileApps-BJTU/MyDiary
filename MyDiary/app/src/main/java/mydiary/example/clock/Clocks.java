package mydiary.example.clock;

/**
 * Created by Administrator on 2015/3/28.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

import mydiary.example.administrator.mydiary.R;


public class Clocks extends Activity
{


    Button mButton1; //timeset button
    Button mButton2; //enable button
    int state = 0; // 0:disable  1:enable
    Calendar c=Calendar.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LayoutInflater factory = LayoutInflater.from(this);
        final View setView = factory.inflate(R.layout.timeset,null);
        final TimePicker tPicker=(TimePicker)setView
                .findViewById(R.id.tPicker);
        tPicker.setIs24HourView(true);

        final AlertDialog di=new AlertDialog.Builder(Clocks.this)
                .setIcon(R.drawable.clock)
                .setTitle("Setting")
                .setView(setView)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY,tPicker.getCurrentHour());
                                c.set(Calendar.MINUTE,tPicker.getCurrentMinute());
                                c.set(Calendar.SECOND,0);
                                c.set(Calendar.MILLISECOND,0);



                                String tmpS=format(tPicker.getCurrentHour())+":"+
                                        format(tPicker.getCurrentMinute());
                                mButton1.setText(""+tmpS);

                                Toast toast = Toast.makeText(Clocks.this,"The set time is"+tmpS,
                                        Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP, 0, 40);
                                toast.show();
                                Log.e("canlendar", c.toString());//log
                                if (state == 1){

                                    Intent intent = new Intent(Clocks.this, CallAlarm.class);
                                    PendingIntent sender = PendingIntent.getBroadcast(
                                            Clocks.this,1, intent, 0);

                                    AlarmManager am;
                                    am = (AlarmManager)getSystemService(ALARM_SERVICE);
                                    am.set(AlarmManager.RTC_WAKEUP,
                                            c.getTimeInMillis(),
                                            sender
                                    );
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                            }
                        }).create();

    /* timeset Button */
        mButton1=(Button) findViewById(R.id.mButton1);
        mButton1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                c.setTimeInMillis(System.currentTimeMillis());
                tPicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
                tPicker.setCurrentMinute(c.get(Calendar.MINUTE));

                di.show();
            }
        });

    /* enable Button */
        mButton2=(Button) findViewById(R.id.mButton2);

        mButton2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                if (state == 0){
                    if (!"Set Time".equals(mButton1.getText().toString())){

                        Intent intent = new Intent(Clocks.this, CallAlarm.class);
                        PendingIntent sender = PendingIntent.getBroadcast(
                                Clocks.this,1, intent, 0);

                        AlarmManager am;
                        am = (AlarmManager)getSystemService(ALARM_SERVICE);
                        am.set(AlarmManager.RTC_WAKEUP,
                                c.getTimeInMillis(),
                                sender
                        );
                    }
                    state = 1;

                    Toast toast = Toast.makeText(Clocks.this,"Clock Start!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 40);
                    toast.show();

                    mButton2.setBackgroundResource(R.drawable.enable);
                    Log.e("canlendar", c.toString());//log
                }
                else if (state == 1){
                    Intent intent = new Intent(Clocks.this, CallAlarm.class);
                    PendingIntent sender = PendingIntent.getBroadcast(
                            Clocks.this,1, intent, 0);

                    AlarmManager am;
                    am = (AlarmManager)getSystemService(ALARM_SERVICE);
                    am.cancel(sender);

                    Toast toast = Toast.makeText(Clocks.this,"Clock End",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 40);
                    toast.show();
                    state = 0;

                    mButton2.setBackgroundResource(R.drawable.disable);
                    Log.e("canlendar", c.toString());//log
                }
            }
        });
    }


    private String format(int x)
    {
        String s=""+x;
        if(s.length()==1) s="0"+s;
        return s;
    }
}

