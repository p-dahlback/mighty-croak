package se.frog.mightycroak.assets;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationList {
	private Animation[] mAnimations;

	public AnimationList(Animation... animations) {
		this.mAnimations = animations;
	}

	public Animation get(int index) {
		return this.mAnimations[index];
	}

	public boolean isEmpty() {
		return this.mAnimations.length == 0;
	}

	public int size() {
		return this.mAnimations.length;
	}
}
