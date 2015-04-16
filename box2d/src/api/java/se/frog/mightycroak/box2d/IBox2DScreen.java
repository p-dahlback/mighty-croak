package se.frog.mightycroak.box2d;

import se.frog.mightycroak.ICroakGame;
import se.frog.mightycroak.ICroakScreen;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public interface IBox2DScreen extends ICroakScreen {
	public World initWorld(ICroakGame game);
	public Box2DDebugRenderer initDebugRenderer(ICroakGame game);
}
