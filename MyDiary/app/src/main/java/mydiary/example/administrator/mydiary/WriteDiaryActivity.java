package mydiary.example.administrator.mydiary;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mydiary.example.administrator.db.Conmon;
import mydiary.example.administrator.db.DBHelpe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WriteDiaryActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText edittext,edit_winder;
    private TextView edit_year,  edit_days;
    private Button btn_join, btn_clean, btn_open, btn_close;
    private DBHelpe db;
    private String mWay;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_write_diary);
        IntView();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
        String time = df.format(new Date());
        edit_year.setText(time);
        Calendar c = Calendar.getInstance();
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "Sunday";
        } else if ("2".equals(mWay)) {
            mWay = "Monday";
        } else if ("3".equals(mWay)) {
            mWay = "Tuesday";
        } else if ("4".equals(mWay)) {
            mWay = "Wednesday";
        } else if ("5".equals(mWay)) {
            mWay = "Thursday";
        } else if ("6".equals(mWay)) {
            mWay = "Friday";
        } else if ("7".equals(mWay)) {
            mWay = "Saturday";
        }
        String day =  mWay;
        edit_days.setText(day);
    }

    private void IntView() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_click_info);
        db = new DBHelpe(WriteDiaryActivity.this);
        edittext = (EditText) findViewById(R.id.edit_writedialy);
        edit_year = (TextView) findViewById(R.id.edit_year);
        edit_days = (TextView) findViewById(R.id.edit_days);
        edit_winder = (EditText) findViewById(R.id.edit_winder);
        btn_join = (Button) findViewById(R.id.btn_join);
        btn_clean = (Button) findViewById(R.id.btn_clean);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_join.setOnClickListener(this);
        btn_clean.setOnClickListener(this);
        btn_open.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        edit_year.setOnClickListener(this);
        edit_days.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_join:
                btn_join.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        String s = edittext.getText().toString();
                        String data = edit_year.getText().toString();
                        String days = edit_days.getText().toString();
                        String winder = edit_winder.getText().toString();

                        if (data.equals("")) {
                            TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                            alphaAnimation2.setDuration(50);
                            alphaAnimation2.setRepeatCount(7);
                            alphaAnimation2.setRepeatMode(Animation.REVERSE);
                            edit_year.setAnimation(alphaAnimation2);
                            alphaAnimation2.start();
                            edit_year.requestFocus();
                        } else {
                            if (days.equals("")) {
                                TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                                alphaAnimation2.setDuration(50);
                                alphaAnimation2.setRepeatCount(7);
                                alphaAnimation2.setRepeatMode(Animation.REVERSE);
                                edit_days.setAnimation(alphaAnimation2);
                                alphaAnimation2.start();
                                edit_days.requestFocus();
                            } else {
                                if (winder.equals("")) {
                                    TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                                    alphaAnimation2.setDuration(50);
                                    alphaAnimation2.setRepeatCount(7);
                                    alphaAnimation2.setRepeatMode(Animation.REVERSE);
                                    edit_winder.setAnimation(alphaAnimation2);
                                    alphaAnimation2.start();
                                    edit_winder.requestFocus();
                                } else

                                {
                                    if (!s.equals("")) {
                                        //Intent intent = new Intent(WriteDiaryActivity.this, MyDiaryActivity.class);
                                        ContentValues values = new ContentValues();
                                        values.put("content", s);
                                        values.put("data", data);
                                        values.put("days", days);
                                        values.put("winder", winder);
                                        db.insert(values);
                                        Conmon.bln_content = true;
                                        Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_SHORT).show();
                                        edittext.setText("");
                                        //startActivity(intent);
                                        //WriteDiaryActivity.this.finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Please enter it！", Toast.LENGTH_SHORT).show();
                                    }
                                    // TODO Auto-generated method stub
                                }
                            }
                        }
                    }
                });
                break;
            case R.id.btn_clean:
                btn_clean.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        edittext.setText("");// TODO Auto-generated method stub

                    }

                });

                break;
            case R.id.btn_open:
                final Intent intent = new Intent(this, MusicServer.class);
                btn_open.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startService(intent);
                    }

                });
                break;
            case R.id.btn_close:
                final Intent intent1 = new Intent(this, MusicServer.class);
                btn_close.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        stopService(intent1);

                    }

                });
                break;

            default:
                break;

        }// TODO Auto-generated method stub
    }
}
