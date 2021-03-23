package de.frauas.oopj.project2048;

import android.app.Application;

/**
 * Application class to carry 'global' variables
 * @author lucas, ana, friedrich, tarik
 */
public class Application2048 extends Application {
	private int height;
	private int width;

	/**
	 * Height getter method
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Height setter method
	 * @param height height to be set
	 */
	public void setHeight(int height) {
		if (height < 1)
			throw new IllegalArgumentException("Height must be larger than 1");
		this.height = height;
	}

	/**
	 * Width getter method
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Width setter method
	 * @param width width to be set
	 */
	public void setWidth(int width) {
		if (width < 1)
			throw new IllegalArgumentException("Width must be larger than 1");
		this.width = width;
	}
}
