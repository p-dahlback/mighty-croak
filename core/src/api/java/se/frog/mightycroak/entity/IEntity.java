package se.frog.mightycroak.entity;

import se.frog.mightycroak.controller.IEntityController;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IEntity extends IUpdateable, IRenderable {
	public void setPosition(float x, float y);
	public void changePosition(float x, float y);
	public void setBounds(float width, float height);
	public void setBounds(float x, float y, float width, float height);
	public void setRotation(float rotation);
	public float getRotation();
	public Vector2 getPosition();
	public Vector2 getOrigin();
	public Rectangle getRect();
	public boolean overlaps(Rectangle rect);
	public void setState(EntityState state);
	public EntityState getState();
	public void setEntityController(IEntityController controller);
	public IEntityController getController();
	public void loadAssets(AssetManager assets);
	public void dispose(AssetManager assets);
}
