package se.frog.mightycroak.overlays;

import se.frog.mightycroak.IConfig;
import se.frog.mightycroak.ICroakGame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class FadingTextOverlay implements IOverlay {
	static final String TAG = FadingTextOverlay.class.getSimpleName();

	final ICroakGame mGame;

	BitmapFont mFont;
	float mElapsedTime;
	float mDuration;

	Vector2 mPos = new Vector2();
	String mText;
	float mTextWidth;
	float mTextHeight;
	int mUpDistance = 60;

	boolean mRunning = false;
	Color mStartColor = Color.WHITE;
	Color mFadeColor = Color.LIGHT_GRAY;

	public FadingTextOverlay(ICroakGame game, BitmapFont font, float duration) {
		this.mGame = game;
		this.mFont = font;
		this.mDuration = duration;
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

	public void setText(String text) {
		this.mText = text;
	}

	public void setPosition(float x, float y) {
		this.mPos.set(x, y);
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
		float timePercent = this.mElapsedTime / this.mDuration;
		if(timePercent > 1.0) {
			timePercent = 1.0f;
		}

		IConfig config = game.getConfig();
		batch.setColor(Color.WHITE);
		this.mFont.setColor((1f-timePercent) * this.mStartColor.r + timePercent * this.mFadeColor.r,
				(1f-timePercent) * this.mStartColor.g + timePercent * this.mFadeColor.g,
				(1f-timePercent) * this.mStartColor.b + timePercent * this.mFadeColor.b,
				1f-timePercent);
		float startX = config.getPixelsFromMeters(this.mPos.x) -this.mTextWidth/2;
		float startY = config.getPixelsFromMeters(this.mPos.y) -this.mTextHeight/2 + this.mUpDistance * timePercent;
		this.mFont.draw(batch, this.mText,
				startX,
				startY);
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
