package de.frauas.oopj.project2048;

import android.content.Context;
import android.media.SoundPool;

/**
 * class to load in sounds and the methods to play those sounds
 * @author tarik, ana, lucas, friedrich
 */
public class SoundPlayer {
	private static SoundPool soundPool;
	private static int wooshSound;
	private static int gameoverSound;
	private static int maxStreams = 2;

	/**
	 * SoundPlayer class constructor
	 * @param context context of android activity
	 */
	public SoundPlayer(Context context) {
		soundPool = new SoundPool.Builder().setMaxStreams(maxStreams).build();
		wooshSound = soundPool.load(context, R.raw.woosh, 1);
		gameoverSound = soundPool.load(context, R.raw.gameover, 1);
	}

	/**
	 * method to play the woosh sound
	 */
	public void playWooshSound(){
		soundPool.play(wooshSound,1,1,1,0,1f);
	}

	/**
	 * method to play the gameover sound
	 */
	public void playGameoverSound(){
		soundPool.play(gameoverSound,1,1,1,0,1f);
	}


}
