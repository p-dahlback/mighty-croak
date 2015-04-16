package se.frog.mightycroak.assets;

import se.frog.mightycroak.IConfig;
import se.frog.mightycroak.ICroakGame;
import se.frog.mightycroak.entity.IEntity;
import se.frog.mightycroak.entity.IRenderable;
import se.frog.mightycroak.entity.IUpdateable;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class AnimationSet implements IRenderable, IUpdateable {
	static final String TAG = AnimationSet.class.getSimpleName();
	IEntity mEntity;
	AnimationList mAnmList;
	AnimationListener mAnmListener;

	// State
	int mCurrentAnimation;
	float mAnimationTime;
	boolean mAnmEnd = false;
	boolean mFlipHorizontal = false;
	boolean mFlipVertical = false;

	public AnimationSet(AnimationList anmList) {
		this.mAnmList = anmList;
		this.mCurrentAnimation = 0;
		this.mAnimationTime = 0;
	}

	public void setEntity(IEntity entity) {
		this.mEntity = entity;
	}

	public void setAnimationListener(AnimationListener listener) {
		this.mAnmListener = listener;
	}

	public void setAnimation(int anmId) {
		this.mCurrentAnimation = anmId;
		this.mAnimationTime = 0f;
		this.mAnmEnd = false;
	}

	public void setFlipHorizontal(boolean flip) {
		this.mFlipHorizontal = flip;
	}

	public void setFlipVertical(boolean flip) {
		this.mFlipVertical = flip;
	}

	@Override
	public void update(float delta) {
		if(!this.mAnmEnd) {
			Animation anm = this.mAnmList.get(this.mCurrentAnimation);
			this.mAnimationTime += delta;

			if(anm.getPlayMode() != PlayMode.LOOP && anm.isAnimationFinished(this.mAnimationTime)) {
				this.mAnmEnd = true;
				if(this.mAnmListener != null) {
					this.mAnmListener.onAnimationFinished(this.mCurrentAnimation);
				}
			} else {
				float duration = anm.getAnimationDuration();
				if(this.mAnimationTime >= duration) {
					this.mAnimationTime -= duration;
				}
			}
		}
	}

	@Override
	public void render(ICroakGame game, OrthographicCamera camera, SpriteBatch batch, ShapeRenderer shapes) {
		Animation anm = this.mAnmList.get(this.mCurrentAnimation);
		TextureRegion keyFrame = anm.getKeyFrame(this.mAnimationTime, anm.getPlayMode() == PlayMode.LOOP);

		keyFrame.flip(this.mFlipHorizontal, this.mFlipVertical);
		Vector2 pos = this.mEntity.getPosition();
		Vector2 origin = this.mEntity.getOrigin();
		IConfig config = game.getConfig();

		float pX = config.getPixelsFromMeters(pos.x) - origin.x;
		float pY = config.getPixelsFromMeters(pos.y) - origin.y;
		batch.draw(keyFrame,
				pX, pY,
				origin.x, origin.y,
				keyFrame.getRegionWidth(), keyFrame.getRegionHeight(),
				1.0f, 1.0f, this.mEntity.getRotation());
		keyFrame.flip(this.mFlipHorizontal, this.mFlipVertical);
	}

	public static interface AnimationListener {
		public void onAnimationFinished(int anmId);
	}

}
