package se.frog.mightycroak.box2d.entity;

import java.util.List;

import se.frog.mightycroak.entity.IEntity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public interface IBox2DEntity extends IEntity {
	public void setFacing(int facing);
	public int getFacing();
	public Body getBody();
	public List<Fixture> getFixtures();
	public Vector2 getOrigin();
	public Vector2 getLinearVelocity();
	public void setLinearVelocity(float x, float y);
}
