package se.frog.mightycroak.overlays;

import se.frog.mightycroak.ICroakGame;
import se.frog.mightycroak.entity.IRenderable;
import se.frog.mightycroak.entity.IUpdateable;

public interface IOverlay extends IUpdateable, IRenderable {

	public void create();
	public void dispose();
	public void start();
	public void stop();
	public boolean isRunning();
	public float getElapsedTime();
	public float getDuration();
	public ICroakGame getGame();

}
