package se.frog.mightycroak.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationLoader extends AsynchronousAssetLoader<Animation[], AnimationLoader.AnimationParameter> {
	public AnimationLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public void loadAsync(AssetManager manager, String fileName,
			FileHandle file, AnimationParameter parameter) {
	}

	@Override
	public Animation[] loadSync(AssetManager manager, String fileName,
			FileHandle file, AnimationParameter parameter) {
		Texture texture = new Texture(file);
		int numAnimations = texture.getHeight() / parameter.frameHeight;
		Animation[] anms = new Animation[numAnimations];
		int framesPerAnm = texture.getWidth() / parameter.frameWidth;
		int frames = 0;
		for(int i = 0; i < numAnimations; i++) {
			if(i == numAnimations - 1) {
				frames = parameter.totalFrames - (numAnimations - 1) * framesPerAnm;
			} else {
				frames = framesPerAnm;
			}
			TextureRegion[] keyFrames = new TextureRegion[frames];
			for (int j = 0; j < frames; j++) {
				keyFrames[j] = new TextureRegion(texture,
						j * parameter.frameWidth,
						i * parameter.frameHeight,
						parameter.frameWidth,
						parameter.frameHeight);
			}
			anms[i] = new Animation(parameter.frameDuration, keyFrames);
		}
		return anms;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			FileHandle file, AnimationParameter parameter) {
		return null;
	}

	public static class AnimationParameter extends AssetLoaderParameters<Animation[]> {
		public int totalFrames;
		public float frameDuration;
		public int frameWidth;
		public int frameHeight;

		public AnimationParameter(int totalFrames, float frameDuration, int frameWidth, int frameHeight) {
			this.totalFrames = totalFrames;
			this.frameDuration = frameDuration;
			this.frameWidth = frameWidth;
			this.frameHeight = frameHeight;
		}
	}

}
