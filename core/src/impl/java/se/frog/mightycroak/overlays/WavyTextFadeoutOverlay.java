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

public class WavyTextFadeoutOverlay extends TimedOverlay implements IOverlay {
	static final String TAG = WavyTextFadeoutOverlay.class.getSimpleName();

	BitmapFont mFont;

	String mText;
	float mTextWidth;
	float mTextHeight;

	boolean mFade = false;

	float mWaveAmplitude = 4.0f;
	float mWaveTime = 2.0f;

	float mFadeTime = 3.0f;
	Color mFadeColor = Color.BLACK;

	public WavyTextFadeoutOverlay(ICroakGame game, String text, BitmapFont font, float duration, boolean fade) {
		super(game, duration);
		this.mText = text;
		this.mFont = font;
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
		super.dispose();
		if(this.mFont != null) {
			this.mFont.dispose();
		}
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes) {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		float timePercent = getElapsedTime() / (getDuration() / 2);
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
					startY + (float) (Math.sin(getElapsedTime() + i * this.mWaveTime / 4) * this.mWaveAmplitude));
		}

		batch.end();
	}

}
