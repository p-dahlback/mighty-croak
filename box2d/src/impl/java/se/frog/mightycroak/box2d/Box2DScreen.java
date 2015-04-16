package se.frog.mightycroak.box2d;

import se.frog.mightycroak.CroakScreen;
import se.frog.mightycroak.ICroakGame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Box2DScreen extends CroakScreen implements IBox2DScreen {

	World mWorld;
	Box2DDebugRenderer mDebugRenderer;

	public Box2DScreen(ICroakGame game) {
		super(game);
		this.mWorld = initWorld(game);
		this.mDebugRenderer = initDebugRenderer(game);
	}


	@Override
	public void render(SpriteBatch batch, Camera camera) {

	}

	@Override
	public void update(float delta) {
		this.mWorld.step(1/60f, 6, 2);
	}

	public World getWorld() {
		return this.mWorld;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return this.mDebugRenderer;
	}

}
