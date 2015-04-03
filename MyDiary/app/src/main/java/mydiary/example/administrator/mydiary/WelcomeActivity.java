package mydiary.example.administrator.mydiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends ActionBarActivity {

    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        login=(Button)findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
