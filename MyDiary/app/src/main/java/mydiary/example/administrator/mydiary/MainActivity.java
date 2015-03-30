package mydiary.example.administrator.mydiary;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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

import mydiary.example.administrator.db.DB_PASSWORD;


public class MainActivity extends ActionBarActivity {

    private EditText edit_password;
    private Button btn_denglu,btn_gywm;
    private Animation animation;
    private DB_PASSWORD db;
    private TextView tv_laogong;
    private NotificationManager manager;
    private Notification noti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        intview();
    }


    private void intview() {
        edit_password = (EditText) findViewById(R.id.edit_denglu);
        tv_laogong = (TextView) findViewById(R.id.tv_laogong);
        btn_denglu = (Button) findViewById(R.id.btn_denglu);
        btn_gywm=(Button)findViewById(R.id.btn_gywm);


        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_click_info);


        btn_denglu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btn_denglu.startAnimation(animation);
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
                        if (edit_password.getText().toString().equals("")) {
                            TranslateAnimation alphaAnimation2 = new TranslateAnimation(0f, 20f, 0, 0);
                            alphaAnimation2.setDuration(50);
                            alphaAnimation2.setRepeatCount(Animation.INFINITE);
                            alphaAnimation2.setRepeatCount(7);
                            alphaAnimation2.setRepeatMode(Animation.REVERSE);
                            edit_password.setAnimation(alphaAnimation2);
                            alphaAnimation2.start();
                            tv_laogong.setBackgroundResource(R.drawable.laogong);


                            Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                        } else {
                            db = new DB_PASSWORD(getApplicationContext());
                            String s = db.selectall();
                            System.out.println("021333" + s);
                            if (s.equals("")) {
                                if (edit_password.getText().toString().equals("123")) {
                                    startActivity(new Intent(MainActivity.this, CallByTabActivity.class));


                                    Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();


                                    // manager.notify(1,noti);//发通知

                                    MainActivity.this.finish();
                                } else {
                                    tv_laogong.setBackgroundResource(R.drawable.laogong);
                                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                if (edit_password.getText().toString().equals(s)) {
                                    startActivity(new Intent(MainActivity.this, CallByTabActivity.class));


                                    Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
                                    // manager.notify(1,noti);//发通知


                                    MainActivity.this.finish();
                                } else {
                                    tv_laogong.setBackgroundResource(R.drawable.laogong);
                                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_LONG).show();
                                }
                            }
                        }// TODO Auto-generated method stub

                    }
                });


            }
        });


        btn_gywm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
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
                        Intent intent=new Intent();
                        intent.setClass(MainActivity.this,AboutUsActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            exit_app();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void exit_app()
    {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Hint").setMessage("Are you sure?").setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        MainActivity.this.finish();
                    }
                }).setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        AlertDialog dialog_dialog = dialog.create();
        dialog_dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
