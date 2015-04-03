package mydiary.example.administrator.mydiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mydiary.example.administrator.db.Conmon;
import mydiary.example.administrator.db.DBHelpe;

public class EditActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btn_return, btn_queding, btn_quxiao;
    private EditText edit_content;
    private String str_content,str_id;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        intView();
    }
    private void intView() {
        btn_return = (Button) findViewById(R.id.btn_bianjifanhui);
        btn_queding = (Button) findViewById(R.id.btn_bianjiqueding);
        btn_quxiao = (Button) findViewById(R.id.btn_bianjiquxiao);
        edit_content = (EditText) findViewById(R.id.edit_content);
        btn_return.setOnClickListener(this);
        btn_queding.setOnClickListener(this);
        btn_quxiao.setOnClickListener(this);
        Intent intent=getIntent();
        Bundle bd=intent.getBundleExtra("bianji");
        str_content=bd.getString("content");
        str_id=bd.getString("id");
        Log.d("xxxxxxxxx", str_content+str_id);
        edit_content.setText(str_content);
        animation=AnimationUtils.loadAnimation(EditActivity.this, R.anim.anim_click_info);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bianjifanhui :
                btn_return.startAnimation(animation);
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
                        // TODO Auto-generated method stub
                        EditActivity.this.finish();
                    }
                });

                break;
            case R.id.btn_bianjiqueding :
                btn_queding.startAnimation(animation);
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
                        // TODO Auto-generated method stub
                        DBHelpe db=new DBHelpe(EditActivity.this);
                        db.update(str_id, edit_content.getText().toString());
                        Toast.makeText(EditActivity.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                        Conmon.bln_content=true;
                        EditActivity.this.finish();
                    }
                });

                break;
            case R.id.btn_bianjiquxiao :
                btn_quxiao.startAnimation(animation);
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
                        // TODO Auto-generated method stub
                        EditActivity.this.finish();
                    }
                });

                break;

            default :
                break;
        }// TODO Auto-generated method stub

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }// TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
