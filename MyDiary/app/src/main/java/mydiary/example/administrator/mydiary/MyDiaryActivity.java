package mydiary.example.administrator.mydiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import mydiary.example.administrator.db.Conmon;
import mydiary.example.administrator.db.DBHelpe;
import mydiary.example.administrator.db.Mode;

import java.util.ArrayList;


public class MyDiaryActivity extends Activity
        implements ListItemFragment.OnFragmentInteractionListener {

    private ListView listview;
    private String[] from = {"content", "data", "days"};
    private int[] to = {R.id.tv, R.id.tv_year, R.id.tv_days};
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private DBHelpe db;
    private int index = 1;
    private TextView tv_madialy;


    public void onBackPressed(){

        FragmentManager fg = getFragmentManager();
        if(fg.getBackStackEntryCount()>0){
            fg.popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        intview();

        String s = db.selectall();
        if (!s.equals("")) {
            cursor = db.query();
            adapter = new SimpleCursorAdapter(this, R.layout.listitem, cursor, from, to);
           // listview.setAdapter(adapter);
           // listview.setDivider(null);
           adapter.notifyDataSetChanged();

        }
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_holder,new ListItemFragment())
                .commit();


        Thread thread = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        Message m = new Message();
                        index++;
                        m.what = 2;
                        MyDiaryActivity.this.handler.sendMessage(m);
                    }
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        };
        thread.start();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2 :
                    if (index % 4 == 1) {
                        tv_madialy.setTextColor(Color.YELLOW);
                    }
                    if (index % 4 == 2) {
                        tv_madialy.setTextColor(Color.GREEN);
                    }
                    if (index % 4 == 3) {
                        tv_madialy.setTextColor(Color.RED);
                    }
                    if (index % 4 == 0) {
                        tv_madialy.setTextColor(Color.WHITE);
                    }

                    break;
                default :
                    break;
            }
        }
    };
    @Override
    protected void onResume() {
        if (Conmon.bln_content == true) {
            cursor = db.query();
            adapter = new SimpleCursorAdapter(this, R.layout.listitem, cursor, from, to);
            adapter.notifyDataSetChanged();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_holder,new ListItemFragment())
                    .commit();
            Conmon.bln_content = false;

        }

        super.onResume();
    }


    private void intview() {

        db = new DBHelpe(MyDiaryActivity.this);
        tv_madialy = (TextView) findViewById(R.id.tv_mytitle);

    }

    public AdapterView.OnItemLongClickListener getOnItemLongClickListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                position=position+1;
                Bundle bundle = new Bundle();
                bundle.putString("id", position + "");
                final String tag = bundle.getString("id");
                // get the tag that the user long touched
               // final String tag = ((TextView) view).getText().toString();

                // create a new AlertDialog
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MyDiaryActivity.this);

                // set the AlertDialog's title
                builder.setTitle(
                        getString(R.string.shareEditDeleteTitle, tag));

                // set list of items to display in dialog
                builder.setItems(R.array.dialog_items,
                        new DialogInterface.OnClickListener() {
                            // responds to user touch by sharing, editing or
                            // deleting a saved search
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // edit
                                        eidtDiary(tag);
                                        break;
                                    case 1: // delete
                                        //int id =Integer.parseInt(tag);
                                        deleteDiary(tag);
                                        break;

                                }
                            }
                        } // end DialogInterface.OnClickListener
                ); // end call to builder.setItems

                // set the AlertDialog's negative Button
                builder.setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            // called when the "Cancel" Button is clicked
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel(); // dismiss the AlertDialog
                            }
                        }
                ); // end call to setNegativeButton

                builder.create().show(); // display the AlertDialog
                return true;
            } // end method onItemLongClick
        }; // end OnItemLongClickListener declaration
    }; // end get method

    //edit
    private void eidtDiary(String id){

        Intent intent = new Intent(MyDiaryActivity.this, EditActivity.class);
        Bundle bd = new Bundle();
        String content =null;
        ArrayList<Mode> list = db.fetchValue(id);
        for (Mode m : list) {
          content = m.getCONTENT();
        }
        bd.putString("content", content);
        bd.putString("id", id);
        intent.putExtra("bianji", bd);
        startActivity(intent);
    }

    //delete
    private void deleteDiary(String id){
        final String sid = id;
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Hint").setMessage("Are you sure？").setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        db.del(Integer.valueOf(sid));
                        ArrayList<Mode> list = db.fetchValue();
                        db.clean();
                        ContentValues values = new ContentValues();
                        for (Mode mode : list) {
                            values.put("content", mode.getCONTENT());
                            values.put("data", mode.getDATA());
                            values.put("days", mode.getDAYS());
                            values.put("winder", mode.getWINDER());
                            db.insert(values);
                        }
                        Conmon.bln_content=true;
                        Toast.makeText(MyDiaryActivity.this, "Delete Success!", Toast.LENGTH_SHORT).show();
                        onResume();
                       // MyDiaryActivity.this.finish();

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
        getMenuInflater().inflate(R.menu.menu_my_diary, menu);
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

    public SimpleCursorAdapter getAdapter(){return adapter;}


    @Override
    public void onFragmentInteraction(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("id", position + "");
        String tag = bundle.getString("id");
        String content= null;
        String date = null;
        String days = null;
        String winder = null;

        ArrayList<Mode> list = db.fetchValue(tag);
        for (Mode m : list) {
            content = m.getCONTENT();
            date = m.getDATA();
            days = m.getDAYS();
            winder = m.getWINDER();

        }


        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder,ContentFragment.newInstance(content,date,days,winder))
                .addToBackStack(null)
                .commit();

    }

}
