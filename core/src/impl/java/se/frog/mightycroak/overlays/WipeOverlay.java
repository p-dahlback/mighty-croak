package se.frog.mightycroak.overlays;

import se.frog.mightycroak.ICroakGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WipeOverlay extends TimedOverlay implements IOverlay {

	// Definition
	Color mStartColorLeft;
	Color mStartColorRight;
	Color mTargetColor;

	// State
	boolean mWipeDone = false;
	boolean mReversed;
	Color mCurrentLeft;
	Color mCurrentRight;
	float[] mDiffsLeft;
	float[] mDiffsRight;

	public WipeOverlay(ICroakGame game, Color startLeft, Color startRight, Color target, float duration) {
		super(game, duration);
		this.mStartColorLeft = startLeft;
		this.mStartColorRight = startRight;
		this.mTargetColor = target;
		this.mCurrentLeft = new Color(this.mStartColorLeft);
		this.mCurrentRight = new Color(this.mStartColorRight);

		this.mDiffsLeft = getDiffs(this.mStartColorLeft, this.mTargetColor);
		this.mDiffsRight = getDiffs(this.mStartColorRight, this.mTargetColor);
	}

	private float[] getDiffs(Color color, Color target) {
		return new float[] {
				target.r - color.r,
				target.g - color.g,
				target.b - color.b,
				target.a - color.a,
		};
	}

	private void updateColor(Color color, Color startColor, float[] diffs, float changeVal) {
		color.r = startColor.r + changeVal * diffs[0];
		color.g = startColor.g + changeVal * diffs[1];
		color.b = startColor.b + changeVal * diffs[2];
		color.a = startColor.a + changeVal * diffs[3];
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		if(isRunning()) {

			float val = getProgress() * 1.2f;
			if(val > 1.0f) {
				val = 1.0f;
			}
			if(this.mReversed) {
				val = 1.0f - val;
			}
			updateColor(this.mCurrentLeft, this.mStartColorLeft, this.mDiffsLeft, val);
			updateColor(this.mCurrentRight, this.mStartColorRight, this.mDiffsRight, val);
		}
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera,
			SpriteBatch batch, ShapeRenderer shapes) {
		float val = getProgress();
		if(this.mReversed) {
			val += 1.0f;
		}

		if(val > 0.0) {

			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapes.setProjectionMatrix(camera.combined);
			shapes.begin(ShapeType.Filled);
			shapes.rect(camera.position.x - val * camera.viewportWidth / 2,
					camera.position.y - camera.viewportHeight / 2,
					camera.viewportWidth, camera.viewportHeight,
					this.mCurrentLeft, this.mCurrentRight, this.mCurrentRight, this.mCurrentLeft);
			shapes.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}

	@Override
	public void create() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void start() {
		super.start();
		this.mReversed = false;
		this.mWipeDone = false;
	}

	public void start(boolean reversed) {
		start();
		this.mReversed = reversed;
	}

	public boolean isWipeDone() {
		return this.mWipeDone;
	}

}
