package se.frog.mightycroak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class CroakScreen implements ICroakScreen {

	protected final ICroakGame game;
	protected final InputMultiplexer input;

	private final SpriteBatch spriteBatch;
	private final OrthographicCamera camera;

	public CroakScreen(ICroakGame game) {
		this.game = game;
		this.input = new InputMultiplexer();
		this.spriteBatch = game.getSpriteBatch();
		this.camera = game.getCamera();
	}

	@Override
	public ICroakGame getGame() {
		return this.game;
	}

	@Override
	public AssetManager getAssets() {
		return this.game.getAssets();
	}

	@Override
	public InputMultiplexer getInputMultiplexer() {
		return this.input;
	}

	@Override
	public SpriteBatch getSpriteBatch() {
		return this.spriteBatch;
	}

	@Override
	public OrthographicCamera getCamera() {
		return this.camera;
	}

	@Override
	public ShapeRenderer getShapeRenderer() {
		return this.game.getShapeRenderer();
	}

	@Override
	public final void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		render(this.spriteBatch, this.camera);
		update(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.input);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
