package se.frog.mightycroak.overlays;

import se.frog.mightycroak.ICroakGame;

public abstract class TimedOverlay implements IOverlay {

	private final ICroakGame mGame;
	private float mElapsedTime;
	private float mDuration;
	private boolean mRunning = false;

	public TimedOverlay(ICroakGame game, float duration) {
		this.mGame = game;
		this.mDuration = duration;
	}

	@Override
	public void update(float delta) {
		if(isRunning()) {
			if(this.mElapsedTime > this.mDuration) {
				stop();
			}
			this.mElapsedTime += delta;
		}
	}

	@Override
	public void dispose() {
		stop();
	}

	@Override
	public void start() {
		this.mElapsedTime = 0;
		this.mRunning = true;
	}

	@Override
	public void stop() {
		this.mElapsedTime = 0;
		this.mRunning = false;
	}

	@Override
	public boolean isRunning() {
		return this.mRunning;
	}

	@Override
	public float getElapsedTime() {
		return this.mElapsedTime;
	}

	@Override
	public float getDuration() {
		return this.mDuration;
	}

	@Override
	public ICroakGame getGame() {
		return this.mGame;
	}

	public float getProgress() {
		return this.mElapsedTime / this.mDuration;
	}

}
