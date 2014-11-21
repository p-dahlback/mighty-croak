package se.frog.mightycroak;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Box2DScreen extends CroakScreen implements IBox2DScreen {

	World mWorld;

	public Box2DScreen(ICroakGame game) {
		super(game);
		this.mWorld = initWorld(game);
	}


	@Override
	public void render(SpriteBatch batch, Camera camera) {

	}

	@Override
	public void update(float delta) {
		this.mWorld.step(1/60f, 6, 2);
	}

}
