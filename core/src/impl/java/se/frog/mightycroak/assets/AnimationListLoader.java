package se.frog.mightycroak.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationListLoader extends AsynchronousAssetLoader<AnimationList, AnimationListLoader.AnimationListParameter>{

	public AnimationListLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName,
			FileHandle file, AnimationListParameter parameter) {
	}

	@Override
	public AnimationList loadSync(AssetManager manager, String fileName,
			FileHandle file, AnimationListParameter parameter) {
		Texture texture = new Texture(file);
		Animation[] anms = new Animation[parameter.animationCount];
		for(int i = 0; i < parameter.animationCount; i++) {
			SingleAnimationParameter singleParam = parameter.animationParams[i];
			int frames = singleParam.frameCount;

			TextureRegion[] keyFrames = new TextureRegion[frames];
			for (int j = 0; j < frames; j++) {
				keyFrames[j] = new TextureRegion(texture,
						j * parameter.frameWidth,
						i * parameter.frameHeight,
						parameter.frameWidth,
						parameter.frameHeight);
			}
			anms[i] = new Animation(singleParam.frameDuration, keyFrames);
			anms[i].setPlayMode(singleParam.looping ? PlayMode.LOOP : PlayMode.NORMAL);
		}
		return new AnimationList(anms);
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			FileHandle file, AnimationListParameter parameter) {
		return null;
	}

	public static class AnimationListParameter extends AssetLoaderParameters<AnimationList> {
		public int animationCount;
		public int frameWidth;
		public int frameHeight;
		public SingleAnimationParameter[] animationParams;

		public AnimationListParameter(int animationCount, int frameWidth, int frameHeight) {
			this.animationCount = animationCount;
			this.frameWidth = frameWidth;
			this.frameHeight = frameHeight;
			this.animationParams = new SingleAnimationParameter[animationCount];
		}

		public AnimationListParameter setForAllAnimations(int frameCount, float frameDuration, boolean looping) {
			SingleAnimationParameter parameters = new SingleAnimationParameter(frameCount, frameDuration, looping);
			for(int i = 0; i < this.animationCount; i++) {
				this.animationParams[i] = parameters;
			}
			return this;
		}

		public AnimationListParameter setSingleAnimation(int index, int frameCount, float frameDuration, boolean looping) {
			this.animationParams[index] = new SingleAnimationParameter(frameCount, frameDuration, looping);
			return this;
		}
	}

	private static class SingleAnimationParameter {
		public int frameCount;
		public float frameDuration;
		public boolean looping;

		public SingleAnimationParameter(int frameCount, float frameDuration, boolean looping) {
			this.frameCount = frameCount;
			this.frameDuration = frameDuration;
			this.looping = looping;
		}
	}
}
