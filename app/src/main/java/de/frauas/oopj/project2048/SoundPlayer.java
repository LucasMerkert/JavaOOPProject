package de.frauas.oopj.project2048;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundPlayer {
//public class SoundPlayer extends Activity {

    private static SoundPool soundPool;
    private static int wooshSound;

    public SoundPlayer(Context context) {

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        wooshSound = soundPool.load(context, R.raw.woosh, 1);
    }

    public void playSound(){
        soundPool.play(wooshSound,1,1,1,0,1f);
    }

    public final void cleanUpIfEnd(){
        //soundID = null;
        soundPool.release();
        soundPool = null;
    }


    /*
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
     */
}
