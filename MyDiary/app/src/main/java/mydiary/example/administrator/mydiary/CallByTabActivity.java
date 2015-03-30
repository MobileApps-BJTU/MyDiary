package mydiary.example.administrator.mydiary;

import java.util.ArrayList;
import java.util.List;


public class CallByTabActivity extends MyTabActivity {

    public CallByTabActivity() {
        super(R.layout.activity_call_by_tab, R.drawable.tabbackground);

    }


    @Override
    public List<MyTab> getMyTabList() {
        List<MyTab> myTabList = new ArrayList<MyTab>();
        String myDiary = this.getString(R.string.my_diary);
        String writeDiary = this.getString(R.string.write_diary);
        String setting = this.getString(R.string.settings);
        myTabList.add(new MyTab(R.drawable.read, myDiary,
                MyDiaryActivity.class));

        myTabList.add(new MyTab(R.drawable.write, writeDiary,
                WriteDiaryActivity.class));

        myTabList.add(new MyTab(R.drawable.setting, setting,
                SetActivity.class));


        return myTabList;

    }

}
