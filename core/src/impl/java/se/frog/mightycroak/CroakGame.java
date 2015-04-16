package se.frog.mightycroak;

import se.frog.mightycroak.assets.AnimationLoader;
import se.frog.mightycroak.assets.TileSet;
import se.frog.mightycroak.assets.TileSetLoader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;

public abstract class CroakGame extends Game implements ICroakGame, ApplicationListener {
	protected AssetManager assets;
	protected SpriteBatch spriteBatch;
	protected ShapeRenderer shapeRenderer;
	protected OrthographicCamera camera;
	protected Matrix4 uiProjection;

	@Override
	public AssetManager getAssets() {
		return this.assets;
	}

	@Override
	public SpriteBatch getSpriteBatch() {
		return this.spriteBatch;
	}

	@Override
	public ShapeRenderer getShapeRenderer() {
		return this.shapeRenderer;
	}

	@Override
	public OrthographicCamera getCamera() {
		return this.camera;
	}

	@Override
	public Matrix4 getUIProjection() {
		return this.uiProjection;
	}

	@Override
	public void create() {
		this.assets = new AssetManager();
		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		this.camera = new OrthographicCamera(width, height);
		this.uiProjection = new Matrix4(this.camera.combined);

		InternalFileHandleResolver resolver = new InternalFileHandleResolver();
		this.assets.setLoader(BitmapFont.class, new BitmapFontLoader(resolver));
		this.assets.setLoader(Texture.class, new TextureLoader(resolver));
		this.assets.setLoader(TileSet.class, new TileSetLoader(resolver));
		this.assets.setLoader(Animation[].class, new AnimationLoader(resolver));
		this.assets.setLoader(Sound.class, new SoundLoader(resolver));
		this.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	}

	@Override
	public void dispose() {
		super.dispose();
		this.assets.dispose();
		this.spriteBatch.dispose();
	}

}
