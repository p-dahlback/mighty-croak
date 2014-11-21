package se.frog.mightycroak;

import com.badlogic.gdx.physics.box2d.World;

public interface IBox2DScreen extends ICroakScreen {
	public World initWorld(ICroakGame game);
}
