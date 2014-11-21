package se.frog.mightycroak;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface ICroakScreen extends Screen {
	public ICroakGame getGame();
	public AssetManager getAssets();
	public SpriteBatch getSpriteBatch();
	public ShapeRenderer getShapeRenderer();
	public InputMultiplexer getInputMultiplexer();
	public Camera getCamera();

	public void render(SpriteBatch batch, Camera camera);
	public void update(float delta);
}
