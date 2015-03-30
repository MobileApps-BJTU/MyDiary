package mydiary.example.administrator.mydiary;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mydiary.example.administrator.db.DB_PASSWORD;
import mydiary.example.clock.Clocks;


public class SetActivity extends ActionBarActivity {

    private EditText edit_shezhi;
    private Button btn_shezhi,btn_clock;
    private DB_PASSWORD db;
    private TextView tv_laogong2;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set);
        intView();
    }
    private void intView() {
        animation=AnimationUtils.loadAnimation(SetActivity.this, R.anim.anim_click_info);
        edit_shezhi = (EditText) findViewById(R.id.edit_shezhi);
        btn_shezhi = (Button) findViewById(R.id.btn_shezhi);
        btn_clock = (Button) findViewById(R.id.btn_clock);
        tv_laogong2=(TextView)findViewById(R.id.tv_laogong2);
        btn_shezhi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                animation.setAnimationListener(new AnimationListener() {

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
                        String s = edit_shezhi.getText().toString().trim();
                        db = new DB_PASSWORD(getApplicationContext());

                        if (!s.equals("") && db.selectall().equals("")) {
                            ContentValues values = new ContentValues();
                            values.put("password", s);
                            db.insert(values);
                            contral();
                            Toast.makeText(getApplicationContext(), "Change the password successfully！", Toast.LENGTH_LONG).show();
                            //tv_laogong2.setVisibility(View.VISIBLE);
                        } else if (!s.equals("") && !db.selectall().equals("")) {
                            db.update(s);
                            contral();
                            //tv_laogong2.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Change the password successfully！", Toast.LENGTH_LONG).show();
                        }
                        else
                        {Toast.makeText(getApplicationContext(), "The password can't be null1", Toast.LENGTH_SHORT).show();}// TODO Auto-generated method stub

                    }
                });
            }
        });

        btn_clock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                animation.setAnimationListener(new AnimationListener() {

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
                        Intent intent1 = intent.setClass(SetActivity.this, Clocks.class);
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

        dialog.setTitle("HINT").setMessage("Are you sure？").setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        System.exit(0);
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
    private void contral(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit_shezhi.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set, menu);
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
