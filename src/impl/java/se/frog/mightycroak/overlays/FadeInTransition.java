package se.frog.mightycroak.overlays;

import se.frog.mightycroak.ICroakGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class FadeInTransition implements IOverlay {
	private static final String TAG = FadeInTransition.class.getSimpleName();

	final ICroakGame mGame;

	float mElapsedTime;
	float mDuration;

	boolean mRunning = false;
	Color mFadeColor = Color.BLACK;

	public FadeInTransition(ICroakGame game, float duration) {
		this.mGame = game;
		this.mDuration = duration;
	}

	@Override
	public void create() {
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
	public void update(float delta) {
		if(isRunning()) {
			if(this.mElapsedTime > this.mDuration) {
				stop();
			}
			this.mElapsedTime += delta;
		}
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes) {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		float timePercent = this.mElapsedTime / this.mDuration;
		if(timePercent > 1.0) {
			timePercent = 1.0f;
		}

		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapes.setProjectionMatrix(this.mGame.getUIProjection());
		shapes.begin(ShapeType.Filled);
		shapes.setColor(this.mFadeColor.r, this.mFadeColor.g, this.mFadeColor.b, 1f-timePercent);
		shapes.rect(-width/2, -height/2, width, height);

		shapes.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
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

}
