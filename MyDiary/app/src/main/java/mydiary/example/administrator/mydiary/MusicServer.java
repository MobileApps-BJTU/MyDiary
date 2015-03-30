package mydiary.example.administrator.mydiary;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by Administrator on 2015/3/28.
 */
public class MusicServer extends Service{
    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        mp.start();
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        //创建一个音乐播放对象
        mp=MediaPlayer.create(this, R.raw.a);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mp.stop();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        mp.start();
    }
}
