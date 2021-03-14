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

    /**
     * SoundPlayer class constructor
     * @param context context of android activity
     */
    public SoundPlayer(Context context) {
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        wooshSound = soundPool.load(context, R.raw.woosh, 1);
    }

    public void playWooshSound(){
        soundPool.play(wooshSound,1,1,1,0,1f);
    }

    public final void cleanUpIfEnd(){
        //soundID = null;
        soundPool.release();
        soundPool = null;
    }
}
