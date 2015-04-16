package se.frog.mightycroak.box2d.entity;

import java.util.List;

import se.frog.mightycroak.box2d.entity.IBox2DEntity;
import se.frog.mightycroak.controller.IEntityController;
import se.frog.mightycroak.entity.EntityState;
import se.frog.mightycroak.entity.IEntity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Box2DEntity implements IEntity, IBox2DEntity {

	EntityState mState = EntityState.DEFAULT;
	IEntityController mController;
	int mFacing;

	Body mBody;
	List<Fixture> mFixtures;

	public Box2DEntity(World world) {
		this.mBody = defineBody(world);
		this.mFixtures = defineFixtures(world, this.mBody);
		this.mBody.setUserData(this);
	}

	public abstract Body defineBody(World world);
	public abstract List<Fixture> defineFixtures(World world, Body body);

	@Override
	public void setFacing(int facing) {
		this.mFacing = facing;
	}

	@Override
	public int getFacing() {
		return this.mFacing;
	}

	@Override
	public Body getBody() {
		return this.mBody;
	}

	@Override
	public List<Fixture> getFixtures() {
		return this.mFixtures;
	}

	@Override
	public Vector2 getLinearVelocity() {
		return this.mBody.getLinearVelocity();
	}

	@Override
	public void setLinearVelocity(float x, float y) {
		this.mBody.setLinearVelocity(x, y);
	}

	@Override
	public void update(float delta) {
		if(this.mController != null) {
			this.mController.update(delta);
		}
	}

	@Override
	public void setPosition(float x, float y) {
		this.mBody.setTransform(x, y, this.mBody.getAngle());
	}

	@Override
	public void changePosition(float x, float y) {
		Vector2 pos = this.mBody.getPosition();
		this.mBody.setTransform(pos.x + x, pos.y + y, this.mBody.getAngle());
	}

	@Override
	public void setBounds(float width, float height) {

	}

	@Override
	public void setBounds(float x, float y, float width, float height) {

	}

	@Override
	public void setRotation(float degrees) {
		this.mBody.setTransform(this.mBody.getPosition(), (float) Math.toRadians(degrees));
	}

	@Override
	public float getRotation() {
		return (float) Math.toDegrees(this.mBody.getAngle());
	}

	@Override
	public Vector2 getPosition() {
		return this.mBody.getPosition();
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	@Override
	public boolean overlaps(Rectangle rect) {
		return false;
	}

	@Override
	public void setState(EntityState state) {
		if(this.mState != state) {
			this.mState = state;
		}
	}

	@Override
	public EntityState getState() {
		return this.mState;
	}

	@Override
	public void setEntityController(IEntityController controller) {
		this.mController = controller;
	}

	@Override
	public IEntityController getController() {
		return this.mController;
	}

}
