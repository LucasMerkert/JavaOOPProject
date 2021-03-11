package de.frauas.oopj.project2048;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager extends Activity {

    static SoundPool soundPool;
    static int[] soundID;

    public void InitSound() {

        int maxStreams = 1;
        Context mContext = SoundManager.this.getApplicationContext();
        soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);

        soundID = new int[2];

        soundID[0] = soundPool.load(mContext,R.raw.sound1,1);
    }

    public void playSound(int Sound){
        soundPool.play(soundID[Sound],1,1,1,0,1f);
    }

    public final void cleanUpIfEnd(){
        soundID = null;
        soundPool.release();
        soundPool = null;
    }
}
