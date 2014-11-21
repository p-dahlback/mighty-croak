package se.frog.mightycroak.overlays;

import se.frog.mightycroak.ICroakGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WavyTextFadeoutOverlay implements IOverlay {
	static final String TAG = WavyTextFadeoutOverlay.class.getSimpleName();

	final ICroakGame mGame;

	BitmapFont mFont;
	float mElapsedTime;
	float mDuration;

	String mText;
	float mTextWidth;
	float mTextHeight;

	boolean mRunning = false;
	boolean mFade = false;

	float mWaveAmplitude = 4.0f;
	float mWaveTime = 2.0f;

	float mFadeTime = 3.0f;
	Color mFadeColor = Color.BLACK;

	public WavyTextFadeoutOverlay(ICroakGame game, String text, BitmapFont font, float duration, boolean fade) {
		this.mGame = game;
		this.mText = text;
		this.mFont = font;
		this.mDuration = duration;
		this.mFade = fade;
	}

	@Override
	public void create() {
		TextBounds bounds = this.mFont.getBounds(this.mText);
		this.mTextWidth = bounds.width;
		this.mTextHeight = bounds.height;
	}

	@Override
	public void dispose() {
		if(this.mFont != null) {
			stop();
			this.mFont.dispose();
		}
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
			this.mElapsedTime += delta;
		}
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes) {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		float timePercent = this.mElapsedTime / (this.mDuration / 2);
		if(timePercent > 1.0) {
			timePercent = 1.0f;
		}

		if(this.mFade) {
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapes.setProjectionMatrix(game.getUIProjection());
			shapes.begin(ShapeType.Filled);
			shapes.setColor(this.mFadeColor.r, this.mFadeColor.g, this.mFadeColor.b, timePercent);
			shapes.rect(-width/2, -height/2, width, height);

			shapes.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}

		batch.setProjectionMatrix(game.getUIProjection());
		batch.begin();

		this.mFont.setColor(1.0f, 1.0f, 1.0f, timePercent);

		int length = this.mText.length();
		float startX = -this.mTextWidth/2;
		float startY = 0;
		for(int i = 0; i < length; i++) {
			this.mFont.draw(batch, this.mText.subSequence(i, i + 1),
					startX + i * 8,
					startY + (float) (Math.sin(this.mElapsedTime + i * this.mWaveTime / 4) * this.mWaveAmplitude));
		}

		batch.end();
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
