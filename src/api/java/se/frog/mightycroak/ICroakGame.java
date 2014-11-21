package se.frog.mightycroak;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

public interface ICroakGame extends ApplicationListener  {
	public SpriteBatch getSpriteBatch();
	public ShapeRenderer getShapeRenderer();
	public AssetManager getAssets();
	public Matrix4 getUIProjection();
	public OrthographicCamera getCamera();
	public IConfig getConfig();
}
