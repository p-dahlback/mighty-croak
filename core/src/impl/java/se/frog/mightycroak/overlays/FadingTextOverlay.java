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

public class FadingTextOverlay extends TimedOverlay implements IOverlay {
	static final String TAG = FadingTextOverlay.class.getSimpleName();

	BitmapFont mFont;

	Vector2 mPos = new Vector2();
	String mText;
	float mTextWidth;
	float mTextHeight;
	int mUpDistance = 60;

	Color mStartColor = Color.WHITE;
	Color mFadeColor = Color.LIGHT_GRAY;

	public FadingTextOverlay(ICroakGame game, BitmapFont font, float duration) {
		super(game, duration);
		this.mFont = font;
	}

	public void setUpDistance(int distance) {
		this.mUpDistance = distance;
	}



	public void setStartColor(Color color) {
		this.mStartColor = color;
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

	public void setText(String text) {
		this.mText = text;
	}

	public void setPosition(float x, float y) {
		this.mPos.set(x, y);
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes) {
		float timePercent = getProgress();
		if(timePercent > 1.0) {
			timePercent = 1.0f;
		}

		IConfig config = game.getConfig();
		batch.begin();
		this.mFont.setColor((1f-timePercent) * this.mStartColor.r + timePercent * this.mFadeColor.r,
				(1f-timePercent) * this.mStartColor.g + timePercent * this.mFadeColor.g,
				(1f-timePercent) * this.mStartColor.b + timePercent * this.mFadeColor.b,
				1f-timePercent);
		float startX = config.getPixelsFromMeters(this.mPos.x) -this.mTextWidth/2;
		float startY = config.getPixelsFromMeters(this.mPos.y) -this.mTextHeight/2 + this.mUpDistance * timePercent;
		this.mFont.draw(batch, this.mText,
				startX,
				startY);
		batch.end();
	}

}
